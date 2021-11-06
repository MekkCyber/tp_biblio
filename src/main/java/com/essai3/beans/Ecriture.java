package com.essai3.beans;

public class Ecriture {
    int livre_id,auteur_id;

    public Ecriture( int livre_id, int auteur_id) {
        this.livre_id = livre_id;
        this.auteur_id = auteur_id;
    }


    public int getLivre_id() {
        return livre_id;
    }

    public void setLivre_id(int livre_id) {
        this.livre_id = livre_id;
    }

    public int getAuteur_id() {
        return auteur_id;
    }

    public void setAuteur_id(int auteur_id) {
        this.auteur_id = auteur_id;
    }
}
