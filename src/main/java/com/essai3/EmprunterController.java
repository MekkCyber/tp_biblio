package com.essai3;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmprunterController {
    @FXML
    private VBox root;

    public void annulerEmprunt(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

}
