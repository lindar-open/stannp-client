package com.lindar.stannp.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StannpDateJsonDeserializer implements JsonDeserializer<LocalDateTime> {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DateTimeFormatter zonedFormatter = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if(json == null) return null;

        try {
            return LocalDateTime.parse(json.getAsString(), formatter);
        } catch (DateTimeParseException e) {
            return ZonedDateTime.parse(json.getAsString(), zonedFormatter).toLocalDateTime();
        }
    }
}
