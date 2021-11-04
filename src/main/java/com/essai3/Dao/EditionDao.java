package com.essai3.Dao;

import com.essai3.beans.Livre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditionDao {

    public static ObservableList getEditions() throws SQLException, ClassNotFoundException {
        ObservableList editions = FXCollections.observableArrayList();
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select editeur from edition");
        while (rs.next()) {
            editions.add(rs.getString("editeur"));
        }
        rs.close();
        st.close();
        con.close();
        return editions;
    }
    public static ObservableList getLivresForEdition(String edition) throws SQLException, ClassNotFoundException {
        ObservableList livres = FXCollections.observableArrayList();
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select livre.id,titre,isbn from livre join edition on livre_id=livre.id where editeur="+edition);
        while (rs.next()) {
            livres.add(new Livre(rs.getInt("id"),rs.getString("titre"),0,"",0,"","",rs.getString("isbn")));
        }
        rs.close();
        st.close();
        con.close();
        return livres;
    }

}
