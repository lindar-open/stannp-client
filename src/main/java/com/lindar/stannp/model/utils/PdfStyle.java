package com.lindar.stannp.model.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PdfStyle {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("font")
    @Expose
    private String font;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("style")
    @Expose
    private String style;
    @SerializedName("colour")
    @Expose
    private String colour;
}
