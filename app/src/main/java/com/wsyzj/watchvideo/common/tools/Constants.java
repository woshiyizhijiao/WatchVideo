package com.wsyzj.watchvideo.common.tools;

/**
 * 创建时间 : 2017/10/26
 * 编写人 : 焦洋
 * 功能描述 :
 */

public class Constants {
    public static String PATH = "http://demo.ecapp.cc/mec/";
    // 网络请求地址
    public static final String BASE_URL = "http://tingapi.ting.baidu.com/";
    // 网络请求相关变量
    public static final int NET_CODE_SUCCESS = 10000;
    public static final int NET_CODE_ERROR = 1;
    public static final int NET_CODE_CONNECT = 400;
    public static final int NET_CODE_UNKNOWN_HOST = 401;
    public static final int NET_CODE_SOCKET_TIMEOUT = 402;
    public static final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
    public static final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    public static final String UNKNOWN_HOST_EXCEPTION = "网络异常，请检查您的网络状态";
}
