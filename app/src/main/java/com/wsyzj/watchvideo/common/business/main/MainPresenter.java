package com.wsyzj.watchvideo.common.business.main;

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
public class MainPresenter extends BasePresenter<MainContract.View, MainContract.Model>
        implements MainContract.Presenter {

    private MainContract.View mView;
    private MainContract.Model mModel;

    private int mPage = 1;
    private List<Music.SongListBean> mSongs = new ArrayList<>();

    public MainPresenter(MainContract.View view) {
        mView = view;
        mModel = new MainModel();
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

                    }
                });
    }
}
