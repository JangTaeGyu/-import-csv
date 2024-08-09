package com.example.study.support;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class CsvLoader {
    private final static String TARGET_PATH = "src/main/resources/csv/";

    public <T> List<T> load(String fileName, Function<CSVRecord, T> mapper) {
        List<T> resultList = new ArrayList<>();

        String fullPath = TARGET_PATH + fileName;

        try (FileReader reader = new FileReader(fullPath);
             CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT)
        ) {
            for (CSVRecord record : parser) {
                T mappedObject = mapper.apply(record);
                resultList.add(mappedObject);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }
}
