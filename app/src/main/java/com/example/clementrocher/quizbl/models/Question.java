package com.example.clementrocher.quizbl.models;

public class Question {

    //Attributs
    private String intituleQuestion;
    private String reponse1;
    private String reponse2;
    private String reponse3;
    private String reponse4;
    private String bonneReponse;
    private int idQuestion;

    //Constructors
    public Question() {
    }

    public Question(String intituleQuestion, String reponse1, String reponse2, String reponse3, String reponse4, String bonneReponse, int idQuestion) {
        this.intituleQuestion = intituleQuestion;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.reponse4 = reponse4;
        this.bonneReponse = bonneReponse;
        this.idQuestion = idQuestion;
    }

    public Question(Question newQuest){
        this.intituleQuestion = newQuest.intituleQuestion;
        this.reponse1 = newQuest.reponse1;
        this.reponse2 = newQuest.reponse2;
        this.reponse3 = newQuest.reponse3;
        this.reponse4 = newQuest.reponse4;
        this.bonneReponse = newQuest.bonneReponse;
        this.idQuestion = newQuest.idQuestion;
    }

    //Getters et Setters
    public String getIntituleQuestion() {
        return intituleQuestion;
    }

    public void setIntituleQuestion(String intituleQuestion) {
        this.intituleQuestion = intituleQuestion;
    }

    public String getReponse1() {
        return reponse1;
    }

    public void setReponse1(String reponse1) {
        this.reponse1 = reponse1;
    }

    public String getReponse2() {
        return reponse2;
    }

    public void setReponse2(String reponse2) {
        this.reponse2 = reponse2;
    }

    public String getReponse3() {
        return reponse3;
    }

    public void setReponse3(String reponse3) {
        this.reponse3 = reponse3;
    }

    public String getReponse4() {
        return reponse4;
    }

    public void setReponse4(String reponse4) {
        this.reponse4 = reponse4;
    }

    public String getBonneReponse() {
        return bonneReponse;
    }

    public void setBonneReponse(String bonneReponse) {
        this.bonneReponse = bonneReponse;
    }

    public int idQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }




}
