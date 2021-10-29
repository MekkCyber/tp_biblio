package com.essai3.beans;

import java.util.ArrayList;

public class Edition {
    int id, date,livre_id;
    String editeur, isbn;

    public Edition(int date, int livre_id, String editeur, String isbn) {
        this.date = date;
        this.livre_id = livre_id;
        this.editeur = editeur;
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getLivre_id() {
        return livre_id;
    }

    public void setLivre_id(int livre_id) {
        this.livre_id = livre_id;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
