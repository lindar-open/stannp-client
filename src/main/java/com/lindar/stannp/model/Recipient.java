package com.lindar.stannp.model;

import com.lindar.stannp.model.enums.OnDuplicate;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Recipient {
    private String title;
    private String firstname;
    private String lastname;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String postcode;
    private String country;
    private Integer groupId;
    private OnDuplicate onDuplicate;
    private Map<String, String> custom = new HashMap<>();
}
