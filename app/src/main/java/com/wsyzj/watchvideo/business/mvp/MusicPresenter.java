package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.SongTest;
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
                            mView.setLoadMoreState(0);
                        }
                        mView.setRefreshing(false);
                    }

                    @Override
                    public void onFailure() {

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
                .subscribeWith(new BaseTSubscriber<SongTest>() {
                    @Override
                    public void onSuccess(Object data) {
                        SongTest songTest = (SongTest) data;
                        if (songTest != null) {
                            mView.setSongInfo(songTest);
                        }
                        mView.dismissProgress();
                    }

                    @Override
                    public void onFailure() {

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
