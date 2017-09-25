package com.wsyzj.watchvideo.http;

import com.wsyzj.watchvideo.base.BaseApp;
import com.wsyzj.watchvideo.tools.Constant;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: wsyzj
 * @date: 2017-08-26 20:36
 * @comment: 网络请求工具类
 */
public class RetrofitHepler {
    private static volatile RetrofitHepler mRetrofitHepler;
    private Retrofit mRetrofit;


    private RetrofitHepler() {

        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(BaseApp.getApp().getCacheDir(), "OkHttpCache");
        Cache cache = new Cache(cacheFile, size);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetworkInterceptor())
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    public static RetrofitHepler getInstance() {
        if (mRetrofitHepler == null) {
            synchronized (RetrofitHepler.class) {
                if (mRetrofitHepler == null) {
                    mRetrofitHepler = new RetrofitHepler();
                }
            }
        }
        return mRetrofitHepler;
    }

    public RetrofitApi getRetrofitApi() {
        return mRetrofit.create(RetrofitApi.class);
    }
}
