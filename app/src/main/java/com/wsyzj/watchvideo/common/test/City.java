package com.wsyzj.watchvideo.common.test;

import java.util.List;

/**
 * @name: City
 * @author: wsyzj
 * @company: 曙华科技
 * @date: 2016-11-21 12:25
 * @comment: 城市数据
 */
public class City {

    /**
     * BIANMA : beijing
     * NAME : 北京
     * subDict : [{"BIANMA":"beij","NAME":"北京","subDict":[]}]
     */

    public String BIANMA;
    public String NAME;
    public List<SubDictBean> subDict;

    public static class SubDictBean {
        /**
         * BIANMA : beij
         * NAME : 北京
         * subDict : []
         */

        public String BIANMA;
        public String NAME;
        public List<?> subDict;
    }
}
