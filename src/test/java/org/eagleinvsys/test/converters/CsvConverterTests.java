package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.eagleinvsys.test.converters.impl.CsvConverter.getConvertibleCollection;
import static org.mockito.Mockito.*;

class CsvConverterTests {
    CsvConverter csvConverter = mock(CsvConverter.class);

    private static final ConvertibleCollection collection = getConvertibleCollection();

    @Test
    void testConvert() {
        try (OutputStream outputStream = new FileOutputStream("c:\\testing\\monitor1.csv")) {
            doNothing().when(csvConverter).convert(collection, outputStream);
            csvConverter.convert(collection, outputStream);
            verify(csvConverter,times(1)).convert(collection, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: implement JUnit 5 tests for CsvConverter

}