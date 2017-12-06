package com.wsyzj.watchvideo.common.tools;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.wsyzj.watchvideo.MainActivity;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.business.activity.NewsActivity;
import com.wsyzj.watchvideo.common.business.activity.WebViewActivity;

/**
 * @author: wsyzj
 * @date: 2017-09-17 18:46
 * @comment: 管理跳转界面
 */
public class IntentUtils {

    private static void defaultStartActivity(Activity activity, Class clazz) {
        activity.startActivity(new Intent(activity, clazz));
        activity.overridePendingTransition(R.anim.anim_act_translate_enter_in, R.anim.anim_act_translate_enter_out);
    }

    private static void startIntentActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.anim_act_translate_enter_in, R.anim.anim_act_translate_enter_out);
    }

    /**
     * 到系统设置
     */
    public static void systemSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }

    /**
     * 到主界面
     */
    public static void main(Activity activity) {
        defaultStartActivity(activity, MainActivity.class);
    }

    /**
     * 新闻界面
     *
     * @param activity
     */
    public static void news(Activity activity) {
        defaultStartActivity(activity, NewsActivity.class);
    }

    /**
     * 展示网页
     *
     * @param activity
     * @param title
     * @param url
     */
    public static void webView(Activity activity, String title, String url) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        startIntentActivity(activity, intent);
    }
}
