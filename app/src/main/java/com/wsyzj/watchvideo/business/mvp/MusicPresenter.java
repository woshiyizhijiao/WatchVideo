package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.Song;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;
import com.wsyzj.watchvideo.common.widget.BaseState;

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
    private List<Music.SongListBean> mMusicList;

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
            public void onFailure() {
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
        mView.showProgress();
        Music.SongListBean bean = mMusicList.get(position);
        BaseTSubscriber<Song> subscriber = mModel.getMusicPlayPath(bean.song_id).subscribeWith(new BaseTSubscriber<Song>() {
            @Override
            public void onSuccess(Object data) {
                Song song = (Song) data;
                if (song != null) {
                    mView.setPlaySong(song);
                }
                mView.dismissProgress();
            }

            @Override
            public void onFailure() {
                mView.dismissProgress();
            }
        });
        mView.addDisposable(subscriber);
    }
}
