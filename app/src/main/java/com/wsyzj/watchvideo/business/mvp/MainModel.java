package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.common.constant.Constant;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.http.BaseRxSchedulers;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/6 9:51
 * @Description: $desc$
 */
public class MainModel implements MainContract.Model {

    @Override
    public Flowable<NewsChannel> getNewsChannel() {
        return BaseRetrofit
                .jingDongNewsChannel()
                .getNewsChannel(Constant.JingDong.JINGDONG_KEY)
                .compose(BaseRxSchedulers.<NewsChannel>io_main());
    }
}
