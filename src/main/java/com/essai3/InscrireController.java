package com.essai3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Stream;

public class InscrireController implements Initializable{

    @FXML
    private ComboBox countries;
    String arr[] = {"dfsdf"};
    private ObservableList coutries_list = FXCollections.observableArrayList();



    public ObservableList readCountries() throws IOException {
//        File file = new File("@misc/countries.txt");    //creates a new file instance
//        FileReader fr = new FileReader(file);   //reads the file
//        BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
//        String line;
//        while ((line = br.readLine()) != null) {
//            coutries_list.add(line);
//        }
        coutries_list.add("Maroc");
        coutries_list.add("France");
        coutries_list.add("Espagne");
        coutries_list.add("Italie");
        coutries_list.add("Allemagne");
        coutries_list.add("Algérie");
        coutries_list.add("Tunisie");
        coutries_list.add("Andora");
        return coutries_list;
    }
    public void setData() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            countries.getItems().addAll(readCountries());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
