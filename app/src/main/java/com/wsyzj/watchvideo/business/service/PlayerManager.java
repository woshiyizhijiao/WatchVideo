package com.wsyzj.watchvideo.business.service;

import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;

import com.blankj.utilcode.util.ToastUtils;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.listener.OnPlayerEventListener;
import com.wsyzj.watchvideo.business.receiver.NoisyAudioStreamReceiver;
import com.wsyzj.watchvideo.common.utils.StorageUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/25
 *     desc   : 音乐服务管理
 * </pre>
 */
public class PlayerManager implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private static final int STATE_IDLE = 0;
    private static final int STATE_PREPARE = 1;
    private static final int STATE_PLAY = 2;
    private static final int STATE_PAUSE = 3;
    private static final int UPDATE_PROGRESS_TIME = 1000;

    private Context mContext;
    private int mState = STATE_IDLE;
    private int mPlayPos;          // 当前播放的位置

    private List<Music.SongListBean> mSongList;
    private List<OnPlayerEventListener> mOnPlayerEventListeners;

    private Handler mHandler;
    private MediaPlayer mMediaPlayer;
    private AudioFocusManager mAudioFocusManager;
    private IntentFilter mNoisyFilter;
    private NoisyAudioStreamReceiver mNoisyAudioStreamReceiver;

    private PlayerManager() {

    }

    private static class SingletonHolder {
        private static PlayerManager sInstance = new PlayerManager();
    }

    public static PlayerManager get() {
        return SingletonHolder.sInstance;
    }


    public void init(Context context) {
        mContext = context;

        mPlayPos = StorageUtils.getPlayPos();
        mSongList = StorageUtils.getSongList();

        mSongList = new ArrayList<>();
        mOnPlayerEventListeners = new ArrayList<>();

        mHandler = new Handler(Looper.getMainLooper());
        mMediaPlayer = new MediaPlayer();
        mAudioFocusManager = new AudioFocusManager(context);
        mNoisyFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        mNoisyAudioStreamReceiver = new NoisyAudioStreamReceiver();

        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnCompletionListener(this);
    }

    /**
     * @param onPlayerEventListener
     */
    public void addOnPlayerEventListener(OnPlayerEventListener onPlayerEventListener) {
        if (!mOnPlayerEventListeners.contains(onPlayerEventListener)) {
            mOnPlayerEventListeners.add(onPlayerEventListener);
        }
    }

    public void removeOnPlayerEventListener(OnPlayerEventListener onPlayerEventListener) {
        mOnPlayerEventListeners.remove(onPlayerEventListener);
    }


    /**
     * 添加到播放列表并且播放
     */
    public void addAndPlay(Music.SongListBean song) {
        if (mSongList == null) {
            return;
        }
        if (!mSongList.contains(song)) {
            mSongList.add(song);
            mPlayPos = mSongList.size() - 1;
            setPlayPos();
            StorageUtils.putSongList(song);
        }
        play(song);
    }

    /**
     * 播放
     */
    public void play(Music.SongListBean songListBean) {
        if (songListBean == null) {
            return;
        }

        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(songListBean.file_link);
            mMediaPlayer.prepareAsync();
            mState = STATE_PREPARE;

            for (OnPlayerEventListener onPlayerEventListener : mOnPlayerEventListeners) {
                onPlayerEventListener.onPlayerChanged(songListBean);
            }
        } catch (IOException e) {
            e.printStackTrace();
            ToastUtils.showShort("播放错误");
        }
    }

    /**
     * 音频准备就绪的回调
     *
     * @param mp
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        start();
    }

    /**
     * 播放完成的监听
     *
     * @param mp
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        next();
    }

    /**
     * 播放/暂停
     */
    public void playPause() {
        if (isPrepare()) {
            stop();
        } else if (isPlay()) {
            pause();
        } else if (isPause()) {
            start();
        } else {
            play(getPlaySong());
        }
    }

    /**
     * 重新播放
     */
    public void start() {
        if (!isPrepare() && !isPause()) {
            return;
        }

        if (mAudioFocusManager.requestAudioFocus()) {
            mMediaPlayer.start();
            mState = STATE_PLAY;
            mHandler.post(mProgressRun);
            mContext.registerReceiver(mNoisyAudioStreamReceiver, mNoisyFilter);

            for (OnPlayerEventListener listener : mOnPlayerEventListeners) {
                listener.onPlayerStart();
            }
        }
    }

    /**
     * 更新音乐进度
     */
    private Runnable mProgressRun = new Runnable() {
        @Override
        public void run() {
            if (isPlay()) {
                for (OnPlayerEventListener listener : mOnPlayerEventListeners) {
                    listener.onPlayerProgress(mMediaPlayer.getCurrentPosition());
                }
            }
            mHandler.postDelayed(this, UPDATE_PROGRESS_TIME);
        }
    };

    /**
     * 暂停
     */
    public void pause() {
        pause(true);
    }

    /**
     * 暂停
     *
     * @param abandonAudioFocus 放弃音频焦点
     */
    public void pause(boolean abandonAudioFocus) {
        if (!isPlay()) {
            return;
        }

        if (abandonAudioFocus) {
            mAudioFocusManager.abandonAudioFocus();
        }

        mMediaPlayer.pause();
        mState = STATE_PAUSE;
        mHandler.removeCallbacks(mProgressRun);
        mContext.unregisterReceiver(mNoisyAudioStreamReceiver);

        for (OnPlayerEventListener listener : mOnPlayerEventListeners) {
            listener.onPlayerPasue();
        }
    }

    /**
     * 停止
     */
    public void stop() {
        if (isIdle()) {
            return;
        }
        pause();
        mMediaPlayer.reset();
        mState = STATE_IDLE;
    }

    /**
     * 下一首
     */
    public void next() {
        if (mSongList.isEmpty()) {
            return;
        }

        Music.SongListBean preSong = mSongList.get(mPlayPos);
        int position = mPlayPos + 1;

        if (position > mSongList.size() - 1) {
            mPlayPos = 0;
        } else {
            mPlayPos = position;
        }

        Music.SongListBean song = mSongList.get(mPlayPos);
        if (preSong.file_link.equals(song.file_link)) {
            return;
        }

        setPlayPos();
        play(song);
    }

    /**
     * 上一首
     */
    public void previous() {
        if (mSongList.isEmpty()) {
            return;
        }

        Music.SongListBean preSong = mSongList.get(mPlayPos);
        int position = mPlayPos - 1;

        if (position < 0 || position > mSongList.size() - 1) {
            mPlayPos = 0;
        } else {
            mPlayPos = position;
        }

        Music.SongListBean song = mSongList.get(mPlayPos);
        if (preSong.file_link.equals(song.file_link)) {
            return;
        }

        setPlayPos();
        play(song);
    }

    /**
     * 设置当前的播放位置
     */
    private void setPlayPos() {
        if (mPlayPos < 0 || mPlayPos > mSongList.size() - 1) {
            mPlayPos = 0;
        }
        StorageUtils.putPlayPos(mPlayPos);
    }

    /**
     * 获取当前播放的位置
     *
     * @return
     */
    public int getPlayPos() {
        return StorageUtils.getPlayPos();
    }

    /**
     * 获取当前播放的音乐
     *
     * @return
     */
    public Music.SongListBean getPlaySong() {
        if (mSongList.isEmpty()) {
            return null;
        }
        if (mPlayPos >= 0 && mPlayPos < mSongList.size()) {
            return mSongList.get(mPlayPos);
        }
        return null;
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    /**
     * 音频长度
     *
     * @return
     */
    public int getDuration() {
        return mMediaPlayer.getDuration();
    }

    public boolean isIdle() {
        return mState == STATE_IDLE;
    }

    public boolean isPrepare() {
        return mState == STATE_PREPARE;
    }

    public boolean isPlay() {
        return mState == STATE_PLAY;
    }

    public boolean isPause() {
        return mState == STATE_PAUSE;
    }
}
