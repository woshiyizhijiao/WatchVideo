package com.wsyzj.watchvideo.common.utils;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.wsyzj.watchvideo.business.BaseApp;


/**
 * @author: wsyzj
 * @date: 2017-03-06 14:45
 * @comment: Ui工具类
 */
public class UiUtils {

    /**
     * @param resource
     * @return 获取一个试图
     */
    public static View inflate(int resource) {
        return View.inflate(BaseApp.getApp(), resource, null);
    }

    public static View inflate(int resource, ViewGroup root) {
        return View.inflate(BaseApp.getApp(), resource, root);
    }

    /**
     * @param id
     * @return 返回一个drawable
     */
    public static Drawable getDrawable(int id) {
        return BaseApp.getApp().getResources().getDrawable(id);
    }

    /**
     * @param colorId
     * @return 返回颜色值
     */
    public static int getColor(int colorId) {
        return BaseApp.getApp().getResources().getColor(colorId);
    }

    /**
     * 根据 EditText 所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
     *
     * @param v
     * @param event
     * @return
     */
    public static boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }
}
