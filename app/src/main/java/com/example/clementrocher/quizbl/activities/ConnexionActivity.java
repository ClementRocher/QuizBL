package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clementrocher.quizbl.R;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

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
                        Toast.makeText(ConnexionActivity.this, "Bravo", Toast.LENGTH_SHORT).show();
                        /*
                        TODO : vérifier dans la base de données que ce couple mail/password est bon
                        if(couple est bon){

                        }
                         */
                        Intent intentAccueil = new Intent(ConnexionActivity.this, AccueilActivity.class);
                        /*
                        TODO : Laisser Login et Mdp en SharedPreferences
                         */
                        startActivity(intentAccueil);
                    }
                }
            }
        });
    }
}

