package org.shashidharkumar.src.mapper;

import org.shashidharkumar.src.parser.FileParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtocolMapper {
    private final Map<String, String> protocolMap = new HashMap<>();
    private final FileParser fileParser;

    public ProtocolMapper(FileParser fileParser, String protocolFilePath) {
        this.fileParser = fileParser;
        try {
            loadProtocolMap(protocolFilePath);
        } catch (IOException e) {
            System.err.println("Error loading protocol map from file: " + protocolFilePath + "\n" + e.getMessage());
            throw new RuntimeException("Failed to load protocol map", e); // Re-throw as runtime exception
        }
    }

    private void loadProtocolMap(String protocolFilePath) throws IOException {
        List<String[]> records = fileParser.parseFile(protocolFilePath);
        for (String[] record : records) {
            if (record.length < 2) {
                System.err.println("Malformed protocol mapping entry: " + String.join(",", record));
                continue; // Skip malformed records
            }
            try {
                protocolMap.put(record[0].toLowerCase(), record[1].toLowerCase());
            } catch (Exception e) {
                System.err.println("Error processing protocol mapping entry: " + String.join(",", record) + "\n" + e.getMessage());
            }
        }
    }

    public String getProtocolName(String protocolNumber) {
        String protocolName = protocolMap.get(protocolNumber.toLowerCase());
        if (protocolName == null) {
            System.err.println("Warning: Protocol number " + protocolNumber + " not found in the protocol map.");
        }
        return protocolName;
    }
}
