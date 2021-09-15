package com.lindar.stannp.model;

import lombok.Data;

@Data
public class StannpResponse<T> {
    private boolean success;
    private String error;
    private T data;
    private boolean timeout;

    public static <E> StannpResponse<E> error(String error){
        StannpResponse<E> ret = new StannpResponse<>();
        ret.setError(error);
        ret.setSuccess(false);
        return ret;
    }

    public static <E> StannpResponse<E> timeout(){
        StannpResponse<E> ret = new StannpResponse<>();
        ret.setError("Request timed out");
        ret.setSuccess(false);
        ret.setTimeout(true);
        return ret;
    }
}
