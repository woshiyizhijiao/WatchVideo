package com.wsyzj.watchvideo.common.business.mvp;

import com.wsyzj.watchvideo.common.business.bean.KaiYan;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.http.BaseRxSchedulers;

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
                .kaiYanApi()
                .getKaiYanList()
                .compose(BaseRxSchedulers.<KaiYan>io_main());
    }
}
