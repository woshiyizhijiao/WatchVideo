package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.IModel;
import com.wsyzj.watchvideo.common.base.mvp.IPresenter;
import com.wsyzj.watchvideo.common.base.mvp.IView;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.Song;

import java.util.List;

import io.reactivex.Flowable;


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
        Flowable<Music> getMusicList(int page);

        Flowable<Song> getMusicPlayPath(String songid);
    }

    interface Presenter extends IPresenter<View> {
        void getMusicList(boolean refreshing);

        void getMusicPlayPath(String songid);

        void previous();

        void next();
    }
}