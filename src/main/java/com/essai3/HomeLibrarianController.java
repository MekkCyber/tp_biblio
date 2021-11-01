package com.essai3;

import com.essai3.Dao.LivreDao;
import com.essai3.Dao.UtilisateurDao;
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

public class HomeLibrarianController implements Initializable {
    @FXML
    public Label prenom;
    @FXML
    private TableView<Livre> table;
    @FXML
    private TableColumn<Livre,Integer> livre_id;
    @FXML
    private TableColumn<Livre,String> titre;
    @FXML
    private TableColumn<Livre,String> mots_cles;
    @FXML
    private TableColumn<Livre,Integer> parution;
    @FXML
    private TableColumn<Livre,String> auteur;
    @FXML
    private TableColumn<Livre,String> edition;
    @FXML
    private TableColumn<Livre, Integer> quantite;
    @FXML
    private TableColumn<Livre, String> isbn;
    @FXML
    private BorderPane root;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        livre_id.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("id"));
        titre.setCellValueFactory(new PropertyValueFactory<Livre,String>("titre"));
        mots_cles.setCellValueFactory(new PropertyValueFactory<Livre,String>("mots_cles"));
        auteur.setCellValueFactory(new PropertyValueFactory<Livre,String>("auteur"));
        edition.setCellValueFactory(new PropertyValueFactory<Livre,String>("edition"));
        parution.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("parution"));
        quantite.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("quantite"));
        isbn.setCellValueFactory(new PropertyValueFactory<Livre,String>("isbn"));
//        ObservableList list = FXCollections.observableArrayList();
//        list.add(new Livre(1,"r",2,"e",5,"d","j", "dd"));
        try {
            table.setItems(LivreDao.getData());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setLibrarianHome(String msg) throws IOException {
        prenom.setText(msg);
    }

    public void getUsers() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UsersFromLibrarianHome.fxml"));
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

    public void getEmprunts() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("emprunts.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1238, 688);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void addLibrarianForm() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addAdmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 297, 300);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void emprunter() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("emprunterLibrarian.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1238, 688);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


}
