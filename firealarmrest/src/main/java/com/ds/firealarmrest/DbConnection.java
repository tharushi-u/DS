package com.ds.firealarmrest;

import java.sql.Connection;
import java.sql.DriverManager;

/***create DB connection ***/
public class DbConnection {
    private static Connection connection;
    private DbConnection() {
    }

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/firealarmsystemdb";
        String username = "root";
        String password = "MySql@#123";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
}
