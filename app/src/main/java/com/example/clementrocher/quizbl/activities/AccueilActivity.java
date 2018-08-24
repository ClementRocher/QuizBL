package com.example.clementrocher.quizbl.activities;

import android.app.Dialog;
import android.content.Intent;
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
    Button classementButton;
    Button retourButton;
    Button decoButton;
    Dialog dialogDeco;
    TextView alertbox_quit_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        //Instanciation
        profilButton = findViewById(R.id.profilButton);
        gameButton = findViewById(R.id.gameButton);
        classementButton = findViewById(R.id.classementButton);
        profilAffichageTextView = findViewById(R.id.profilAffichageTextView);
        deconnexionButton = findViewById(R.id.deconnexionButton);


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

        classementButton.setText("Accéder à mon \nclassement");
        classementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentClassement = new Intent(AccueilActivity.this, ClassementActivity.class);
                startActivity(intentClassement);
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
                        Intent intentDeco = new Intent(AccueilActivity.this,LoginActivity.class);
                        startActivity(intentDeco);
                    }
                });

                dialogDeco.show();
            }
        });

        /*
        TODO : Récuperer le nom et prénom en BDD et les afficher dans profilAffichageTextView
         */
    }

    @Override
    public void onBackPressed() {
        //do nothing
        //Empecher le retour à la page d'inscription ou de connexion
    }
}
