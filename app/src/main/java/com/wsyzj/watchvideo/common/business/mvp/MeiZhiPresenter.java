package com.wsyzj.watchvideo.common.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

/**
 * @author 焦洋
 * @date 2017/12/1 10:42
 */
public class MeiZhiPresenter extends BasePresenter<MeiZhiContract.View, MeiZhiContract.Model> implements MeiZhiContract.Presenter {

    private MeiZhiContract.View mView;
    private MeiZhiContract.Model mModel;

    public MeiZhiPresenter(MeiZhiContract.View view) {
        mView = view;
        mModel = new MeiZhiModel();
    }

}
