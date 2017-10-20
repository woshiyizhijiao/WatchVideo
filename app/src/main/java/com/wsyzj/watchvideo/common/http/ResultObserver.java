package com.wsyzj.watchvideo.common.http;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 对网络请求的统一封装
 */
public class ResultObserver<T> implements Observer<HttpResult<T>> {


    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull HttpResult<T> tHttpResult) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
