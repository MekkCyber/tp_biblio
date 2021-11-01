package com.essai3;

import com.essai3.Dao.Hash;
import com.essai3.Dao.LibrarianDao;
import com.essai3.Dao.UtilisateurDao;
import com.essai3.beans.Librarian;
import com.essai3.beans.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AddAdminController {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField pwd;
    @FXML
    private BorderPane addadmin;

    public Librarian getLibrarianAdded() throws NoSuchAlgorithmException {
        String nom_ = nom.getText();
        String prenom_ = prenom.getText();
        String email_ = email.getText();
        String pwd_ = pwd.getText();

        return new Librarian(200,nom_,prenom_,email_, Hash.sha1(pwd_));
    }

    public void addInDb() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        LibrarianDao.addLibrarian(getLibrarianAdded());
        Stage stage = (Stage) addadmin.getScene().getWindow();
        stage.close();
    }
}
