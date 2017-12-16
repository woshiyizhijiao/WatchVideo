package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.business.bean.KaiYan;
import com.wsyzj.watchvideo.common.base.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.base.http.BaseRxSchedulers;

import io.reactivex.Flowable;

/**
 * author : 焦洋
 * time   : 2017/11/2  12:19
 * desc   : KaiYanModel
 */
public class KaiYanModel implements KaiYanContract.Model {

    @Override
    public Flowable<KaiYan> getKaiYanList() {
        return BaseRetrofit
                .videoApi()
                .getVideoList()
                .compose(BaseRxSchedulers.<KaiYan>io_main());
    }
}