package com.essai3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BannedController {
    @FXML
    private AnchorPane root;
    @FXML
    private Label date;

    public void setDate(String d){
        date.setText(d);
    }
    public void close(){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}
