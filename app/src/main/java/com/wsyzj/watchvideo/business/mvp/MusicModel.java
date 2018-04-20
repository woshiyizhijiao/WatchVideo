package com.wsyzj.watchvideo.business.mvp;

import android.os.Build;

import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.Song;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.http.BaseRxSchedulers;

import io.reactivex.Flowable;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/19
 *     desc   :
 * </pre>
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
    public Flowable<Song> getMusicPlayPath(String songid) {
        return BaseRetrofit
                .musicApi()
                .getMusicPlayPath(getUserAgent(), "baidu.ting.song.play", songid)
                .compose(BaseRxSchedulers.<Song>io_main());
    }

    private String getUserAgent() {
        return Build.BRAND + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE;
    }
}
