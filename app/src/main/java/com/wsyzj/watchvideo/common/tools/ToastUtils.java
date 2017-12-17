package com.wsyzj.watchvideo.common.tools;

import android.widget.Toast;

import com.wsyzj.watchvideo.BaseApp;


/**
 * 吐司单利的
 */
public class ToastUtils {

    public static Toast mToast;

    /**
     * @param message
     */
    public static void showToast(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApp.getApp(), "", Toast.LENGTH_SHORT);
        }
        mToast.setText(message);
        mToast.show();
    }
}
