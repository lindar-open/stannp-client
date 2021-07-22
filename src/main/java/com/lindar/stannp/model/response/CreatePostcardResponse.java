package com.lindar.stannp.model.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class CreatePostcardResponse {
    private String pdf;
    private String id;
    private ZonedDateTime created;
    private String format;
    private BigDecimal cost;
    private String status;
}
