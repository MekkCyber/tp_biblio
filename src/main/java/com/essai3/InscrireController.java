package com.essai3;

import com.essai3.Dao.Hash;
import com.essai3.Dao.UtilisateurDao;
import com.essai3.beans.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Stream;

public class InscrireController implements Initializable{

    @FXML
    private ComboBox countries;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private TextField pwd;
    @FXML
    private TextField pwd_;
    @FXML
    private BorderPane inscrire;

    public ObservableList readCountries() throws IOException {
//        File file = new File("@misc/countries.txt");    //creates a new file instance
//        FileReader fr = new FileReader(file);   //reads the file
//        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
//        String line;
//        while ((line = br.readLine()) != null) {
//            coutries_list.add(line);
//        }
        ObservableList coutries_list = FXCollections.observableArrayList();
        coutries_list.add("Maroc");
        coutries_list.add("France");
        coutries_list.add("Espagne");
        coutries_list.add("Italie");
        coutries_list.add("Allemagne");
        coutries_list.add("Algérie");
        coutries_list.add("Tunisie");
        coutries_list.add("Andora");
        return coutries_list;
    }
    public void setData() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            countries.getItems().addAll(readCountries());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Utilisateur getInscription() throws IOException, NoSuchAlgorithmException {
        String nom = this.nom.getText();
        String prenom = this.prenom.getText();
        String email = this.email.getText();
        String pwd = this.pwd.getText();
        String pwd_ = this.pwd_.getText();
        if (!pwd.equals(pwd_)){
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pwd.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 420, 150);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            return null;
        } else{
            return new Utilisateur(0,nom,prenom,email, Hash.sha1(pwd),"notclient","");
        }
    }

    public void addInDb() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException, IOException {
        if(getInscription()!=null){
            UtilisateurDao.addUtilisateur(getInscription());
            Stage stage = (Stage) inscrire.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 650, 420);
            stage.setTitle("EasyBiblio");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
    }


}
