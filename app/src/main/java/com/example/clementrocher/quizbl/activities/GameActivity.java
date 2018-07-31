package com.example.clementrocher.quizbl.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.clementrocher.quizbl.R;
import com.example.clementrocher.quizbl.fragments.GameFragment;

public class GameActivity extends AppCompatActivity {

    final String GAMEFRAGMENTTAG = "GAMEFRAGMENTTAG";
    final String ANSWER_FRAGMENT_TAG = "ANSWER_FRAGMENT_TAG";
    String intituleQuestion;
    String reponse1;
    String reponse2;
    String reponse3;
    String reponse4;
    String bonneReponse;
    GameFragment gameFragment;
    Fragment reponseFragment;
    int numQuestion;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        numQuestion = 1;
        score = 0;
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
            /*
            try {
                wait(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            numQuestion++;
            closeFragment();
        } while (numQuestion < 11);
        Intent t = new Intent(GameActivity.this,ClassementActivity.class);
        startActivity(t);*/
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
}
