package com.lindar.stannp;

import com.google.gson.reflect.TypeToken;
import com.lindar.stannp.model.StannpResponse;
import com.lindar.stannp.model.Template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TemplateResource extends BaseResource {

    private static final String ENDPOINT = "/api/v1/templates/list";

    protected TemplateResource(RequestBuilder requestBuilder) {
        super(requestBuilder);
    }

    public StannpResponse<ArrayList<Template>> list(int offset, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", limit);
        return postRequest(ENDPOINT, params, new TypeToken<StannpResponse<ArrayList<Template>>>(){});
    }

    public StannpResponse<ArrayList<Template>> list(int offset, int limit, String format) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", limit);
        return postRequest(ENDPOINT+"/"+format, params, new TypeToken<StannpResponse<ArrayList<Template>>>(){});
    }
}
