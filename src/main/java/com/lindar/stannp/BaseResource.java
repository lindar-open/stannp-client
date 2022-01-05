package com.lindar.stannp;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.reflect.TypeToken;
import com.lindar.stannp.model.StannpResponse;
import com.lindar.stannp.utils.HttpEntityUtils;
import com.lindar.wellrested.vo.WellRestedResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract class BaseResource {
    public static final String X_IDEMPOTENCY_KEY = "X-Idempotency-Key";
    private final RequestBuilder requestBuilder;

    protected BaseResource(RequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    final protected <T> StannpResponse<T> postRequest(String url, Map<String, Object> request, TypeToken<StannpResponse<T>> typeToken) {
        return postRequest(url, request, typeToken, null);
    }

    final protected <T> StannpResponse<T> postRequest(String url, Map<String, Object> request, TypeToken<StannpResponse<T>> typeToken, String idempotencyKey) {

        requestBuilder.updateRequestMap(request);

        HttpEntity entity;
        if (HttpEntityUtils.requiresMultipart(request)) {
            entity = HttpEntityUtils.buildMultipartHttpEntity(request);
        } else {
            entity = HttpEntityUtils.buildUrlEncodedFormEntity(request);
        }

        List<Header> headers = new ArrayList<>();
        if (StringUtils.isNotEmpty(idempotencyKey)) {
            headers.add(idempotencyKeyHeader(idempotencyKey));
        }

        WellRestedResponse response = requestBuilder.newRequest(url).post()
                .httpEntity(entity)
                .headers(headers)
                .submit();

        if (response.isClientTimeout() || response.isConnectionTimeout() || response.isSocketTimeout()) {
            return StannpResponse.timeout();
        }

        return response
                .fromJson().castTo(typeToken);
    }

    private static void gsonCustomiser(GsonBuilder gson) {
        gson.registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (jsonElement, type, jsonDeserializationContext) -> {
            if (jsonElement == null) return null;
            return LocalDateTime.parse(jsonElement.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        });
    }

    private Header idempotencyKeyHeader(String idempotencyKey) {
        return new BasicHeader(X_IDEMPOTENCY_KEY, idempotencyKey);
    }
}
