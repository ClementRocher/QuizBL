package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.clementrocher.quizbl.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

    }

    @Override
    protected void onStart() {
        super.onStart();

        /*
        TODO : fonction isLogged pour checker SharedPreferences pour vérifier login

        if(Login bon)
        {
            Intent goToAccueilIntent = new Intent(SplashScreenActivity.this,AccueilActivity.class);
            startActivity(goToAccueilIntent);
        }
        else{
            Intent goToLoginIntent = new Intent(SplashScreenActivity.this,LoginActivity.class);
            startActivity(goToLoginIntent);
        }

        TODO : supprimer le timer quand le isLogged est implémenté
         */

        CountDownTimer timerTemp = new CountDownTimer(2000, 10) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intentouvrir = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intentouvrir);
            }
        };
        timerTemp.start();
    }
}
