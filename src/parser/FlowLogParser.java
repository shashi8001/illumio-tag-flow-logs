package org.shashidharkumar.src.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlowLogParser implements FileParser {

    @Override
    public List<String[]> parseFile(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }
                // Assume flow log entries are space-separated
                String[] values = line.split("\\s+");
                records.add(values);
            }
        } catch (IOException e) {
            System.err.println("Error reading flow log file: " + filePath + "\n" +e.getMessage());
        }
        return records;
    }
}
