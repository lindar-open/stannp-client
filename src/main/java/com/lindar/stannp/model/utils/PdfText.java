package com.lindar.stannp.model.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PdfText {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("x")
    @Expose
    private String x;
    @SerializedName("y")
    @Expose
    private Float y;
    @SerializedName("width")
    @Expose
    private String width;
    @SerializedName("align")
    @Expose
    private String align;
    @SerializedName("font")
    @Expose
    private String font;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("style")
    @Expose
    private String style;
    @SerializedName("line_height")
    @Expose
    private String lineHeight;
    @SerializedName("colour")
    @Expose
    private String colour;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("fixed_height")
    @Expose
    private Boolean fixedHeight;
    @SerializedName("fit_text")
    @Expose
    private Boolean fitText;
    @SerializedName("rotate")
    @Expose
    private Long rotate;
    @SerializedName("empty_lines")
    @Expose
    private Boolean emptyLines;
}
