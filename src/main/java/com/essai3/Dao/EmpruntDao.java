package com.essai3.Dao;

import com.essai3.beans.DemandeEmprunt;
import com.essai3.beans.Emprunt;
import com.essai3.beans.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.HashMap;

public class EmpruntDao {
    public static ObservableList<Emprunt> showEmprunts() throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        Statement st = con.createStatement();
        ObservableList emprunts = FXCollections.observableArrayList();
        ResultSet rs = st.executeQuery("select utilisateur.nom,utilisateur.prenom,livre.titre,isbn,date_emprunt,date_retour,status,emprunt.emprunt_id from emprunt join utilisateur on utilisateur.id=utilisateur_id join edition on edition.edition_id=emprunt.edition_id join livre on livre.id=edition.livre_id");
        while (rs.next()) {
            emprunts.add(new Emprunt(0, rs.getString("titre"),0, "", 0, rs.getString("nom") + " " + rs.getString("prenom"),
                    "", rs.getString("isbn"),rs.getInt("emprunt_id"),0,0,rs.getString("date_emprunt"),rs.getString("date_retour"),rs.getString("status")));
        }
        rs.close();
        st.close();
        con.close();
        return emprunts;
    }

    public static void addEmprunt(HashMap<String,String> emprunt) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("insert into emprunt (utilisateur_id,edition_id,date_retour,date_emprunt,status) values(?,?,?,?,?)");
        st.setInt(1,Integer.parseInt(emprunt.get("utilisateur_id")));
        st.setInt(2,Integer.parseInt(emprunt.get("edition_id")));
        st.setString(3,emprunt.get("date_emprunt"));
        st.setString(4,emprunt.get("date_retour"));
        st.setString(5,emprunt.get("status"));
        st.executeUpdate();
        st.close();
        con.close();
    }

    public static void addDemandeEmprunt(HashMap<String,String> emprunt) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("insert into demade_emprunt (id_utilisateur,id_livre,date) values(?,?,?)");
        st.setInt(1,Integer.parseInt(emprunt.get("id_utilisateur")));
        st.setInt(2,Integer.parseInt(emprunt.get("id_livre")));
        st.setString(3,emprunt.get("date"));
        st.executeUpdate();
        st.close();
        con.close();
    }

    public static ObservableList getDemandeEmpruntForUser(String email) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select * from demade_emprunt where id_utilisateur=?");
        ObservableList demandes = FXCollections.observableArrayList();
        st.setInt(1,UtilisateurDao.findUserId(email));
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            demandes.add(new DemandeEmprunt(rs.getInt("id_demande"),rs.getInt("id_livre"),LivreDao.findLivreTitre(rs.getInt("id_livre")),rs.getString("date")));
        }
        rs.close();
        st.close();
        con.close();
        return demandes;
    }

}
