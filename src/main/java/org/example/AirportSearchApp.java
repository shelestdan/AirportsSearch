package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirportSearchApp {

    private static final Logger logger = LoggerFactory.getLogger(AirportSearchApp.class);
    private final SearchAlgorithm searchAlgorithm;
    private final ResultWriter resultWriter;

    public AirportSearchApp(SearchAlgorithm searchAlgorithm, ResultWriter resultWriter) {
        this.searchAlgorithm = searchAlgorithm;
        this.resultWriter = resultWriter;
    }

    public void search(String inputFilePath) throws IOException {
        List<SearchResult> results = new ArrayList<>();
        long initTime = System.currentTimeMillis();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                long searchStartTime = System.currentTimeMillis();
                List<Integer> searchResult = searchAlgorithm.search(line.trim());
                long searchTime = System.currentTimeMillis() - searchStartTime;
                results.add(new SearchResult(line.trim(), searchResult, searchTime));
            }
        } catch (IOException e) {
            logger.error("Ошибка при чтении файла: " + inputFilePath, e);
            throw e;
        }
        initTime = System.currentTimeMillis() - initTime;
        resultWriter.writeResults(results, initTime);
    }

    public static void main(String[] args) {
        if (args.length != 8 || !args[0].equals("--data") || !args[2].equals("--indexed-column-id") ||
                !args[4].equals("--input-file") || !args[6].equals("--output-file")) {
            logger.error("Использование: java AirportSearchApp" +
                    " --data <путь к файлу данных> --indexed-column-id" +
                    " <id индексируемой колонки>" +
                    " --input-file <путь к файлу ввода> --output-file <путь к файлу вывода>");
            System.exit(1);
        }

        String dataFilePath = args[1];
        int indexedColumnId = Integer.parseInt(args[3]);
        String inputFilePath = args[5];
        String outputFilePath = args[7];

        try {
            AirportSearchApp app = new AirportSearchApp(
                    new PrefixSearch(new CSVDataReader(dataFilePath).readData(), indexedColumnId),
                    new JSONResultWriter(outputFilePath)
            );

            app.search(inputFilePath);
        }catch (IOException e) {
            logger.error("Произошла ошибка при выполнении поиска.", e);
        }
    }
}

