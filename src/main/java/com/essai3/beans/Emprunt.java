package com.essai3.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class Emprunt extends Livre{
    private SimpleIntegerProperty emprunt_id;
    private SimpleIntegerProperty edition_id;
    private SimpleIntegerProperty utilisateur_id;
    private SimpleStringProperty date_emprunt;
    private SimpleStringProperty date_retour;
    private SimpleStringProperty status;

    public  Emprunt(int id, String titre, int quantite, String mots_cles, int parution, String auteur, String edition, String isbn, int emprunt_id, Integer edition_id, Integer utilisateur_id, String date_emprunt, String date_retour, String status){
        super(id,titre,quantite,mots_cles,parution,auteur,edition,isbn);
        this.emprunt_id = new SimpleIntegerProperty(emprunt_id);
        this.edition_id = new SimpleIntegerProperty(edition_id);
        this.utilisateur_id = new SimpleIntegerProperty(utilisateur_id);
        this.date_emprunt = new SimpleStringProperty(date_emprunt);
        this.date_retour = new SimpleStringProperty(date_retour);
        this.status = new SimpleStringProperty(status);
    }

    public int getId() {
        return emprunt_id.get();

    }

    public SimpleIntegerProperty idProperty() {
        return emprunt_id;
    }

    public void setId(int id) {
        this.emprunt_id.set(id);
    }

    public int getEdition_id() {
        return edition_id.get();
    }

    public SimpleIntegerProperty edition_idProperty() {
        return edition_id;
    }

    public void setEdition_id(int edition_id) {
        this.edition_id.set(edition_id);
    }

    public int getUtilisateur_id() {
        return utilisateur_id.get();
    }

    public SimpleIntegerProperty utilisateur_idProperty() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id.set(utilisateur_id);
    }

    public String getDate_emprunt() {
        return date_emprunt.get();
    }

    public SimpleStringProperty date_empruntProperty() {
        return date_emprunt;
    }

    public void setDate_emprunt(String date_emprunt) {
        this.date_emprunt.set(date_emprunt);
    }

    public String getDate_retour() {
        return date_retour.get();
    }

    public SimpleStringProperty date_retourProperty() {
        return date_retour;
    }

    public void setDate_retour(String date_retour) {
        this.date_retour.set(date_retour);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public static Emprunt findEmpruntByIsbn(String isbn, ObservableList<Emprunt> list) {
        for (Emprunt emprunt : list) {
            if (emprunt.getIsbn().equals(isbn)){
                return emprunt;
            }
        }
        return null;

    }

}
