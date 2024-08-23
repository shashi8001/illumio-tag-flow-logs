package org.shashidharkumar.src;

import java.util.*;

public class DefaultTaggingStrategy implements TaggingStrategy {
    private final Map<String, String> lookupTable;
    private final ProtocolMapper protocolMapper;

    public DefaultTaggingStrategy(String lookupFilePath, ProtocolMapper protocolMapper) {
        this.lookupTable = new HashMap<>();
        this.protocolMapper = protocolMapper;
        loadLookupTable(lookupFilePath);
    }

    private void loadLookupTable(String lookupFilePath) {
        CSVParser csvParser = new CSVParser();
        String delimiter = ",";
        List<String[]> records = csvParser.parseCsvFile(lookupFilePath,delimiter);
        for (String[] record : records) {
            String key = record[0].toLowerCase() + "," + record[1].toLowerCase();
            lookupTable.put(key, record[2]);
        }
    }

    @Override
    public String getTag(int dstPort, String protocolNumber) {
        String protocolName = protocolMapper.getProtocolName(protocolNumber);
        if (protocolName == null) {
            protocolName = "unknown";
        }
        return lookupTable.getOrDefault(String.valueOf(dstPort)+ "," + protocolName, "Untagged");
    }
}
