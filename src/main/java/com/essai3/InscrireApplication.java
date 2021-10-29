package com.essai3;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class InscrireApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("inscrire.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 370);
        stage.setTitle("EasyBiblio");
        stage.setScene(scene);
        InscrireController myController = new InscrireController();
        myController.setData();
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}