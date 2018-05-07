package com.wsyzj.watchvideo.common.http;

import com.wsyzj.watchvideo.business.bean.DouBan;
import com.wsyzj.watchvideo.business.bean.Gank;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.News;
import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.business.bean.NewsDetails;
import com.wsyzj.watchvideo.business.bean.Song;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Admin on 2017/10/30.
 */

public interface BaseRetrofitApi {

    /**
     * 获取歌曲列表
     *
     * @param userAgent
     * @param method
     * @param type
     * @param size
     * @param offset
     * @return
     */
    @GET("v1/restserver/ting/")
    Flowable<Music> getMusciList(@Header("User-Agent") String userAgent, @Query("method") String method,
                                 @Query("type") String type, @Query("size") String size, @Query("offset") String offset);

    /**
     * 获取歌曲的播放地址
     *
     * @param userAgent
     * @param method
     * @param songid
     * @return
     */
    @GET("v1/restserver/ting/")
    Flowable<Song> getMusicPlayPath(@Header("User-Agent") String userAgent,
                                    @Query("method") String method, @Query("songid") String songid);

    /**
     * 每个新闻标题下的数据
     *
     * @param channel
     * @param start
     * @param num
     * @return
     */
    @POST("get")
    Flowable<News> getNewsListByTitle(@Query("channel") String channel, @Query("start") int start, @Query("num") int num, @Query("appkey") String appkey);

    /**
     * 首页数据
     *
     * @param type
     * @param pageNumber
     * @param page
     * @return
     */
    @GET("{type}/{pageNumber}/{page}")
    Flowable<Gank> getGankData(@Path("type") String type, @Path("pageNumber") int pageNumber, @Path("page") int page);

    /**
     * 获取上映电影信息
     *
     * @param apikey
     * @param city
     * @param start
     * @param count
     * @return
     */
    @POST("in_theaters")
    Flowable<DouBan> getTheatersList(@Query("apikey") String apikey, @Query("city") String city, @Query("start") int start, @Query("count") int count);

    /**
     * 获取新闻的标题
     *
     * @return
     */
    @POST("channel_news")
    Flowable<NewsChannel> getNewsChannel(@Query("appkey") String appkey);

    /**
     * 每个新闻频道下的列表
     *
     * @param channelId
     * @param channelName
     * @param title
     * @param page
     * @return
     */
    @POST("search_news")
    Flowable<NewsDetails> getNewsList(@Query("appkey") String appkey, @Query("channelId") String channelId, @Query("channelName") String channelName, @Query("title") String title, @Query("page") int page);
}
