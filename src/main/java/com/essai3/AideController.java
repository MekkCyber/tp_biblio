package com.essai3;

import com.essai3.Dao.AuteurDao;
import com.essai3.Dao.EditionDao;
import com.essai3.Dao.EmpruntDao;
import com.essai3.beans.Auteur;
import com.essai3.beans.Livre;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AideController implements Initializable {
    @FXML
    private ComboBox auteur;
    @FXML
    private ComboBox edition;
    @FXML
    private TableView<Livre> atable;
    @FXML
    private TextArea info;
    @FXML
    private TableView<Livre> etable;
    @FXML
    private TableColumn<Livre,Integer> aid;
    @FXML
    private TableColumn<Livre,Integer> eid;
    @FXML
    private TableColumn<Livre,String> atitre;
    @FXML
    private TableColumn<Livre,String> etitre;
    @FXML
    private TableColumn<Livre,String> isbn;
    @FXML
    private BorderPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            auteur.getItems().addAll(AuteurDao.getAuteurs());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            edition.getItems().addAll(EditionDao.getEditions());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void afficherLivres() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("homeUserLivre.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 500);
        HomeUserLivreController controller = fxmlLoader.getController();
//        controller.email_=email;
//        controller.pwd_=pwd;
//        System.out.println(controller.email_);
//        System.out.println(email);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

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
    public void emprunter() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("emprunter.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 420);
        stage.setTitle("EasyBiblio");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public void choisirAuteur() throws SQLException, ClassNotFoundException {
        String nom = (String) this.auteur.getValue();
        Auteur auteur = AuteurDao.findAuteur(nom);
        info.setText("id :"+auteur.getId()+"\n"+
                "Nom : "+auteur.getNom()+"\n"+
                "Prenom : "+auteur.getPrenom()+"\n"+
                "Date de naissance : "+auteur.getDate_naissance());
        aid.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("id"));
        atitre.setCellValueFactory(new PropertyValueFactory<Livre,String>("titre"));
        try {
            atable.setItems(AuteurDao.getLivresForAuteur(auteur.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void choisirEdition(){
        String edition = (String) this.edition.getValue();
        eid.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("id"));
        etitre.setCellValueFactory(new PropertyValueFactory<Livre,String>("titre"));
        isbn.setCellValueFactory(new PropertyValueFactory<Livre,String>("isbn"));
        try {
            etable.setItems(EditionDao.getLivresForEdition(edition));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
