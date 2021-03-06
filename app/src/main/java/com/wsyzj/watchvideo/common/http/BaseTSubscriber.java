package com.wsyzj.watchvideo.common.http;

import com.blankj.utilcode.util.ToastUtils;
import com.wsyzj.watchvideo.common.constant.Constant;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * author : 焦洋
 * time   : 2017/11/2  9:55
 * desc   : 有一些数据没有继承BaseEntity
 */
public abstract class BaseTSubscriber<T> extends DisposableSubscriber<T> {

    @Override
    public void onNext(T data) {
        if (data != null) {
            onSuccess(data);
        } else {
            ToastUtils.showShort("没有数据，可能错误啦");
        }
    }

    @Override
    public void onError(Throwable throwable) {
        String errorMsg = "";
        if (throwable instanceof SocketTimeoutException) {
            errorMsg = Constant.NetMessage.SOCKET_TIMEOUT_EXCEPTION;
        } else if (throwable instanceof ConnectException) {
            errorMsg = Constant.NetMessage.CONNECT_EXCEPTION;
        } else if (throwable instanceof UnknownHostException) {
            errorMsg = Constant.NetMessage.UNKNOWN_HOST_EXCEPTION;
        } else {
            errorMsg = throwable.getMessage();
        }
        onFailure(throwable);
        ToastUtils.showShort(errorMsg);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(Object data);

    public abstract void onFailure(Throwable throwable);
}
