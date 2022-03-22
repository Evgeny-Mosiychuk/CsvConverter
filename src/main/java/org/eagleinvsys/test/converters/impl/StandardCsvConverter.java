package org.eagleinvsys.test.converters.impl;

import com.opencsv.CSVWriter;
import org.eagleinvsys.test.converters.StandardConverter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.util.*;

public class StandardCsvConverter implements StandardConverter {

    private final CsvConverter csvConverter;

    public StandardCsvConverter(CsvConverter csvConverter) {
        this.csvConverter = csvConverter;
    }

    /**
     * Converts given {@link List<Map>} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format. All maps must have the same set of keys
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(List<Map<String, String>> collectionToConvert, OutputStream outputStream) {
        List<String[]> resultData = createCsvDataSpecial(collectionToConvert);

        try (CSVWriter newWriter = new CSVWriter(new OutputStreamWriter(outputStream))) {
            newWriter.writeAll(resultData, true);
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в CSV формат.");
            e.printStackTrace();
        }
    }

    private List<String[]> createCsvDataSpecial(List<Map<String, String>> collectionToConvert) {
        List<String[]> result = new ArrayList<>();
        var headers = collectionToConvert.get(0).keySet().toArray(String[]::new);
        result.add(headers);
        for (Map<String, String> map: collectionToConvert) {
            String[] data = new String[headers.length];
            int i = 0;
            for (String s: headers) {
                data[i] = map.get(s);
                i++;
            }
            result.add(data);
        }
        return result;
    }

    public static List<Map<String, String>> getStandardCollection() {
        return List.of(
                Map.of("name", "name1", "code", "code1", "fullName", "fullName1"),
                Map.of("name", "name2", "code", "code2", "fullName", "fullName2"),
                Map.of("name", "name3", "code", "code3", "fullName", "fullName3")
        );
    }
}