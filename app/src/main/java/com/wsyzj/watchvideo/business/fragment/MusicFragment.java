package com.wsyzj.watchvideo.business.fragment;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.MusicAdapter;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.Song;
import com.wsyzj.watchvideo.business.mvp.MusicContract;
import com.wsyzj.watchvideo.business.mvp.MusicPresenter;
import com.wsyzj.watchvideo.business.service.PlayMusicService;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间 : 2017/10/25
 * 编写人 : 焦洋
 * 功能描述 : 音乐
 */

public class MusicFragment extends BaseFragment implements MusicContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.RequestLoadMoreListener, SeekBar.OnSeekBarChangeListener {
    private static final int UPDATE_SONG_PROGRESS_TIME = 500;   // 更新音乐进度的时间
    private static final int MSG_CHANGED_MEDIA_PROGRESS = 101;  // 监听音乐播放进度

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    @BindView(R.id.img_player_state)
    ImageView img_player_state;

    @BindView(R.id.sb_progress)
    SeekBar sb_progress;

    private MusicPresenter mPresenter;
    private MusicAdapter mAdapter;
    private Intent mPlayMusicService;
    private boolean mIsRegister;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_CHANGED_MEDIA_PROGRESS:
                    int currentPosition = mPlayMusicBinder.getCurrentPosition();
                    sb_progress.setProgress(currentPosition / 1000);
                    mHandler.sendEmptyMessageDelayed(MSG_CHANGED_MEDIA_PROGRESS, UPDATE_SONG_PROGRESS_TIME);
                    break;
                default:
                    break;
            }
        }
    };
    private PlayMusicService.PlayMusicBinder mPlayMusicBinder;

    @Override
    protected BasePresenter presenter() {
        mPresenter = new MusicPresenter(this);
        return mPresenter;
    }

    @Override
    public int contentView() {
        return R.layout.fragment_music;
    }

    @Override
    public void initView(View view) {
        pull_to_refresh.setOnRefreshListener(this);
        pull_to_refresh.setRequestLoadMoreListener(this);
        sb_progress.setOnSeekBarChangeListener(this);
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
        if (mIsRegister) {
            mActivity.unbindService(mConn);
        }
    }

    /**
     * 开启音乐的服务
     */
    private void initPlayMusicService() {
        mPlayMusicService = new Intent(mActivity, PlayMusicService.class);
        mActivity.startService(mPlayMusicService);
        mActivity.bindService(mPlayMusicService, mConn, Activity.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPlayMusicBinder = (PlayMusicService.PlayMusicBinder) service;
            mIsRegister = true;
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

    /**
     * 设置音乐列表数据
     *
     * @param musicList
     */
    @Override
    public void setMusicList(List<Music.SongListBean> musicList) {
        if (mAdapter == null) {
            mAdapter = new MusicAdapter(mActivity, R.layout.item_music_adapter, musicList);
            pull_to_refresh.setAdapter(mAdapter);
            pull_to_refresh.setLayoutManager(new LinearLayoutManager(mActivity));
            mAdapter.setOnItemClickListener(this);
        } else {
            mAdapter.setNewData(musicList);
        }
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mPresenter.getMusicList(true);
    }


    @Override
    public void setRefreshing(boolean refreshing) {
        pull_to_refresh.setRefreshing(refreshing);
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMusicList(false);
    }

    /**
     * 加载更多的状态
     *
     * @param totalCount
     * @param currentCount
     */
    @Override
    public void setLoadMoreState(int totalCount, int currentCount) {
        pull_to_refresh.setLoadMoreState(totalCount);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPresenter.mCurrentPos = position;
        Music.SongListBean bean = mAdapter.getData().get(position);
        mPresenter.getMusicPlayPath(bean.song_id);
    }

    /**
     * 设置歌曲信息播放
     *
     * @param song
     */
    @Override
    public void setSongInfo(Song song) {
        sb_progress.setMax(song.bitrate.file_duration);
        sb_progress.setProgress(0);
        mPlayMusicBinder.play(song.bitrate.file_link);
        mHandler.sendEmptyMessageDelayed(MSG_CHANGED_MEDIA_PROGRESS, UPDATE_SONG_PROGRESS_TIME);
    }

    /**
     * 切换暂停播放
     */
    @OnClick(R.id.frame_player)
    public void player() {
        if (mPlayMusicBinder.isPlaying()) {
            mPlayMusicBinder.pause();
            img_player_state.setImageResource(R.drawable.btn_playback_pause);
            mHandler.removeMessages(MSG_CHANGED_MEDIA_PROGRESS);
        } else {
            mPlayMusicBinder.start();
            img_player_state.setImageResource(R.drawable.btn_playback_play);
            mHandler.sendEmptyMessageDelayed(MSG_CHANGED_MEDIA_PROGRESS, UPDATE_SONG_PROGRESS_TIME);
        }
    }

    /**
     * 上一首
     */
    @OnClick(R.id.img_previous)
    public void previous() {
        mPresenter.previous();
    }

    /**
     * 下一首
     */
    @OnClick(R.id.img_next)
    public void next() {
        mPresenter.next();
    }

    /**
     * 拖拽音乐进度
     *
     * @param seekBar
     * @param i
     * @param b
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int progress = seekBar.getProgress();
        mPlayMusicBinder.seekTo(progress * 1000);
    }
}
