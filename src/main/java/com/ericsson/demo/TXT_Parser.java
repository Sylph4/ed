package com.ericsson.demo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TXT_Parser {

    public void readTXTFile(String fileName) {

        List<String> lines;
        {
            try {
                lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
                for (String s : lines) {
                    parse(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parse(String s) {

        List<String> tags = new LinkedList<String>();

        //Split by : symbol
        String[] parts = s.split("\\:");
        String folderName = parts[0];

        //Find all @tags
        Pattern p = Pattern.compile("@\\w+");
        Matcher m1 = p.matcher(parts[1]);
        while (m1.find()) {
            tags.add(m1.group());
        }

        //removes @tags, trims multiple spaces, result -> itemName
        String itemName = parts[1].replaceAll("@\\w+", "").replaceAll(" +", " ").trim();

        output(folderName, itemName, tags);
    }

    private void output(String folderName, String itemName, List<String> tags) {
        Database db;
        db = new Database();

        //Foldername
        try {
            System.out.print(folderName);
            db.DbPrint("SELECT id FROM FOLDERS WHERE '" + folderName + "'=foldername");
            System.out.print(": " + itemName + " ");
        } catch (SQLException e) {
            System.out.println("Folders Query error");
        }

        //Tagnames
        tags.forEach(
                tag -> {
                    try {
                        System.out.print(tag);
                        db.DbPrint("SELECT id FROM TAGS WHERE '" + tag.substring(1) + "'=tagname");
                        System.out.print(", ");
                    } catch (SQLException e) {
                        System.out.println("Tags Query error");
                    }
                });

        //Just to remove last coma
        System.out.print('\b');
        System.out.println('\b');
    }
}


