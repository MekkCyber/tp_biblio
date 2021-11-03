package com.essai3.Dao;

import com.essai3.beans.Livre;
import com.essai3.beans.Utilisateur;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class UtilisateurDao {

    public static ArrayList<String> authenticateUser(String email, String passwdhash) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select * from utilisateur where email=?");
        st.setString(1,email);
        ResultSet rs = st.executeQuery();
        ArrayList<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getInt("id")+"");
            list.add(rs.getString("nom"));
            list.add(rs.getString("prenom"));
            list.add(rs.getString("email"));
            list.add(rs.getString("passwdhash"));
        }
        if(list.get(4).equals(Hash.sha1(passwdhash))){
            rs.close();
            st.close();
            con.close();
            return list;
        }
        rs.close();
        st.close();
        con.close();
        return null;
    }

    public static ObservableList getUsersData() throws SQLException, ClassNotFoundException {
        ObservableList users = FXCollections.observableArrayList();
        ArrayList<String> already_treated_books = new ArrayList();
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from utilisateur");
        while (rs.next()) {
            users.add(new Utilisateur(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"), rs.getString("email"),rs.getString("passwdhash"),rs.getString("catg"), rs.getString("date_premier_emprunt") ));
        }
        rs.close();
        st.close();
        con.close();
        return users;
    }

    public static void addUtilisateur(Utilisateur user) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("insert into utilisateur (nom, prenom,email,passwdhash,catg,date_premier_emprunt) values(?,?,?,?,?,?)");
        st.setString(1,user.getNom());
        st.setString(2,user.getPrenom());
        st.setString(3,user.getEmail());
        st.setString(4,user.getPasswdhash());
        st.setString(5,user.getCatg());
        st.setString(6,user.getDate_premier_emprunt());
        st.executeUpdate();
        st.close();
        con.close();
    }

    public static int findUserId(String email) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select * from utilisateur where email=?");
        st.setString(1,email);
        ResultSet rs = st.executeQuery();
        int id = rs.getInt("id");
        rs.close();
        st.close();
        con.close();
        return id;
    }

    public static String findCatg(int id) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select * from utilisateur where id=?");
        st.setInt(1,id);
        ResultSet rs = st.executeQuery();
        String catg = rs.getString("catg");
        rs.close();
        st.close();
        con.close();
        return catg;
    }

    public static Utilisateur findInfoUser(String email) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select * from utilisateur where email=?");
        st.setString(1,email);
        ResultSet rs = st.executeQuery();
        Utilisateur user = new Utilisateur(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),
                email,"",rs.getString("catg"),rs.getString("date_premier_emprunt"));
        rs.close();
        st.close();
        con.close();
        return user;
    }

    public static ObservableList getEmails() throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        ObservableList emails = FXCollections.observableArrayList();
        PreparedStatement st = con.prepareStatement("select email from utilisateur ");
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            emails.add(rs.getString("email"));
        }
        rs.close();
        st.close();
        con.close();
        return emails;
    }

    public static ObservableList getCatgs() throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        ObservableList catgs = FXCollections.observableArrayList();
        catgs.add("student");
        catgs.add("novice_client");
        catgs.add("notclient");
        catgs.add("old_client");
        catgs.add("regular_client");
        return catgs;
    }

    public static void updateCatg(String catg , String email) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select * from utilisateur where email=?");
        st.setString(1,email);
        ResultSet rs = st.executeQuery();
        int id = rs.getInt("id");
        rs.close();
        st.close();
        PreparedStatement st_ = con.prepareStatement("update utilisateur set catg=? where id=?");
        st_.setString(1,catg);
        st_.setInt(2, id);
        st_.executeUpdate();
        st_.close();
        con.close();
    }

}
