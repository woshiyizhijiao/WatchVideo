package com.wsyzj.watchvideo.common.http;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: wsyzj
 * @date: 2017-08-27 14:12
 * @comment: RxSchedulers
 */
public class RxSchedulers {
    public static <T extends HttpResult> ObservableTransformer<T, T> baseSwitchThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> test() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


//    public static <T extends HttpResult> FlowableTransformer<T, T> switchThread() {
//        return new FlowableTransformer<T, T>() {
//            @Override
//            public Publisher<T> apply(@NonNull Flowable<T> upstream) {
//                return upstream
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .filter(new Predicate<T>() {
//                            @Override
//                            public boolean test(@NonNull T t) throws Exception {
//                                LogUtils.e(t.result.toString());
//                                return true;
//                            }
//                        });
//            }
//        };
//    }
}
