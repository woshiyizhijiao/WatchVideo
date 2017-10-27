package com.wsyzj.watchvideo.common.http;


import com.wsyzj.watchvideo.common.business.bean.Music;
import com.wsyzj.watchvideo.common.business.bean.Song;
import com.wsyzj.watchvideo.common.test.City;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * @author: wsyzj
 * @date: 2017-08-26 22:42
 * @comment: 公共api
 */
public interface RetrofitApi {

    @GET("v1/restserver/ting/")
    Observable<Music> getMusciList(@Header("User-Agent") String userAgent, @Query("method") String method,
                                   @Query("type") String type, @Query("size") String size, @Query("offset") String offset);

    @GET("v1/restserver/ting/")
    Observable<Song> getMusicPlayPath(@Header("User-Agent") String userAgent, @Query("method") String method, @Query("songid") String songid);

    // 城市数据
    @GET("api/v1/common/region")
    Flowable<BaseEntity<City>> region();
}
