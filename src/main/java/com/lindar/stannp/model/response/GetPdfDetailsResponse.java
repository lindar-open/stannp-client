package com.lindar.stannp.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lindar.stannp.model.utils.PdfAddress;
import com.lindar.stannp.model.utils.PdfPage;
import com.lindar.stannp.model.utils.PdfStyle;
import lombok.Data;

import java.util.List;

@Data
public class GetPdfDetailsResponse {
    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("pdf")
    @Expose
    private String pdf;
    @SerializedName("backgroundScale")
    @Expose
    private String backgroundScale;
    @SerializedName("scale")
    @Expose
    private String scale;
    @SerializedName("return_address")
    @Expose
    private String returnAddress;
    @SerializedName("address_format")
    @Expose
    private String addressFormat;
    @SerializedName("duplex")
    @Expose
    private String duplex;
    @SerializedName("white_clearzone")
    @Expose
    private String whiteClearzone;
    @SerializedName("fonts")
    @Expose
    private List<String> fonts = null;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("pages")
    @Expose
    private List<PdfPage> pages = null;
    @SerializedName("styles")
    @Expose
    private List<PdfStyle> styles = null;
    @SerializedName("sample")
    @Expose
    private Boolean sample;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("transactional")
    @Expose
    private Boolean transactional;
    @SerializedName("address")
    @Expose
    private PdfAddress address;
}
