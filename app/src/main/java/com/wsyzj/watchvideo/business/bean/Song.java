package com.wsyzj.watchvideo.business.bean;

/**
 * 创建时间 : 2017/10/24
 * 编写人 : 焦洋
 * 功能描述 : 获取音乐播放地址
 */

public class Song {


    /**
     * bitrate : {"file_duration":310,"file_link":"http://yinyueshiting.baidu.com/data2/music/62cf8e4129d6fb866ed7b8e31167ac21/559184467/559184467.mp3?xcode=a1bb4bddd9b117730d9b8f0c677c76d2"}
     */

    public BitrateBean bitrate;

    public static class BitrateBean {
        /**
         * file_duration : 310
         * file_link : http://yinyueshiting.baidu.com/data2/music/62cf8e4129d6fb866ed7b8e31167ac21/559184467/559184467.mp3?xcode=a1bb4bddd9b117730d9b8f0c677c76d2
         */

        public int file_duration;
        public String file_link;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Song)) {
            return false;
        }

        Song song = (Song) obj;
        return bitrate.file_link.equals(song.bitrate.file_link);
    }
}
