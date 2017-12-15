package com.wsyzj.watchvideo.common.tools;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.wsyzj.watchvideo.common.base.BaseApp;


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
}
