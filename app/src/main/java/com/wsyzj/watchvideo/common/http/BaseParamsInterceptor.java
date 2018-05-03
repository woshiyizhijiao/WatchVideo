package com.wsyzj.watchvideo.common.http;

import com.blankj.utilcode.util.LogUtils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
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
        RequestBody body = oldRequest.body();

        LogUtils.e(body.contentLength() + " --- ");
        if (body instanceof FormBody) {
            FormBody formBody = (FormBody) body;
            for (int i = 0; i < formBody.size(); i++) {
                LogUtils.e(formBody.encodedName(i), formBody.encodedValue(i));
            }
        }
        return chain.proceed(oldRequest);
    }
}
