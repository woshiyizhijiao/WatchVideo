package com.wsyzj.watchvideo.business;

import android.app.Application;

/**
 * @author: wsyzj
 * @date: 2017-03-06 14:45
 * @comment: 应用程序的入口
 */
public class BaseApp extends Application {
    public static boolean isDebug = false;
    private static BaseApp baseApp;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;
    }

    public static BaseApp getApp() {
        return baseApp;
    }
}
