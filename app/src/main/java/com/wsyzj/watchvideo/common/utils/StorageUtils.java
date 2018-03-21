package com.wsyzj.watchvideo.common.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wsyzj.watchvideo.business.BaseApp;
import com.wsyzj.watchvideo.business.bean.NewsChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/14
 *     desc   : 在SPUtils的基础上对存储的值进行二次封装，对外不直接操作键名，取值和设置值使用本类的方法
 * </pre>
 */
public class StorageUtils {

    private static final String NEWS_CHANNEL_TITLES = "news_channel_titles";

    /**
     * 保存用户选择的新闻标题
     *
     * @param channelList
     */
    public static void putNewsChannelTitles(List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> channelList) {
        Gson gson = new Gson();
        SPUtils.put(BaseApp.getApp(), NEWS_CHANNEL_TITLES, gson.toJson(channelList));
    }

    /**
     * 获取保存的新闻频道的标题 默认会有一个推荐页
     */
    public static List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> getNewsChannelTitles() {
        String newsChannelTitles = (String) SPUtils.get(BaseApp.getApp(), NEWS_CHANNEL_TITLES, "");
        if (TextUtils.isEmpty(newsChannelTitles)) {
            List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> channelList = new ArrayList<>();
            NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = new NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean();
            channelListBean.name = "推荐";
            channelListBean.channelId = "0";
            channelList.add(0, channelListBean);
            return channelList;
        } else {
            Gson gson = new Gson();
            return gson.fromJson(newsChannelTitles, new TypeToken<List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean>>() {
            }.getType());
        }
    }

    private static final String CACHE_NEWS_CHANNEL_TITLES = "cache_news_channel_titles";

    /**
     * 把频道缓存起来
     */
    public static void putCacheNewsChannelTitles(List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> channelList) {
        Gson gson = new Gson();
        SPUtils.put(BaseApp.getApp(), CACHE_NEWS_CHANNEL_TITLES, gson.toJson(channelList));
    }

    /**
     * 获取缓存的频道
     */
    public static List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> getCacheNewsChannelTitle() {
        Gson gson = new Gson();
        String cacheNewsChannelTitles = (String) SPUtils.get(BaseApp.getApp(), CACHE_NEWS_CHANNEL_TITLES, "");
        return gson.fromJson(cacheNewsChannelTitles, new TypeToken<List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean>>() {
        }.getType());
    }
}
