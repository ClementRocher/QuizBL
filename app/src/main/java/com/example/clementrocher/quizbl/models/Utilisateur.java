package com.example.clementrocher.quizbl.models;

public class Utilisateur {

    private String nom;
    private String prenom;
    private String mail;
    private String password;
    private String mandat;
    private String circonscription;
    private String departement;
    private String commune;
    private String scoreGlobal;
    private String nbParties;

    public Utilisateur(){

    }

    public Utilisateur(String nom, String prenom, String mail, String password, String mandat, String circonscription, String departement, String commune, String scoreGlobal,String nbParties) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.mandat = mandat;
        this.circonscription = circonscription;
        this.departement = departement;
        this.commune = commune;
        this.scoreGlobal=scoreGlobal;
        this.nbParties=nbParties;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMandat() {
        return mandat;
    }

    public void setMandat(String mandat) {
        this.mandat = mandat;
    }

    public String getCirconscription() {
        return circonscription;
    }

    public void setCirconscription(String circonscription) {
        this.circonscription = circonscription;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getScoreGlobal() {
        return scoreGlobal;
    }

    public void setScoreGlobal(String scoreglobal) {
        this.scoreGlobal = scoreglobal;
    }

    public String getNbParties() {
        return nbParties;
    }

    public void setNbParties(String nbParties) {
        this.nbParties = nbParties;
    }
}
