package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.common.constant.Constant;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.http.BaseRxSchedulers;

import io.reactivex.Flowable;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/16
 *     desc   :
 * </pre>
 */
public class MainTestModel implements MainTestContract.Model {

    @Override
    public Flowable<NewsChannel> getNewsChannel() {
        return BaseRetrofit
                .jingDongNewsChannel()
                .getNewsChannel(Constant.JingDong.JINGDONG_KEY)
                .compose(BaseRxSchedulers.<NewsChannel>io_main());
    }
}
