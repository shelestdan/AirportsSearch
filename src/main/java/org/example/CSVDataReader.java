package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataReader {
    private final String filePath;

    public CSVDataReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String[]> readData() throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replace("\"", "");
                data.add(line.split(","));
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + filePath);
            throw e;
        }
        return data;
    }
}
