package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

/**
 * @author 焦洋
 * @date 2017/12/19 15:00
 * @Description: $desc$
 */
public class PreviewLargePresenter extends BasePresenter<PreviewLargeContract.View, PreviewLargeContract.Model> implements PreviewLargeContract.Presenter {

    private PreviewLargeContract.View mView;
    private PreviewLargeContract.Model mModel;

    public PreviewLargePresenter(PreviewLargeContract.View view) {
        mView = view;
        mModel = new PreviewLargeModel();
    }
}
