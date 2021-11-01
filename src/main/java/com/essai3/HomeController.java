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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    String email;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
//
//        ObservableList list = FXCollections.observableArrayList();
//        try {
//            LivreDao.getData();
//            table.setItems(LivreDao.getDataForUser(email));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }
    @FXML
    public void setUserHome(String msg) throws IOException {
            prenom.setText(msg);
    }

    public void setEmail(String email) {
        this.email=email;
        livre_id.setCellValueFactory(new PropertyValueFactory<Emprunt,Integer>("id"));
        titre.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("titre"));
        mots_cles.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("mots_cles"));
        auteur.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("auteur"));
        edition.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("edition"));
        parution.setCellValueFactory(new PropertyValueFactory<Emprunt,Integer>("parution"));
        isbn.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("isbn"));
        date_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("date_emprunt"));
        date_retour.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("date_retour"));
        returned.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("status"));

        ObservableList list = FXCollections.observableArrayList();
        try {
            LivreDao.getData();
            table.setItems(LivreDao.getDataForUser(email));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



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




