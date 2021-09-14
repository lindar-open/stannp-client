package com.lindar.stannp;

import com.google.gson.reflect.TypeToken;
import com.lindar.stannp.model.StannpHelper;
import com.lindar.stannp.model.StannpResponse;
import com.lindar.stannp.model.request.CreateBatchLetterRequest;
import com.lindar.stannp.model.request.CreateLetterRequest;
import com.lindar.stannp.model.response.CreateBatchLetterResponse;
import com.lindar.stannp.model.response.CreateLetterResponse;
import com.lindar.stannp.utils.CsvUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LetterResource extends BaseResource {

    private static final String ENDPOINT = "/api/v1/letters";

    LetterResource(RequestBuilder requestBuilder) {
        super(requestBuilder);
    }

    public StannpResponse<CreateLetterResponse> create(CreateLetterRequest request) {
        return create(request, null);
    }

    public StannpResponse<CreateLetterResponse> create(CreateLetterRequest request, String idempotencyKey) {
        Map<String, Object> requestMap = requestToMap(request);
        return postRequest(ENDPOINT + "/create", requestMap, new TypeToken<StannpResponse<CreateLetterResponse>>() {
        }, idempotencyKey);
    }

    public StannpResponse<CreateBatchLetterResponse> batch(CreateBatchLetterRequest request) {
        return batch(request, null);
    }

    public StannpResponse<CreateBatchLetterResponse> batch(CreateBatchLetterRequest request, String idempotencyKey) {

        Map<String, Object> requestMap = null;
        try {
            requestMap = requestToMap(request);
        } catch (IOException e) {
            return StannpResponse.error(e.getMessage());
        }

        try {
            return postRequest(ENDPOINT + "/batch", requestMap, new TypeToken<StannpResponse<CreateBatchLetterResponse>>() {
            }, idempotencyKey);
        } finally {
            ((File) requestMap.get("csv")).delete();
        }
    }

    private Map<String, Object> requestToMap(CreateLetterRequest request) {
        HashMap<String, Object> map = new HashMap<>();

        if (request.getRecipient() != null)
            map.putAll(StannpHelper.recipientToNestedRequestMap(request.getRecipient()));

        if (request.getRecipientId() != null)
            map.put("recipient", String.valueOf(request.getRecipientId()));

        if (request.getTemplateId() != null)
            map.put("template", request.getTemplateId());

        if (request.getBackgroundImageUrl() != null)
            map.put("background", request.getBackgroundImageUrl().toString());

        if (request.getBackgroundImageFile() != null)
            map.put("background", request.getBackgroundImageFile());

        if (request.getFileUrl() != null)
            map.put("file", request.getFileUrl());

        if (request.getFile() != null)
            map.put("file", request.getFile());

        if (request.getDuplex() != null)
            map.put("duplex", request.getDuplex());

        if (request.getPages() != null)
            map.putAll(StannpHelper.pagesToRequestMap(request.getPages()));

        if (request.getTest() != null)
            map.put("test", request.getTest());

        if (request.getAddons() != null)
            map.put("addons", request.getAddons());

        return map;
    }

    private Map<String, Object> requestToMap(CreateBatchLetterRequest request) throws IOException {
        HashMap<String, Object> map = new HashMap<>();

        map.put("csv", CsvUtils.createRecipientsCsv(request.getRecipients()));

        if (request.getTemplateId() != null)
            map.put("template", request.getTemplateId());

        if (request.getBackgroundImageUrl() != null)
            map.put("background", request.getBackgroundImageUrl().toString());

        if (request.getBackgroundImageFile() != null)
            map.put("background", request.getBackgroundImageFile());

        if (request.getFileUrl() != null)
            map.put("file", request.getFileUrl());

        if (request.getFile() != null)
            map.put("file", request.getFile());

        if (request.getDuplex() != null)
            map.put("duplex", request.getDuplex());

        if (request.getPages() != null)
            map.putAll(StannpHelper.pagesToRequestMap(request.getPages()));

        if (request.getTest() != null)
            map.put("test", request.getTest());

        return map;
    }
}
