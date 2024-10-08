# Tag Flow Logs

## Overview
This program is designed to parse a flow log file and map each entry to one or more tags based on a lookup table. The program generates two output files: one containing the count of matches for each tag and another containing the count of matches for each port/protocol combination.

## Assumptions
- **Default Log Format**: The program only supports the default log format as provided in the problem statement. Custom log formats are not supported.
- **Log Version**: The program only supports version 2 of the log format.
- **File Locations**: The program assumes that the necessary files (flow log, lookup table, protocol mapping) are located in the project directory under the `resources/` folder. The output files will be generated in the `output/` folder.
- **CSV File Format**:
  - The lookup table, and protocol number mapping files must be in CSV format.
  - The lookup table is expected to have three columns: `dstport`, `protocol`, and `tag`.
  - The protocol number mapping file should map protocol names to their respective numbers.
- **Case Insensitivity**: The program is case-insensitive for protocol names and tags.
- **Multiple Tags**: The program supports multiple tags for a single port/protocol combination. If multiple tags are found, all tags will be counted.
- **Simpler Implementation**: The program is implemented in such a way for a simple understanding and not feature and framework heavy. 


## Instructions to Compile and Run

### The best way to run the project is to open or clone the project in popular IDE's such as *IntelliJ*, *Eclipse* etc. and run the `App.java` file.

or you can follow the below steps

1. **Clone or Download the Project**:
   - Ensure that the project directory structure remains as outlined above.

2. **Compile the Program**:
   - Navigate to the `~/src` directory.
   - Run the following command to compile the Java files:
     ```sh
     javac App.java
     ```

3. **Run the Program**:
   - After compilation, run the program using the following command:
     ```sh
     java App
     ```
   - This will generate two output files:
     - `output/tagCount.csv`: Contains the count of matches for each tag.
     - `output/portProtocolTagCount.csv`: Contains the count of matches for each port/protocol combination.

## Output Files

- **Tag Count Output (tagCount.csv)**:
  - This file contains the count of each tag found in the flow logs.
  - Example:
    ```
    Tag,Count
    sv_P2,1
    sv_P1,2
    email,3
    Untagged,8
    ```

- **Port/Protocol Combination Count Output (portProtocolTagCount.csv)**:
  - This file contains the count of each port/protocol combination found in the flow logs.
  - Example:
    ```
    Port,Protocol,Count
    22,tcp,1
    23,tcp,1
    25,tcp,1
    110,tcp,1
    443,tcp,1
    ```

## Tests Conducted

- **Case Sensitivity**: Verified that the program correctly handles protocol names and tags in a case-insensitive manner.
- **Tagging**: Tested with various combinations of flow logs and lookup tables to ensure correct tagging.
- **Edge Cases**: Handled edge cases such as empty files, missing fields, and unsupported protocols.

## Known Limitations

- **Custom Log Formats**: The program does not support custom log formats or versions other than 2.
- **Error Handling**: The program expects correctly formatted CSV files. Malformed files may cause exceptions.
- **Unsupported Protocols**: If a protocol number is not found in the protocol mapping file, the program will use "unknown" as the protocol name.

## Future Enhancements

- **Custom Log Format Support**: Extend support to handle custom log formats by adding configurable parsers.
- **Improved Error Handling**: Enhance error handling to manage malformed CSV files and missing data gracefully.
- **Additional Tagging Strategies**: Implement more flexible tagging strategies based on different business rules.

