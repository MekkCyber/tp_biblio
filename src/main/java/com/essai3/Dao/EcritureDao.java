package com.essai3.Dao;

import com.essai3.beans.Ecriture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EcritureDao {
    public static void addEcriture(Ecriture ecriture) throws SQLException, ClassNotFoundException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("insert into ecriture values (?,?)");
        st.setInt(1,ecriture.getAuteur_id());
        st.setInt(2,ecriture.getLivre_id());
        st.executeUpdate();
        st.executeUpdate();
        st.close();
        con.close();
    }
}
