package com.wsyzj.watchvideo.business.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/20
 *     desc   : 音乐播放器服务，晒特，
 * </pre>
 */
public class PlayerService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        PlayerManager.get().init(this);
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
}
