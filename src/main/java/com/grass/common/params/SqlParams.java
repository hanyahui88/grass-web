package com.grass.common.params;

/**
 * Created by Duo Nuo on 2016/6/16.
 */
public class SqlParams {
    //键
    private String key;
    //操作符   =  >  <
    private String operation;
    //值
    private Object value;
    //连接符   and | or
    private String link;

    public SqlParams() {
    }

    public SqlParams(String key, String operation, String link, Object value) {
        this.key = key;
        this.operation = operation;
        this.link = link;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
