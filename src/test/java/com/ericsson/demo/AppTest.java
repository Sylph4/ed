package com.ericsson.demo;

import static junit.framework.TestCase.assertEquals;
import static org.apache.derby.impl.sql.compile.SQLParserConstants.FROM;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.sql.*;

/**
 *
 */
public class AppTest 
{
    /**
     * Sample Test
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void shouldInsertRightFolderValueToDB() throws SQLException {
        Database db = new Database();
        try {
            // Creates tables
            db.connectionToDerby();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        XML_Parser c = new XML_Parser();
        c.parseXMLFile("test.xml"); //reads XML file
        Connection conn;
        String dbUrl = "jdbc:derby:memory:demo;create=true";
        conn = DriverManager.getConnection(dbUrl);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM FOLDERS");
        String s = null;
        while (rs.next()) {
           s  = rs.getString("foldername");
        }
        assertEquals( "Second Folder", s);
    }

    @Test
    public void shouldInsertRightTagValueToDB() throws SQLException {
        Database db = new Database();
        try {
            // Creates tables
            db.connectionToDerby();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        XML_Parser c = new XML_Parser();
        c.parseXMLFile("test2.xml"); //reads XML file
        Connection conn;
        String dbUrl = "jdbc:derby:memory:demo;create=true";
        conn = DriverManager.getConnection(dbUrl);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TAGS");
        String s = null;
        while (rs.next()) {
            s  = rs.getString("tagname");
        }
        assertEquals( "mytag1", s);
    }






}
