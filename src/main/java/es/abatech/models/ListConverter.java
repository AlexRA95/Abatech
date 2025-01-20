package es.abatech.models;

import org.apache.commons.beanutils.Converter;

import java.util.ArrayList;
import java.util.List;

public class ListConverter implements Converter {
    @Override
    public <T> T convert(Class<T> type, Object value) {
        if (value == null) {
            return (T) new ArrayList<>();
        }
        if (value instanceof String) {
            List<String> list = new ArrayList<>();
            if (!((String) value).isEmpty()) {
                list.add((String) value);
            }
            return (T) list;
        }
        if (value instanceof String[]) {
            String[] values = (String[]) value;
            List<String> list = new ArrayList<>();
            for (String val : values) {
                if (val != null && !val.isEmpty()) {
                    list.add(val);
                }
            }
            return (T) list;
        }
        throw new IllegalArgumentException("Unsupported type: " + value.getClass());
    }
}