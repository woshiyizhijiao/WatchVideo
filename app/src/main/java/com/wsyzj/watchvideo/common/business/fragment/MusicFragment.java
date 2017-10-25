package com.wsyzj.watchvideo.common.business.fragment;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.business.adapter.MusicAdapter;
import com.wsyzj.watchvideo.common.business.bean.Music;
import com.wsyzj.watchvideo.common.business.bean.Song;
import com.wsyzj.watchvideo.common.business.mvp.MusicContract;
import com.wsyzj.watchvideo.common.business.mvp.MusicPresenter;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 创建时间 : 2017/10/25
 * 编写人 : 焦洋
 * 功能描述 : 音乐
 */

public class MusicFragment extends BaseFragment implements MusicContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.RequestLoadMoreListener, SeekBar.OnSeekBarChangeListener, MediaPlayer.OnCompletionListener {

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
    private MediaPlayer mMediaPlayer = new MediaPlayer();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_CHANGED_MEDIA_PROGRESS:
                    int currentPosition = mMediaPlayer.getCurrentPosition();
                    sb_progress.setProgress(currentPosition / 1000);
                    mHandler.sendEmptyMessageDelayed(MSG_CHANGED_MEDIA_PROGRESS, UPDATE_SONG_PROGRESS_TIME);
                    break;
                default:
                    break;
            }
        }
    };

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
    public void initView() {
        pull_to_refresh.setOnRefreshListener(this);
        pull_to_refresh.setRequestLoadMoreListener(this);
        sb_progress.setOnSeekBarChangeListener(this);
        mMediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public void initData() {
        mPresenter.getMusicList(true);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(MSG_CHANGED_MEDIA_PROGRESS);
        mMediaPlayer.reset();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

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
        pull_to_refresh.setLoadMoreState(totalCount, currentCount);
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
        play(song.bitrate.file_link);
    }

    private void play(String path) {
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            mHandler.sendEmptyMessageDelayed(MSG_CHANGED_MEDIA_PROGRESS, UPDATE_SONG_PROGRESS_TIME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 切换暂停播放
     */
    @OnClick(R.id.frame_player)
    public void player() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            img_player_state.setImageResource(R.drawable.btn_playback_pause);
            mHandler.removeMessages(MSG_CHANGED_MEDIA_PROGRESS);
        } else {
            mMediaPlayer.start();
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
        mMediaPlayer.seekTo(progress * 1000);
    }

    /**
     * 播放完成
     *
     * @param mediaPlayer
     */
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mPresenter.next();
    }
}
