package com.lindar.stannp.model.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class PdfPage {
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("text")
    @Expose
    private List<PdfText> text = null;
    @SerializedName("objects")
    @Expose
    private List<Object> objects = null;
    @SerializedName("background_scale")
    @Expose
    private String backgroundScale;
    @SerializedName("background_colour")
    @Expose
    private String backgroundColour;
    @SerializedName("background_size")
    @Expose
    private String backgroundSize;
    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("dynamic_background")
    @Expose
    private String dynamicBackground;
}
