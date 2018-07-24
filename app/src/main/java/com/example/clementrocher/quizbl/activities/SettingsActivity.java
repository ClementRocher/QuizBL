package com.example.clementrocher.quizbl.activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.clementrocher.quizbl.R;

public class SettingsActivity extends AppCompatActivity {

        Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button connexionFbButton = findViewById(R.id.connexionFbButton);
        Button notificationsButton = findViewById(R.id.notifButton);
        Button sonButton = findViewById(R.id.sonButton);
        Button aideButton = findViewById(R.id.aideButton);
        Button conditionsButton = findViewById(R.id.conditionsButton);
        Button deconnexionButton = (Button) findViewById(R.id.deconnexionButton);

        deconnexionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDeconnexion = new Intent(SettingsActivity.this,LoginActivity.class);
                startActivity(intentDeconnexion);
            }
        });

        conditionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(SettingsActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.conditions_pop_up);
                dialog.setCanceledOnTouchOutside(false);
                TextView alertbox_title = (TextView) dialog.findViewById(R.id.alertbox_title);
                alertbox_title.setText("Conditions légales d'utilisation");

                Button quitterButton = (Button) dialog.findViewById(R.id.quitterButton);

                quitterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}
