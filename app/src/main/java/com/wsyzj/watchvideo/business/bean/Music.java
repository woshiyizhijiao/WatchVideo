package com.wsyzj.watchvideo.business.bean;

import java.util.List;

/**
 * 单曲信息
 * Created by wcy on 2015/11/27.
 */
public class Music {


    /**
     * billboard : {"comment":"该榜单是根据百度音乐平台歌曲每周播放量自动生成的数据榜单，统计范围为百度音乐平台上的全部歌曲，每日更新一次","name":"热歌榜","pic_s210":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_58c1700bf56062108d8d622a95708032.jpg","pic_s260":"http://a.hiphotos.baidu.com/ting/pic/item/838ba61ea8d3fd1f1326c83c324e251f95ca5f8c.jpg","pic_s444":"http://d.hiphotos.baidu.com/ting/pic/item/c83d70cf3bc79f3d98ca8e36b8a1cd11728b2988.jpg","pic_s640":"http://b.hiphotos.baidu.com/ting/pic/item/5d6034a85edf8db1194683910b23dd54574e74df.jpg","update_date":"2017-10-22"}
     * song_list : [{"album_title":"无法长大","artist_name":"赵雷","lrclink":"http://musicdata.baidu.com/data2/lrc/b02df89ece73338f2248f32813f35ddd/549929948/549929948.lrc","pic_big":"http://musicdata.baidu.com/data2/pic/cd8dcc4f40cbb37c7dcf0e6c151fbcc6/275347355/275347355.jpg@s_1,w_150,h_150","pic_small":"http://musicdata.baidu.com/data2/pic/cd8dcc4f40cbb37c7dcf0e6c151fbcc6/275347355/275347355.jpg@s_1,w_90,h_90","song_id":"274841326","ting_uid":"90654808","title":"成都"},{"album_title":"意外","artist_name":"薛之谦","lrclink":"http://musicdata.baidu.com/data2/lrc/238665983/238665983.lrc","pic_big":"http://musicdata.baidu.com/data2/pic/5dd1ba70bb3e2d9d7fc79cd614130c8c/93104033/93104033.jpg@s_1,w_150,h_150","pic_small":"http://musicdata.baidu.com/data2/pic/5dd1ba70bb3e2d9d7fc79cd614130c8c/93104033/93104033.jpg@s_1,w_90,h_90","song_id":"100575177","ting_uid":"2517","title":"你还要我怎样"},{"album_title":"夏至未至 电视原声带","artist_name":"岑宁儿","lrclink":"http://musicdata.baidu.com/data2/lrc/fa91441061758b2ba4a1c28c5f18d3c0/542373665/542373665.lrc","pic_big":"http://musicdata.baidu.com/data2/pic/58754f79bde49e3ee8fd6c5326e5a240/541982838/541982838.jpg@s_1,w_150,h_150","pic_small":"http://musicdata.baidu.com/data2/pic/58754f79bde49e3ee8fd6c5326e5a240/541982838/541982838.jpg@s_1,w_90,h_90","song_id":"542369506","ting_uid":"556015","title":"追光者"},{"album_title":"半壶纱","artist_name":"刘珂矣","lrclink":"http://musicdata.baidu.com/data2/lrc/528f6a9ec04a984931c3170a3c74c64b/267170581/267170581.lrc","pic_big":"http://musicdata.baidu.com/data2/pic/260983575/260983575.jpg@s_0,w_150","pic_small":"http://musicdata.baidu.com/data2/pic/260983575/260983575.jpg@s_0,w_90","song_id":"121353608","ting_uid":"132632388","title":"半壶纱"},{"album_title":"红颜旧","artist_name":"崔子格","lrclink":"http://musicdata.baidu.com/data2/lrc/936c7028f3d4231a5cb13d25c6259cdc/554926295/554926295.lrc","pic_big":"http://musicdata.baidu.com/data2/pic/96323321dfdc24e7b0df35a7daa66cdc/554924383/554924383.jpg@s_1,w_150,h_150","pic_small":"http://musicdata.baidu.com/data2/pic/96323321dfdc24e7b0df35a7daa66cdc/554924383/554924383.jpg@s_1,w_90,h_90","song_id":"554926752","ting_uid":"1224778","title":"红颜旧"}]
     */

    public BillboardBean billboard;
    public List<SongListBean> song_list;

    public static class BillboardBean {
        /**
         * comment : 该榜单是根据百度音乐平台歌曲每周播放量自动生成的数据榜单，统计范围为百度音乐平台上的全部歌曲，每日更新一次
         * name : 热歌榜
         * pic_s210 : http://business.cdn.qianqian.com/qianqian/pic/bos_client_58c1700bf56062108d8d622a95708032.jpg
         * pic_s260 : http://a.hiphotos.baidu.com/ting/pic/item/838ba61ea8d3fd1f1326c83c324e251f95ca5f8c.jpg
         * pic_s444 : http://d.hiphotos.baidu.com/ting/pic/item/c83d70cf3bc79f3d98ca8e36b8a1cd11728b2988.jpg
         * pic_s640 : http://b.hiphotos.baidu.com/ting/pic/item/5d6034a85edf8db1194683910b23dd54574e74df.jpg
         * update_date : 2017-10-22
         */

        public String comment;
        public String name;
        public String pic_s210;
        public String pic_s260;
        public String pic_s444;
        public String pic_s640;
        public String update_date;
    }

    public static class SongListBean {
        /**
         * album_title : 无法长大
         * artist_name : 赵雷
         * lrclink : http://musicdata.baidu.com/data2/lrc/b02df89ece73338f2248f32813f35ddd/549929948/549929948.lrc
         * pic_big : http://musicdata.baidu.com/data2/pic/cd8dcc4f40cbb37c7dcf0e6c151fbcc6/275347355/275347355.jpg@s_1,w_150,h_150
         * pic_small : http://musicdata.baidu.com/data2/pic/cd8dcc4f40cbb37c7dcf0e6c151fbcc6/275347355/275347355.jpg@s_1,w_90,h_90
         * song_id : 274841326
         * ting_uid : 90654808
         * title : 成都
         */

        public String album_title;
        public String artist_name;
        public String lrclink;
        public String pic_big;
        public String pic_small;
        public String song_id;
        public String ting_uid;
        public String title;
        public String file_link;
        public int file_duration;
    }
}
