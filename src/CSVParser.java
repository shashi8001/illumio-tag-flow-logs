package org.shashidharkumar.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    // Default delimiter value
    private static final String DEFAULT_DELIMITER = ",";

    // Method with specified delimiter
    public List<String[]> parseCsvFile(String filePath, String delimiter) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    if (line.trim().isEmpty()) {
                        continue; // Skipping the empty lines
                    }
                    String[] values = line.split(delimiter);
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

    public List<String[]> parseCsvFile(String filePath) {
        return parseCsvFile(filePath, DEFAULT_DELIMITER);
    }
}
