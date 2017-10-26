package com.wsyzj.watchvideo.common.test;

import com.wsyzj.watchvideo.common.http.BaseEntity;

import java.util.List;

/**
 * @name: City
 * @author: wsyzj
 * @company: 曙华科技
 * @date: 2016-11-21 12:25
 * @comment: 城市数据
 */
public class City extends BaseEntity {

    public List<DataBean> data;

    public static class DataBean {
        public String BIANMA;
        public String NAME;
        public List<DataBeanBean> subDict;

    }

    public static class DataBeanBean {
        public String BIANMA;
        public String NAME;
    }
}
