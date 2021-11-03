package com.essai3.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DemandeEmprunt {
    private SimpleIntegerProperty id_demande;
    private SimpleIntegerProperty id_utilisateur;
    private SimpleStringProperty titre;
    private SimpleStringProperty date;

    public DemandeEmprunt(int id_demande, int id_utilisateur, String titre, String date) {
        this.id_demande = new SimpleIntegerProperty(id_demande);
        this.id_utilisateur = new SimpleIntegerProperty(id_utilisateur);
        this.titre = new SimpleStringProperty(titre);
        this.date = new SimpleStringProperty(date);
    }

    public int getId_demande() {
        return id_demande.get();
    }

    public SimpleIntegerProperty id_demandeProperty() {
        return id_demande;
    }

    public int getId_utilisateur() {
        return id_utilisateur.get();
    }

    public SimpleIntegerProperty id_utilisateurProperty() {
        return id_utilisateur;
    }

    public String getTitre() {
        return titre.get();
    }

    public SimpleStringProperty titreProperty() {
        return titre;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }
}