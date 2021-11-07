package com.essai3;

import com.essai3.Dao.EmpruntDao;
import com.essai3.Dao.Hash;
import com.essai3.Dao.ListeRougeDao;
import com.essai3.Dao.UtilisateurDao;
import com.essai3.beans.DemandeEmprunt;
import com.essai3.beans.ListeRouge;
import com.essai3.beans.Utilisateur;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GererProfilesController implements Initializable {
    @FXML
    private ComboBox email;
    @FXML
    private ComboBox catg;
    @FXML
    private TextArea info_;
    @FXML
    private TableView<DemandeEmprunt> table;
    @FXML
    private TableColumn<DemandeEmprunt,Integer> id_demande;
    @FXML
    private TableColumn<DemandeEmprunt,Integer> id_livre;
    @FXML
    private TableColumn<DemandeEmprunt,String> titre;
    @FXML
    private TableColumn<DemandeEmprunt,String> date;
    @FXML
    private BorderPane root;
    @FXML
    private TextField id;
    @FXML
    private DatePicker date1;
    @FXML
    private DatePicker date2;


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
        date1.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            {
                date1.setPromptText(pattern.toLowerCase());
            }
            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        date2.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            {
                date2.setPromptText(pattern.toLowerCase());
            }
            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });


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
                    "Date du premier emprunt : "+date_premier_emprunt+"\n"+
                    "Utilisateur banni : "+ ListeRougeDao.userBanned(email)+"\n"+
                    "Date début du bannissement : "+ListeRougeDao.dateDebut(email)+"\n"+
                    "Date fin du bannissement : "+ListeRougeDao.dateFin(email));
        id_demande.setCellValueFactory(new PropertyValueFactory<DemandeEmprunt,Integer>("id_demande"));
        id_livre.setCellValueFactory(new PropertyValueFactory<DemandeEmprunt,Integer>("id_utilisateur"));
        titre.setCellValueFactory(new PropertyValueFactory<DemandeEmprunt,String>("titre"));
        date.setCellValueFactory(new PropertyValueFactory<DemandeEmprunt,String>("date"));
        try {
            table.setItems(EmpruntDao.getDemandeEmpruntForUser(email));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    public void addDemandeEmpruntToEmprunts() throws SQLException, ClassNotFoundException {
        String id = this.id.getText();
        HashMap<String,String> emprunt = EmpruntDao.getDemandeEmpruntById(Integer.parseInt(id));
        EmpruntDao.addEmprunt(emprunt);
        this.id.setText("");
        EmpruntDao.deleteDemandeEmprunt(Integer.parseInt(id));
        table.setItems(EmpruntDao.getDemandeEmpruntForUser((String) email.getValue()));
    }

    public void deleteUtilisateur() throws SQLException, ClassNotFoundException {
        int id = UtilisateurDao.findUserId((String) this.email.getValue());
        UtilisateurDao.deleteUser(id);
        email.getItems().clear();
        email.getItems().addAll(UtilisateurDao.getEmails());
        table.setItems(FXCollections.observableArrayList());
        info_.setText("");

    }

    public void addToRedList() throws SQLException, ClassNotFoundException {
        String date1 = String.valueOf(this.date1.getValue());
        String date2 = String.valueOf(this.date2.getValue());
        String email = (String) this.email.getValue();
        int id = UtilisateurDao.findUserId(email);
        ListeRougeDao.addToRedList(new ListeRouge(id,date1,date2));
    }


}
