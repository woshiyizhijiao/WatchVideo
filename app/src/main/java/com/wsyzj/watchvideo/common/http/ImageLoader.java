package com.wsyzj.watchvideo.common.http;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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
        Glide.with(context).load(path).into(imageView);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param path        图片路径
     * @param placeholder 加载中的图片
     * @param errorResIs  加载失败图片
     * @param imageView   ImageView
     */
    public static void with(Context context, String path, int placeholder, int errorResIs, ImageView imageView) {
        Glide.with(context).load(path).placeholder(placeholder).error(errorResIs).into(imageView);
    }
}
