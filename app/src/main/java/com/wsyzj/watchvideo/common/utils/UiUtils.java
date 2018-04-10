package com.wsyzj.watchvideo.common.utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

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
     * 获取系统栏的高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = BaseApp.getApp().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = BaseApp.getApp().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
