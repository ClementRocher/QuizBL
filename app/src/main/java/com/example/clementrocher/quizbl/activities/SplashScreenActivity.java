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

        /*
        TODO : Check SharedPreferences pour vérifier login
         */

        CountDownTimer timerTemp = new CountDownTimer(3000,10) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intentouvrir = new Intent(SplashScreenActivity.this,LoginActivity.class);
                startActivity(intentouvrir);
            }
        };
        timerTemp.start();
    }
}
