package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.clementrocher.quizbl.R;

public class ProfilActivity extends AppCompatActivity {

    ImageButton avatarImageButton;
    TextView nomTextView;
    TextView prenomTextView;
    TextView mandatTextView;
    TextView mailTextView;
    Button editMailButton;

    private static final String PREFS = "PREFS";
    private static final String PREFS_NOM = "PREFS_NOM";
    private static final String PREFS_PRENOM = "PREFS_PRENOM";
    private static final String PREFS_MAIL = "PREFS_MAIL";
    SharedPreferences sharedPreferences;

    String nomProfil;
    String prenomProfil;
    String mailProfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);

        //Instanciation
        editMailButton = findViewById(R.id.editMailButton);
        nomTextView = findViewById(R.id.nomTextView);
        prenomTextView = findViewById(R.id.prenomTextView);
        mandatTextView = findViewById(R.id.mandatTextView);
        mailTextView = findViewById(R.id.mailTextView);

        //Setters
        editMailButton.setText("Modifier mon Mail");
        editMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEditMail = new Intent(ProfilActivity.this, EditMailActivity.class);
                startActivity(intentEditMail);
            }
        });

        /*
        TODO : Implémenter le choix d'un avatar parmi une liste en cliquant sur l'imageButton (images stockées dans l'app ou en BDD
         */

        /*
        TODO : Récupérer les infos du profil en BDD et les afficher
         */
    }

    @Override
    protected void onStart() {
        super.onStart();

        nomProfil = sharedPreferences.getString(PREFS_NOM, null);
        prenomProfil = sharedPreferences.getString(PREFS_PRENOM, null);
        mailProfil = sharedPreferences.getString(PREFS_MAIL, null);

        nomTextView.setText(nomProfil);
        prenomTextView.setText(prenomProfil);
        mailTextView.setText(mailProfil);
    }
}
