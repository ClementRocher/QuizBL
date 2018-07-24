package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.clementrocher.quizbl.R;

public class GameActivity extends AppCompatActivity {

    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;
    Button reponse1; //Bouton haut gauche
    Button reponse2; //Bouton haut droite
    Button reponse3; //Bouton bas gauche
    Button reponse4; //Bouton bas droite
    boolean clicked = false;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Sérialisation
        reponse1 = findViewById(R.id.reponse1Button);
        reponse2 = findViewById(R.id.reponse2Button);
        reponse3 = findViewById(R.id.reponse3Button);
        reponse4 = findViewById(R.id.reponse4Button);
        mProgressBar=(ProgressBar)findViewById(R.id.progressBar);

        //Setters
        mProgressBar.setProgress(i);
        reponse1.setOnClickListener(buttonClickListener);
        reponse2.setOnClickListener(buttonClickListener);
        reponse3.setOnClickListener(buttonClickListener);
        reponse4.setOnClickListener(buttonClickListener);

        //Timer
        mCountDownTimer=new CountDownTimer(10000,10) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
                i++;
                mProgressBar.setProgress(i*100/(10000/10));

            }

            @Override
            public void onFinish() {
                    Intent intentFinTimer = new Intent(GameActivity.this, ClassementActivity.class);
                    startActivity(intentFinTimer);
                    i++;
                    mProgressBar.setProgress(100);
            }
        };
        mCountDownTimer.start();
    }

    @Override
    public void onBackPressed(){
        //do nothing
    }

    private View.OnClickListener buttonClickListener= new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            int id = v.getId();
            mCountDownTimer.cancel();
            switch(id){
                case R.id.reponse1Button:
                    Intent intentTest = new Intent(GameActivity.this,SettingsActivity.class);
                    startActivity(intentTest);
                    break;
                case R.id.reponse2Button:
                    //code
                    break;
                case R.id.reponse3Button:
                    //code
                    break;
                case R.id.reponse4Button:
                    Intent intentLoL = new Intent(GameActivity.this,AccueilActivity.class);
                    startActivity(intentLoL);
                    break;
            }
        }
    };




}
