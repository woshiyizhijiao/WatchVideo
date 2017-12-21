package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.Song;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/21 11:02
 * @Description: $desc$
 */
public class MusicPresenter extends BasePresenter<MusicContract.View, MusicContract.Model> implements MusicContract.Presenter {

    private MusicContract.View mView;
    private MusicContract.Model mModel;

    private int mPage = 1;
    private List<Music.SongListBean> mSongs = new ArrayList<>();

    public MusicPresenter(MusicContract.View view) {
        mView = view;
        mModel = new MusicModel();
    }

    /**
     * 获取音乐列表
     */
    @Override
    public void getMusicList(final boolean isRefresh) {
        if (isRefresh) {
            mPage = 1;
        } else {
            mPage++;
        }
        BaseTSubscriber<Music> baseTSubscriber = mModel
                .getMusicList(mPage)
                .subscribeWith(new BaseTSubscriber<Music>() {
                    @Override
                    public void onSuccess(Object data) {
                        Music music = (Music) data;
                        List<Music.SongListBean> song_list = music.song_list;
                        if (song_list != null) {
                            if (isRefresh) {
                                mSongs = song_list;
                            } else {
                                mSongs.addAll(song_list);
                            }
                            mView.setMusicList(mSongs);
                        } else {
                            mView.setLoadMoreState(0);
                        }
                        mView.setRefreshing(false);
                    }
                });
        mView.addDisposable(baseTSubscriber);
    }

    /**
     * 获取歌曲的播放列表
     *
     * @param songid
     */
    @Override
    public void getMusicPlayPath(String songid) {
        mView.showProgress();
        mModel.getMusicPlayPath(songid)
                .subscribeWith(new BaseTSubscriber<Song>() {
                    @Override
                    public void onSuccess(Object data) {
                        Song song = (Song) data;
                        if (song != null) {
                            mView.setSongInfo(song);
                        }
                        mView.dismissProgress();
                    }
                });
    }
}
