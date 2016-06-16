package com.grass.common.constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Duo Nuo on 2016/6/12.
 */
public class ResultCode {
    //公共参数没有传入
    public static final int COMMON_PARAM_FAIL_CODE = 9999;
    public static final String COMMON_PARAM_FAIL_MESSAGE = "请传入正确的公共参数";
    //1001 -1050 签名的代码
    public static final int SIGN_SUCCESS_CODE = 1001;
    public static final String SIGN_SUCCESS_MESSAGE = "签名正确";
    public static final int SIGN_FAIL_CODE = 1002;
    public static final String SIGN_FAIL_MESSAGE = "签名错误";
    //1051 -1100 accessToken的代码
    public static final int GET_TOKEN_SUCCESS_CODE = 1051;
    public static final String GET_TOKEN_SUCCESS_MESSAGE = "获取accessToken成功";
    public static final int GET_TOKEN_FAIL_CODE = 1052;
    public static final String GET_TOKEN_FAIL_MESSAGE = "获取accessToken失败";
    public static final int REFRESH_TOKEN_SUCCESS_CODE = 1053;
    public static final String REFRESH_TOKEN_SUCCESS_MESSAGE = "刷新accessToken成功";
    public static final int REFRESH_TOKEN_FAIL_CODE = 1054;
    public static final String REFRESH_TOKEN_FAIL_MESSAGE = "刷新accessToken失败";
    public static final int TOKEN_VISIT_COUNT_CODE = 1055;
    public static final String TOKEN_VISIT_COUNT_MESSAGE = "accessToken接口访问的次数已达上限";
    public static final int TOKEN_REFRESH_COUNT_CODE = 1056;
    public static final String TOKEN_REFRESH_COUNT_MESSAGE = "accessToken接口刷新的次数已达上限";
    public static final int TOKEN_EXPIRE_COUNT_CODE = 1057;
    public static final String TOKEN_EXPIRE_COUNT_MESSAGE = "accessToken已经过期";
    //1150 -1200 正常请求的代码
    public static final int PARAMS_FAIL_CODE = 1150;
    public static final String PARAMS_FAIL_MESSAGE = "{0}接口参数错误";
    public static final int SUCCESS_CODE = 1151;
    public static final String SUCCESS_MESSAGE = "{0}接口请求成功";
    public static final int FAIL_CODE = 1152;
    public static final String FAIL_MESSAGE = "{0}接口请求失败";

    public static String message(String message, String... params) {
        return fillStringByArgs(message, params);
    }

    private static String fillStringByArgs(String str, String[] arr) {
        Matcher m = Pattern.compile("\\{(\\d)\\}").matcher(str);
        while (m.find()) {
            str = str.replace(m.group(), arr[Integer.parseInt(m.group(1))]);
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(message("I'm{0},she's{1}","dayan","xiaoyan"));
    }
}
