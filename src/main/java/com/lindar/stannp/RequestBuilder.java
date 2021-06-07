package com.lindar.stannp;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.lindar.wellrested.WellRestedRequest;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
public class RequestBuilder {

    private final String endpoint;
    private final String apiKey;
    private final boolean forceTest;

    public RequestBuilder(String endpoint, String apiKey, boolean forceTest) {
        this.endpoint = endpoint;
        this.apiKey = apiKey;
        this.forceTest = forceTest;
    }

    public WellRestedRequest newRequest(String path){
        return WellRestedRequest.builder()
                .url(endpoint + path)
                .gsonCustomiser(gson -> {
                    gson.registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (jsonElement, type, jsonDeserializationContext) -> {
                        if(jsonElement == null) return null;
                        return LocalDateTime.parse(jsonElement.getAsString());
                    });
                })
                .build();
    }

    public void updateRequestMap(Map<String, Object> request){
        if(forceTest) {
            log.info("Request forced running as test");
            request.put("test", "yes");
        }

        request.put("api_key", apiKey);
    }
}
