package com.essai3;

import com.essai3.Dao.LivreDao;
import com.essai3.beans.Emprunt;
import com.essai3.beans.Livre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController {
    public  String email;
    public  String pwd;

    @FXML
    public Label prenom;
    @FXML
    private TableView<Emprunt> table;
    @FXML
    private TableColumn<Emprunt,Integer> livre_id;
    @FXML
    private TableColumn<Emprunt,String> titre;
    @FXML
    private TableColumn<Emprunt,String> mots_cles;
    @FXML
    private TableColumn<Emprunt,Integer> parution;
    @FXML
    private TableColumn<Emprunt,String> auteur;
    @FXML
    private TableColumn<Emprunt,String> edition;
    @FXML
    private TableColumn<Emprunt, String> isbn;
    @FXML
    private TableColumn<Emprunt, String> date_emprunt;
    @FXML
    private TableColumn<Emprunt, String> date_retour;
    @FXML
    private TableColumn<Emprunt, String> returned;
    @FXML
    private BorderPane root;

    public TableView<Emprunt> getTable() {
        return table;
    }

    public TableColumn<Emprunt, Integer> getLivre_id() {
        return livre_id;
    }

    public TableColumn<Emprunt, String> getTitre() {
        return titre;
    }

    public TableColumn<Emprunt, String> getMots_cles() {
        return mots_cles;
    }

    public TableColumn<Emprunt, Integer> getParution() {
        return parution;
    }

    public TableColumn<Emprunt, String> getAuteur() {
        return auteur;
    }

    public TableColumn<Emprunt, String> getEdition() {
        return edition;
    }

    public TableColumn<Emprunt, String> getIsbn() {
        return isbn;
    }

    public TableColumn<Emprunt, String> getDate_emprunt() {
        return date_emprunt;
    }

    public TableColumn<Emprunt, String> getDate_retour() {
        return date_retour;
    }

    public TableColumn<Emprunt, String> getReturned() {
        return returned;
    }

    public BorderPane getRoot() {
        return root;
    }

    //@Override
    //public void initialize(URL url, ResourceBundle resourceBundle) {

//        livre_id.setCellValueFactory(new PropertyValueFactory<Emprunt,Integer>("id"));
//        titre.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("titre"));
//        mots_cles.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("mots_cles"));
//        auteur.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("auteur"));
//        edition.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("edition"));
//        parution.setCellValueFactory(new PropertyValueFactory<Emprunt,Integer>("parution"));
//        isbn.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("isbn"));
//        date_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("date_emprunt"));
//        date_retour.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("date_retour"));
//        returned.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("status"));
//        //HelloController controller = new HelloController();
//        FXMLLoader loader = new FXMLLoader(HomeApplication.class.getResource("hello-view.fxml"));
//        try {
//            Scene scene = new Scene(loader.load(), 1320, 450);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        HelloController controller = loader.getController();
//        email=controller.email_;
//        System.out.println(controller.getEmail_());
//        System.out.println(email);
//
//        HelloController controller1 = new HelloController();
//        email= controller1.getEmail_();
//        System.out.println(email);
//
//        email=controller1.map.get("email");
//        System.out.println(email);
//        try {
//            table.setItems(LivreDao.getDataForUser(email));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
 //   }
    @FXML
    public void setUserHome(String msg) throws IOException {
        //prenom.setText(msg);
    }

    public void seDeconnecter() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 420);
        stage.setTitle("EasyBiblio");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void afficherLivres() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("homeUserLivre.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 500);
        HomeUserLivreController controller = fxmlLoader.getController();
        controller.email_=email;
        controller.pwd_=pwd;
        System.out.println(controller.email_);
        System.out.println(email);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    public void emprunter() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("emprunter.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 420);
        stage.setTitle("EasyBiblio");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


}




