package com.wsyzj.watchvideo.common.http;

import com.wsyzj.watchvideo.common.business.bean.KaiYan;
import com.wsyzj.watchvideo.common.business.bean.Music;
import com.wsyzj.watchvideo.common.business.bean.Song;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
     * 开眼视频数据
     *
     * @return
     */
    @GET("api/v4/tabs/selected")
    Flowable<KaiYan> getKaiYanList();
}
