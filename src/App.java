package org.shashidharkumar.src;

import java.io.IOException;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        try {
            // Define the file paths
            String flowLogFile = "C:\\Users\\shash\\Documents\\Learning\\Spring\\Illumio-tag-network-logs\\src\\input\\flow-logs";
            String lookUpFile = "C:\\Users\\shash\\Documents\\Learning\\Spring\\Illumio-tag-network-logs\\src\\input\\look-up-table";
            String protocolFile = "C:\\Users\\shash\\Documents\\Learning\\Spring\\Illumio-tag-network-logs\\src\\input\\protocol-numbers-1.csv";
            String tagCountOutputFile = "C:\\Users\\shash\\Documents\\Learning\\Spring\\Illumio-tag-network-logs\\src\\output\\tagCount";
            String portProtocolTagCountOutputFile = "C:\\Users\\shash\\Documents\\Learning\\Spring\\Illumio-tag-network-logs\\src\\output\\portProtocolTagCount";

            // Initializing the protocol mapper for mapping protocol names with their numbers
            ProtocolMapper protocolMapper = new ProtocolMapper(protocolFile);

            // Initializing the flow log processor with the default tagging strategy assuming we might add more strategies in the future
            FlowLogProcessor flowLogProcessor = new FlowLogProcessor(new DefaultTaggingStrategy(lookUpFile, protocolMapper));

            // Processing the flow logs
            flowLogProcessor.processFlowLogs(flowLogFile);

            // Writing the results to the output files
            flowLogProcessor.writeTagCountsToFile(tagCountOutputFile);
            flowLogProcessor.writePortProtocolCountsToFile(portProtocolTagCountOutputFile);

        } catch (Exception e) {
            System.err.println("An unexpected error occurred while processing: " + e.getMessage());
        }
    }
}
