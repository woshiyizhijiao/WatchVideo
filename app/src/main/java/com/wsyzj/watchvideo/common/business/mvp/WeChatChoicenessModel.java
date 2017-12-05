package com.wsyzj.watchvideo.common.business.mvp;

import com.wsyzj.watchvideo.common.business.bean.WeChatChoiceness;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.http.BaseRxSchedulers;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/5 15:10
 */
public class WeChatChoicenessModel implements WeChatChoicenessContract.Model {

    @Override
    public Flowable<WeChatChoiceness> getWeChatChoiceness(int pno, int ps) {
        return BaseRetrofit
                .wechatChoiceness()
                .wechatChoiceness(pno, ps, "a1c4a00ab26734546271ea0bf5546803")
                .compose(BaseRxSchedulers.<WeChatChoiceness>io_main());
    }
}
