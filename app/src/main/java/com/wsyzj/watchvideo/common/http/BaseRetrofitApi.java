package com.wsyzj.watchvideo.common.http;

import com.wsyzj.watchvideo.common.business.bean.KaiYan;
import com.wsyzj.watchvideo.common.business.bean.Music;
import com.wsyzj.watchvideo.common.business.bean.Song;
import com.wsyzj.watchvideo.common.business.bean.WeChatChoiceness;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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
     * 获取微信精选数据
     *
     * @param pno
     * @param ps
     * @param key
     * @return
     */
    @POST("query")
    Flowable<WeChatChoiceness> wechatChoiceness(@Query("pno") int pno, @Query("ps") int ps, @Query("key") String key);
}