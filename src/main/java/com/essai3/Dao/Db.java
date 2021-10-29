package com.essai3.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private String DBPath;
    private Connection connection = null;

    public Db(String DBPath) {
        this.DBPath=DBPath;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(DBPath);
        return connection;
    }

    public void close() throws SQLException {
        connection.close();
    }
}
