package com.lindar.stannp.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpEntityUtils {
    public static boolean requiresMultipart(Map<String, Object> map){
        return map.values().stream().anyMatch(value -> value instanceof File);
    }

    public static HttpEntity buildMultipartHttpEntity(Map<String, Object> map){
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        map.forEach((key, value) -> {
            if(value instanceof File) {
                entityBuilder.addBinaryBody(key, (File) value);
            } else if(value instanceof String){
                entityBuilder.addTextBody(key, (String) value);
            } else {
                entityBuilder.addTextBody(key, String.valueOf(value));
            }
        });

        return entityBuilder.build();
    }

    public static HttpEntity buildUrlEncodedFormEntity(Map<String, Object> map){

        List<BasicNameValuePair> mappedValues = map.entrySet().stream()
                .map(entry -> new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())))
                .collect(Collectors.toList());

        return new UrlEncodedFormEntity(mappedValues, Consts.ISO_8859_1);
    }
}
