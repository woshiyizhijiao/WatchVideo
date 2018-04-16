package com.wsyzj.watchvideo.business.mvp;

import android.os.Build;

import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.SongTest;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.http.BaseRxSchedulers;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/21 11:02
 * @Description: $desc$
 */
public class MusicModel implements MusicContract.Model {

    @Override
    public Flowable<Music> getMusicList(int page) {
        return BaseRetrofit
                .musicApi()
                .getMusciList(getUserAgent(), "baidu.ting.billboard.billList", "1", "20", String.valueOf(page * 20))
                .compose(BaseRxSchedulers.<Music>io_main());
    }

    @Override
    public Flowable<SongTest> getMusicPlayPath(String songid) {
        return BaseRetrofit
                .musicApi()
                .getMusicPlayPath(getUserAgent(), "baidu.ting.song.play", songid)
                .compose(BaseRxSchedulers.<SongTest>io_main());
    }

    private String getUserAgent() {
        return Build.BRAND + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE;
    }
}
