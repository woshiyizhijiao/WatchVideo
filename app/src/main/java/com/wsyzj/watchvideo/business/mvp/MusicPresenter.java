package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.Song;
import com.wsyzj.watchvideo.common.base.http.BaseTSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wsyzj
 * @date: 2017-09-17 17:16
 * @comment:
 */
public class MusicPresenter extends BasePresenter<MusicContract.View, MusicContract.Model> implements MusicContract.Presenter {

    private MusicContract.View mView;
    private MusicContract.Model mModel;

    private int mPage = 1;
    public int mCurrentPos;
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
                            mView.setLoadMoreState(0, 0);
                        }
                        mView.setRefreshing(false);
                    }
                });
        mView.addDisposable(baseTSubscriber);
    }

    /**
     * 获取音乐播放链接
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

    /**
     * 上一首
     */
    @Override
    public void previous() {
        if (mCurrentPos != 0) {
            mCurrentPos = mCurrentPos - 1;
        } else {
            mCurrentPos = mSongs.size() - 1;
        }
        Music.SongListBean bean = mSongs.get(mCurrentPos);
        getMusicPlayPath(bean.song_id);
    }

    /**
     * 下一首
     */
    @Override
    public void next() {
        if (mCurrentPos == mSongs.size() - 1) {
            mCurrentPos = 0;
        } else {
            mCurrentPos++;
        }
        Music.SongListBean bean = mSongs.get(mCurrentPos);
        getMusicPlayPath(bean.song_id);
    }
}
