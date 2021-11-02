package com.essai3;

import com.essai3.Dao.LibrarianDao;
import com.essai3.Dao.UtilisateurDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class HelloLibrarianController {
    @FXML
    private TextField libEmail;
    @FXML
    private TextField libPwd;
    @FXML
    private VBox root;
    public void authenticatelib() throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, IOException {
        String email = this.libEmail.getText();
        String passwdhash = this.libPwd.getText();
        if (LibrarianDao.authenticateLibrarian(email, passwdhash)!=null){
            Stage stage = (Stage) root.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("homeLibrarian.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1238, 688);
            HomeLibrarianController controller = fxmlLoader.getController();
            controller.setLibrarianHome(LibrarianDao.authenticateLibrarian(email, passwdhash).get(2));
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        } else {
            System.out.println("non");
        }
    }

    public void goback() throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 420);
        stage.setTitle("EasyBiblio");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

}
