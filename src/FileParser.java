package org.shashidharkumar.src;

import java.io.IOException;
import java.util.List;

public interface FileParser {
    List<String[]> parseFile(String filePath) throws IOException;
}
