package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import static org.eagleinvsys.test.converters.impl.StandardCsvConverter.getStandardCollection;
import static org.mockito.Mockito.*;

class StandardCsvConverterTests {

    StandardCsvConverter standardCsvConverter = mock(StandardCsvConverter.class);

    private static final List<Map<String,String>> standardCollection = getStandardCollection();

    @Test
    void testConvert() {
        try (OutputStream outputStream = new FileOutputStream("c:\\testing\\monitor2.csv")) {
            doNothing().when(standardCsvConverter).convert(standardCollection, outputStream);
            standardCsvConverter.convert(standardCollection, outputStream);
            verify(standardCsvConverter,times(1)).convert(standardCollection, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // TODO: implement JUnit 5 tests for StandardCsvConverter

}