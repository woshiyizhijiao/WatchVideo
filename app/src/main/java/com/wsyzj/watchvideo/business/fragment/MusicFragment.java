package com.wsyzj.watchvideo.business.fragment;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.MusicAdapter;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.Song;
import com.wsyzj.watchvideo.business.mvp.MusicContract;
import com.wsyzj.watchvideo.business.mvp.MusicPresenter;
import com.wsyzj.watchvideo.business.service.PlayMusicService;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.http.ImageLoader;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.util.List;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2017/12/21 11:00
 * @Description: $desc$
 */
public class MusicFragment extends BaseFragment implements MusicContract.View {

    private static final int UPDATE_SONG_PROGRESS_TIME = 500;   // 更新音乐进度的时间
    private static final int MSG_CHANGED_MEDIA_PROGRESS = 101;  // 监听音乐播放进度

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.iv_conver)
    ImageView iv_conver;

    @BindView(R.id.iv_play_pause)
    ImageView iv_play_pause;

    private MusicPresenter mPresenter;
    private MusicAdapter mMusicAdapter;

    private Intent mPlayMusicService;
    private boolean isRegisterPlayService;
    private boolean isPlayMusic;    // 是否正在播放

    private PlayMusicService.PlayMusicBinder mPlayMusicBinder;

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPlayMusicBinder = (PlayMusicService.PlayMusicBinder) service;
            isRegisterPlayService = true;
            mPlayMusicBinder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mPresenter.next();
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_CHANGED_MEDIA_PROGRESS:
                    int currentPosition = mPlayMusicBinder.getCurrentPosition();
                    progressBar.setProgress(currentPosition / 1000);
                    mHandler.sendEmptyMessageDelayed(MSG_CHANGED_MEDIA_PROGRESS, UPDATE_SONG_PROGRESS_TIME);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected BaseIPresenter presenter() {
        mPresenter = new MusicPresenter(this);
        return mPresenter;
    }

    @Override
    public int contentView() {
        return R.layout.fragment_music;
    }

    @Override
    public void initView() {
//        pull_to_refresh.setOnRefreshListener(this);
//        pull_to_refresh.setRequestLoadMoreListener(this);
    }

    @Override
    public void initData() {
        initPlayMusicService();
        mPresenter.getMusicList(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(MSG_CHANGED_MEDIA_PROGRESS);
        if (isRegisterPlayService && mConn != null) {
            mPlayMusicBinder.onDestroy();
            mActivity.unbindService(mConn);
        }
    }

    /**
     * 设置音乐列表
     *
     * @param musicList
     */
    @Override
    public void setMusicList(List<Music.SongListBean> musicList) {
//        pull_to_refresh.getSwipeRefresh().setEnabled(false);
        if (mMusicAdapter == null) {
            mMusicAdapter = new MusicAdapter(R.layout.item_music, musicList);
            pull_to_refresh.setLayoutManager(new LinearLayoutManager(mActivity));
            pull_to_refresh.setAdapter(mMusicAdapter);
//            mMusicAdapter.setOnItemClickListener(this);
        } else {
            mMusicAdapter.setNewData(musicList);
        }
    }


    private void initPlayMusicService() {
        mPlayMusicService = new Intent(mActivity, PlayMusicService.class);
        mActivity.startService(mPlayMusicService);
        mActivity.bindService(mPlayMusicService, mConn, Activity.BIND_AUTO_CREATE);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
//        pull_to_refresh.setRefreshing(refreshing);
    }

    @Override
    public void setLoadMoreState(int totalCount) {
        pull_to_refresh.setLoadMoreState(totalCount);
    }

    /**
     * 播放歌曲
     *
     * @param song
     */
    @Override
    public void setSongInfo(Song song) {
        String pic_big = mMusicAdapter.getData().get(mPresenter.mCurrentPos).pic_big;
        ImageLoader.with(mActivity, pic_big, R.drawable.bg_player_default_cover, R.drawable.bg_player_default_cover, iv_conver);
        iv_play_pause.setImageResource(R.drawable.ic_pause_circle_filled_black_48dp);
        progressBar.setMax(song.bitrate.file_duration);
        progressBar.setProgress(0);
        mPlayMusicBinder.play(song.bitrate.file_link);
        mHandler.sendEmptyMessageDelayed(MSG_CHANGED_MEDIA_PROGRESS, UPDATE_SONG_PROGRESS_TIME);
        isPlayMusic = true;
    }

//    /**
//     * 下拉刷新
//     */
//    @Override
//    public void onRefresh() {
//        mPresenter.getMusicList(true);
//    }
//
//    /**
//     * 下载更多
//     */
//    @Override
//    public void onLoadMoreRequested() {
//        mPresenter.getMusicList(false);
//    }
//
//    @OnClick({R.id.iv_play_pause, R.id.iv_previous, R.id.iv_next})
//    public void bkOnClick(View view) {
//        switch (view.getId()) {
//            case R.id.iv_play_pause:
//                if (mPlayMusicBinder.isPlaying()) {
//                    isPlayMusic = false;
//                    mPlayMusicBinder.pause();
//                    iv_play_pause.setImageResource(R.drawable.ic_play_circle_filled_white_black_48dp);
//                    mHandler.removeMessages(MSG_CHANGED_MEDIA_PROGRESS);
//                } else {
//                    isPlayMusic = true;
//                    mPlayMusicBinder.start();
//                    iv_play_pause.setImageResource(R.drawable.ic_pause_circle_filled_black_48dp);
//                    mHandler.sendEmptyMessageDelayed(MSG_CHANGED_MEDIA_PROGRESS, UPDATE_SONG_PROGRESS_TIME);
//                }
//                break;
//            case R.id.iv_previous:
//                mPresenter.previous();
//                break;
//            case R.id.iv_next:
//                mPresenter.next();
//                break;
//            default:
//                break;
//        }
//    }

//    @Override
//    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        mPresenter.mCurrentPos = position;
//        Music.SongListBean bean = mMusicAdapter.getData().get(position);
//        mPresenter.getMusicPlayPath(bean.song_id);
//    }
}
