package com.example.springbootserver;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.*;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        if (list == null || list.isEmpty()) return "[]";
        return "[\"" + String.join("\",\"", list) + "\"]";
    }

    @Override
    public List<String> convertToEntityAttribute(String value) {

        if (value == null || value.isBlank() || value.equals("[]"))
            return new ArrayList<>();

        return Arrays.stream(
                        value.replace("[", "")
                                .replace("]", "")
                                .replace("\"", "")
                                .replace("'", "")
                                .split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toList();
    }
}
