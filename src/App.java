package org.shashidharkumar.src;

import org.shashidharkumar.src.mapper.ProtocolMapper;
import org.shashidharkumar.src.parser.CSVParser;
import org.shashidharkumar.src.parser.FlowLogParser;
import org.shashidharkumar.src.processor.FlowLogProcessor;
import org.shashidharkumar.src.strategy.DefaultTaggingStrategy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class App {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            // Loading the properties file
            props.load(new FileInputStream("..\\Illumio-tag-network-logs\\src\\config\\config.properties"));

            // Retrieving file paths from properties
            String flowLogFile = props.getProperty("flowLogFile");
            String lookUpFile = props.getProperty("lookUpFile");
            String protocolFile = props.getProperty("protocolFile");
            String tagCountOutputFile = props.getProperty("tagCountOutputFile");
            String portProtocolTagCountOutputFile = props.getProperty("portProtocolTagCountOutputFile");

            // Initializing the objects and process the logs
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
