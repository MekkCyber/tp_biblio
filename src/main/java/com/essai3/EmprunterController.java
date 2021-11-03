package com.essai3;

import com.essai3.Dao.EmpruntDao;
import com.essai3.Dao.LivreDao;
import com.essai3.Dao.UtilisateurDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EmprunterController implements Initializable {
    @FXML
    private VBox root;
    @FXML
    private ComboBox titre;
    @FXML
    private TextField email;
    @FXML
    private ComboBox id;



    public void annulerEmprunt(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            titre.getItems().addAll(LivreDao.getLivresDisponibles());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            id.getItems().addAll(LivreDao.getLivresid());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void demanderEmprunt() throws SQLException, ClassNotFoundException {
        String titre = (String) this.titre.getValue();
        String email = this.email.getText();
        int id = (int) this.id.getValue();
        Date date_demande = new Date();
        HashMap<String,String> demade_emprunt = new HashMap<>();
        demade_emprunt.put("id_utilisateur", UtilisateurDao.findUserId(email) +"");
        demade_emprunt.put("id_livre",id+"");
        demade_emprunt.put("date",date_demande.toString());
        EmpruntDao.addDemandeEmprunt(demade_emprunt);

    }
}
