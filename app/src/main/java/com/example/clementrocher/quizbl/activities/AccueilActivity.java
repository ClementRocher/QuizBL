package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.clementrocher.quizbl.R;

public class AccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        ImageButton profilButton= (ImageButton) findViewById(R.id.profilButton);
        profilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProfil = new Intent(AccueilActivity.this,ProfilActivity.class);
                startActivity(intentProfil);
            }
        });

        ImageButton settingsButton= (ImageButton) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSettings = new Intent(AccueilActivity.this,SettingsActivity.class);
                startActivity(intentSettings);
            }
        });

        Button gameButton= (Button) findViewById(R.id.gameButton);
        gameButton.setText("Lancer Une \nPartie !");
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGame = new Intent(AccueilActivity.this,GameActivity.class);
                startActivity(intentGame);
            }
        });

        Button classementButton= (Button) findViewById(R.id.classementButton);
        classementButton.setText("Accéder à mon \nclassement");
        classementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentClassement = new Intent(AccueilActivity.this,ClassementActivity.class);
                startActivity(intentClassement);
            }
        });
    }

    @Override
    public void onBackPressed(){
        //do nothing
    }
}
