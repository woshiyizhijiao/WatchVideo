package com.wsyzj.watchvideo.common.tools;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * @author: wsyzj
 * @date: 2017-09-17 18:46
 * @comment: 管理跳转界面
 */
public class IntentUtils {

    /**
     * 到系统设置
     */
    public static void systemSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }
}
