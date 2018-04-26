package com.wsyzj.watchvideo.business.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wsyzj.watchvideo.business.service.PlayerManager;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/26
 *     desc   : 来电/耳机拨出时暂停播放
 * </pre>
 */
public class NoisyAudioStreamReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        PlayerManager.get().playPause();
    }
}
