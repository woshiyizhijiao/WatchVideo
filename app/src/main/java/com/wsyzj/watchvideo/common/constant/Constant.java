package com.wsyzj.watchvideo.common.constant;

/**
 * 创建时间 : 2017/10/26
 * 编写人 : 焦洋
 * 功能描述 :
 * 京东万象key: 9280e20dec44795b1a0bb724a24834c3
 */
public class Constant {

    /**
     * 网络请求地址
     */
    public final static String MUSIC_URL = "http://tingapi.ting.baidu.com/";
    public final static String VIDEO_URL = "http://app.pearvideo.com/clt/jsp/v2/";
    public final static String WECHAT_CHOICENESS_URL = "http://v.juhe.cn/weixin/";
    public final static String GANK_IO_URL = "http://gank.io/api/data/";
    public final static String MEIRIYIWEN_URL = "https://interface.meiriyiwen.com/";
    public final static String DOUBAN_URL = "https://api.douban.com/v2/movie/";
    public final static String UPDATE_VERSION_URL = "http://www.pgyer.com/apiv1/app/";

    // 小游戏链接的h5
    public final static String URL_GAME_H5 = "http://www.4399.com/";
    public final static String URL_PHONT_QUERY = "http://www.hao123.com/haoserver/showjicc.htm";

    /**
     * 网络请求
     */
    public static class NetMessage {
        public final static int NET_CODE_SUCCESS = 10000;
        public final static int NET_CODE_ERROR = 1;
        public final static int NET_CODE_CONNECT = 400;
        public final static int NET_CODE_UNKNOWN_HOST = 401;
        public final static int NET_CODE_SOCKET_TIMEOUT = 402;
        public final static String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
        public final static String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
        public final static String UNKNOWN_HOST_EXCEPTION = "网络异常，请检查您的网络状态";
    }

    /**
     * 京东万象
     */
    public static class JingDong {
        public final static String JINGDONG_URL = "https://way.jd.com/jisuapi/";
        public final static String JINGDONG_KEY = "9280e20dec44795b1a0bb724a24834c3";
        public final static int JINGDONG_CODE = 10000;
        public final static int JINGDONG_STATUS = 0;
    }

    /**
     * 有米
     */
    public static class Youmi {
        public final static String APP_ID = "832292121bce6ebc";
        public final static String APP_SECRET = "88371d6d90c54630";
    }

    /**
     * 蒲公英
     */
    public static class PuGongYing {
        public final static String API_KEY = "6b927c9eae959ae1190bfe0439faf570";
        public final static String USER_KEY = "eccfca9978aa37b677458085437bda26";
        public final static String APP_KEY = "8971c5e41d4b18d5afd19d114cadbbb4";

        public static String getAppUpdateUrl() {
            return UPDATE_VERSION_URL + "install?appKey=" + APP_KEY + "&_api_key=" + API_KEY;
        }
    }
}
