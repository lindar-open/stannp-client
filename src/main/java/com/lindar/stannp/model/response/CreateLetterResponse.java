package com.lindar.stannp.model.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class CreateLetterResponse {
    private String pdf;
    private String id;
    private Instant created;
    private String format;
    private BigDecimal cost;
    private String status;
}
