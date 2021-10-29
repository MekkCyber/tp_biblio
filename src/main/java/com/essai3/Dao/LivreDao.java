package com.essai3.Dao;

import com.essai3.beans.Livre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class LivreDao {

    private static String SELECT_REQ = "SELECT livre.id, titre, quantite, mots_cles, parution, nom, prenom, editeur,isbn from livre join ecriture on ecriture.livre_id = livre.id join auteur on auteur.id = ecriture.auteur_id join edition on edition.livre_id=livre.id" ;

    public static void addLivre() throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
//        PreparedStatement st = con.prepareStatement("insert into livre (titre,quantite,mots_cles,parution) values (?,?,?,?)");
//        st.setString(1,livre.getTitre());
//        st.setInt(2,livre.getQuantite());
//        st.setString(3,livre.getMots_cles());
//        st.setInt(4, livre.getParution());
//        st.executeUpdate();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from livre");
        while (rs.next()) {
            System.out.println(rs.getString("titre"));
        }
        rs.close();
        st.close();
        con.close();
    }

    public void deleteLivre(Livre livre) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("dd.db").getConnection());
        PreparedStatement st = con.prepareStatement("delete from livre where id=?");
        st.setInt(1,livre.getId());
        st.executeUpdate();
        st.close();
        con.close();
    }

    public static ObservableList getData() throws SQLException, ClassNotFoundException {
        ObservableList livres = FXCollections.observableArrayList();
        ArrayList<String> already_treated_books = new ArrayList();
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(SELECT_REQ);
        while (rs.next()) {
            if (!already_treated_books.contains(rs.getString("isbn"))) {
                livres.add(new Livre(rs.getInt("id"), rs.getString("titre"), rs.getInt("quantite"),
                        rs.getString("mots_cles"), rs.getInt("parution"), rs.getString("nom") + " " + rs.getString("prenom"),
                        rs.getString("editeur"), rs.getString("isbn")));
                already_treated_books.add(rs.getString("isbn"));

            } else {
                Livre livre = Livre.findLivreByIsbn(rs.getString("isbn"), livres);
                if (livre != null)
                    livre.setAuteur(livre.getAuteur() + ", " + rs.getString("nom") + " " + rs.getString("prenom"));
            }
        }
        rs.close();
        st.close();
        con.close();
        return livres;
    }

//    public static  String findLivreByIsbn(String isbn) throws SQLException, ClassNotFoundException {
//        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
//        String titre = null;
//        PreparedStatement st= con.prepareStatement("select titre from livre join edition on livre_id=livre.id where isbn=?");
//        st.setString(1,isbn);
//        ResultSet rs=st.executeQuery();
//        while (rs.next()){
//            titre = rs.getString("titre");
//        }
//        return titre;
//    }
}
