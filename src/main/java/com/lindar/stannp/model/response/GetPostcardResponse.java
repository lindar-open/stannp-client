package com.lindar.stannp.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GetPostcardResponse {
    @SerializedName("id")
    private long id;
    @SerializedName("account_id")
    private long accountId;
    @SerializedName("timestamp")
    private LocalDateTime timestamp;
    @SerializedName("status")
    private String status;
    @SerializedName("type")
    private String type;
    @SerializedName("format")
    private String format;
    @SerializedName("json_file")
    private String jsonFile;
    @SerializedName("pdf_file")
    private String pdfFile;
    @SerializedName("dispatched")
    private LocalDateTime dispatched;
    @SerializedName("country")
    private String country;
    @SerializedName("cost")
    private BigDecimal cost;
    @SerializedName("insert_pdf")
    private String insertPdf;
    @SerializedName("insert_size")
    private String insertSize;
    @SerializedName("addons")
    private String addons;
    @SerializedName("hashstring")
    private String hashstring;
    @SerializedName("tags")
    private String tags;
    @SerializedName("postcode")
    private String postcode;
    @SerializedName("dps")
    private String dps;
    @SerializedName("address")
    private String address;
    @SerializedName("campaign_id")
    private String campaignId;
    @SerializedName("recipient_id")
    private String recipientId;
    @SerializedName("transactional")
    private String transactional;
    @SerializedName("admail")
    private String admail;
    @SerializedName("class")
    private String postalClass;
    @SerializedName("trigger_id")
    private String triggerId;
    @SerializedName("track_ref")
    private String trackRef;
}
