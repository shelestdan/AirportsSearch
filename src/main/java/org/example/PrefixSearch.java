package org.example;

import java.util.ArrayList;
import java.util.List;

public class PrefixSearch implements SearchAlgorithm {
    private final List<String[]> data;
    private final int indexedColumnId;

    public PrefixSearch(List<String[]> data, int indexedColumnId) {
        this.data = data;
        this.indexedColumnId = indexedColumnId;
    }

    @Override
    public List<Integer> search(String query) {
        List<Integer> result = new ArrayList<>();
        for (String[] row : data) {
            if (row.length > indexedColumnId && row[indexedColumnId - 1].startsWith(query)) {
                try {
                    result.add(Integer.parseInt(row[0]));
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка при преобразовании строки в число: " + row[0]);
                }
            }
        }
        return result;
    }
}
