package com.essai3.Dao;

import com.essai3.beans.Ecriture;
import com.essai3.beans.ListeRouge;

import java.sql.ResultSet;
import java.util.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ListeRougeDao {
    public static void addToRedList(ListeRouge list) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("insert into liste_rouge values (?,?,?)");
        st.setInt(1,list.getUser_id());
        st.setString(2,list.getDate_debut());
        st.setString(3,list.getDate_fin());
        st.executeUpdate();
        st.close();
        con.close();
    }
    public static Boolean userBanned(String email) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select * from liste_rouge where utilisateur_id=? and date_début<=? and date_fin>=?");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        st.setInt(1,UtilisateurDao.findUserId(email));
        st.setString(2,dateFormat.format(new Date()));
        st.setString(3,dateFormat.format(new Date()));
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            rs.close();
            st.close();
            con.close();
            return true;

        }
        rs.close();
        st.close();
        con.close();
        return false;
    }

    public static String dateFin(String email) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select * from liste_rouge where utilisateur_id=? and date_début<=? and date_fin>=?");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        st.setInt(1, UtilisateurDao.findUserId(email));
        st.setString(2, dateFormat.format(new Date()));
        st.setString(3, dateFormat.format(new Date()));
        ResultSet rs = st.executeQuery();
        String date_fin = "";
        if (rs.next()) {
            date_fin = rs.getString("date_fin");
        }
        rs.close();
        st.close();
        con.close();
        return date_fin;
    }
    public static String dateDebut(String email) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select * from liste_rouge where utilisateur_id=? and date_début<=? and date_fin>=?");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        st.setInt(1, UtilisateurDao.findUserId(email));
        st.setString(2, dateFormat.format(new Date()));
        st.setString(3, dateFormat.format(new Date()));
        ResultSet rs = st.executeQuery();
        String date = "";
        if (rs.next()) {
            date = rs.getString("date_début");
        }
        rs.close();
        st.close();
        con.close();
        return date;
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(dateFin("veronica@gmail.com"));
        System.out.println(dateFin("sara@gmail.com"));
    }
}
