package com.wsyzj.watchvideo.common.base.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 创建时间 : 2017/10/26
 * 编写人 : 焦洋
 * 功能描述 : retrofit请求请求头部的信息.
 */

public class BaseHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
//        ArrayMap<String, String> headers = BaseApp.getAppContext().getRequestHeader();
//        if (headers != null) {
//            Request.Builder requestBuilder = originalRequest.newBuilder();
//
//            for (String key : headers.keySet()) {
//                requestBuilder.addHeader(key, headers.get(key));
//            }
//            requestBuilder.method(originalRequest.method(), originalRequest.body());
//            return chain.proceed(requestBuilder.build());
//        }
        return chain.proceed(originalRequest);
    }
}
