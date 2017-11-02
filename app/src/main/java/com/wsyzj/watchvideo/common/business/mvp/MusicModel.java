package com.wsyzj.watchvideo.common.business.mvp;

import android.os.Build;

import com.wsyzj.watchvideo.common.business.bean.Music;
import com.wsyzj.watchvideo.common.business.bean.Song;
import com.wsyzj.watchvideo.common.http.BaseEntity;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.http.BaseRetrofitApi;
import com.wsyzj.watchvideo.common.http.BaseRxSchedulers;
import com.wsyzj.watchvideo.common.http.RetrofitHepler;
import com.wsyzj.watchvideo.common.http.RxSchedulers;
import com.wsyzj.watchvideo.common.test.City;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * @author: wsyzj
 * @date: 2017-09-17 17:19
 * @comment:
 */
public class MusicModel implements MusicContract.Model {

    @Override
    public Observable<Music> getMusicList(int page) {
        return RetrofitHepler
                .getInstance()
                .getRetrofitApi()
                .getMusciList(getUserAgent(), "baidu.ting.billboard.billList", "1", "20", String.valueOf(page * 20))
                .compose(RxSchedulers.<Music>test());
    }

    @Override
    public Observable<Song> getMusicPlayPath(String songid) {
        return RetrofitHepler
                .getInstance()
                .getRetrofitApi()
                .getMusicPlayPath(getUserAgent(), "baidu.ting.song.play", songid)
                .compose(RxSchedulers.<Song>test());
    }

    @Override
    public Flowable<BaseEntity<List<City>>> getRegion() {
        return BaseRetrofit
                .getInstance()
                .create(BaseRetrofitApi.class)
                .getRegion()
                .compose(BaseRxSchedulers.<BaseEntity<List<City>>>io_main());
    }

    private String getUserAgent() {
        return Build.BRAND + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE;
    }
}
