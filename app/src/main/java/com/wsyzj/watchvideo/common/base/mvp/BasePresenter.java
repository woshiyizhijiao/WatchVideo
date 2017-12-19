package com.wsyzj.watchvideo.common.base.mvp;


/**
 * @author: wsyzj
 * @date: 2017-03-18 10:07
 * @comment: MVP模式的指挥者(连接View和Model)
 */
public class BasePresenter<V extends BaseIView, M extends BaseIModel> implements BaseIPresenter<V> {

    protected V mView;
    protected M mModel;


    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

}
