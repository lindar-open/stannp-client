package com.lindar.stannp;

public class StannpClient {

    private static final String DEFAULT_ENDPOINT = "https://dash.stannp.com";

    private LetterResource letterResource;
    private PostcardResource postcardResource;
    private TemplateResource templateResource;
    private PdfResource pdfResource;


    public StannpClient(String apiKey, boolean forceTest) {
        RequestBuilder requestBuilder = new RequestBuilder(DEFAULT_ENDPOINT, apiKey, forceTest);
        this.letterResource = new LetterResource(requestBuilder);
        this.postcardResource = new PostcardResource(requestBuilder);
        this.templateResource = new TemplateResource(requestBuilder);
        this.pdfResource = new PdfResource(requestBuilder);
    }

    public LetterResource letters() {
        return letterResource;
    }

    public PostcardResource postcard() {
        return postcardResource;
    }

    public TemplateResource template() {
        return templateResource;
    }

    public PdfResource pdf() {
        return pdfResource;
    }

    public static StannpClient of(String apiKey) {
        return new StannpClient(apiKey, false);
    }

    public static StannpClient of(String apiKey, boolean forceTest) {
        return new StannpClient(apiKey, forceTest);
    }
}
