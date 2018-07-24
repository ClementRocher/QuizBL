package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

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
}
