package com.essai3;

import com.essai3.Dao.LivreDao;
import com.essai3.Dao.UtilisateurDao;
import com.essai3.beans.Emprunt;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private BorderPane root;
    @FXML
    private Button inscrire;
    @FXML
    private TextField pwd;
    @FXML
    private TextField email;
    @FXML
    private Label admin;
    public String email_;
    private String pwd_;
    private String prenom_;
    public HashMap<String,String> map;

    public void getHomeLibrarian() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("helloLibrarian.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 300);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void getInscription() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("inscrire.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


    public TextField getPwd() {
        return pwd;
    }

    public TextField getEmail() {
        return email;
    }

    public String getEmail_() {
        return email_;
    }

    public String getPwd_() {
        return pwd_;
    }

    public String getPrenom_() {
        return prenom_;
    }

    public ArrayList<String> authenticate() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, IOException {
        String email = this.email.getText();
        String passwdhash = this.pwd.getText();
        if (UtilisateurDao.authenticateUser(email, passwdhash) != null) {
//            HomeController controller = new HomeController();
//            email_ = UtilisateurDao.authenticateUser(email, passwdhash).get(3);
//            System.out.println(email_);
//            prenom_ = UtilisateurDao.authenticateUser(email, passwdhash).get(2);
//            System.out.println(prenom_);
//            map.put("email",email_);
//            map.put("prenom",prenom_);
//            controller.setEmail(UtilisateurDao.authenticateUser(email, passwdhash).get(3));
//            controller.setPrenom_(UtilisateurDao.authenticateUser(email, passwdhash).get(2));
//            controller.setUserHome(UtilisateurDao.authenticateUser(email, passwdhash).get(2));
            Stage stage = (Stage) root.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1300, 500);
            HomeController controller = fxmlLoader.getController();
            controller.setUserHome(UtilisateurDao.authenticateUser(email, passwdhash).get(2));
//            controller.setEmail(UtilisateurDao.authenticateUser(email, passwdhash).get(3));
            controller.getLivre_id().setCellValueFactory(new PropertyValueFactory<Emprunt,Integer>("id"));
            controller.getTitre().setCellValueFactory(new PropertyValueFactory<Emprunt,String>("titre"));
            controller.getMots_cles().setCellValueFactory(new PropertyValueFactory<Emprunt,String>("mots_cles"));
            controller.getAuteur().setCellValueFactory(new PropertyValueFactory<Emprunt,String>("auteur"));
            controller.getEdition().setCellValueFactory(new PropertyValueFactory<Emprunt,String>("edition"));
            controller.getParution().setCellValueFactory(new PropertyValueFactory<Emprunt,Integer>("parution"));
            controller.getIsbn().setCellValueFactory(new PropertyValueFactory<Emprunt,String>("isbn"));
            controller.getDate_emprunt().setCellValueFactory(new PropertyValueFactory<Emprunt,String>("date_emprunt"));
            controller.getDate_retour().setCellValueFactory(new PropertyValueFactory<Emprunt,String>("date_retour"));
            controller.getReturned().setCellValueFactory(new PropertyValueFactory<Emprunt,String>("status"));
            controller.email=email;
            controller.pwd=passwdhash;
            try {
                controller.getTable().setItems(LivreDao.getDataForUser(UtilisateurDao.authenticateUser(email, passwdhash).get(3)));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            return UtilisateurDao.authenticateUser(email, passwdhash);

        } else {
            Stage stage_ = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("incorrect.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 492, 160);
            stage_.setScene(scene);
            stage_.centerOnScreen();
            stage_.show();
            return null;
        }
    }



}