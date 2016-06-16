package com.grass.common.params;

/**
 * Created by Duo Nuo on 2016/6/16.
 */
public class Result {
    private int code;
    private String message;
    private Object data;

    private Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Result(int code, String message, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
