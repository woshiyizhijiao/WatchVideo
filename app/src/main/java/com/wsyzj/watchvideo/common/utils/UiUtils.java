package com.wsyzj.watchvideo.common.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.blankj.utilcode.util.KeyboardUtils;
import com.wsyzj.watchvideo.business.BaseApp;

import java.util.Timer;
import java.util.TimerTask;


/**
 * @author: wsyzj
 * @date: 2017-03-06 14:45
 * @comment: Ui工具类
 */
public class UiUtils {

    public static Resources getResources() {
        return BaseApp.getApp().getResources();
    }

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
        return getResources().getDrawable(id);
    }

    /**
     * @param colorId
     * @return 返回颜色值
     */
    public static int getColor(int colorId) {
        return getResources().getColor(colorId);
    }

    /**
     * 获取字符串数组
     *
     * @return
     */
    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
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

    /**
     * 隐藏键盘
     *
     * @param activity
     */
    public static void hideSoftInput(Activity activity) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               KeyboardUtils.hideSoftInput(activity);
                           }
                       },
                150);
    }

    /**
     * 弹出键盘
     */
    public static void showSoftInput(Activity activity) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               KeyboardUtils.showSoftInput(activity);
                           }
                       },
                200);
    }
}
