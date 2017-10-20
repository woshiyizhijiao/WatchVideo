package com.wsyzj.watchvideo.common.business.main;

import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

/**
 * @author: wsyzj
 * @date: 2017-09-17 17:16
 * @comment:
 */
public class MainPresenter extends BasePresenter<MainContract.View, MainContract.Model>
        implements MainContract.Presenter {

    private MainContract.View mView;
    private MainContract.Model mModel;

    public MainPresenter(MainContract.View view) {
        mView = view;
        mModel = new MainModel();
    }
}
