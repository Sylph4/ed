/*
 * Aleksey S. Copyright (c) 2018.
 */

package com.ericsson.demo;


import java.sql.SQLException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {

        System.out.println("Enter input xml file path (leave empty for default data.xml one): ");
        Scanner user = new Scanner(System.in);
        String inputFileName = user.nextLine().trim();
        if (inputFileName.isEmpty()) {
            inputFileName = "data.xml";
        }

        Database db = new Database();
        try {
            // Creates tables
            db.connectionToDerby();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        XML_Parser c = new XML_Parser();
        c.parseXMLFile(inputFileName); //reads XML file

        TXT_Parser t = new TXT_Parser();
        t.readTXTFile("items.txt"); //reads TXT file
    }
}






