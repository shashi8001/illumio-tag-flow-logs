package org.shashidharkumar.src.parser;

import java.io.IOException;
import java.util.List;

public interface Parser {
    List<String[]> parseFile(String filePath) throws IOException;
}
