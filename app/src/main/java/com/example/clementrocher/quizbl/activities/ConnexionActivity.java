package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clementrocher.quizbl.R;
import com.example.clementrocher.quizbl.models.Utilisateur;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConnexionActivity extends AppCompatActivity {

    String emailAttempt;
    String passwordAttempt;

    Pattern mailPattern;
    Matcher mailMatcher;

    Button connexionButton;
    EditText emailEditText;
    EditText passwordEditText;

    private DatabaseReference db;
    private DatabaseReference utilisateurReference;

    private static final String PREFS = "PREFS";
    private static final String PREFS_NOM = "PREFS_NOM";
    private static final String PREFS_PRENOM = "PREFS_PRENOM";
    private static final String PREFS_MAIL = "PREFS_MAIL";
    SharedPreferences sharedPreferences;

    String nomUtilisateur;
    String prenomUtilisateur;
    String passwordVerif;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);

        //Instanciation DB
        db = FirebaseDatabase.getInstance().getReference();
        utilisateurReference = FirebaseDatabase.getInstance().getReference("utilisateurs");


        //Instanciation
        connexionButton = findViewById(R.id.connexionButton);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        mailPattern = Pattern.compile(".+@.+\\.[a-z]+");

        //Setters
        connexionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                emailAttempt = emailEditText.getText().toString();
                passwordAttempt = passwordEditText.getText().toString();
                mailMatcher = mailPattern.matcher(emailAttempt);

                if (emailAttempt.matches("") || passwordAttempt.matches("")) {
                    Toast.makeText(ConnexionActivity.this, "Entrez votre mail et votre mot de passe ", Toast.LENGTH_SHORT).show();
                } else {
                    if (!mailMatcher.matches()) {
                        Toast.makeText(ConnexionActivity.this, "Le format du mail entré n'est pas valide", Toast.LENGTH_LONG).show();
                    } else {

                        utilisateurReference.orderByChild("mail")
                                .equalTo(emailAttempt)
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                            passwordVerif = datas.child("password").getValue().toString();
                                            nomUtilisateur = datas.child("nom").getValue().toString();
                                            prenomUtilisateur = datas.child("prenom").getValue().toString();
                                        }

                                        if (passwordAttempt.equals(passwordVerif)) {
                                            sharedPreferences.edit()
                                                    .putString(PREFS_NOM, nomUtilisateur)
                                                    .putString(PREFS_PRENOM, prenomUtilisateur)
                                                    .putString(PREFS_MAIL, emailAttempt)
                                                    .apply();
                                            Intent intentAccueil = new Intent(ConnexionActivity.this, AccueilActivity.class);
                                            startActivity(intentAccueil);
                                        } else {
                                            nomUtilisateur = null;
                                            prenomUtilisateur = null;
                                            passwordVerif = null;
                                            Toast.makeText(ConnexionActivity.this, "Les informations de connexion ne sont pas bonnes", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                    }
                }
            }
        });
    }
}

