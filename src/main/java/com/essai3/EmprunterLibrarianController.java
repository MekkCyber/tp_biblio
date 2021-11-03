package com.essai3;

import com.essai3.Dao.EmpruntDao;
import com.essai3.Dao.LivreDao;
import com.essai3.Dao.UtilisateurDao;
import com.essai3.beans.Livre;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EmprunterLibrarianController implements Initializable {
    @FXML
    private BorderPane root;
    @FXML
    private TableView<Livre> table;
    @FXML
    private TableColumn<Livre,Integer> id;
    @FXML
    private TableColumn<Livre,String> titre;
    @FXML
    private TableColumn<Livre,Integer> qt_dispo;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom_;
    @FXML
    private TextField prenom_;
    @FXML
    private ComboBox isbn;
    @FXML
    private ComboBox isbn_;
    @FXML
    private ComboBox livre;
    @FXML
    private ComboBox livre_;

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

    public void getUsers() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UsersFromLibrarianHome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1238, 688);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("id"));
        titre.setCellValueFactory(new PropertyValueFactory<Livre,String>("titre"));
        qt_dispo.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("quantite"));

        try {
            table.setItems(LivreDao.showQuantiteRestantes());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            livre.getItems().addAll(LivreDao.getLivresDisponibles());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            livre_.getItems().addAll(LivreDao.getLivresTitres());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            isbn.getItems().addAll(LivreDao.getIsbns());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            isbn_.getItems().addAll(LivreDao.getIsbns());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public HashMap getEmpruntAdded() throws SQLException, ClassNotFoundException {
        HashMap<String,String> emprunt = new HashMap<>();
        String nom = this.nom.getText();
        String prenom = this.prenom.getText();
        String isbn = (String) this.isbn.getValue();
        String email = nom+"@gmail.com";
        int user_id= UtilisateurDao.findUserId(email);
        int edition_id=LivreDao.findLivreEditionId(isbn);
        Date date_emprunt = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date_emprunt);
        String catg = UtilisateurDao.findCatg(user_id);
        switch (catg){
            case "student":
                c.add(Calendar.MONTH,2);
            case "novice_client" :
                c.add(Calendar.MONTH,1);
            case "regular_client" :
                c.add(Calendar.MONTH,2);
            case "old_client" :
                c.add(Calendar.MONTH,3);
            case "notclient" :
                c.add(Calendar.DATE,15);

        }
        Date date_retour = c.getTime();
        emprunt.put("utilisateur_id",user_id+"");
        emprunt.put("edition_id",edition_id+"");
        emprunt.put("date_emprunt",dateFormat.format(date_emprunt));
        emprunt.put("date_retour",dateFormat.format(date_retour));
        emprunt.put("status","no");
        return emprunt;
    }


    public void addInDb() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        EmpruntDao.addEmprunt(getEmpruntAdded());
    }

    public void returnLivre(){

    }
}
