package com.wsyzj.watchvideo.common.business.mvp;

import com.wsyzj.watchvideo.common.business.bean.Gank;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.http.BaseRxSchedulers;

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
}
