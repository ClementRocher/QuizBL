package com.example.clementrocher.quizbl.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clementrocher.quizbl.R;
import com.example.clementrocher.quizbl.models.Question;
import com.google.firebase.database.DatabaseReference;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    String intituleQuestion;
    String reponse1;
    String reponse2;
    String reponse3;
    String reponse4;
    String bonneReponse;
    String reponseUtilisateur;

    ProgressBar mProgressBar; //barre de progression du timer
    CountDownTimer mCountDownTimer; //Timer
    TextView intituleQuestionTextView; //Intitulé de la question
    Button reponse1Button; //Bouton haut gauche
    Button reponse2Button; //Bouton haut droite
    Button reponse3Button; //Bouton bas gauche
    Button reponse4Button; //Bouton bas droite
    Button nextQuestionButton;
    TextView numQuestionTextView; //Numéro de question

    Question[] tabQuestions;
    Question currentQuestion;
    Question q;
    Question[] tabfiller = new Question[10];

    private DatabaseReference questionsReference;
    int numQuestion;
    int score;
    int time;
    boolean repondu = false;
    boolean verif = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /**
         * Création d'un tableau pour simuler les questions, partie à supprimer
         */

        Question q1 = new Question("Quelle est la préfecture de l'Aisne ?", "Château-Thierry", "Laon", "Saint Quentin", "Soissons", "Laon", 1);
        Question q2 = new Question("De quel département Moulins est-elle la préfecture", "Aisne", "Ain", "Allier", "Aude", "Allier", 2);
        Question q3 = new Question("De quelle département La Rochelle est-elle préfecture ?", "Charente", "Vendée", "Charente-Maritime", "Deux-Sèvres", "Charente-Maritime", 3);
        Question q4 = new Question("La réforme des régimes matrimoniaux de 1965 a attribué de nouveaux droits aux femmes . Lesquels ?", "le droit de vote", "Le droit à un compte bancaire", "Le droit d'éligibilité", "le droit de conduire", "Le droit à un compte bancaire", 4);
        Question q5 = new Question("Le premier Code civil ou \"Code Napoléon\" fut promulgué en 1804. Quel romancier a déclaré : \"(�) pour prendre le ton, je lisais chaque matin deux ou trois pages du Code civil, afin d'être toujours naturel ?", "Stendhal", "Victor Hugo", "Honoré de Balzac", "Guy de Maupassant", "Stendhal", 5);
        Question q6 = new Question("intitulé q6", "reponse1", "reponse2 a", "reponse3", "reponse4", "reponse2 a", 6);
        Question q7 = new Question("intitulé q7", "reponse1", "reponse2", "reponse3", "reponse4 a", "reponse4 a", 7);
        Question q8 = new Question("intitulé q8", "reponse1 a", "reponse2", "reponse3", "reponse4", "reponse1 a", 8);
        Question q9 = new Question("intitulé q9", "reponse1", "reponse2 a", "reponse3", "reponse4", "reponse2 a", 9);
        Question q10 = new Question("intitulé q10", "reponse1", "reponse2", "reponse3 a", "reponse4", "reponse3 a", 0);


        tabfiller[0] = q1;
        tabfiller[1] = q2;
        tabfiller[2] = q3;
        tabfiller[3] = q4;
        tabfiller[4] = q5;
        tabfiller[5] = q6;
        tabfiller[6] = q7;
        tabfiller[7] = q8;
        tabfiller[8] = q9;
        tabfiller[9] = q10;
        /**
         * Fin du code à supprimer
         */

        intituleQuestionTextView = findViewById(R.id.intituleQuestionTextView);
        reponse1Button = findViewById(R.id.reponse1Button);
        reponse2Button = findViewById(R.id.reponse2Button);
        reponse3Button = findViewById(R.id.reponse3Button);
        reponse4Button = findViewById(R.id.reponse4Button);
        nextQuestionButton = findViewById(R.id.nextQuestionButton);
        numQuestionTextView = findViewById(R.id.numQuestionTextView);
        mProgressBar = findViewById(R.id.progressBar);


        tabQuestions = new Question[10];
        int tabIndice[] = new int[10];
        numQuestion = 1;
        score = 0;


        tabIndice = remplirIndices(tabIndice);


        for (int i = 0; i < tabQuestions.length; i++) {
            tabQuestions[i] = new Question(fetchData(tabIndice[i]));
        }


        currentQuestion = tabQuestions[(numQuestion - 1)];

        setQuestionView();


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setQuestionView() {
        //Toast.makeText(GameActivity.this,currentQuestion.getIntituleQuestion(),Toast.LENGTH_LONG).show();

        if (numQuestion > 1) {
            reponse1Button.setEnabled(true);
            reponse2Button.setEnabled(true);
            reponse3Button.setEnabled(true);
            reponse4Button.setEnabled(true);

            reponse1Button.setBackground(getResources().getDrawable(R.drawable.mybutton));
            reponse2Button.setBackground(getResources().getDrawable(R.drawable.mybutton));
            reponse3Button.setBackground(getResources().getDrawable(R.drawable.mybutton));
            reponse4Button.setBackground(getResources().getDrawable(R.drawable.mybutton));
        }
        reponse1Button.setEnabled(true);
        reponse2Button.setEnabled(true);
        reponse3Button.setEnabled(true);
        reponse4Button.setEnabled(true);
        time = 0;
        intituleQuestionTextView.setText(currentQuestion.getIntituleQuestion());
        reponse1Button.setText(currentQuestion.getReponse1());
        reponse2Button.setText(currentQuestion.getReponse2());
        reponse3Button.setText(currentQuestion.getReponse3());
        reponse4Button.setText(currentQuestion.getReponse4());
        numQuestionTextView.setText("Question : " + String.valueOf(numQuestion) + " sur 10");

        reponse1Button.setOnClickListener(buttonClickListener);
        reponse2Button.setOnClickListener(buttonClickListener);
        reponse3Button.setOnClickListener(buttonClickListener);
        reponse4Button.setOnClickListener(buttonClickListener);
        nextQuestionButton.setEnabled(false);
        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numQuestion < 11) {
                    currentQuestion = tabQuestions[numQuestion - 1];
                    setQuestionView();
                } else {
                    //Toast.makeText(GameActivity.this,String.valueOf(score),Toast.LENGTH_SHORT).show();

                    Intent intentScore = new Intent(GameActivity.this, ClassementActivity.class);
                    intentScore.putExtra("Score", score);
                    startActivity(intentScore);

                }

            }
        });

        mProgressBar.setProgress(time);
        mCountDownTimer = new CountDownTimer(10000, 10) {

            @Override
            public void onTick(long millisUntilFinished) {
                time++;
                mProgressBar.setProgress(time * 100 / (10000 / 10));
            }

            @Override
            public void onFinish() {
                //gameListener.onButtonClicked("Pas de réponse"); //N'existe plus car plus de fragment
                mProgressBar.setProgress(100);
                reponse1Button.setEnabled(false);
                reponse2Button.setEnabled(false);
                reponse3Button.setEnabled(false);
                reponse4Button.setEnabled(false);
                nextQuestionButton.setEnabled(true);
                numQuestion++;
                if (currentQuestion.getBonneReponse().equals(reponse1Button.getText())) {
                    reponse1Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                } else if (currentQuestion.getBonneReponse().equals(reponse2Button.getText())) {
                    reponse2Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                } else if (currentQuestion.getBonneReponse().equals(reponse3Button.getText())) {
                    reponse3Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                } else if (currentQuestion.getBonneReponse().equals(reponse4Button.getText())) {
                    reponse4Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                }
            }
        };
        mCountDownTimer.start();
    }


    private View.OnClickListener buttonClickListener = new View.OnClickListener() {

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v) {

            int id = v.getId(); //Récupération id du bouton cliqué
            mCountDownTimer.cancel(); //Arrêt du timer

            switch (id) {
                case R.id.reponse1Button:
                    if ((reponse1Button.getText()) == currentQuestion.getBonneReponse()) {
                        reponse1Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        score++;
                    } else {
                        reponse1Button.setBackground(getResources().getDrawable(R.drawable.boutonfaux));

                        if (currentQuestion.getBonneReponse().equals(reponse2Button.getText())) {
                            reponse2Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        } else if (currentQuestion.getBonneReponse().equals(reponse3Button.getText())) {
                            reponse3Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        } else if (currentQuestion.getBonneReponse().equals(reponse4Button.getText())) {
                            reponse4Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        }
                    }

                    break;

                case R.id.reponse2Button:
                    if ((reponse2Button.getText()) == currentQuestion.getBonneReponse()) {
                        reponse2Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        score++;
                    } else {
                        reponse2Button.setBackground(getResources().getDrawable(R.drawable.boutonfaux));

                        if (currentQuestion.getBonneReponse().equals(reponse1Button.getText())) {
                            reponse1Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        } else if (currentQuestion.getBonneReponse().equals(reponse3Button.getText())) {
                            reponse3Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        } else if (currentQuestion.getBonneReponse().equals(reponse4Button.getText())) {
                            reponse4Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        }
                    }

                    break;

                case R.id.reponse3Button:
                    if ((reponse3Button.getText()) == currentQuestion.getBonneReponse()) {
                        reponse3Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        score++;
                    } else {
                        reponse3Button.setBackground(getResources().getDrawable(R.drawable.boutonfaux));

                        if (currentQuestion.getBonneReponse().equals(reponse1Button.getText())) {
                            reponse1Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        } else if (currentQuestion.getBonneReponse().equals(reponse2Button.getText())) {
                            reponse2Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        } else if (currentQuestion.getBonneReponse().equals(reponse4Button.getText())) {
                            reponse4Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        }
                    }

                    break;

                case R.id.reponse4Button:
                    if ((reponse4Button.getText()) == currentQuestion.getBonneReponse()) {
                        reponse4Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        score++;
                    } else {
                        reponse4Button.setBackground(getResources().getDrawable(R.drawable.boutonfaux));

                        if (currentQuestion.getBonneReponse().equals(reponse1Button.getText())) {
                            reponse1Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        } else if (currentQuestion.getBonneReponse().equals(reponse2Button.getText())) {
                            reponse2Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        } else if (currentQuestion.getBonneReponse().equals(reponse3Button.getText())) {
                            reponse3Button.setBackground(getResources().getDrawable(R.drawable.boutonvrai));
                        }
                    }

                    break;
            }
            reponse1Button.setEnabled(false);
            reponse2Button.setEnabled(false);
            reponse3Button.setEnabled(false);
            reponse4Button.setEnabled(false);
            nextQuestionButton.setEnabled(true);
            numQuestion++;
        }
    };

    private Question fetchData(int indice) {

        /**
         * Remplacer ce morceau de code par un appel serveur pour obtenir une liste de 10 questions
         */

        for (int i = 0; i < tabfiller.length; i++) {
            if (tabfiller[i].idQuestion() == indice) {
                q = new Question(tabfiller[i]);

            }
        }
        /**
         * fin du code à remplacer
         */

        return q;
    }

    private int[] remplirIndices(int[] tabIndice) {
        int n = 10;

        for (int index = 0; index < n; ++index) {
            tabIndice[index] = index;
        }
        Random random = new Random(System.currentTimeMillis());
        for (int index = 0; index < n; ++index) {
            int j = (int) (random.nextDouble() * (n - index) + index);
            int tmp = tabIndice[index];
            tabIndice[index] = tabIndice[j];
            tabIndice[j] = tmp;
        }
        return tabIndice;
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}


