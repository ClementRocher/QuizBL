package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clementrocher.quizbl.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilActivity extends AppCompatActivity {


    TextView nomTextView;
    TextView prenomTextView;
    TextView mandatTextView;
    TextView mailTextView;
    Button editInfosButton;

    private static final String PREFS = "PREFS";
    private static final String PREFS_NOM = "PREFS_NOM";
    private static final String PREFS_PRENOM = "PREFS_PRENOM";
    private static final String PREFS_MAIL = "PREFS_MAIL";
    SharedPreferences sharedPreferences;

    DatabaseReference utilisateurReference;

    String nomProfil;
    String prenomProfil;
    String mailProfil;
    String retrieveMandat;
    String retrieveCirconscription;
    String retrieveDepartement;
    String retrieveCommune;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);
        utilisateurReference = FirebaseDatabase.getInstance().getReference("utilisateurs");

        //Instanciation
        editInfosButton = findViewById(R.id.editInfosButton);
        nomTextView = findViewById(R.id.nomTextView);
        mandatTextView = findViewById(R.id.mandatTextView);
        mailTextView = findViewById(R.id.mailTextView);

        //Setters
        editInfosButton.setText("Modifier Infos");
        editInfosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEditMail = new Intent(ProfilActivity.this, EditInfosActivity.class);
                startActivity(intentEditMail);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        nomProfil = sharedPreferences.getString(PREFS_NOM, null);
        prenomProfil = sharedPreferences.getString(PREFS_PRENOM, null);
        mailProfil = sharedPreferences.getString(PREFS_MAIL, null);
        nomTextView.setText(prenomProfil + " " + nomProfil);
        mailTextView.setText(mailProfil);


        utilisateurReference.orderByChild("mail")
                .equalTo(mailProfil)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            retrieveMandat = data.child("mandat").getValue().toString();
                            retrieveCirconscription = data.child("circonscription").getValue().toString();
                            retrieveDepartement = data.child("departement").getValue().toString();
                            retrieveCommune = data.child("commune").getValue().toString();
                        }

                        mandatTextView.setText("Mandat : " + retrieveMandat + "\nCirconscription : " + retrieveCirconscription +
                                "\nDépartement : " + retrieveDepartement + "\nCommune : " + retrieveDepartement);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
