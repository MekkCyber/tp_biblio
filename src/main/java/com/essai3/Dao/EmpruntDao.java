package com.essai3.Dao;

import com.essai3.beans.Emprunt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpruntDao {
    public static ObservableList<Emprunt> showEmprunts() throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        Statement st = con.createStatement();
        ObservableList emprunts = FXCollections.observableArrayList();
        ResultSet rs = st.executeQuery("select utilisateur.nom,utilisateur.prenom,livre.titre,isbn,date_emprunt,date_retour,status from emprunt join utilisateur on utilisateur.id=utilisateur_id join edition on edition.edition_id=emprunt.edition_id join livre on livre.id=edition.livre_id");
        while (rs.next()) {
            emprunts.add(new Emprunt(0, rs.getString("titre"),0, "", 0, rs.getString("nom") + " " + rs.getString("prenom"),
                    "", rs.getString("isbn"),0,0,0,rs.getString("date_emprunt"),rs.getString("date_retour"),rs.getString("status")));
        }
        rs.close();
        st.close();
        con.close();
        return emprunts;
    }


}
