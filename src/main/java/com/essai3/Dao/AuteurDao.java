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
            auteurs.add(rs.getString("nom").trim()+" "+rs.getString("prenom").trim());
        }
        rs.close();
        st.close();
        con.close();
        return auteurs;
    }

    public static Auteur findAuteur(String nom_complet) throws SQLException, ClassNotFoundException {
        String[] list = nom_complet.split(" ");
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select id,date_naissance,TRIM(nom) as new_nom,TRIM(prenom) as new_prenom from auteur where new_nom=? and new_prenom=?");
        st.setString(1,list[0].trim());
        st.setString(2,list[1].trim());
        ResultSet rs = st.executeQuery();
        Auteur auteur = new Auteur(rs.getInt("id"),rs.getInt("date_naissance"),rs.getString("new_nom"),rs.getString("new_prenom"));
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

    public static void addAuteur(Auteur auteur) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("insert into auteur (date_naissance,nom,prenom) values (?,?,?)");
        st.setInt(1,auteur.getDate_naissance());
        st.setString(2,auteur.getNom());
        st.setString(3,auteur.getPrenom());
        st.executeUpdate();
        st.close();
        con.close();
    }

    public static int getNumberAuteurs() throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select seq from sqlite_sequence where name=\"auteur\"");
        ResultSet rs = st.executeQuery();
        int nombre = rs.getInt("seq");
        rs.close();
        st.close();
        con.close();
        return nombre;
    }
}
