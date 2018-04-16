package com.wsyzj.watchvideo.common.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * 创建时间 : 2017/10/19
 * 编写人 : 焦洋
 * 功能描述 :
 */

public class ImageLoader {

    /**
     * 加载图片
     *
     * @param context
     * @param path      图片路径
     * @param imageView ImageView
     */
    public static void with(Context context, String path, ImageView imageView) {
        if (context == null) {
            return;
        }
        Glide.with(context).load(path).into(imageView);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param path             图片路径
     * @param placeholderResId 加载中的图片
     * @param errorResId       加载失败图片
     * @param imageView        ImageView
     */
    @SuppressLint("CheckResult")
    public static void with(Context context, String path, int placeholderResId, int errorResId, ImageView imageView) {
        if (context == null) {
            return;
        }
        RequestOptions options = new RequestOptions();
        options.placeholder(placeholderResId);
        options.error(errorResId);
        Glide.with(context).load(path).apply(options).into(imageView);
    }

    /**
     * @param context
     * @param path
     * @param simpleTarget
     */
    public static void with(Context context, String path, SimpleTarget<Bitmap> simpleTarget) {
        if (context == null) {
            return;
        }
        Glide.with(context).asBitmap().load(path).into(simpleTarget);
    }
}
