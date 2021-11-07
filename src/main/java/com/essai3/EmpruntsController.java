package com.essai3;

import com.essai3.Dao.EmpruntDao;
import com.essai3.Dao.UtilisateurDao;
import com.essai3.beans.Emprunt;
import com.essai3.beans.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmpruntsController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private TableView<Emprunt> table;
    @FXML
    private TableColumn<Emprunt,String> status;
    @FXML
    private TableColumn<Emprunt,String> titre;
    @FXML
    private TableColumn<Emprunt,String> utilisateur;
    @FXML
    private TableColumn<Emprunt,String> date_emprunt;
    @FXML
    private TableColumn<Emprunt,String> date_retour;
    @FXML
    private TableColumn<Emprunt,String> isbn;
    @FXML
    private TableColumn<Emprunt,String> emprunt_id;
    @FXML
    private ToggleGroup gr;
    @FXML
    private ToggleButton all;
    @FXML
    private ToggleButton yes;
    @FXML
    private ToggleButton no;

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

    public void getUsers() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UsersFromLibrarianHome.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emprunt_id.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("eid"));
        titre.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("titre"));
        isbn.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("isbn"));
        status.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("status"));
        date_emprunt.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("date_emprunt"));
        date_retour.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("date_retour"));
        utilisateur.setCellValueFactory(new PropertyValueFactory<Emprunt,String>("auteur"));

        try {
            table.setItems(EmpruntDao.showEmprunts());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void gerer() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gererProfiles.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1238, 688);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void allStatus() throws SQLException, ClassNotFoundException {
        table.setItems(EmpruntDao.showEmprunts());
    }
    public void yesStatus() throws SQLException, ClassNotFoundException {
        table.setItems(EmpruntDao.showYesEmprunts());
    }
    public void noStatus() throws SQLException, ClassNotFoundException {
        table.setItems(EmpruntDao.showNoEmprunts());
    }

}
