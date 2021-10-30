package com.essai3;

import com.essai3.Dao.LivreDao;
import com.essai3.Dao.UtilisateurDao;
import com.essai3.beans.Livre;
import com.essai3.beans.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class UsersFromLibrarianHome implements Initializable {
    @FXML
    public Label prenom;
    @FXML
    private TableView<Utilisateur> table;
    @FXML
    private TableColumn<Utilisateur,Integer> id;
    @FXML
    private TableColumn<Utilisateur,String> nom;
    @FXML
    private TableColumn<Utilisateur,String> prenom_;
    @FXML
    private TableColumn<Utilisateur,String> email;
    @FXML
    private TableColumn<Utilisateur,String> catg;
    @FXML
    private TableColumn<Utilisateur,String> date1emprunt;
    @FXML
    private BorderPane root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Utilisateur,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
        prenom_.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("email"));
        catg.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("catg"));
        date1emprunt.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("date_premier_emprunt"));

//        ObservableList list = FXCollections.observableArrayList();
//        list.add(new Livre(1,"r",2,"e",5,"d","j", "dd"));
        try {
            table.setItems(UtilisateurDao.getUsersData());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setLibrarianHome(String msg) throws IOException {
        prenom.setText(msg);
    }

    public void getLivres() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("homeLibrarian.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1238, 688);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void addUserForm() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addUser.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 297, 390);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void refresh() throws SQLException, ClassNotFoundException {
        table.setItems(UtilisateurDao.getUsersData());
    }

    public void close(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }


}
