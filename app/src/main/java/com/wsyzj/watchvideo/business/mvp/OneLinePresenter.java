package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/05/02
 *     desc   :
 * </pre>
 */
public class OneLinePresenter extends BasePresenter<OneLineContract.View, OneLineContract.Model> implements OneLineContract.Presenter {

    private OneLineContract.View mView;
    private OneLineContract.Model mModel;

    public OneLinePresenter(OneLineContract.View view) {
        mView = view;
        mModel = new OneLineModel();
    }
}
