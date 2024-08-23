package org.shashidharkumar.src;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultTaggingStrategy implements TaggingStrategy {
    private final Map<String, String> lookupTable;
    private final ProtocolMapper protocolMapper;
    private final FileParser fileParser;

    public DefaultTaggingStrategy(FileParser fileParser, ProtocolMapper protocolMapper, String lookupFilePath) {
        this.lookupTable = new HashMap<>();
        this.protocolMapper = protocolMapper;
        this.fileParser = fileParser;
        try {
            loadLookupTable(lookupFilePath);
        } catch (IOException e) {
            System.err.println("Error loading lookup table from file: " + lookupFilePath + "\n" + e.getMessage());
        }
    }

    private void loadLookupTable(String lookupFilePath) throws IOException {
        List<String[]> records = fileParser.parseFile(lookupFilePath);
        for (String[] record : records) {
            try {
                if (record.length < 3) {
                    throw new IOException("Malformed lookup table entry: " + String.join(",", record));
                }
                String key = record[0].toLowerCase() + "," + record[1].toLowerCase();
                lookupTable.put(key, record[2]);
            } catch (Exception e) {
                System.err.println("Error processing lookup table entry: " + String.join(",", record) + "\n" + e.getMessage());
            }
        }
    }

    @Override
    public String getTag(int dstPort, String protocolNumber) {
        String protocolName = protocolMapper.getProtocolName(protocolNumber);
        if (protocolName == null) {
            System.err.println("Warning: Protocol number " + protocolNumber + " is not recognized. Using 'unknown'.");
            protocolName = "unknown";
        }
        return lookupTable.getOrDefault(dstPort + "," + protocolName, "Untagged");
    }
}
