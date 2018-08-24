package com.ericsson.demo;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.sql.SQLException;

public class XML_Parser {

    public void parseXMLFile(String fileName) {
        final Database app;
        app = new Database();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                // Parser found starting tag
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    // TAG <folder> found
                    if (qName.equalsIgnoreCase("folder")) {
                        String value = attributes.getValue("name");
                        if (value.length() <= 50) {
                            try {
                                //insert to DB
                                app.DbUsage("INSERT INTO FOLDERS(foldername) VALUES ('" + value + "')");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    // TAG <tag> found
                    if (qName.equalsIgnoreCase("tag")) {
                        String value = attributes.getValue("name");
                        if (value.length() <= 50) {
                            try {
                                //insert to DB
                                app.DbUsage("INSERT INTO TAGS(tagname) VALUES ('" + value + "')");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            };

            // XML file parsing start
            try {
                saxParser.parse(fileName, handler);
            } catch (IOException ex) {
                System.out.println("Could not find file " + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}






