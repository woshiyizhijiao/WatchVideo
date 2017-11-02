package com.wsyzj.watchvideo.common.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.IModel;
import com.wsyzj.watchvideo.common.base.mvp.IPresenter;
import com.wsyzj.watchvideo.common.base.mvp.IView;
import com.wsyzj.watchvideo.common.business.bean.Music;
import com.wsyzj.watchvideo.common.business.bean.Song;
import com.wsyzj.watchvideo.common.http.BaseEntity;
import com.wsyzj.watchvideo.common.test.City;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;


/**
 * @author: wsyzj
 * @date: 2017-09-17 17:18
 * @comment:
 */
public class MusicContract {

    public interface View extends IView {
        void setMusicList(List<Music.SongListBean> musicList);

        void setRefreshing(boolean refreshing);

        void setLoadMoreState(int totalCount, int currentCount);

        void setSongInfo(Song song);
    }

    public interface Model extends IModel {
        Observable<Music> getMusicList(int page);

        Observable<Song> getMusicPlayPath(String songid);

        Flowable<BaseEntity<List<City>>> getRegion();
    }

    interface Presenter extends IPresenter<View> {
        void getMusicList(boolean refreshing);

        void getMusicPlayPath(String songid);

        void previous();

        void next();

        void getRegion();
    }
}
