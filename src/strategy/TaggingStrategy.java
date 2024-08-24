package org.shashidharkumar.src.strategy;

public interface TaggingStrategy {
    String getTag(int dstPort, String protocol);
}
