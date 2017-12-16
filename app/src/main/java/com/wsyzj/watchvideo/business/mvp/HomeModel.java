package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.business.bean.DouBan;
import com.wsyzj.watchvideo.business.bean.Gank;
import com.wsyzj.watchvideo.common.base.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.base.http.BaseRxSchedulers;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/12 14:05
 * @Description: $desc$
 */
public class HomeModel implements HomeContract.Model {

    @Override
    public Flowable<Gank> getGankData(String type, int pageNumber, int page) {
        return BaseRetrofit
                .gank()
                .getGankData(type, pageNumber, page)
                .compose(BaseRxSchedulers.<Gank>io_main());
    }

    @Override
    public Flowable<DouBan> getTheatersList(int start, int count) {
        return BaseRetrofit
                .douBan()
                .getTheatersList("0b2bdeda43b5688921839c8ecb20399b", "深圳", start, count)
                .compose(BaseRxSchedulers.<DouBan>io_main());
    }

}
