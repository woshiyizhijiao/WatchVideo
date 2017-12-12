package com.wsyzj.watchvideo.common.tools;

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
    public static final String MUSIC_URL = "http://tingapi.ting.baidu.com/";
    public static final String VIDEO_URL = "http://app.pearvideo.com/clt/jsp/v2/";
    public static final String WECHAT_CHOICENESS_URL = "http://v.juhe.cn/weixin/";
    public static final String GANK_IO_URL = "http://gank.io/api/data/";

    /**
     * 网络请求
     */
    public static class NetMessage {
        public static final int NET_CODE_SUCCESS = 10000;
        public static final int NET_CODE_ERROR = 1;
        public static final int NET_CODE_CONNECT = 400;
        public static final int NET_CODE_UNKNOWN_HOST = 401;
        public static final int NET_CODE_SOCKET_TIMEOUT = 402;
        public static final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
        public static final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
        public static final String UNKNOWN_HOST_EXCEPTION = "网络异常，请检查您的网络状态";
    }

    /**
     * 微信
     */
    public static class WeChat {
        public static final int WECHAT_CHOICENESS_ERROR_CODE = 0;
    }

    /**
     * 京东万象
     */
    public static class JingDong {
        public static final String JINGDONG_URL = "https://way.jd.com/jisuapi/";
        public static final String JINGDONG_KEY = "9280e20dec44795b1a0bb724a24834c3";
        public static final int JINGDONG_CODE = 10000;
        public static final int JINGDONG_STATUS = 0;
    }

    /**
     * EventBus通知的action
     */
    public static class EventBusC {
        public static final int NEW_FIRST_PAGE_LOAD_FINISH = 0x1000;    // 新闻第一个page加载完成
    }
}
