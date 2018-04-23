package com.wsyzj.watchvideo.business.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.wsyzj.watchvideo.business.bean.Song;
import com.wsyzj.watchvideo.common.utils.StorageUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/20
 *     desc   : 音乐播放器服务，晒特，
 * </pre>
 */
public class PlayerService extends Service implements MediaPlayer.OnPreparedListener {

    private final static int STATE_IDLE = 0;
    private final static int STATE_PREPARE = 1;
    private final static int STATE_PLAY = 2;
    private final static int STATE_PAUSE = 3;

    private int mState = STATE_IDLE;
    private int mPlayPos = -1;          // 当前播放的位置

    private List<Song> mSongList;
    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        mSongList = new ArrayList<>();

        mPlayPos = StorageUtils.getPlayPos();
        mSongList = StorageUtils.getSongList();

        mMediaPlayer.setOnPreparedListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mPlayerBinder;
    }

    private PlayerBinder mPlayerBinder = new PlayerBinder();

    public class PlayerBinder extends Binder {

        public PlayerService getService() {
            return PlayerService.this;
        }
    }

    /**
     * 播放
     */
    public void play(Song song) {
        boolean contains = mSongList.contains(song);

        if (mSongList.contains(song)) {
            mSongList.add(song);
            mPlayPos = mSongList.size() - 1;
        } else {
            mPlayPos = mSongList.indexOf(song);
        }

        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(song.bitrate.file_link);
            mMediaPlayer.prepareAsync();
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
        mMediaPlayer.start();
        mState = STATE_PLAY;
    }

    /**
     * 开始
     */
    public void start() {
        if (!isPrepare() && !isPause()) {
            return;
        }
        mMediaPlayer.start();
        mState = STATE_PLAY;
    }

    /**
     * 暂停
     */
    public void pause() {
        if (!isPlay()) {
            return;
        }
        mMediaPlayer.pause();
        mState = STATE_PAUSE;
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

        Song song;
        int positon = mPlayPos + 1;

        if (positon < 0 || positon > mSongList.size() - 1) {
            song = mSongList.get(0);
        } else {
            song = mSongList.get(positon);
        }

        Song songPre = mSongList.get(mPlayPos);
        if (songPre.equals(song)) {
            return;
        }

        play(mSongList.get(mPlayPos));
    }

    /**
     * 上一首
     */
    public void previous() {
        if (mSongList.isEmpty()) {
            return;
        }

        int indexOf = mSongList.indexOf(mSongList.get(mPlayPos));
        if (indexOf == mPlayPos) {
            return;
        }

        Song song;
        int positon = mPlayPos - 1;
        if (positon < 0 || positon > mSongList.size() - 1) {
            song = mSongList.get(0);
        } else {
            song = mSongList.get(positon);
        }

        Song songPre = mSongList.get(mPlayPos);
        if (songPre.equals(song)) {
            return;
        }

        play(song);
    }

    /**
     * 设置当前的播放位置
     *
     * @param playPos
     */
    private void setPlayPos(int playPos) {
        mPlayPos = playPos;
        StorageUtils.putPlayPos(mPlayPos);
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
