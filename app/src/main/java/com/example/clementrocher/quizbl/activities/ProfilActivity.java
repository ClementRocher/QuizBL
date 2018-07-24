package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clementrocher.quizbl.R;

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        Button editMailButton = (Button) findViewById(R.id.editMailButton);
        editMailButton.setText("Modifier mon Mail");
        editMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEditMail = new Intent(ProfilActivity.this, EditMailActivity.class);
                startActivity(intentEditMail);
            }
        });
    }
}
