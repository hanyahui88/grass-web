package com.grass.common.constants;

/**
 * Created by Duo Nuo on 2016/6/11.
 */
public class Constants {
    public static final String CHARSET_UTF8 = "utf-8";
    /**
     * 签名加密方式
     */
    public static final String SIGN_METHOD_MD5 = "md5";
    public static final String SIGN_METHOD_HMAC = "hmac";
    /**
     * 请求的授权页面
     */
    public static final String OAUTH_VIEW_WEB = "web";
    public static final String OAUTH_VIEW_WAP = "wap";
    /**
     * 请求token的接口的访问次数和刷新次数
     */
    public static final int TOKEN_VISIT_COUNT = 12;
    public static final int TOKEN_ACTIVE_TIME = 7200;
    public static final int TOKEN_REFRESH_COUNT = 12;



}
