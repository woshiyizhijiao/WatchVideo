package com.wsyzj.watchvideo.common.business.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 创建时间 : 2017/10/24
 * 编写人 : 焦洋
 * 功能描述 : 音乐播放的服务
 */

public class PlayService extends Service {

    // 当前音乐音乐播放的集中状态
    private static final int STATE_IDLE = 0;        // 闲置
    private static final int STATE_PREPARING = 1;   // 准备
    private static final int STATE_PLAYING = 2;     // 播放
    private static final int STATE_PAUSE = 2;       // 暂停

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
