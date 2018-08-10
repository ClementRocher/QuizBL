package com.example.clementrocher.quizbl.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.clementrocher.quizbl.R;
import com.example.clementrocher.quizbl.fragments.GameFragment;
import com.example.clementrocher.quizbl.fragments.ReponseFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GameActivity extends AppCompatActivity implements GameFragment.OnItemSelectedListener {

    String intituleQuestion;
    String reponse1;
    String reponse2;
    String reponse3;
    String reponse4;
    String bonneReponse;
    String reponseUtilisateur;
    GameFragment gameFragment;
    ReponseFragment reponseFragment;

    FirebaseDatabase questionsDatabase;
    DatabaseReference questionsDatabaseReference;
    int numQuestion;
    int score;
    boolean repondu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        numQuestion = 1;
        score = 0;
        questionsDatabase = FirebaseDatabase.getInstance();
        questionsDatabaseReference = questionsDatabase.getReference("questions");
    }

    @Override
    protected void onStart() {
        super.onStart();
        //do {
        fetchData(intituleQuestion, reponse1, reponse2, reponse3, reponse4, bonneReponse);
        intituleQuestion = "la question est";
        reponse1 = "a";
        reponse2 = "b";
        reponse3 = "d";
        reponse4 = "c";
        bonneReponse= "a";


        displayGameFragment(intituleQuestion, reponse1, reponse2, reponse3, reponse4,numQuestion);
        //displayReponseFragment(reponseUtilisateur,bonneReponse);
        numQuestion++;


        //closeFragment();
        //Intent t = new Intent(GameActivity.this,ClassementActivity.class);
        //startActivity(t);
    }

    private void fetchData(String intituleQuestion, String reponse1, String reponse2, String reponse3, String reponse4, String bonneReponse) {
        /*
        TODO : Fetch in DB and fill Data
        TODO : Créer un objet ?
         */

    }

    public void displayGameFragment(String intituleQuestion, String reponse1, String reponse2, String reponse3, String reponse4, int numQuestion) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        gameFragment = GameFragment.newInstance(intituleQuestion, reponse1, reponse2, reponse3, reponse4, numQuestion);
        ft.replace(R.id.fragmentContainer, gameFragment);
        ft.commit();
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, f, GAMEFRAGMENTTAG).commit();

    }

    public void displayReponseFragment(String reponseUtilisateur, String bonneReponse) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        reponseFragment = ReponseFragment.newInstance(reponseUtilisateur, bonneReponse);
        ft.replace(R.id.fragmentContainer, reponseFragment);
        ft.addToBackStack("");
        ft.commit();
    }

    public void closeFragment() {
        // Get the FragmentManager.
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Check to see if the fragment is already showing.
        GameFragment gameFragment = (GameFragment) fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (gameFragment != null) {
            // Create and commit the transaction to remove the fragment.
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(gameFragment).commit();
        }
    }

    @Override
    public void onButtonClicked(String reponseChoisie) {
        reponseUtilisateur = reponseChoisie;



        /*
        if(reponseUtilisateur==bonneReponse){
            Toast.makeText(GameActivity.this,"Bonne Réponse",Toast.LENGTH_LONG).show();
            closeFragment();
        }
        else if(reponseUtilisateur=="Pas de réponse"){
            Toast.makeText(GameActivity.this,"Vous n'avez pas répondu :-(",Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(GameActivity.this,"Raté",Toast.LENGTH_LONG).show();
*/
    }

/*
    @Override
    public void onBackPressed() {

    }*/
}
