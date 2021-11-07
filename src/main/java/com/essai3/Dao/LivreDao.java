package com.essai3.Dao;

import com.essai3.beans.Emprunt;
import com.essai3.beans.Livre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class LivreDao {

    private static String SELECT_REQ = "SELECT livre.id, titre, quantite, mots_cles, parution, nom, prenom, editeur,isbn from livre join ecriture on ecriture.livre_id = livre.id join auteur on auteur.id = ecriture.auteur_id join edition on edition.livre_id=livre.id";

    public static void addLivre(Livre livre) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("insert into livre (titre,quantite,mots_cles,parution) values (?,?,?,?)");
        st.setString(1,livre.getTitre());
        st.setInt(2,livre.getQuantite());
        st.setString(3,livre.getMots_cles());
        st.setInt(4, livre.getParution());
        st.executeUpdate();
        st.executeUpdate();
        st.close();
        con.close();
    }

    public void deleteLivre(Livre livre) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("dd.db").getConnection());
        PreparedStatement st = con.prepareStatement("delete from livre where id=?");
        st.setInt(1, livre.getId());
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

    public static ObservableList getDataForUser(String email) throws SQLException, ClassNotFoundException {
        ObservableList livres = FXCollections.observableArrayList();
        ArrayList<String> already_treated_books = new ArrayList();
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("SELECT livre.id, titre,quantite, mots_cles, parution, auteur.nom, auteur.prenom, editeur,isbn, date_emprunt, date_retour, status, emprunt_id from livre join ecriture on ecriture.livre_id = livre.id join auteur on auteur.id = ecriture.auteur_id join edition on edition.livre_id=livre.id join emprunt on emprunt.edition_id=edition.edition_id join utilisateur on utilisateur.id=emprunt.utilisateur_id where utilisateur.email=?");
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            if (!already_treated_books.contains(rs.getString("isbn"))) {
                livres.add(new Emprunt(rs.getInt("id"), rs.getString("titre"), rs.getInt("quantite"),
                        rs.getString("mots_cles"), rs.getInt("parution"), rs.getString("nom") + " " + rs.getString("prenom"),
                        rs.getString("editeur"), rs.getString("isbn"),rs.getString("emprunt_id"),0,0,rs.getString("date_emprunt"),rs.getString("date_retour"),rs.getString("status")));
                already_treated_books.add(rs.getString("isbn"));
            } else {

                Livre livre = Livre.findLivreByIsbn(rs.getString("isbn"), livres);
                if (livre != null)
                    livre.setAuteur(livre.getAuteur() + ", " + rs.getString("nom") + " " + rs.getString("prenom"));
            }
        }

//        while (rs.next()) {
//            livres.add(new Emprunt(rs.getInt("id"), rs.getString("titre"), rs.getInt("quantite"),
//                        rs.getString("mots_cles"), rs.getInt("parution"), rs.getString("nom") + " " + rs.getString("prenom"),
//                        rs.getString("editeur"), rs.getString("isbn"),3,2,1,rs.getString("date_emprunt"),rs.getString("date_retour"),rs.getString("status")));
//        }
        rs.close();
        st.close();
        con.close();
        return livres;
    }

    public static HashMap qtLivresRestants() throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        Statement st = con.createStatement();
        HashMap<Integer, Integer> qt_totale = new HashMap<Integer,Integer>();
        HashMap<Integer, Integer> qt_restante = new HashMap<Integer,Integer>();
        ResultSet rs = st.executeQuery("select livre.id, quantite from  livre");
        while(rs.next()){
            qt_totale.put(rs.getInt("id"),rs.getInt("quantite"));

        }
        rs.close();
        st.close();
        for (int livre_id : qt_totale.keySet()){
            PreparedStatement pst = con.prepareStatement("select count(*) as qt from emprunt join edition on edition.edition_id" +
                    "=emprunt.edition_id join livre on livre.id=edition.livre_id where status=\"no\" and livre_id=?");
            pst.setInt(1,livre_id);
            ResultSet rs_=pst.executeQuery();
            qt_restante.put(livre_id,qt_totale.get(livre_id)-rs_.getInt("qt"));
            rs_.close();
            pst.close();

        }
        con.close();
        return qt_restante;
    }

    public static ObservableList showQuantiteRestantes() throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        Statement st = con.createStatement();
        ObservableList livres = FXCollections.observableArrayList();
        HashMap<Integer,Integer> qt_restants = qtLivresRestants();
        ResultSet rs = st.executeQuery("select livre.id, titre from livre ");
        while (rs.next()) {
            if (qt_restants.get(rs.getInt("id")) != null){
                int qt_restant = qt_restants.get(rs.getInt("id")).intValue();
                livres.add(new Livre(rs.getInt("id"),rs.getString("titre"),qt_restant,"",0,"","",""));
            }
        }
        rs.close();
        st.close();
        con.close();
        return livres;
    }

    public static int findLivreEditionId(String isbn) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select * from edition where isbn=?");
        st.setString(1,isbn);
        ResultSet rs = st.executeQuery();
        int id = rs.getInt("edition_id");
        rs.close();
        st.close();
        con.close();
        return id;
    }

    public static ObservableList getLivresDisponibles() throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        ObservableList livre_restants = FXCollections.observableArrayList();
        HashMap<Integer,Integer> qt_restante=qtLivresRestants();
        for (int id : qt_restante.keySet()){
            if (qt_restante.get(id)>0){
                Statement st_ = con.createStatement();
                ResultSet rs = st_.executeQuery("select titre from livre where id="+id);
                livre_restants.add(rs.getString("titre"));
                rs.close();
                st_.close();
            }
        }
        con.close();
        return livre_restants;
    }

    public static ObservableList getLivresTitres() throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select titre from livre ");
        ObservableList livre_titres = FXCollections.observableArrayList();
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            livre_titres.add(rs.getString("titre"));
        }
        rs.close();
        st.close();
        con.close();
        return livre_titres;
    }
    public static ObservableList getIsbns() throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select isbn from edition join livre on livre_id=livre.id");
        ObservableList isbn = FXCollections.observableArrayList();
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            isbn.add(rs.getString("isbn"));
        }
        rs.close();
        st.close();
        con.close();
        return isbn;
    }

    public static ObservableList getLivresid() throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select id from livre ");
        ObservableList livre_ids = FXCollections.observableArrayList();
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            livre_ids.add(rs.getString("id"));
        }
        rs.close();
        st.close();
        con.close();
        return livre_ids;
    }

    public static String findLivreTitre(int id) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select * from livre where id=?");
        st.setInt(1,id);
        ResultSet rs = st.executeQuery();
        String titre = rs.getString("titre");
        rs.close();
        st.close();
        con.close();
        return titre;
    }

    public static ArrayList findEditionIdsForLivre(int id) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select edition_id from livre join edition on livre_id=livre.id where id=?");
        st.setInt(1,id);
        ArrayList<Integer> ids = new ArrayList<>();
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            ids.add(rs.getInt("edition_id"));
        }
        rs.close();
        st.close();
        con.close();
        return ids;
    }

    public static int getNumberLivres() throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select seq from sqlite_sequence where name=\"livre\"");
        ResultSet rs = st.executeQuery();
        int nombre = rs.getInt("seq");
        rs.close();
        st.close();
        con.close();
        return nombre;
    }
}
