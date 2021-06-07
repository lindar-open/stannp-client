package com.lindar.stannp.model.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateBatchLetterResponse {
    private BigDecimal cost;
    private int ukMatched;
    private int ukNotMatched;
    private int international;
    private List<Letter> letters;

    @Data
    public static class Letter {
        private String pdf;
        private int id;
    }
}
