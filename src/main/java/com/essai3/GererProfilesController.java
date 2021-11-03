package com.essai3;

import com.essai3.Dao.UtilisateurDao;
import com.essai3.beans.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GererProfilesController implements Initializable {
    @FXML
    private ComboBox email;
    @FXML
    private ComboBox catg;
    @FXML
    private TextArea info_;
    @FXML
    private TableView table;
    @FXML
    private BorderPane root;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            email.getItems().addAll(UtilisateurDao.getEmails());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            catg.getItems().addAll(UtilisateurDao.getCatgs());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void showProfile() throws SQLException, ClassNotFoundException {
        String email = (String) this.email.getValue();
        Utilisateur user = UtilisateurDao.findInfoUser(email);
        String date_premier_emprunt = user.getDate_premier_emprunt().equals("") ? "pas d'emprunt pour le moment" : user.getDate_premier_emprunt();
        info_.setText("id :"+user.getId()+"\n"+
                    "Nom : "+user.getNom()+"\n"+
                    "Prenom : "+user.getPrenom()+"\n"+
                    "email : "+user.getEmail()+"\n"+
                    "Catégorie : "+ user.getCatg()+"\n"+
                    "Date du premier emprunt : "+date_premier_emprunt);
    }

    public void updateCatg() throws SQLException, ClassNotFoundException {
        UtilisateurDao.updateCatg((String) catg.getValue(),(String) email.getValue());
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

    public void getEmprunts() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("emprunts.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1238, 688);
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

    public void getUsers() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UsersFromLibrarianHome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1238, 688);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


}
