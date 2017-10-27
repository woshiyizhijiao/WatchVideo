package com.wsyzj.watchvideo.common.business.mvp;

import android.os.Build;

import com.wsyzj.watchvideo.common.business.bean.Music;
import com.wsyzj.watchvideo.common.business.bean.Song;
import com.wsyzj.watchvideo.common.http.RetrofitHepler;
import com.wsyzj.watchvideo.common.http.RxSchedulers;

import io.reactivex.Observable;

/**
 * @author: wsyzj
 * @date: 2017-09-17 17:19
 * @comment:
 */
public class MusicModel implements MusicContract.Model {


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

    private String getUserAgent() {
        return Build.BRAND + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE;
    }
}
