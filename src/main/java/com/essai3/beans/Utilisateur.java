package com.essai3.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Utilisateur {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prenom;
    private final SimpleStringProperty email;
    private final SimpleStringProperty passwdhash;
    private final SimpleStringProperty catg;
    private final SimpleStringProperty date_premier_emprunt;

    public  Utilisateur(int id, String nom, String prenom, String email, String passwdhash, String catg, String date_premier_emprunt){
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.email = new SimpleStringProperty(email);
        this.passwdhash = new SimpleStringProperty(passwdhash);
        this.catg = new SimpleStringProperty(catg);
        this.date_premier_emprunt = new SimpleStringProperty(date_premier_emprunt);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public SimpleStringProperty prenomProperty() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPasswdhash() {
        return passwdhash.get();
    }

    public SimpleStringProperty passwdhashProperty() {
        return passwdhash;
    }

    public void setPasswdhash(String passwdhash) {
        this.passwdhash.set(passwdhash);
    }

    public String getCatg() {
        return catg.get();
    }

    public SimpleStringProperty catg_idProperty() {
        return catg;
    }

    public void setCatg_id(String catg_id) {
        this.catg.set(catg_id);
    }

    public String getDate_premier_emprunt() {
        return date_premier_emprunt.get();
    }

    public SimpleStringProperty date_premier_empruntProperty() {
        return date_premier_emprunt;
    }

    public void setDate_premier_emprunt(String date_premier_emprunt) {
        this.date_premier_emprunt.set(date_premier_emprunt);
    }
}
