package com.essai3;

import com.essai3.Dao.UtilisateurDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
    public void getHomeLibrarian() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("helloLibrarian.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 300);
        stage.setScene(scene);
        stage.show();
    }

    public void getInscription() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("inscrire.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 450);
        stage.setScene(scene);
        stage.show();
    }


    public void authenticate() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, IOException {
        String email = this.email.getText();
        String passwdhash = this.pwd.getText();
        if (UtilisateurDao.authenticateUser(email, passwdhash)!=null){
            Stage stage = (Stage) root.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 850, 450);
            HomeController controller = fxmlLoader.getController();
            controller.setUserHome(UtilisateurDao.authenticateUser(email, passwdhash).get(2));
            stage.setScene(scene);
            stage.show();

        } else {
            System.out.println("non");
        }
    }
}