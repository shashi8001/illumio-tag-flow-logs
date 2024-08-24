package org.shashidharkumar.src.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser implements Parser {

    @Override
    public List<String[]> parseFile(String filePath){
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    if (line.trim().isEmpty()) {
                        continue; // Skip empty lines
                    }
                    String[] values = line.split(","); // Use comma as default separator for CSV
                    records.add(values);
                } catch (Exception e) {
                    System.err.println("Error processing line: " + line + "\n" + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath + "\n" + e.getMessage());
        }
        return records;
    }
}
