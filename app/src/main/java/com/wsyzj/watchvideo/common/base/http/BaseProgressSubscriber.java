package com.wsyzj.watchvideo.common.base.http;

import com.wsyzj.watchvideo.common.base.BaseProgressDialog;

/**
 * 创建时间 : 2017/10/26
 * 编写人 : 焦洋
 * 功能描述 :
 */

public abstract class BaseProgressSubscriber<T> extends BaseSubscriber<T> {
    private BaseProgressDialog dialog;

    protected BaseProgressSubscriber(BaseProgressDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onComplete() {
        super.onComplete();
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
