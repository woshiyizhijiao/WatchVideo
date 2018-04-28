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
 *     time   : 2018/04/27
 *     desc   : 音乐下载的服务
 * </pre>
 */
public class MusicDownLoaderService extends Service {

    private MusicDownLoaderBinder mBinder = new MusicDownLoaderBinder();

    private class MusicDownLoaderBinder extends Binder {
        public MusicDownLoaderService getService() {
            return MusicDownLoaderService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
