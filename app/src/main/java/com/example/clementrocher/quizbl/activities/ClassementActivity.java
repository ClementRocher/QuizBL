package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clementrocher.quizbl.R;

public class ClassementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);
        Button retourButton = (Button) findViewById(R.id.button5);
        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRetour = new Intent(ClassementActivity.this, AccueilActivity.class);
                startActivity(intentRetour);
            }
        });

        /*
        TODO : A designer entièrement
         */
    }
}
