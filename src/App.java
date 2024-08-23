package org.shashidharkumar.src;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            // Load the properties file
            props.load(new FileInputStream("..\\Illumio-tag-network-logs\\src\\config\\config.properties"));

            // Retrieve file paths from properties
            String flowLogFile = props.getProperty("flowLogFile");
            String lookUpFile = props.getProperty("lookUpFile");
            String protocolFile = props.getProperty("protocolFile");
            String tagCountOutputFile = props.getProperty("tagCountOutputFile");
            String portProtocolTagCountOutputFile = props.getProperty("portProtocolTagCountOutputFile");

            // Initialize your objects and process the logs
            ProtocolMapper protocolMapper = new ProtocolMapper(new CSVParser(),protocolFile);
            FlowLogProcessor flowLogProcessor = new FlowLogProcessor(new DefaultTaggingStrategy(new CSVParser(), protocolMapper, lookUpFile));
            flowLogProcessor.processFlowLogs(flowLogFile, new FlowLogParser());
            flowLogProcessor.writeTagCountsToFile(tagCountOutputFile);
            flowLogProcessor.writePortProtocolCountsToFile(portProtocolTagCountOutputFile);

        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }
    }
}
