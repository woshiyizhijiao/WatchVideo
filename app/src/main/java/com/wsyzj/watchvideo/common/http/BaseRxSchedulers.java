package com.wsyzj.watchvideo.common.http;

import android.content.Context;
import android.content.DialogInterface;

import com.wsyzj.watchvideo.common.base.BaseProgressDialog;
import com.wsyzj.watchvideo.common.tools.NetUtils;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 创建时间 : 2017/10/26
 * 编写人 : 焦洋
 * 功能描述 :
 */

public class BaseRxSchedulers {

    /**
     * 基本请求
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> io_main(final Context context) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Subscription>() {
                            @Override
                            public void accept(@NonNull Subscription subscription) throws Exception {
                                if (!NetUtils.isConnected(context)) {
                                    subscription.cancel();
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 携带进度条的请求
     *
     * @param context
     * @param progressDialog
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> io_main(final Context context, final BaseProgressDialog progressDialog) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
                return upstream
                        .delay(1, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Subscription>() {
                            @Override
                            public void accept(@NonNull final Subscription subscription) throws Exception {
                                if (!NetUtils.isConnected(context)) {
                                    subscription.cancel();
                                } else {
                                    if (progressDialog != null) {
                                        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            @Override
                                            public void onCancel(DialogInterface dialogInterface) {
                                                subscription.cancel();
                                            }
                                        });
                                    }
                                    progressDialog.show();
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
