package com.example.clementrocher.quizbl.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.clementrocher.quizbl.R;


public class GameFragment extends Fragment {


    //Attributs
    String frag_intituleQuestion;
    String frag_reponse1;
    String frag_reponse2;
    String frag_reponse3;
    String frag_reponse4;
    int frag_numQuestion;

    ProgressBar mProgressBar; //barre de progression du timer
    CountDownTimer mCountDownTimer; //Timer
    TextView intituleQuestionTextView; //Intitulé de la question
    Button reponse1Button; //Bouton haut gauche
    Button reponse2Button; //Bouton haut droite
    Button reponse3Button; //Bouton bas gauche
    Button reponse4Button; //Bouton bas droite
    TextView numQuestionTextView; //Numéro de question
    boolean clicked = false;
    int i = 0;
    private OnItemSelectedListener gameListener;

    //Constructeur vide obligatoire
    public GameFragment() {
    }

    //Interface pour interagir avec l'activité
    public interface OnItemSelectedListener {


        public void onButtonClicked(String reponseChoisie);
    }

    public static GameFragment newInstance(String intituleQuestion, String reponse1, String reponse2, String reponse3, String reponse4, int numQuestion) {
        GameFragment gameFragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString("intitule", intituleQuestion);
        args.putString("reponse1", reponse1);
        args.putString("reponse2", reponse2);
        args.putString("reponse3", reponse3);
        args.putString("reponse4", reponse4);
        args.putInt("numQuestion", numQuestion);
        gameFragment.setArguments(args);
        return gameFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            this.gameListener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        frag_intituleQuestion = getArguments().getString("intitule");
        frag_reponse1 = getArguments().getString("reponse1");
        frag_reponse2 = getArguments().getString("reponse2");
        frag_reponse3 = getArguments().getString("reponse3");
        frag_reponse4 = getArguments().getString("reponse4");
        frag_numQuestion = getArguments().getInt("numQuestion");

        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intituleQuestionTextView = view.findViewById(R.id.intituleQuestionTextView);
        reponse1Button = view.findViewById(R.id.reponse1Button);
        reponse2Button = view.findViewById(R.id.reponse2Button);
        reponse3Button = view.findViewById(R.id.reponse3Button);
        reponse4Button = view.findViewById(R.id.reponse4Button);
        numQuestionTextView = view.findViewById(R.id.numQuestionTextView);
        mProgressBar = view.findViewById(R.id.progressBar);

        intituleQuestionTextView.setText(frag_intituleQuestion);
        reponse1Button.setText(frag_reponse1);
        reponse2Button.setText(frag_reponse2);
        reponse3Button.setText(frag_reponse3);
        reponse4Button.setText(frag_reponse4);
        numQuestionTextView.setText("Question numéro " + frag_numQuestion);

        //Setters
        mProgressBar.setProgress(i);
        reponse1Button.setOnClickListener(buttonClickListener);
        reponse2Button.setOnClickListener(buttonClickListener);
        reponse3Button.setOnClickListener(buttonClickListener);
        reponse4Button.setOnClickListener(buttonClickListener);

        mCountDownTimer = new CountDownTimer(10000, 10) {

            @Override
            public void onTick(long millisUntilFinished) {
                i++;
                mProgressBar.setProgress(i * 100 / (10000 / 10));
            }

            @Override
            public void onFinish() {
                gameListener.onButtonClicked("Pas de réponse");
                mProgressBar.setProgress(100);
            }
        };
        mCountDownTimer.start();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.gameListener = null;
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            int id = v.getId(); //Récupération id du bouton cliqué
            mCountDownTimer.cancel(); //Arrêt du timer

            switch (id) {
                case R.id.reponse1Button:
                    gameListener.onButtonClicked(frag_reponse1);
                    break;
                case R.id.reponse2Button:
                    gameListener.onButtonClicked(frag_reponse2);
                    break;
                case R.id.reponse3Button:
                    gameListener.onButtonClicked(frag_reponse3);
                    break;
                case R.id.reponse4Button:
                    gameListener.onButtonClicked(frag_reponse4);
                    break;
            }
        }
    };

}
