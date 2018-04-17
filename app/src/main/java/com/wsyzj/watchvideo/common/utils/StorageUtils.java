package com.wsyzj.watchvideo.common.utils;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.BaseApp;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/14
 *     desc   : 在SPUtils的基础上对存储的值进行二次封装，对外不直接操作键名，取值和设置值使用本类的方法
 * </pre>
 */
public class StorageUtils {

    private static final String COLOR_PRIMARY = "color_primary";

    /**
     * 获取主题颜色
     */
    public static int getColorPrimary() {
        return (int) SPUtils.get(BaseApp.getApp(), COLOR_PRIMARY, R.color.colorPrimary);
    }

    /**
     * 设置主题颜色
     */
    public static void putColorPrimary(int colorPrimary) {
        SPUtils.put(BaseApp.getApp(), COLOR_PRIMARY, colorPrimary);
    }
}
