package com.wsyzj.watchvideo.common.http;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.LogUtils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/05/07
 *     desc   :
 * </pre>
 */
public class BaseLogInterceptor implements Interceptor {

    @SuppressLint("DefaultLocale")
    @Override
    public Response intercept(Chain chain) throws IOException {
        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        Request request = chain.request();
        long t1 = System.nanoTime();

        String method = request.method();
        if ("POST".equals(method)) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                LogUtils.e(String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}", request.url(), chain.connection(), request.headers(), sb.toString()));
            }
        } else {
            LogUtils.e(String.format("发送请求 %s on %s%n%s", request.url(), chain.connection(), request.headers()));
        }
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();

        /**
         * 这里不能直接使用response.body().string()的方式输出日志
         * 因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
         * 个新的response给应用层处理
         */
        ResponseBody responseBody = response.peekBody(1024 * 1024);

        LogUtils.e("请求url : " + response.request().url() + "\n响应时间 : " + (t2 - t1) / 1e6d + "\n请求体 : " + response.headers());
        LogUtils.e(responseBody.string());
        return response;
    }
}
