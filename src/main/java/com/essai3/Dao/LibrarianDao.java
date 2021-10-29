package com.essai3.Dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibrarianDao {
    public static ArrayList<String> authenticateLibrarian(String email, String passwdhash) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
        Connection con = (new Db("jdbc:sqlite:D:\\Coding\\Projets\\java\\tp\\Essai3\\src\\main\\java\\com\\essai3\\Dao\\biblio.db").getConnection());
        PreparedStatement st = con.prepareStatement("select * from librarian where email=?");
        st.setString(1,email);
        ResultSet rs = st.executeQuery();
        ArrayList<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getInt("id")+"");
            list.add(rs.getString("nom"));
            list.add(rs.getString("prenom"));
            list.add(rs.getString("passdhash"));
        }
        if(list.get(3).equals(Hash.sha1(passwdhash))){
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
}

