package org.shashidharkumar.src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlowLogProcessor {
    private final TaggingStrategy taggingStrategy;
    private final Map<String, Integer> tagCounts = new HashMap<>();
    private final Map<String, Integer> portProtocolCounts = new HashMap<>();

    public FlowLogProcessor(TaggingStrategy taggingStrategy) {
        this.taggingStrategy = taggingStrategy;
    }

    public void processFlowLogs(String flowLogFile) {
        CSVParser csvParser = new CSVParser();
        String delimiter = " ";

        List<String[]> flowLogs = csvParser.parseCsvFile(flowLogFile, delimiter);

        for (String[] log : flowLogs) {
            try {
                if (log.length < 8) {
                    throw new IOException("Malformed log entry or Not a Default log format: " + String.join(" ", log));
                }

                int dstPort = Integer.parseInt(log[6]);
                String protocol = log[7];

                String tag = taggingStrategy.getTag(dstPort, protocol);

                tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
                portProtocolCounts.put(dstPort + "," + protocol, portProtocolCounts.getOrDefault(dstPort + "," + protocol, 0) + 1);

            }
            catch (Exception e) {
                System.err.println("Error processing log entry: " + String.join(" ", log) + "\n" + e.getMessage());
            }
        }
    }

    public void writeTagCountsToFile(String outputFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write("Tag,Count\n");
            for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + outputFilePath + "\n" + e.getMessage());
        }
    }

    public void writePortProtocolCountsToFile(String outputFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write("Port,Protocol,Count\n");
            for (Map.Entry<String, Integer> entry : portProtocolCounts.entrySet()) {
                String[] keyParts = entry.getKey().split(",");
                writer.write(keyParts[0] + "," + keyParts[1] + "," + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + outputFilePath + "\n" + e.getMessage());
        }
    }
}
