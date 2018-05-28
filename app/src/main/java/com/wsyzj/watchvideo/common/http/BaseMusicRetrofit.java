package com.wsyzj.watchvideo.common.http;

import com.wsyzj.watchvideo.business.BaseApp;
import com.wsyzj.watchvideo.common.constant.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/05/04
 *     desc   : 音乐请求类
 * </pre>
 */
public class BaseMusicRetrofit {

    private final Retrofit mRetrofit;

    private BaseMusicRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BaseApp.isDebug) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor).build();

//            BaseLogInterceptor logInterceptor = new BaseLogInterceptor();
//            builder.addInterceptor(logInterceptor);
        }
//        builder.addInterceptor(new BaseParamsInterceptor());
        builder.connectTimeout(12, TimeUnit.SECONDS);
        builder.writeTimeout(12, TimeUnit.SECONDS);
        builder.readTimeout(12, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .baseUrl(Constant.MUSIC_URL)
                .build();
    }

    private static BaseMusicRetrofit sInstance;

    public static BaseMusicRetrofit getInstance() {
        if (sInstance == null) {
            synchronized (BaseMusicRetrofit.class) {
                if (sInstance == null) {
                    sInstance = new BaseMusicRetrofit();
                }
            }
        }
        return sInstance;
    }

    public BaseRetrofitApi getService() {
        return mRetrofit.create(BaseRetrofitApi.class);
    }
}
