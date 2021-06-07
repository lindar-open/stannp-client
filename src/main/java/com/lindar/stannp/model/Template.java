package com.lindar.stannp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lindar.stannp.model.enums.TemplateSize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Template {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("account_id")
    @Expose
    private int accountId;

    @SerializedName("template_name")
    @Expose
    private String templateName;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("branding")
    @Expose
    private String branding;

    @SerializedName("created")
    @Expose
    private LocalDateTime created;

    @SerializedName("updated")
    @Expose
    private LocalDateTime updated;

    @SerializedName("size")
    @Expose
    private TemplateSize size;

    @SerializedName("page_count")
    @Expose
    private int pageCount;

    @SerializedName("duplex")
    @Expose
    private boolean duplex;

    @SerializedName("back_image")
    @Expose
    private String backImage;

    @SerializedName("font")
    @Expose
    private String font;

    @SerializedName("font_heading")
    @Expose
    private String fontHeading;

    @SerializedName("font_size")
    @Expose
    private String fontSize;

    @SerializedName("font_colour")
    @Expose
    private String fontColour;

    @SerializedName("padding_front")
    @Expose
    private String paddingFront;

    @SerializedName("padding_back")
    @Expose
    private String paddingBack;

    @SerializedName("address_format")
    @Expose
    private Object addressFormat;

    @SerializedName("return_address")
    @Expose
    private String returnAddress;

    @SerializedName("return_address_text")
    @Expose
    private String returnAddressText;

    @SerializedName("pages")
    @Expose
    private String pages;

    @SerializedName("is_public")
    @Expose
    private boolean isPublic;

    @SerializedName("is_template")
    @Expose
    private boolean isTemplate;

    @SerializedName("version")
    @Expose
    private int version;

    @SerializedName("is_hidden")
    @Expose
    private boolean isHidden;
}
