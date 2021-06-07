package com.lindar.stannp.model;

import lombok.Data;

@Data
public class StannpResponse<T> {
    private boolean success;
    private String error;
    private T data;

    public static <E> StannpResponse<E> error(String error){
        StannpResponse<E> ret = new StannpResponse<>();
        ret.setError(error);
        ret.setSuccess(false);
        return ret;
    }
}
