package com.essai3;

import com.essai3.Dao.LivreDao;
import com.essai3.Dao.UtilisateurDao;
import com.essai3.beans.Livre;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeUserLivreController implements Initializable {
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



    public void emprunter() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("emprunter.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 420);
        stage.setTitle("EasyBiblio");
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

    public void afficheAccueil() throws IOException, SQLException, NoSuchAlgorithmException, ClassNotFoundException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1320, 450);
        HomeController controller = fxmlLoader.getController();
        HelloController controller1=fxmlLoader.getController();
        String email= controller1.getEmail().getText();
        String passwdhash = controller1.getPwd().getText();
        controller.setUserHome(UtilisateurDao.authenticateUser(email, passwdhash).get(2));
        controller.setEmail(UtilisateurDao.authenticateUser(email, passwdhash).get(3));
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
