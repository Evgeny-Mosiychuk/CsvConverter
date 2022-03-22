package org.eagleinvsys.test.converters;

import java.util.Collection;
import java.util.List;

public class ConvertibleCollectionImpl implements ConvertibleCollection {
    public List<String> header;
    public List<ConvertibleMessage> records;

    public ConvertibleCollectionImpl(List<String> header, List<ConvertibleMessage> records) {
        this.header = header;
        this.records = records;
    }

    @Override
    public Collection<String> getHeaders() {
        return header;
    }

    @Override
    public Iterable<ConvertibleMessage> getRecords() {
        return records;
    }
}
