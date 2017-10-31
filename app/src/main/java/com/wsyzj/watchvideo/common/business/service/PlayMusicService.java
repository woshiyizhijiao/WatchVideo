package com.wsyzj.watchvideo.common.business.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * 音乐播放服务
 */
public class PlayMusicService extends Service {

    private PlayMusicBinder mBinder = new PlayMusicBinder();
    private MediaPlayer mPlayer = new MediaPlayer();

    public PlayMusicService() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class PlayMusicBinder extends Binder {

        /**
         * 播放
         */
        public void play(String songPath) {
            try {
                mPlayer.reset();
                mPlayer.setDataSource(songPath);
                mPlayer.prepare();
                mPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 播放
         */
        public void start() {
            mPlayer.start();
        }

        /**
         * 重新播放
         */
        public void reStart() {
            if (!mPlayer.isPlaying()) {
                mPlayer.start();
            }
        }

        /**
         * 暂停
         */
        public void pause() {
            if (mPlayer.isPlaying()) {
                mPlayer.pause();
            }
        }

        /**
         * 是否在播放
         *
         * @return
         */
        public boolean isPlaying() {
            return mPlayer.isPlaying();
        }

        /**
         * 关闭
         */
        public void close() {
            if (mPlayer != null) {
                mPlayer.reset();
                mPlayer.release();
                mPlayer = null;
            }
        }

        /**
         * 当前播放音乐的长度
         *
         * @return
         */
        public int getDuration() {
            return mPlayer.getDuration();
        }

        /**
         * 获取当前播放位置
         *
         * @return
         */
        public int getCurrentPosition() {
            return mPlayer.getCurrentPosition();
        }

        /**
         * 播放指定位置
         */
        public void seekTo(int msec) {
            mPlayer.seekTo(msec);
        }

        public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
            mPlayer.setOnCompletionListener(onCompletionListener);
        }
    }
}
