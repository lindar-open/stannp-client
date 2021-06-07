package com.lindar.stannp;

import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.lindar.stannp.model.StannpResponse;
import com.lindar.stannp.utils.HttpEntityUtils;
import org.apache.http.HttpEntity;

import java.time.LocalDateTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.Map;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

abstract class BaseResource {
    private final RequestBuilder requestBuilder;

    protected BaseResource(RequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    final protected <T extends StannpResponse> T postRequest(String url, Map<String, Object> request, TypeToken<T> typeToken){

        requestBuilder.updateRequestMap(request);

        HttpEntity entity;
        if(HttpEntityUtils.requiresMultipart(request)){
            entity = HttpEntityUtils.buildMultipartHttpEntity(request);
        } else {
            entity = HttpEntityUtils.buildUrlEncodedFormEntity(request);
        }

        return requestBuilder.newRequest(url).post()
                .httpEntity(entity)
                .submit()
                .fromJson().gsonCustomiser(gson -> {
                    gson.registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (jsonElement, type, jsonDeserializationContext) -> {
                        if(jsonElement == null) return null;
                        return LocalDateTime.parse(jsonElement.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    });
                }).castTo(typeToken);
    }
}
