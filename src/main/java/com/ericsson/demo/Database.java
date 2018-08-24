package com.ericsson.demo;

import java.sql.*;

public class Database {

    Connection conn;
    String dbUrl = "jdbc:derby:memory:demo;create=true"; //in-memory DB

    public void connectionToDerby() throws SQLException {

        conn = DriverManager.getConnection(dbUrl);
        Statement stmt = conn.createStatement();

        // create tables
        stmt.executeUpdate("Create table FOLDERS (id int GENERATED ALWAYS AS IDENTITY not null primary key, foldername varchar(50))");
        stmt.executeUpdate("Create table TAGS (id int GENERATED ALWAYS AS IDENTITY not null primary key, tagname varchar(50))");
    }

    public void DbUsage(String query) throws SQLException {

        conn = DriverManager.getConnection(dbUrl);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
    }

    public void DbPrint(String query) throws SQLException {

        conn = DriverManager.getConnection(dbUrl);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
           System.out.print("["+rs.getInt("id")+"]");
        }
    }
}
