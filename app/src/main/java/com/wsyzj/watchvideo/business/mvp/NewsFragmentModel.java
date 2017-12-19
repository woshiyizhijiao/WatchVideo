package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.business.bean.News;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.http.BaseRxSchedulers;
import com.wsyzj.watchvideo.common.tools.Constant;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/6 9:51
 * @Description: $desc$
 */
public class NewsFragmentModel implements NewsFragmentContract.Model {

    @Override
    public Flowable<News> getNewsListByTitle(String channel, int start, int num) {
        return BaseRetrofit
                .jingDong()
                .getNewsListByTitle(channel, start, num, Constant.JingDong.JINGDONG_KEY)
                .compose(BaseRxSchedulers.<News>io_main());
    }
}
