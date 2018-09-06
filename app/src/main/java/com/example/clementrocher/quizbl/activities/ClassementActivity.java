package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clementrocher.quizbl.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClassementActivity extends AppCompatActivity {


    TextView scorePartieTextView;
    TextView scoreGlobalTextView;
    TextView userTextView;
    TextView encouragementsTextView;

    Button nouvellepartieButton;

    private static final String PREFS = "PREFS";
    private static final String PREFS_NOM = "PREFS_NOM";
    private static final String PREFS_PRENOM = "PREFS_PRENOM";
    private static final String PREFS_MAIL = "PREFS_MAIL";


    DatabaseReference utilisateurReference;

    SharedPreferences sharedPreferences;
    String nomUtilisateur;
    String prenomUtilisateur;
    String mailUtilisateur;
    int score;
    String retrieveScoreGlobal;
    String retrieveNbParties;
    int nbPart;
    int scorGlob;
    double moyenneDuJoueur;
    Object key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);

        Bundle extras = getIntent().getExtras();
        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);
        utilisateurReference = FirebaseDatabase.getInstance().getReference("utilisateurs");

        encouragementsTextView = findViewById(R.id.encouragementTextView);
        nouvellepartieButton = findViewById(R.id.nouvellePartieButton);
        scorePartieTextView = findViewById(R.id.scorePartieTextView);
        scoreGlobalTextView = findViewById(R.id.scoreGlobalTextView);

        userTextView = findViewById(R.id.userTextView);
        nouvellepartieButton = findViewById(R.id.nouvellePartieButton);

        nomUtilisateur = sharedPreferences.getString(PREFS_NOM, null);
        prenomUtilisateur = sharedPreferences.getString(PREFS_PRENOM, null);
        mailUtilisateur = sharedPreferences.getString(PREFS_MAIL, null);

        nouvellepartieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), GameActivity.class));
            }
        });

        score = extras.getInt("Score");
        scorePartieTextView.setText(String.valueOf(score));

        userTextView.setText(prenomUtilisateur + nomUtilisateur);

        if (score < 5) {
            encouragementsTextView.setText(getString(R.string.encouragementsUnder5));
        }
        switch (score) {
            case 5:
                encouragementsTextView.setText(getString(R.string.encouragements5));
                break;

            case 6:
                encouragementsTextView.setText(getString(R.string.encouragements6));
                break;

            case 7:
                encouragementsTextView.setText(getString(R.string.encouragements7));
                break;

            case 8:
                encouragementsTextView.setText(getString(R.string.encouragements8));
                break;

            case 9:
                encouragementsTextView.setText(getString(R.string.encouragements9));
                break;
        }

        utilisateurReference.orderByChild("mail").equalTo(mailUtilisateur)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            retrieveScoreGlobal = data.child("scoreGlobal").getValue().toString();
                            retrieveNbParties = data.child("nbParties").getValue().toString();
                            key = data.getKey();
                        }


                        nbPart = Integer.valueOf(retrieveNbParties);
                        scorGlob = Integer.valueOf(retrieveScoreGlobal);

                        scorGlob = scorGlob + score;
                        nbPart = nbPart + 1;
                        moyenneDuJoueur = (double) scorGlob / nbPart;
                        scoreGlobalTextView.setText(String.valueOf(moyenneDuJoueur));

                        utilisateurReference.child(key.toString()).child("nbParties").setValue(String.valueOf(nbPart));
                        utilisateurReference.child(key.toString()).child("scoreGlobal").setValue(String.valueOf(scorGlob));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    @Override
    public void onBackPressed() {
        //do nothing
        //Empecher le retour à la page d'inscription ou de connexion
    }
}
