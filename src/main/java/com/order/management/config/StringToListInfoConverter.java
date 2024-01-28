package com.order.management.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.management.model.InputListInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component
public class StringToListInfoConverter implements Converter<String, InputListInfo> {

    private final ObjectMapper objectMapper;

    @Autowired
    public StringToListInfoConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public InputListInfo convert(String source) {
        try {
            return objectMapper.readValue(URLDecoder.decode(source, StandardCharsets.UTF_8),
                    new TypeReference<>() {});
        } catch (IOException e) {
            return null;
        }
    }
}

