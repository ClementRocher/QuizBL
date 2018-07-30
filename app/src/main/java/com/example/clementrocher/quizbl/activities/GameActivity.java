package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.clementrocher.quizbl.R;

public class GameActivity extends AppCompatActivity {

    ProgressBar mProgressBar; //barre de progression du timer
    CountDownTimer mCountDownTimer; //Timer
    TextView intituleQuestion; //Intitulé de la question
    Button reponse1; //Bouton haut gauche
    Button reponse2; //Bouton haut droite
    Button reponse3; //Bouton bas gauche
    Button reponse4; //Bouton bas droite
    TextView numQuestion; //Numéro de question
    boolean clicked = false;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Sérialisation
        reponse1 = findViewById(R.id.reponse1Button);
        reponse2 = findViewById(R.id.reponse2Button);
        reponse3 = findViewById(R.id.reponse3Button);
        reponse4 = findViewById(R.id.reponse4Button);
        mProgressBar = findViewById(R.id.progressBar);
        numQuestion = findViewById(R.id.numQuestionTextView);
        intituleQuestion = findViewById(R.id.intituleQuestionTextView);


        //Appel d'une boucle void game() pertinent pour la série de 10 questions ?
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Setters
        mProgressBar.setProgress(i);
        reponse1.setOnClickListener(buttonClickListener);
        reponse2.setOnClickListener(buttonClickListener);
        reponse3.setOnClickListener(buttonClickListener);
        reponse4.setOnClickListener(buttonClickListener);

        //Timer
        mCountDownTimer = new CountDownTimer(10000, 10) {

            @Override
            public void onTick(long millisUntilFinished) {
                i++;
                mProgressBar.setProgress(i * 100 / (10000 / 10));
            }

            @Override
            public void onFinish() {
                    /*
                    TODO : Afficher la réponse et passage à la prochaine question (via un interstitiel ?)
                    */
                Intent intentFinTimer = new Intent(GameActivity.this, ClassementActivity.class);
                startActivity(intentFinTimer);
                i++;
                mProgressBar.setProgress(100);
            }
        };
        mCountDownTimer.start();


    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId(); //Récupération id du bouton cliqué
            mCountDownTimer.cancel(); //Arrêt du timer
            switch (id) {
                case R.id.reponse1Button:

                    break;
                case R.id.reponse2Button:
                    //code
                    break;
                case R.id.reponse3Button:
                    //code
                    break;
                case R.id.reponse4Button:

                    break;
            }
        }
    };


    @Override
    public void onBackPressed() {
        //Blocage de la fonction retour une fois en jeu
    }
}
