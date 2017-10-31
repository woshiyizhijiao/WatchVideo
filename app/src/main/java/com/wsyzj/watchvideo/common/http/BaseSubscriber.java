package com.wsyzj.watchvideo.common.http;

import com.wsyzj.watchvideo.common.tools.Constants;
import com.wsyzj.watchvideo.common.tools.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * 创建时间 : 2017/10/26
 * 编写人 : 焦洋
 * 功能描述 :
 */

public abstract class BaseSubscriber<T> extends DisposableSubscriber<BaseEntity<T>> {

    @Override
    public void onNext(BaseEntity<T> baseEntity) {
        if (baseEntity.getCode() == Constants.NET_CODE_SUCCESS) {
            onSuccess(baseEntity.getResult());
        } else {
            ToastUtils.showToast(baseEntity.getMsg());
        }
    }

    @Override
    public void onError(Throwable throwable) {
        String errorMsg = "";
        if (throwable instanceof SocketTimeoutException) {
            errorMsg = Constants.SOCKET_TIMEOUT_EXCEPTION;
        } else if (throwable instanceof ConnectException) {
            errorMsg = Constants.CONNECT_EXCEPTION;
        } else if (throwable instanceof UnknownHostException) {
            errorMsg = Constants.UNKNOWN_HOST_EXCEPTION;
        } else {
            errorMsg = throwable.getMessage();
        }
        ToastUtils.showToast(errorMsg);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T data);
}
