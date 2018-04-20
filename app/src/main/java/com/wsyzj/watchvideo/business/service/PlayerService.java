package com.wsyzj.watchvideo.business.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wsyzj.watchvideo.business.bean.Song;

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
public class PlayerService extends Service {

    private final static int STATE_IDLE = 0;
    private final static int STATE_PREPARE = 1;
    private final static int STATE_PLAY = 2;
    private final static int STATE_PAUSE = 3;

    private int mState = STATE_IDLE;
    private List<Song> mSongList;
    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        mSongList = new ArrayList<>();
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
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(song.bitrate.file_link);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
            ToastUtils.showShort("播放错误");
        }
    }

    /**
     * 暂停
     */
    public void pause() {
        LogUtils.e("暂停");
    }

    /**
     * 下一首
     */
    public void next() {
        LogUtils.e("下一首");
    }

    /**
     * 上一首
     */
    public void previous() {
        LogUtils.e("上一首");
    }

    private boolean isIdle() {
        return mState == STATE_IDLE;
    }

    private boolean isPrepare() {
        return mState == STATE_PREPARE;
    }

    private boolean isPlay() {
        return mState == STATE_PLAY;
    }

    private boolean isPause() {
        return mState == STATE_PAUSE;
    }
}
