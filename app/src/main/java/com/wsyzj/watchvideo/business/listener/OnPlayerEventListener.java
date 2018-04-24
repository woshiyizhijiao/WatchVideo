package com.wsyzj.watchvideo.business.listener;

import com.wsyzj.watchvideo.business.bean.Music;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/24s
 *     desc   : 播放进度监听器
 * </pre>
 */
public interface OnPlayerEventListener {

    /**
     * 切换音乐
     *
     * @param songListBean
     */
    void onPlayerChanged(Music.SongListBean songListBean);

    /**
     * 继续播放
     */
    void onPlayerStart();

    /**
     * 暂停
     */
    void onPlayerPasue();

    /**
     * 播放进度
     */
    void onPlayerProgress(int progress);
}
