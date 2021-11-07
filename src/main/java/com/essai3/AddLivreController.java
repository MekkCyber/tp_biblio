package com.essai3;

import com.essai3.Dao.*;
import com.essai3.beans.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AddLivreController {
    @FXML
    private TextField titre;
    @FXML
    private TextField parution;
    @FXML
    private TextField quantite;
    @FXML
    private TextField edition;
    @FXML
    private TextField isbn;
    @FXML
    private TextField mots_cles;
    @FXML
    private BorderPane addlivre;
    @FXML
    private TextField auteur;

    public Livre getLivreAdded() throws NoSuchAlgorithmException {
        String titre = this.titre.getText();
        String mots_cles = this.mots_cles.getText();
        int quantite = Integer.parseInt(this.quantite.getText());
        int parution = Integer.parseInt(this.parution.getText());

        return new Livre(200,titre,quantite,mots_cles,parution,"","","");
    }
    public Auteur getAuteur(){
        String nom_complet = this.auteur.getText();
        String nom = nom_complet.split(" ")[0];
        String prenom = nom_complet.split(" ")[1];
        return new Auteur(200,0,nom,prenom);
    }
    public Edition getEdition() throws SQLException, ClassNotFoundException {
        String edition = this.edition.getText();
        String isbn = this.isbn.getText();
        return new Edition(200,0,LivreDao.getNumberLivres(),edition,isbn);
    }
    public Ecriture getEcriture() throws SQLException, ClassNotFoundException {
        int nbr_livres = LivreDao.getNumberLivres();
        int nbr_auteurs = AuteurDao.getNumberAuteurs();
        return new Ecriture(nbr_auteurs,nbr_livres);
    }
    public void addInDb() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        LivreDao.addLivre(getLivreAdded());
        AuteurDao.addAuteur(getAuteur());
        EditionDao.addEdition(getEdition());
        EcritureDao.addEcriture(getEcriture());
        Stage stage = (Stage) addlivre.getScene().getWindow();
        stage.close();
    }
}
