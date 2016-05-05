package com.grass.common.result;

import com.grass.common.mapper.JsonMapper;

/**
 * 每个方法返回都用这个对象
 * Created by yahui on 2016/4/28.
 */
public class ResultDTO {
    private int code;
    private String message;
    private Object data;

    private ResultDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResultDTO(int code, String message, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public String toJson() {
        return JsonMapper.toJsonString(this);
    }

    public static String toJson(int code, String message, Object data) {
        ResultDTO resultDTO = new ResultDTO(code, message, data);
        return JsonMapper.toJsonString(resultDTO);
    }

    public static String toJson(int code, String message) {
        ResultDTO resultDTO = new ResultDTO(code, message);
        return JsonMapper.toJsonString(resultDTO);
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

    public static void main(String[] agr){
       System.out.println(ResultDTO.toJson(12,"fsd"));
    }

}
