package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clementrocher.quizbl.R;

public class LoginActivity extends AppCompatActivity {

    Button inscriptionButton;
    Button connexionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inscriptionButton = findViewById(R.id.goToInscriptionBouton);
        connexionButton = findViewById(R.id.goToConnexionButton);
        
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Setters des Boutons
        inscriptionButton.setText("Inscription");
        inscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentInscription = new Intent(LoginActivity.this, InscriptionActivity.class);
                startActivity(intentInscription);
            }
        });

        connexionButton.setText("Se Connecter");
        connexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentConnexion = new Intent(LoginActivity.this, ConnexionActivity.class);
                startActivity(intentConnexion);
            }
        });


    }

    @Override
    public void onBackPressed() {
        //do nothing
        //Pour empêcher le retour après déconnexion
    }
}
