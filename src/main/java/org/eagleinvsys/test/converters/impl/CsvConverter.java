package org.eagleinvsys.test.converters.impl;

import com.opencsv.CSVWriter;
import org.eagleinvsys.test.converters.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CsvConverter implements Converter {
    /**
     * Converts given {@link ConvertibleCollection} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) {
        List<String[]> resultData = createCsvDataSpecial(collectionToConvert);

        try (CSVWriter newWriter = new CSVWriter(new OutputStreamWriter(outputStream))) {
            newWriter.writeAll(resultData, true);
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в CSV формат.");
            e.printStackTrace();
        }
    }

    public List<String[]> createCsvDataSpecial(ConvertibleCollection collectionToConvert) {
        List<String[]> result = new ArrayList<>();
        var headers = collectionToConvert.getHeaders().toArray(new String[0]);
        result.add(headers);
        var records = collectionToConvert.getRecords();

        for (ConvertibleMessage message: records) {
            String[] strings = new String[headers.length];
            int i = 0;
            for (String elementId: headers) {
                strings[i] = message.getElement(elementId);
                i++;
            }
            result.add(strings);
        }
        return result;
    }

    public static ConvertibleCollection getConvertibleCollection() {
        List<String> headers = List.of("name", "code", "fullName");
        List<ConvertibleMessage> messages = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Map<String, String> map = new HashMap<>();
            for (String header: headers) {
                map.put(header, header + "-" + i);
            }
            messages.add(new ConvertibleMessageImpl(map));
        }
        return new ConvertibleCollectionImpl(headers, messages);
    }

}