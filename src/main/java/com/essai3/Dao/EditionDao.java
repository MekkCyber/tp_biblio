package com.essai3.Dao;

import com.essai3.beans.Edition;
import com.essai3.beans.Livre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

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
        PreparedStatement st = con.prepareStatement("select livre.id,titre,isbn from livre join edition on livre_id=livre.id where editeur=?");
        st.setString(1,edition);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            livres.add(new Livre(rs.getInt("id"),rs.getString("titre"),0,"",0,"","",rs.getString("isbn")));
        }
        rs.close();
        st.close();
        con.close();
        return livres;
    }

    public static void addEdition(Edition edition) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("insert into edition (editeur,isbn,date,livre_id) values (?,?,?,?)");
        st.setString(1,edition.getEditeur());
        st.setString(2,edition.getIsbn());
        st.setInt(3,edition.getDate());
        st.setInt(4, edition.getLivre_id());
        st.executeUpdate();
        st.executeUpdate();
        st.close();
        con.close();
    }

}
