package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clementrocher.quizbl.R;

public class SplashScreenActivity extends AppCompatActivity {


    private static final String PREFS = "PREFS";
    private static final String PREFS_NOM = "PREFS_NOM";
    private static final String PREFS_PRENOM = "PREFS_PRENOM";
    private static final String PREFS_MAIL = "PREFS_MAIL";
    SharedPreferences sharedPreferences;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);

        TextView titreTextView = findViewById(R.id.titretextView);
        TextView catchphraseTextView = findViewById(R.id.catchphraseTextView);
        ImageView logoImageview = findViewById(R.id.logoImageView);

        titreTextView.setText("BL Quiz");
        catchphraseTextView.setText("Avez vous le niveau pour être élu ?");

        new CountDownTimer(3000,10){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if (sharedPreferences.contains(PREFS_NOM) && sharedPreferences.contains(PREFS_PRENOM) && sharedPreferences.contains(PREFS_MAIL)) {

                    Intent goToAccueilIntent = new Intent(SplashScreenActivity.this, AccueilActivity.class);
                    startActivity(goToAccueilIntent);
                } else {
                    Intent goToLoginIntent = new Intent(SplashScreenActivity.this, InscriptionActivity.class);
                    startActivity(goToLoginIntent);
                }
            }
        }.start();







    }
}
