package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.SongTest;
import com.wsyzj.watchvideo.common.base.mvp.BaseIModel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/21 11:02
 * @Description: $desc$
 */
public class MusicContract {
    public interface View extends BaseIView {
        void setMusicList(List<Music.SongListBean> musicList);

        void setRefreshing(boolean refreshing);

        void setLoadMoreState(int totalCount);

        void setSongInfo(SongTest songTest);
    }

    public interface Model extends BaseIModel {
        Flowable<Music> getMusicList(int page);

        Flowable<SongTest> getMusicPlayPath(String songid);
    }

    interface Presenter extends BaseIPresenter<View> {
        void getMusicList(boolean refreshing);

        void getMusicPlayPath(String songid);

        void previous();

        void next();
    }
}
