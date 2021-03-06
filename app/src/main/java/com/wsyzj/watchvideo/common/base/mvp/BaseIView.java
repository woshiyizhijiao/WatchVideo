package com.wsyzj.watchvideo.common.base.mvp;

import com.wsyzj.watchvideo.common.widget.StateLayout;

import io.reactivex.disposables.Disposable;

/**
 * @author: wsyzj
 * @date: 2017-03-18 10:09
 * @comment: MVP模式的View(通过Presenter将数据传入到该层，负责View的展示相关)
 */
public interface BaseIView {

    void setStateLayout(StateLayout baseState);

    void showProgress();

    void dismissProgress();

    void showToast(String message);

    void addDisposable(Disposable disposable);
}
