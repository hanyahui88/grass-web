package com.grass.common.params;

import java.util.List;
import java.util.Map;

/**
 * Created by Duo Nuo on 2016/6/16.
 */
public class RequestParams {
    //map参数上传
    private Map<String, Object> map;
    //list 参数上传
    private List<Object> list;
    //页码
    private int pageNum=1;
    //每页的数量
    private int pageSize;
    //必须 权大师分配给应用的AppKey
    private String appKey;
    //用户登录授权成功后，颁发给应用的授权信息，详细介绍请点击这里。当此API文档的标签上注明：“需要授权”，则此参数必传；“不需要授权”，则此参数不需要传；“可选授权”，则此参数为可选。
    private String session;
    //必须 时间戳，格式为yyyy-MM-dd HH:mm:ss，时区为GMT+8，例如：2016-01-01 12:00:00
    private String timestamp;
    //合作伙伴身份标识
    private String partnerId;
    //被调用的目标AppKey，仅当被调用的API为第三方ISV提供时有效。
    private String targetAppKey;
    //必须 签名的摘要算法，可选值为：hmac，md5。
    private String signMethod;
    //必须 API输入参数签名结果，签名算法参照下面的介绍
    private String sign;
    //必须 API协议版本，可选值：2.0
    private String v;
    private String secret;
    private String code;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getTargetAppKey() {
        return targetAppKey;
    }

    public void setTargetAppKey(String targetAppKey) {
        this.targetAppKey = targetAppKey;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
