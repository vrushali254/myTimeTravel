package org.records.models.converters;


import jakarta.persistence.*;
import lombok.SneakyThrows;
import org.springframework.boot.configurationprocessor.json.JSONObject;


@Converter
public class JSONObjectConverter implements AttributeConverter<JSONObject, String> {
    @Override
    public String convertToDatabaseColumn(JSONObject jsonData) {
        return jsonData.toString();
    }

    @SneakyThrows
    @Override
    public JSONObject convertToEntityAttribute(String jsonDataAsJson) {
        return new JSONObject(jsonDataAsJson);
    }
}
