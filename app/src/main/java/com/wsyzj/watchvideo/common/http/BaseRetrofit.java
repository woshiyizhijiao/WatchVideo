package com.wsyzj.watchvideo.common.http;

import com.wsyzj.watchvideo.business.BaseApp;
import com.wsyzj.watchvideo.common.constant.Constant;

import java.util.HashMap;
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

    private static HashMap<String, CompositeDisposable> mNetManager = new HashMap<>();

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BaseApp.isDebug) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);

//            BaseLogInterceptor logInterceptor = new BaseLogInterceptor();
//            builder.addInterceptor(logInterceptor);
        }
        builder.addInterceptor(new BaseParamsInterceptor());
        builder.connectTimeout(12, TimeUnit.SECONDS);
        builder.writeTimeout(12, TimeUnit.SECONDS);
        builder.readTimeout(12, TimeUnit.SECONDS);
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


    /**
     * 获取音频api
     *
     * @return
     */
    public static BaseRetrofitApi musicApi() {
        return BaseMusicRetrofit.getInstance().getService();
    }

    /**
     * 视频api
     *
     * @return
     */
    public static BaseRetrofitApi videoApi() {
        return getBaseRetrofitApi(Constant.VIDEO_URL);
    }

    /**
     * 微信精选
     *
     * @return
     */
    public static BaseRetrofitApi wechatChoiceness() {
        return getBaseRetrofitApi(Constant.WECHAT_CHOICENESS_URL);
    }

    /**
     * 京东新闻数据
     *
     * @return
     */
    public static BaseRetrofitApi jingDong() {
        return getBaseRetrofitApi(Constant.JingDong.JINGDONG_URL);
    }

    /**
     * gank数据
     *
     * @return
     */
    public static BaseRetrofitApi gank() {
        return getBaseRetrofitApi(Constant.GANK_IO_URL);
    }


    /**
     * 每日一文数据
     *
     * @return
     */
    public static BaseRetrofitApi meiRiYiWen() {
        return getBaseRetrofitApi(Constant.MEIRIYIWEN_URL);
    }

    /**
     * 豆瓣
     *
     * @return
     */
    public static BaseRetrofitApi douBan() {
        return getBaseRetrofitApi(Constant.DOUBAN_URL);
    }

    /**
     * 狗东
     *
     * @return
     */
    public static BaseRetrofitApi jingDongNewsChannel() {
        return getBaseRetrofitApi("http://v2inuf.natappfree.cc/");
    }
}
