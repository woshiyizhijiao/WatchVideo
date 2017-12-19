package com.wsyzj.watchvideo.common.base.mvp;

/**
 * @author: wsyzj
 * @date: 2017-03-19 17:08
 * @comment: MVP
 */
public interface BaseIPresenter<V extends BaseIView> {

    void attachView(V view);

    void detachView();
}

