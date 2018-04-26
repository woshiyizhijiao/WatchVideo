package com.wsyzj.watchvideo.common.http;

import com.blankj.utilcode.util.ToastUtils;
import com.wsyzj.watchvideo.common.constant.Constant;

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
        if (baseEntity.code == Constant.NetMessage.NET_CODE_SUCCESS) {
            onSuccess(baseEntity.data);
        } else {
            ToastUtils.showShort(baseEntity.msg);
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

    public abstract void onSuccess(T data);

    public abstract void onFailure(Throwable throwable);
}
