package com.cubewealth.notifications.utilities;

import java.io.IOException;
import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter
public class JsonToMapConverter implements AttributeConverter<Map<String, Object>, String> {
 
	private final ObjectMapper objectMapper = new ObjectMapper();
	private static final Logger logger = LoggerFactory.getLogger(JsonToMapConverter.class);
	
    @Override
    public String convertToDatabaseColumn(Map<String, Object> propertiesMap) {
 
        String propertiesString = null;
        try {
            propertiesString = objectMapper.writeValueAsString(propertiesMap);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }
 
        return propertiesString;
    }
 
    @Override
    public Map<String, Object> convertToEntityAttribute(String propertiesString) {
 
        Map<String, Object> propertiesMap = null;
        try {
            propertiesMap = objectMapper.readValue(propertiesString, new TypeReference<Map<String, Object>>() {});
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }
 
        return propertiesMap;
    }
 
}