package com.wsyzj.watchvideo.common.http;

import com.wsyzj.watchvideo.business.bean.DouBan;
import com.wsyzj.watchvideo.business.bean.Gank;
import com.wsyzj.watchvideo.business.bean.KaiYan;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.News;
import com.wsyzj.watchvideo.business.bean.NewsTitle;
import com.wsyzj.watchvideo.business.bean.Song;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
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
     * 梨视频
     *
     * @return
     */
    @Headers({
            "X-Channel-Code:official",
            "X-Client-Agent:Xiaomi",
            "X-Client-Hash:2f3d6ffkda95dlz2fhju8d3s6dfges3t",
            "X-Client-ID:123456789123456",
            "X-Client-Version:2.3.2",
            "X-Long-Token: ",
            "X-Platform-Type:0",
            "X-Platform-Version:5.0",
            "X-Serial-Num:1492140134",
            "X-User-ID: ",
    })
    @GET("home.jsp?lastLikeIds=1063871%2C1063985%2C1064069%2C1064123%2C1064078%2C1064186%2C1062372%2C1064164%2C1064081%2C1064176%2C1064070%2C1064019")
    Flowable<KaiYan> getVideoList();

    /**
     * 获取所有的新闻标题
     *
     * @param appkey
     * @return
     */
    @POST("channel")
    Flowable<NewsTitle> getNewsTitle(@Query("appkey") String appkey);

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
}
