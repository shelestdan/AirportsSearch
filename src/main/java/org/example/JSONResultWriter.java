package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class JSONResultWriter implements ResultWriter {
    private final String filePath;

    public JSONResultWriter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void writeResults(List<SearchResult> results, long initTime) throws IOException {
        JSONObject jsonObject = new JSONObject();
        JSONArray resultsArray = new JSONArray();

        for (SearchResult result : results) {
            JSONObject resultObject = new JSONObject();
            List<Integer> sortedResult = new ArrayList<>(result.getResult());
            sortedResult.sort(null);
            resultObject.put("search", result.getSearch());
            resultObject.put("result", sortedResult);
            resultObject.put("time", result.getTime());
            resultsArray.put(resultObject);
        }

        jsonObject.put("initTime", initTime);
        jsonObject.put("result", resultsArray);

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonObject.toString(4));
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + filePath);
            throw e;
        }
    }
}
