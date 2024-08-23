package org.shashidharkumar.src;

public interface TaggingStrategy {
    String getTag(int dstPort, String protocol);
}
