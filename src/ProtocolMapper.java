package org.shashidharkumar.src;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtocolMapper {
    private final Map<String, String> protocolMap = new HashMap<>();

    public ProtocolMapper(String protocolFilePath) {
        loadProtocolMap(protocolFilePath);
    }

    private void loadProtocolMap(String protocolFilePath) {
        CSVParser csvParser = new CSVParser();
        String delimiter = ",";
        List<String[]> records = csvParser.parseCsvFile(protocolFilePath, delimiter);
        for (String[] record : records) {
            protocolMap.put(record[0], record[1].toLowerCase());
        }
    }

    public String getProtocolName(String protocolNumber) {
        return protocolMap.get(protocolNumber.toLowerCase());
    }
}

