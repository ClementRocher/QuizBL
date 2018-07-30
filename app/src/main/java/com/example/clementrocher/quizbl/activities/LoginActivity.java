package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clementrocher.quizbl.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button inscriptionBouton = (Button) findViewById(R.id.goToInscriptionBouton);
        inscriptionBouton.setText("Inscription");
        inscriptionBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentInscription = new Intent(LoginActivity.this,InscriptionActivity.class);
                startActivity(intentInscription);
            }
        });

        Button connexionBouton = (Button) findViewById(R.id.goToConnexionButton);
        connexionBouton.setText("Se Connecter");
        connexionBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentConnexion = new Intent(LoginActivity.this,ConnexionActivity.class);
                startActivity(intentConnexion);
            }
        });


        //Bouton d'acces rapide au menu principal
        /*
        Todo : supprimer ce bouton absolument quand le stayLogged sera pret
         */
        Button accesrapideBouton = (Button) findViewById(R.id.raccourci);
        accesrapideBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAcces = new Intent(LoginActivity.this,AccueilActivity.class);
                startActivity(intentAcces);
            }
        });
    }

    @Override
    public void onBackPressed(){
        //do nothing
        //Pour empêcher le retour après déconnexion
    }
}
