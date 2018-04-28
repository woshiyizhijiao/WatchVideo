package com.wsyzj.watchvideo.business.service;

import android.content.Context;
import android.media.AudioManager;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/26
 *     desc   : 处理音频焦点
 * </pre>
 */
public class AudioFocusManager implements AudioManager.OnAudioFocusChangeListener {

    private Context mContext;
    private AudioManager mAudioManager;
    private boolean isPausedByFocusLossTransient;

    public AudioFocusManager(Context context) {
        mContext = context;
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public boolean requestAudioFocus() {
        return mAudioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN)
                == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
    }

    public void abandonAudioFocus() {
        mAudioManager.abandonAudioFocus(this);
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            // 重新获取焦点
            case AudioManager.AUDIOFOCUS_GAIN:
                if (isPausedByFocusLossTransient) {
                    // 通话结束，恢复播放
                    PlayerManager.getInstance().start();
                }
                PlayerManager.getInstance().getMediaPlayer().setVolume(1f, 1f);
                isPausedByFocusLossTransient = false;
                break;
            // 永久失去焦点，被其他播放器抢占
            case AudioManager.AUDIOFOCUS_LOSS:
                PlayerManager.getInstance().pause();
                break;
            // 短暂失去焦点，如来电
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                PlayerManager.getInstance().pause(false);
                isPausedByFocusLossTransient = true;
                break;
            // 瞬间失去焦点，如通知
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                PlayerManager.getInstance().getMediaPlayer().setVolume(0.5f, 0.5f);
                break;
            default:
                break;
        }
    }
}
