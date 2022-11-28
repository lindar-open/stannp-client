package com.lindar.stannp.utils;

import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpEntityUtils {
    public static boolean requiresMultipart(Map<String, Object> map) {
        return map.values().stream().anyMatch(value -> value instanceof File);
    }

    public static HttpEntity buildMultipartHttpEntity(Map<String, Object> map) {
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.setMode(HttpMultipartMode.LEGACY);

        map.forEach((key, value) -> {
            if (value instanceof File) {
                entityBuilder.addBinaryBody(key, (File) value);
            } else if (value instanceof String) {
                entityBuilder.addTextBody(key, (String) value);
            } else {
                entityBuilder.addTextBody(key, String.valueOf(value));
            }
        });

        return entityBuilder.build();
    }

    public static HttpEntity buildUrlEncodedFormEntity(Map<String, Object> map) {

        List<BasicNameValuePair> mappedValues = map.entrySet().stream()
                                                   .map(entry -> new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())))
                                                   .collect(Collectors.toList());

        return new UrlEncodedFormEntity(mappedValues, Charset.forName("ISO_8859_1"));
    }
}
