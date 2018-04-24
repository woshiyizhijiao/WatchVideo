package com.wsyzj.watchvideo.common.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.BaseApp;
import com.wsyzj.watchvideo.business.bean.Music;

import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/14
 *     desc   : 在SPUtils的基础上对存储的值进行二次封装，对外不直接操作键名，取值和设置值使用本类的方法
 * </pre>
 */
public class StorageUtils {

    private static final Gson GSON = new Gson();

    private static final String COLOR_PRIMARY = "color_primary";

    /**
     * 获取主题颜色
     */
    public static int getColorPrimary() {
        return (int) SPUtils.get(BaseApp.getApp(), COLOR_PRIMARY, R.color.colorPrimary);
    }

    /**
     * 设置主题颜色
     */
    public static void putColorPrimary(int colorPrimary) {
        SPUtils.put(BaseApp.getApp(), COLOR_PRIMARY, colorPrimary);
    }

    private static final String SONG_LIST = "song_list";

    /**
     * 将音乐加入缓存中
     *
     * @return
     */
    public static void putSongList(Music.SongListBean song) {
        List<Music.SongListBean> list = getSongList();
        if (list != null) {
            list.add(song);
        }
        SPUtils.put(BaseApp.getApp(), SONG_LIST, GSON.toJson(list));
    }

    /**
     * @return
     */
    public static List<Music.SongListBean> getSongList() {
        String songsJson = (String) SPUtils.get(BaseApp.getApp(), SONG_LIST, "[]");
        return GSON.fromJson(songsJson, new TypeToken<List<Music.SongListBean>>() {
        }.getType());
    }

    public static final String PLAY_POSITION = "play_position";

    /**
     * 记录当前音乐播放的位置
     */
    public static void putPlayPos(int playPos) {
        SPUtils.put(BaseApp.getApp(), PLAY_POSITION, playPos);
    }

    /**
     * 获取当前音乐播放的位置
     *
     * @return
     */
    public static int getPlayPos() {
        return (int) SPUtils.get(BaseApp.getApp(), PLAY_POSITION, -1);
    }
}
