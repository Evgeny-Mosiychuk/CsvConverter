package org.eagleinvsys.test.converters;

import java.util.Map;

public class ConvertibleMessageImpl implements ConvertibleMessage{
    public Map<String, String> elements;

    public ConvertibleMessageImpl(Map<String, String> elements) {
        this.elements = elements;
    }

    @Override
    public String getElement(String elementId) {
        return elements.get(elementId);
    }
}
