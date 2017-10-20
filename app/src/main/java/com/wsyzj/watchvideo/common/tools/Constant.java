package com.wsyzj.watchvideo.common.tools;

/**
 * @author: wsyzj
 * @date: 2017-08-26 22:47
 * @comment: 一些配置存放
 */
public class Constant {
    // 音乐的地址
    public static String BASE_URL = "http://tingapi.ting.baidu.com/";

    // 音乐播放
    public static class PlayMusic {
        public static final String EXTRA_NOTIFICATION = "me.wcy.music.notification";
        public static final String MUSIC_LIST_TYPE = "music_list_type";
        public static final String TING_UID = "ting_uid";
        public static final String MUSIC = "music";

        public static final int REQUEST_WRITE_SETTINGS = 0;
        public static final int REQUEST_ALBUM = 1;
        public static final int REQUEST_CORP = 2;

        public static final String ACTION_MEDIA_PLAY_PAUSE = "me.wcy.music.ACTION_MEDIA_PLAY_PAUSE";
        public static final String ACTION_MEDIA_NEXT = "me.wcy.music.ACTION_MEDIA_NEXT";
        public static final String ACTION_MEDIA_PREVIOUS = "me.wcy.music.ACTION_MEDIA_PREVIOUS";
        public static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    }
}
