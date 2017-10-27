package com.wsyzj.watchvideo.common.http;

import com.wsyzj.watchvideo.common.tools.Constants;
import com.wsyzj.watchvideo.common.tools.LogUtils;
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
    public void onNext(BaseEntity<T> tBaseEntity) {
        if (tBaseEntity.code == Constants.NET_CODE_SUCCESS) {
//            onSuccess(tBaseEntity.t);
            LogUtils.e(tBaseEntity.code +" -- " + tBaseEntity.msg +" -- " + tBaseEntity.data);
//            LogUtils.e(tBaseEntity.code +" -- " + tBaseEntity.msg +" -- " );
        } else {
            ToastUtils.showToast(tBaseEntity.msg);
        }
    }

    @Override
    public void onError(Throwable t) {
        String errorMsg = "";
        if (t instanceof SocketTimeoutException) {
            errorMsg = Constants.SOCKET_TIMEOUT_EXCEPTION;
        } else if (t instanceof ConnectException) {
            errorMsg = Constants.CONNECT_EXCEPTION;
        } else if (t instanceof UnknownHostException) {
            errorMsg = Constants.UNKNOWN_HOST_EXCEPTION;
        } else {
            errorMsg = t.getMessage();
        }
        ToastUtils.showToast(errorMsg);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);
}
