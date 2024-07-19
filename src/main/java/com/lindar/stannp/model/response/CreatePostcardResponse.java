package com.lindar.stannp.model.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreatePostcardResponse {
    private String pdf;
    private String id;
    private LocalDateTime created;
    private String format;
    private BigDecimal cost;
    private String status;
}
