package com.wsyzj.watchvideo.business.bean;

import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/6 12:24
 * @Description: $desc$
 */
public class News {


    /**
     * code : 10000
     * charge : false
     * msg : 查询成功
     * result : {"status":"0","msg":"ok","result":{"channel":"头条","num":"1","list":[{"title":"收评：权重拉动沪指反弹涨0.17% 煤飞色舞行情再现","time":"2017-07-06 15:02","src":"新浪财经","category":"finance","pic":"http://api.jisuapi.com/news/upload/201707/06160007_47862.jpg","content":"7月6日消息，沪指早盘低开后保持震荡，盘中一度反弹翻红，上证50指数[股评]临近午盘跳水，沪指在权重股回调带动下午前亦跳水，锂电池板块急跌，多只个股炸板；午后开盘，两市企稳，锂电池板块反弹，沪指在走出V型探底后翻红。","url":"http://finance.sina.cn/stock/dpps/2017-07-06/detail-ifyhwefp0163259.d.html?vt=4&pos=108","weburl":"http://finance.sina.com.cn/stock/jsy/2017-07-06/doc-ifyhwefp0163259.shtml"}]}}
     */

    public int code;
    public boolean charge;
    public String msg;
    public ResultBeanX result;

    public static class ResultBeanX {
        /**
         * status : 0
         * msg : ok
         * result : {"channel":"头条","num":"1","list":[{"title":"收评：权重拉动沪指反弹涨0.17% 煤飞色舞行情再现","time":"2017-07-06 15:02","src":"新浪财经","category":"finance","pic":"http://api.jisuapi.com/news/upload/201707/06160007_47862.jpg","content":"7月6日消息，沪指早盘低开后保持震荡，盘中一度反弹翻红，上证50指数[股评]临近午盘跳水，沪指在权重股回调带动下午前亦跳水，锂电池板块急跌，多只个股炸板；午后开盘，两市企稳，锂电池板块反弹，沪指在走出V型探底后翻红。","url":"http://finance.sina.cn/stock/dpps/2017-07-06/detail-ifyhwefp0163259.d.html?vt=4&pos=108","weburl":"http://finance.sina.com.cn/stock/jsy/2017-07-06/doc-ifyhwefp0163259.shtml"}]}
         */

        public int status;
        public String msg;
        public ResultBean result;

        public static class ResultBean {
            /**
             * channel : 头条
             * num : 1
             * list : [{"title":"收评：权重拉动沪指反弹涨0.17% 煤飞色舞行情再现","time":"2017-07-06 15:02","src":"新浪财经","category":"finance","pic":"http://api.jisuapi.com/news/upload/201707/06160007_47862.jpg","content":"7月6日消息，沪指早盘低开后保持震荡，盘中一度反弹翻红，上证50指数[股评]临近午盘跳水，沪指在权重股回调带动下午前亦跳水，锂电池板块急跌，多只个股炸板；午后开盘，两市企稳，锂电池板块反弹，沪指在走出V型探底后翻红。","url":"http://finance.sina.cn/stock/dpps/2017-07-06/detail-ifyhwefp0163259.d.html?vt=4&pos=108","weburl":"http://finance.sina.com.cn/stock/jsy/2017-07-06/doc-ifyhwefp0163259.shtml"}]
             */

            public String channel;
            public String num;
            public List<ListBean> list;

            public static class ListBean {
                /**
                 * title : 收评：权重拉动沪指反弹涨0.17% 煤飞色舞行情再现
                 * time : 2017-07-06 15:02
                 * src : 新浪财经
                 * category : finance
                 * pic : http://api.jisuapi.com/news/upload/201707/06160007_47862.jpg
                 * content : 7月6日消息，沪指早盘低开后保持震荡，盘中一度反弹翻红，上证50指数[股评]临近午盘跳水，沪指在权重股回调带动下午前亦跳水，锂电池板块急跌，多只个股炸板；午后开盘，两市企稳，锂电池板块反弹，沪指在走出V型探底后翻红。
                 * url : http://finance.sina.cn/stock/dpps/2017-07-06/detail-ifyhwefp0163259.d.html?vt=4&pos=108
                 * weburl : http://finance.sina.com.cn/stock/jsy/2017-07-06/doc-ifyhwefp0163259.shtml
                 */

                public String title;
                public String time;
                public String src;
                public String category;
                public String pic;
                public String content;
                public String url;
                public String weburl;
            }
        }
    }
}
