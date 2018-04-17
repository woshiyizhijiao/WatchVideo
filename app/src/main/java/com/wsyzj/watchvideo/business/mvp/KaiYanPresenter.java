package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.business.bean.KaiYan;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;

import java.util.List;

/**
 * author : 焦洋
 * time   : 2017/11/2  12:19
 * desc   : KaiYanPresenter
 */
public class KaiYanPresenter extends BasePresenter<KaiYanContract.View, KaiYanContract.Model> implements KaiYanContract.Presenter {

    private KaiYanContract.View mView;
    private KaiYanContract.Model mModel;
    private List<KaiYan.DataListBean> mDatas;

    public KaiYanPresenter(KaiYanContract.View view) {
        mView = view;
        mModel = new KaiYanModel();
    }

    /**
     * 获取开眼数据
     */
    @Override
    public void getKaiYanList() {
        BaseTSubscriber<KaiYan> baseTSubscriber = mModel.getKaiYanList()
                .subscribeWith(new BaseTSubscriber<KaiYan>() {
                    @Override
                    public void onSuccess(Object data) {
                        KaiYan kaiYan = (KaiYan) data;
                        mDatas = kaiYan.dataList;
                        mView.setVideoList(mDatas);
                    }

                    @Override
                    public void onFailure() {

                    }
                });
        mView.addDisposable(baseTSubscriber);
    }
}
