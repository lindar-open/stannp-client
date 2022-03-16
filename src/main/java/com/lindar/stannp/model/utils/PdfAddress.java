package com.lindar.stannp.model.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Map;

@Data
public class PdfAddress {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("job_title")
    @Expose
    private String jobTitle;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("recipient_id")
    @Expose
    private Long recipientId;
    @SerializedName("ref_id")
    @Expose
    private String refId;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("address3")
    @Expose
    private String address3;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("county")
    @Expose
    private String county;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("postcode")
    @Expose
    private String postcode;
    @SerializedName("dps")
    @Expose
    private String dps;
    @SerializedName("checksum")
    @Expose
    private String checksum;
    @SerializedName("data")
    @Expose
    private Map<String, String> data;
}
