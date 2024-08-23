package org.shashidharkumar.src;

public class Main {
    public static void main(String[] args) {

        //The file path have to be updated based on the input files location
        String flowLogFile = "C:\\Users\\shash\\Documents\\Learning\\Spring\\Illumio-tag-network-logs\\src\\input\\flow-logs";
        String lookUpFile = "C:\\Users\\shash\\Documents\\Learning\\Spring\\Illumio-tag-network-logs\\src\\input\\look-up-table";
        String protocolFile = "C:\\Users\\shash\\Documents\\Learning\\Spring\\Illumio-tag-network-logs\\src\\input\\protocol-numbers-1.csv";
        String tagCountOutputFile = "C:\\Users\\shash\\Documents\\Learning\\Spring\\Illumio-tag-network-logs\\src\\output\\tagCount";
        String portProtocolTagCountOutputFile = "C:\\Users\\shash\\Documents\\Learning\\Spring\\Illumio-tag-network-logs\\src\\output\\portProtocolTagCount";

        ProtocolMapper protocolMapper = new ProtocolMapper(protocolFile);

        FlowLogProcessor flowLogProcessor = new FlowLogProcessor(new DefaultTaggingStrategy(lookUpFile,protocolMapper));

        flowLogProcessor.processFlowLogs(flowLogFile);

        flowLogProcessor.writeTagCountsToFile(tagCountOutputFile);

        flowLogProcessor.writePortProtocolCountsToFile(portProtocolTagCountOutputFile);




    }
}