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

    Button aideButton;
    Button conditionsButton;

    Button quitterButton;
    TextView alertbox_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Instanciation

        aideButton = findViewById(R.id.aideButton);
        conditionsButton = findViewById(R.id.conditionsButton);

        //Setters


        conditionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Pop Up des conditions d'utilisations
                dialog = new Dialog(SettingsActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.conditions_pop_up);
                dialog.setCanceledOnTouchOutside(false);
                alertbox_title = dialog.findViewById(R.id.alertbox_title);
                alertbox_title.setText("Conditions légales d'utilisation");

                quitterButton = dialog.findViewById(R.id.quitterButton);
                quitterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                /*
                TODO : Enlever le Lorem Ipsum avant version finale
                 */
            }
        });
    }
}
