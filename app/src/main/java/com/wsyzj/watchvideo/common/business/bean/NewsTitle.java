package com.wsyzj.watchvideo.common.business.bean;

import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/6 10:20
 * @Description: $desc$
 */
public class NewsTitle {


    /**
     * charge : false
     * code : 10000
     * msg : 查询成功
     * result : {"msg":"ok","result":["头条","新闻","财经","体育","娱乐","军事","教育","科技","NBA","股票","星座","女性","健康","育儿"],"status":"0"}
     */

    public boolean charge;
    public int code;
    public String msg;
    public ResultBean result;

    public static class ResultBean {
        /**
         * msg : ok
         * result : ["头条","新闻","财经","体育","娱乐","军事","教育","科技","NBA","股票","星座","女性","健康","育儿"]
         * status : 0
         */

        public String msg;
        public int status;
        public List<String> result;
    }
}
