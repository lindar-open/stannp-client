package com.lindar.stannp;

public class StannpClient {

    private static final String DEFAULT_ENDPOINT = "https://dash.stannp.com";

    private LetterResource letterResource;
    private PostcardResource postcardResource;
    private TemplateResource templateResource;

    public StannpClient(String apiKey, boolean forceTest){
        RequestBuilder requestBuilder = new RequestBuilder(DEFAULT_ENDPOINT, apiKey, forceTest);
        this.letterResource = new LetterResource(requestBuilder);
        this.postcardResource = new PostcardResource(requestBuilder);
        this.templateResource = new TemplateResource(requestBuilder);
    }

    public LetterResource letters(){
        return letterResource;
    }

    public PostcardResource postcode(){
        return postcardResource;
    }

    public TemplateResource template(){
        return templateResource;
    }

    public static StannpClient of(String apiKey){
        return new StannpClient(apiKey, false);
    }

    public static StannpClient of(String apiKey, boolean forceTest){
        return new StannpClient(apiKey, forceTest);
    }
}