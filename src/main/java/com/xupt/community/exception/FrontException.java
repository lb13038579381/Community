package com.xupt.community.exception;

public class FrontException extends RuntimeException {
    private Integer code;
    private String msg;

    public FrontException(String msg) {
        super(msg);
    }

    public FrontException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }
}
