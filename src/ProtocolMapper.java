package org.shashidharkumar.src;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtocolMapper {
    private final Map<String, String> protocolMap = new HashMap<>();

    public ProtocolMapper(String protocolFilePath) {
        try {
            loadProtocolMap(protocolFilePath);
        } catch (IOException e) {
            System.err.println("Error loading protocol map from file: " + protocolFilePath + "\n" + e.getMessage());
        }
    }

    private void loadProtocolMap(String protocolFilePath) throws IOException {
        CSVParser csvParser = new CSVParser();
        List<String[]> records = csvParser.parseCsvFile(protocolFilePath);
        for (String[] record : records) {
            try {
                protocolMap.put(record[0], record[1].toLowerCase());
            } catch (Exception e) {
                System.err.println("Error processing protocol mapping entry: " + String.join(",", record) +"\n" +e.getMessage());
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
