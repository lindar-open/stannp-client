package com.lindar.stannp;

import com.google.gson.reflect.TypeToken;
import com.lindar.stannp.model.StannpResponse;
import com.lindar.stannp.model.response.GetPdfDetailsResponse;
import com.lindar.wellrested.vo.WellRestedResponse;

public class PdfResource {

    private RequestBuilder requestBuilder;

    PdfResource(RequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    public StannpResponse<GetPdfDetailsResponse> get(String url) {
        return getRequest(url, new TypeToken<GetPdfDetailsResponse>() {
        });
    }

    final protected <T> StannpResponse<T> getRequest(String url, TypeToken<T> typeToken) {

        WellRestedResponse response = requestBuilder.newRequestNoBaseUrl(url).get()
                .submit();

        if (response.isClientTimeout() || response.isConnectionTimeout() || response.isSocketTimeout()) {
            return StannpResponse.timeout();
        }

        return StannpResponse.success(response.fromJson().castTo(typeToken));
    }
}
