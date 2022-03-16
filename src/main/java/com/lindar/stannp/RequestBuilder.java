package com.lindar.stannp;

import com.google.gson.JsonDeserializer;
import com.lindar.wellrested.WellRestedRequest;
import com.lindar.wellrested.json.GsonJsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
public class RequestBuilder {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


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
                .jsonMapper(new GsonJsonMapper.Builder()
                        .gsonCustomiser(gson -> {
                            gson.registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (jsonElement, type, jsonDeserializationContext) -> {
                                if(jsonElement == null) return null;
                                return LocalDateTime.parse(jsonElement.getAsString(), formatter);
                            });
                        })
                        .build()
                )
                .build();
    }

    public WellRestedRequest newRequestNoBaseUrl(String path){
        return WellRestedRequest.builder()
                .url(path)
                .jsonMapper(new GsonJsonMapper.Builder()
                        .gsonCustomiser(gson -> {
                            gson.registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (jsonElement, type, jsonDeserializationContext) -> {
                                if(jsonElement == null) return null;
                                return LocalDateTime.parse(jsonElement.getAsString(), formatter);
                            });
                        })
                        .build()
                )
                .build();
    }

    public void updateRequestMap(Map<String, Object> request){
        if(forceTest) {
            log.trace("Request forced running as test");
            request.put("test", "yes");
        }

        request.put("api_key", apiKey);
    }

    public String addApiKey(String url){
        try {
            return new URIBuilder(url).addParameter("api_key", apiKey).build().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("invalid url", e);
        }
    }
}
