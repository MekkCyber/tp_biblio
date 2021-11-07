package com.essai3.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class Livre {
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty quantite;
    private final SimpleIntegerProperty parution;
    private final SimpleStringProperty mots_cles;
    private final SimpleStringProperty titre;
    private final SimpleStringProperty auteur;
    private final SimpleStringProperty edition;
    private final SimpleStringProperty isbn;


    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getQuantite() {
        return quantite.get();
    }

    public void setQuantite(int quantite) {
        this.quantite.set(quantite);
    }

    public int getParution() {
        return parution.get();
    }

    public void setParution(int parution) {
        this.parution.set(parution);
    }

    public String getMots_cles() {
        return mots_cles.get();
    }

    public void setMots_cles(String mots_cles) {
        this.mots_cles.set(mots_cles);
    }

    public String getTitre() {
        return titre.get();
    }

    public void setTitre(String titre) {
        this.auteur.set(titre);
    }

    public String getAuteur() {
        return auteur.get();
    }

    public void setAuteur(String auteur) {
        this.auteur.set(auteur);
    }

    public String getEdition() {
        return edition.get();
    }

    public void setEdition(String edition) {
        this.edition.set(edition);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public SimpleStringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public static Livre findLivreByIsbn(String isbn, ObservableList<Livre> list) {
        for (Livre livre : list) {
            if (livre.getIsbn().equals(isbn)){
                return livre;
            }
        }
        return null;
    }

    public Livre(int id, String titre, int quantite, String mots_cles, int parution, String auteur, String edition, String isbn) {
        this.id = new SimpleIntegerProperty(id);
        this.quantite = new SimpleIntegerProperty(quantite);
        this.parution = new SimpleIntegerProperty(parution);
        this.mots_cles = new SimpleStringProperty(mots_cles);
        this.titre = new SimpleStringProperty(titre);
        this.auteur = new SimpleStringProperty(auteur);
        this.edition = new SimpleStringProperty(edition);
        this.isbn = new SimpleStringProperty(isbn);
    }
}
