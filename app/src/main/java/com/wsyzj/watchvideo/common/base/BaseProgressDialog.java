package com.wsyzj.watchvideo.common.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.KeyEvent;

/**
 * 创建时间 : 2017/10/23
 * 编写人 : 焦洋
 * 功能描述 :
 */

public class BaseProgressDialog extends ProgressDialog {
    public BaseProgressDialog(Context context) {
        super(context);
        init();
    }

    private void init() {
        setCanceledOnTouchOutside(true);
        setCancelable(false);
        setMessage("请稍候...");
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (isShowing())
                    dismiss();
                break;
        }
        return true;
    }
}
