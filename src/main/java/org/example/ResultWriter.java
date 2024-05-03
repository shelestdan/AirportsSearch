package org.example;

import java.io.IOException;
import java.util.List;

public interface ResultWriter {
    void writeResults(List<SearchResult> results, long initTime) throws IOException;
}