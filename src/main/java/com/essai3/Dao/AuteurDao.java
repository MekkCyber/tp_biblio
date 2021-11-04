package com.essai3.Dao;

import com.essai3.beans.Auteur;
import com.essai3.beans.Livre;
import com.essai3.beans.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class AuteurDao {

    public static ObservableList getAuteurs() throws SQLException, ClassNotFoundException {
        ObservableList auteurs = FXCollections.observableArrayList();
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select nom,prenom from auteur");
        while (rs.next()) {
            auteurs.add(rs.getString("nom")+" "+rs.getString("prenom"));
        }
        rs.close();
        st.close();
        con.close();
        return auteurs;
    }

    public static Auteur findAuteur(String nom_complet) throws SQLException, ClassNotFoundException {
        String[] list = nom_complet.split(" ");
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select * from auteur where nom=? and prenom=?");
        st.setString(1,list[0].trim());
        st.setString(2,list[1].trim());
        ResultSet rs = st.executeQuery();
        Auteur auteur = new Auteur(0,0,"","");
        auteur = new Auteur(rs.getInt("id"),rs.getInt("date_naissance"),rs.getString("nom"),rs.getString("prenom"));
        System.out.println(rs.getInt("nom"));
        rs.close();
        st.close();
        con.close();
        return auteur;
    }

    public static ObservableList getLivresForAuteur(int id) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select livre.id,titre from livre join ecriture on livre_id=livre.id join" +
                " auteur on auteur.id=auteur_id where auteur.id=?");
        st.setInt(1,id);
        ResultSet rs = st.executeQuery();
        ObservableList livres = FXCollections.observableArrayList();
        while(rs.next()){
            livres.add(new Livre(rs.getInt("id"),rs.getString("titre"),0,"",0,"","",""));
        }
        rs.close();
        st.close();
        con.close();
        return livres;
    }
}
