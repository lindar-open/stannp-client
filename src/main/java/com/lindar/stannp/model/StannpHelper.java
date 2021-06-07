package com.lindar.stannp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StannpHelper {

    public static Map<String, String> recipientToRequestMap(Recipient recipient){
        Map<String, String> params = new HashMap<>();
        params.put("title", recipient.getTitle());
        params.put("firstname", recipient.getFirstname());
        params.put("lastname", recipient.getLastname());
        params.put("address1", recipient.getAddress1());
        params.put("address2", recipient.getAddress2());
        params.put("address3", recipient.getAddress3());
        params.put("city", recipient.getCity());
        params.put("postcode", recipient.getPostcode());
        params.put("country", recipient.getCountry());

        if(recipient.getGroupId() != null)
            params.put("group_id", String.valueOf(recipient.getGroupId()));

        if(recipient.getOnDuplicate() != null)
            params.put("on_duplicate", recipient.getOnDuplicate().name().toLowerCase());

        if(recipient.getCustom() != null){
            recipient.getCustom().forEach(params::put);
        }

        return params;
    }

    public static Map<String, String> recipientToNestedRequestMap(Recipient recipient){
        Map<String, String> params = new HashMap<>();
        params.put("recipient[title]", recipient.getTitle());
        params.put("recipient[firstname]", recipient.getFirstname());
        params.put("recipient[lastname]", recipient.getLastname());
        params.put("recipient[address1]", recipient.getAddress1());
        params.put("recipient[address2]", recipient.getAddress2());
        params.put("recipient[address3]", recipient.getAddress3());
        params.put("recipient[city]", recipient.getCity());
        params.put("recipient[postcode]", recipient.getPostcode());
        params.put("recipient[country]", recipient.getCountry());

        if(recipient.getGroupId() != null)
            params.put("recipient[group_id]", String.valueOf(recipient.getGroupId()));

        if(recipient.getOnDuplicate() != null)
            params.put("recipient[on_duplicate]", recipient.getOnDuplicate().name().toLowerCase());

        if(recipient.getCustom() != null){
            recipient.getCustom().forEach((key, value) -> params.put("recipient["+key+"]", value));
        }

        return params;
    }

    public static Map<String, String> pagesToRequestMap(List<String> pages){
        Map<String, String> params = new HashMap<>();
        if(pages != null) {
            if(pages.size() == 1){
                params.put("pages", pages.get(0));
            } else {
                for (int i = 0; i < pages.size(); i++) {
                    params.put("pages["+i+"]", pages.get(i));
                }
            }
        }

        return params;
    }
}
