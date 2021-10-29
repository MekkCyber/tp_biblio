package com.essai3;

import com.essai3.Dao.Hash;
import com.essai3.Dao.UtilisateurDao;
import com.essai3.beans.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AddUserController {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField pwd;
    @FXML
    private TextField date;
    @FXML
    private TextField catg;

    public Utilisateur getUserAdded() throws NoSuchAlgorithmException {
        String nom_ = nom.getText();
        String prenom_ = prenom.getText();
        String email_ = email.getText();
        String pwd_ = pwd.getText();
        String catg_ = catg.getText();
        String date_ = date.getText();
        return new Utilisateur(0,nom_,prenom_,email_, Hash.sha1(pwd_),catg_,date_);
    }

    public void addInDb() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        UtilisateurDao.addUtilisateur(getUserAdded());
    }

}
