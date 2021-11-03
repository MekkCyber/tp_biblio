package com.essai3;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class IncorrectController {
    @FXML
    private  AnchorPane root;

    public void close(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}
