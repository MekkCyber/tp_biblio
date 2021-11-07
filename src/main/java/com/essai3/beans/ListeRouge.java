package com.essai3.beans;

public class ListeRouge {
    int user_id;
    String date_debut,date_fin;

    public ListeRouge(int user_id, String date_debut, String date_fin) {
        this.user_id = user_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }
}
