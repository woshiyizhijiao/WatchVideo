package com.wsyzj.watchvideo.business.mvp;

import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.Song;
import com.wsyzj.watchvideo.business.service.PlayerManager;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;
import com.wsyzj.watchvideo.common.widget.BaseState;

import java.io.File;
import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/19
 *     desc   :
 * </pre>
 */
public class MusicPresenter extends BasePresenter<MusicContract.View, MusicContract.Model> implements MusicContract.Presenter {

    private MusicContract.View mView;
    private MusicContract.Model mModel;

    private int mPage = 1;
    public List<Music.SongListBean> mMusicList;

    public MusicPresenter(MusicContract.View view) {
        mView = view;
        mModel = new MusicModel();
    }

    /**
     * 获取音乐列表
     *
     * @param refreshing
     */
    @Override
    public void getMusicList(boolean refreshing) {
        if (refreshing) {
            mPage = 1;
        } else {
            mPage++;
        }

        BaseTSubscriber<Music> subscriber = mModel.getMusicList(mPage).subscribeWith(new BaseTSubscriber<Music>() {
            @Override
            public void onSuccess(Object data) {
                Music music = (Music) data;
                List<Music.SongListBean> list = music.song_list;
                if (list != null) {
                    if (refreshing) {
                        mMusicList = list;
                    } else {
                        mMusicList.addAll(list);
                    }
                    mView.setPageState(BaseState.STATE_SUCCESS);
                    mView.setLoadMoreByPageCount(list.size(), 20);
                } else {
                    mView.setPageState(BaseState.STATE_EMPTY);
                    mView.setLoadMoreByPageCount(0, 20);
                }

                mView.finishRefresh();
                mView.setMusicList(mMusicList);
            }

            @Override
            public void onFailure(Throwable throwable) {
                mView.setPageState(BaseState.STATE_ERROR);
            }
        });

        mView.addDisposable(subscriber);
    }

    /**
     * 获取歌曲的播放路径
     *
     * @param position
     */
    @Override
    public void getMusicPlayPath(int position) {
        Music.SongListBean bean = mMusicList.get(position);
        Music.SongListBean playSong = PlayerManager.getInstance().getPlaySong();
        // 是否和现在播放的是同一首
        if (playSong != null && TextUtils.equals(bean.song_id, playSong.song_id)) {
            return;
        }

        mView.showProgress();
        BaseTSubscriber<Song> subscriber = mModel.getMusicPlayPath(bean.song_id).subscribeWith(new BaseTSubscriber<Song>() {
            @Override
            public void onSuccess(Object data) {
                Song song = (Song) data;
                if (song != null) {
                    bean.file_link = song.bitrate.file_link;
                    bean.file_duration = song.bitrate.file_duration;
                    mView.addAndPlay(bean);
                    mView.setPlaySong(bean);
                }
                mView.dismissProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                mView.dismissProgress();
            }
        });
        mView.addDisposable(subscriber);
    }

    /**
     * 下载音乐(先查看有没有下载链接)
     *
     * @param position
     */
    @Override
    public void downMusic(int position) {
        Music.SongListBean songListBean = mMusicList.get(position);

        if (!TextUtils.isEmpty(songListBean.file_link)) {
            addDownloaderList(songListBean);
            return;
        }

        mView.showProgress();
        BaseTSubscriber<Song> subscriber = mModel.getMusicPlayPath(songListBean.song_id).subscribeWith(new BaseTSubscriber<Song>() {
            @Override
            public void onSuccess(Object data) {
                Song song = (Song) data;
                if (song != null) {
                    mMusicList.get(position).file_link = song.bitrate.file_link;
                    mMusicList.get(position).file_duration = song.bitrate.file_duration;
                    addDownloaderList(mMusicList.get(position));
                }
                mView.dismissProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                mView.dismissProgress();
            }
        });
        mView.addDisposable(subscriber);
    }

    /**
     * 加入下载列表
     */
    private void addDownloaderList(Music.SongListBean songListBean) {
        if (OkDownload.getInstance().getTask(songListBean.file_link) != null) {
            mView.showToast("已在队列");
            return;
        }
        LogUtils.e(songListBean.file_link);
        GetRequest<File> request = OkGo.<File>get(songListBean.file_link);
        OkDownload.request(songListBean.file_link, request)
                .extra1(songListBean)
                .save()
                .start();
    }
}
