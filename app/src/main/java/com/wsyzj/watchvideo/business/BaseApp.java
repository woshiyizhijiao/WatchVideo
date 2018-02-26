package com.wsyzj.watchvideo.business;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.wsyzj.watchvideo.R;

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
        Utils.init(baseApp);
    }

    public static BaseApp getApp() {
        return baseApp;
    }
}
