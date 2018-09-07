package com.example.clementrocher.quizbl.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.clementrocher.quizbl.R;

public class AccueilActivity extends AppCompatActivity {

    TextView profilAffichageTextView;
    ImageButton profilButton;
    ImageButton deconnexionButton;
    Button gameButton;
    Button retourButton;
    Button decoButton;
    Dialog dialogDeco;
    TextView alertbox_quit_text;

    String nomUtilisateur;
    String prenomUtilisateur;

    private static final String PREFS = "PREFS";
    private static final String PREFS_NOM = "PREFS_NOM";
    private static final String PREFS_PRENOM = "PREFS_PRENOM";
    private static final String PREFS_MAIL = "PREFS_MAIL";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);


        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);

        //Instanciation
        profilButton = findViewById(R.id.profilButton);
        gameButton = findViewById(R.id.gameButton);
        profilAffichageTextView = findViewById(R.id.profilAffichageTextView);
        deconnexionButton = findViewById(R.id.deconnexionButton);

        nomUtilisateur = sharedPreferences.getString(PREFS_NOM, null);
        prenomUtilisateur = sharedPreferences.getString(PREFS_PRENOM, null);

        profilAffichageTextView.setText(prenomUtilisateur + " " + nomUtilisateur);

        //Setters
        profilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProfil = new Intent(AccueilActivity.this, ProfilActivity.class);
                startActivity(intentProfil);
            }
        });


        gameButton.setText("Lancer Une \nPartie !");
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGame = new Intent(AccueilActivity.this, GameActivity.class);
                startActivity(intentGame);
            }
        });


        deconnexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Pop Up des conditions d'utilisations
                dialogDeco = new Dialog(AccueilActivity.this);
                dialogDeco.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogDeco.setContentView(R.layout.deconnexion_pop_up);
                dialogDeco.setCanceledOnTouchOutside(false);
                alertbox_quit_text = dialogDeco.findViewById(R.id.alertbox_quit_text);

                retourButton = dialogDeco.findViewById(R.id.retourButton);
                decoButton = dialogDeco.findViewById(R.id.decoButton);
                retourButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogDeco.dismiss();
                    }
                });
                decoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        sharedPreferences.edit().remove(PREFS_PRENOM).apply();
                        sharedPreferences.edit().remove(PREFS_NOM).apply();
                        sharedPreferences.edit().remove(PREFS_MAIL).apply();
                        Intent intentDeco = new Intent(AccueilActivity.this, InscriptionActivity.class);
                        startActivity(intentDeco);
                    }
                });

                dialogDeco.show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        //do nothing
        //Empecher le retour à la page d'inscription ou de connexion
    }
}
