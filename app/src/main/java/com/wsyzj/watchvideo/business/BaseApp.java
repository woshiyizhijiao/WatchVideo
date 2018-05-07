package com.wsyzj.watchvideo.business;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;

import org.litepal.LitePal;

/**
 * @author: wsyzj
 * @date: 2017-03-06 14:45
 * @comment: 应用程序的入口
 */
public class BaseApp extends Application {

    public static boolean isDebug = true;
    private static BaseApp baseApp;

    /**
     * 获取全局Context
     *
     * @return
     */
    public static BaseApp getApp() {
        return baseApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApp = this;
        initLib();
    }

    /**
     * 第三方库，SDK，初始化
     */
    private void initLib() {
        Utils.init(baseApp);
        LitePal.initialize(baseApp);
        OkGo.getInstance().init(this);
    }
}
