package com.wsyzj.watchvideo.common.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.business.bean.Music;
import com.wsyzj.watchvideo.common.business.bean.Song;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

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

        mModel.getMusicList(mPage)
                .subscribeWith(new Observer<Music>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Music music) {
                        List<Music.SongListBean> song_list = music.song_list;
                        if (song_list != null) {
                            if (isRefresh) {
                                mSongs = music.song_list;
                            } else {
                                mSongs.addAll(music.song_list);
                            }
                            mView.setMusicList(mSongs);
                        } else {
                            mView.setLoadMoreState(0, 0);
                        }
                        mView.setRefreshing(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showToast("获取音乐列表失败，请重试");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取音乐播放链接
     */
    @Override
    public void getMusicPlayPath(String songid) {
        mView.showProgress();
        mModel.getMusicPlayPath(songid)
                .subscribeWith(new Observer<Song>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Song song) {
                        if (song != null) {
                            mView.setSongInfo(song);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        mView.showToast("获取歌曲链接失败，请重试");
                    }

                    @Override
                    public void onComplete() {
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
