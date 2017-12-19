package com.wsyzj.watchvideo.common.http;

import com.wsyzj.watchvideo.common.tools.Constant;
import com.wsyzj.watchvideo.common.tools.LogUtils;
import com.wsyzj.watchvideo.common.tools.ToastUtils;

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
            ToastUtils.showToast("没有数据，可能错误啦");
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
        ToastUtils.showToast(errorMsg);
        LogUtils.e(throwable.toString());
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(Object data);

}
