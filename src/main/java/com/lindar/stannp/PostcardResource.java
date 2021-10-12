package com.lindar.stannp;

import com.google.gson.reflect.TypeToken;
import com.lindar.stannp.model.StannpHelper;
import com.lindar.stannp.model.StannpResponse;
import com.lindar.stannp.model.request.CreatePostcardRequest;
import com.lindar.stannp.model.response.CreatePostcardResponse;

import java.util.HashMap;
import java.util.Map;

public class PostcardResource extends BaseResource {

    private static final String ENDPOINT = "/api/v1/postcards";

    PostcardResource(RequestBuilder requestBuilder) {
        super(requestBuilder);
    }

    public StannpResponse<CreatePostcardResponse> create(CreatePostcardRequest request) {
        return create(request, null);
    }

    public StannpResponse<CreatePostcardResponse> create(CreatePostcardRequest request, String idempotencyKey) {
        Map<String, Object> requestMap = requestToMap(request);
        return postRequest(ENDPOINT + "/create", requestMap, new TypeToken<StannpResponse<CreatePostcardResponse>>() {
        }, idempotencyKey);
    }

    private Map<String, Object> requestToMap(CreatePostcardRequest request) {
        HashMap<String, Object> map = new HashMap<>();

        if (request.getRecipient() != null)
            map.putAll(StannpHelper.recipientToNestedRequestMap(request.getRecipient()));

        if (request.getRecipientId() != null)
            map.put("recipient", String.valueOf(request.getRecipientId()));

        if (request.getTemplateId() != null)
            map.put("template", request.getTemplateId());

        if (request.getSize() != null)
            map.put("size", request.getSize().name());

        if (request.getFrontImageUrl() != null)
            map.put("front", request.getFrontImageUrl().toString());

        if (request.getFrontImageFile() != null)
            map.put("front", request.getFrontImageFile());

        if (request.getSignatureImageUrl() != null)
            map.put("signature", request.getSignatureImageUrl().toString());

        if (request.getSignatureImageFile() != null)
            map.put("signature", request.getSignatureImageFile());

        if (request.getBackImageUrl() != null)
            map.put("back", request.getBackImageUrl().toString());

        if (request.getBackImageFile() != null)
            map.put("back", request.getBackImageFile());

        if (request.getMessage() != null)
            map.put("message", request.getMessage());

        if (request.getTest() != null)
            map.put("test", request.getTest());

        if (request.getAddons() != null)
            map.put("addons", request.getAddons());

        if (request.getPostUnverified() != null)
            map.put("post_unverified", request.getPostUnverified());

        return map;
    }
}
