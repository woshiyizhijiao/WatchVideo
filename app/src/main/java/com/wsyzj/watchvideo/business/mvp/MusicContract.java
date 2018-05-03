package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.Song;
import com.wsyzj.watchvideo.common.base.mvp.BaseIModel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;

import java.util.List;

import io.reactivex.Flowable;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/19
 *     desc   :
 * </pre>
 */
public class MusicContract {
    public interface View extends BaseIView {

        void setMusicList(List<Music.SongListBean> musicList);

        void finishRefresh();

        void setLoadMoreByPageCount(int listSize, int pageCount);

        void addAndPlay(Music.SongListBean songListBean);
    }

    public interface Model extends BaseIModel {
        Flowable<Music> getMusicList(int page);

        Flowable<Song> getMusicPlayPath(String songid);
    }

    interface Presenter extends BaseIPresenter<View> {

        void getMusicList(boolean refreshing);

        void getMusicPlayPath(int position);

        void downMusic(int position);
    }
}
