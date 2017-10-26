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

    public static Retrofit getInstance() {
        return Instance.retrofit;
    }

    private static class Instance {
        private static Retrofit retrofit = getRetrofit();

        private static Retrofit getRetrofit() {
            OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
            //debug模式添加log信息拦截
            if (BaseApp.isDebug) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                okHttpBuilder.addInterceptor(interceptor);
            }
//            okHttpBuilder.addInterceptor(new HeaderInterceptor());
//            okHttpBuilder.addInterceptor(new ParamsInterceptor());
            //设置网络连接失败时自动重试
            okHttpBuilder.retryOnConnectionFailure(true);
            //设置连接超时
            okHttpBuilder.connectTimeout(5, TimeUnit.SECONDS);
            //设置写超时
            okHttpBuilder.writeTimeout(10, TimeUnit.SECONDS);
            //设置读超时
            okHttpBuilder.readTimeout(10, TimeUnit.SECONDS);


            Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
            retrofitBuilder.baseUrl(Constants.PATH);
            retrofitBuilder.client(okHttpBuilder.build());
            retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
            return retrofitBuilder.build();
        }
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
