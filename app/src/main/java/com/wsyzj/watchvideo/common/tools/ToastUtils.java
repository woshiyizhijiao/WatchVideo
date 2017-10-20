package com.wsyzj.watchvideo.common.tools;

import android.widget.Toast;

import com.wsyzj.watchvideo.common.base.BaseApp;


/**
 * 吐司单利的
 */
public class ToastUtils {

    public static Toast mToast;

    /**
     * @param msg
     */
    public static void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApp.getApp(), "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
}
