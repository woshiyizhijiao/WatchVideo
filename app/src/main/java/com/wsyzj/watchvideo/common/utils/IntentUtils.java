package com.wsyzj.watchvideo.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.activity.ChannelManagerActivity;
import com.wsyzj.watchvideo.business.activity.MainActivity;
import com.wsyzj.watchvideo.business.activity.MeiRiYiWenActivity;
import com.wsyzj.watchvideo.business.activity.PreviewLargeActivity;
import com.wsyzj.watchvideo.business.activity.TestActivity;
import com.wsyzj.watchvideo.business.activity.WebViewActivity;

import java.util.ArrayList;

/**
 * @author: wsyzj
 * @date: 2017-09-17 18:46
 * @comment: 管理跳转界面
 */
public class IntentUtils {

    private static void startActivity(Activity activity, Class clazz) {
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
        startActivity(activity, MainActivity.class);
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

    /**
     * 每日一文
     */
    public static void meiRiYiWen(Activity activity, String title, String author, String content) {
        Intent intent = new Intent(activity, MeiRiYiWenActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("author", author);
        intent.putExtra("content", content);
        startIntentActivity(activity, intent);
    }

    /**
     * 预览大图
     *
     * @param position
     */
    public static void previewLarge(Activity activity, int position, ArrayList<String> imgUrls) {
        Intent intent = new Intent(activity, PreviewLargeActivity.class);
        intent.putExtra("position", position);
        intent.putStringArrayListExtra("imgUrls", imgUrls);
        startIntentActivity(activity, intent);
    }

    /**
     * 测试
     *
     * @param activity
     */
    public static void test(Activity activity) {
        Intent intent = new Intent(activity, TestActivity.class);
        startIntentActivity(activity, intent);
    }

    /**
     * 频道管理
     */
    public static void channelManager(Activity activity) {
        startActivity(activity, ChannelManagerActivity.class);
    }
}
