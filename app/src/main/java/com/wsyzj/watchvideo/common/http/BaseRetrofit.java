package com.wsyzj.watchvideo.common.http;

import android.util.ArrayMap;

import com.wsyzj.watchvideo.common.base.BaseApp;
import com.wsyzj.watchvideo.common.tools.Constants;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建时间 : 2017/10/26
 * 编写人 : 焦洋
 * 功能描述 :
 */

public class BaseRetrofit {
    private static ArrayMap<String, CompositeDisposable> mNetManager = new ArrayMap<>();

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BaseApp.isDebug) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        builder.connectTimeout(12, TimeUnit.SECONDS);       //设置连接超时
        builder.writeTimeout(12, TimeUnit.SECONDS);         //设置写超时
        builder.readTimeout(12, TimeUnit.SECONDS);          //设置读超时
        return builder.build();
    }

    private static BaseRetrofitApi getBaseRetrofitApi(String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.client(getOkHttpClient());
        builder.baseUrl(baseUrl);
        BaseRetrofitApi api = builder.build().create(BaseRetrofitApi.class);
        return api;
    }

    /**
     * 获取音频api
     *
     * @return
     */
    public static BaseRetrofitApi musicApi() {
        return getBaseRetrofitApi(Constants.MUSIC_URL);
    }

    public static BaseRetrofitApi videoApi() {
        return getBaseRetrofitApi(Constants.VIDEO_URL);
    }

    /**
     * 请网络请求进行统一管理，方便没用的时候进行取消
     *
     * @param key
     * @param disposable
     */
    public static void add(String key, Disposable disposable) {
        if (mNetManager.containsKey(key)) {
            mNetManager.get(key).add(disposable);
        } else {
            CompositeDisposable cd = new CompositeDisposable();
            cd.add(disposable);
            mNetManager.put(key, cd);
        }
    }

    public static void clear(String key) {
        if (mNetManager.containsKey(key)) {
            CompositeDisposable cd = mNetManager.get(key);
            cd.clear();
        }
    }
}
