package com.wsyzj.watchvideo.common.tools;

import android.graphics.drawable.Drawable;

import com.wsyzj.watchvideo.business.BaseApp;

/**
 * @name: DrawableUtils
 * @author: wsyzj
 * @company: 曙华科技
 * @date: 2016-09-05 14:47
 * @comment: 涉及图片操作的类
 */
public class DrawableUtils {

    /**
     * 获取一张可以显示的PaddingDrawable
     *
     * @param drawableId
     * @return
     */
    public static Drawable getPaddingDrawable(int drawableId) {
        Drawable drawable = BaseApp.getApp().getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

}
