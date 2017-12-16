package com.wsyzj.watchvideo.common.base.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 创建时间 : 2017/10/26
 * 编写人 : 焦洋
 * 功能描述 :
 */

public class BaseParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
//        ArrayMap<String, String> params = BaseApp.getAppContext().getRequestParams();
//        if (params != null) {
//            Request.Builder newRequestBuilder = oldRequest.newBuilder();
//            if ("GET".equalsIgnoreCase(oldRequest.method())) {
//                HttpUrl.Builder httpUrlBuilder = oldRequest.url().newBuilder();
//                for (String key : params.keySet()) {
//                    httpUrlBuilder.addQueryParameter(key, params.get(key));
//                }
//                newRequestBuilder.url(httpUrlBuilder.build());
//            } else {
//                if (oldRequest.body() instanceof FormBody) {
//                    FormBody.Builder formBodyBuilder = new FormBody.Builder();
//                    for (String key : params.keySet()) {
//                        formBodyBuilder.add(key, params.get(key));
//                    }
//                    FormBody oldFormBody = (FormBody) oldRequest.body();
//                    int size = oldFormBody.size();
//                    for (int i = 0; i < size; i++) {
//                        formBodyBuilder.add(oldFormBody.name(i), oldFormBody.value(i));
//                    }
//                    newRequestBuilder.post(formBodyBuilder.build());
//                }
//            }
//            return chain.proceed(newRequestBuilder.build());
//        }
        return chain.proceed(oldRequest);
    }
}
