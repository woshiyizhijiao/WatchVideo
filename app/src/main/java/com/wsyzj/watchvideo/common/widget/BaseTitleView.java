package com.wsyzj.watchvideo.common.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.tools.UiUtils;


/**
 * @author: wsyzj
 * @date: 2017-03-18 10:38
 * @comment: 统一的标题布局
 */
public class BaseTitleView extends LinearLayout {

    private Context mContext;
    private View base_layout;
    private Toolbar toobar;

    private OnClickListener mNavigationOnClickListener;

    public BaseTitleView(Context context) {
        super(context);
        init(context);
    }

    public BaseTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setOrientation(LinearLayout.VERTICAL);
        base_layout = LayoutInflater.from(mContext).inflate(R.layout.widget_base_title_view, null);
        toobar = (Toolbar) base_layout.findViewById(R.id.tooBar);


        if (mContext instanceof BaseActivity) {
            final BaseActivity activity = (BaseActivity) mContext;
            activity.setSupportActionBar(toobar);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setTitle("");
            toobar.setNavigationOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mNavigationOnClickListener != null) {
                        mNavigationOnClickListener.onClick(v);
                    } else {
                        activity.finish();
                    }
                }
            });
        }
        addView(base_layout);
    }

    /**
     * 隐藏整个BaseTitleView
     */
    public View hide() {
        base_layout.setVisibility(View.GONE);
        return base_layout;
    }

    /**
     * 设置返回键的图标
     *
     * @param resId
     * @return
     */
    public Toolbar setNavigationIcon(int resId) {
        toobar.setNavigationIcon(resId);
        return toobar;
    }

    /**
     * 设置返回键的图标
     *
     * @param icon
     * @return
     */
    public Toolbar setNavigationIcon(Drawable icon) {
        toobar.setNavigationIcon(icon);
        return toobar;
    }

    /**
     * 设置背景颜色
     *
     * @param color
     * @return
     */
    public Toolbar setTbBackgroundColor(int color) {
        toobar.setBackgroundColor(UiUtils.getColor(color));
        return toobar;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public Toolbar setTitle(String title) {
        if (mContext instanceof Activity) {
            BaseActivity activity = (BaseActivity) mContext;
            activity.getSupportActionBar().setTitle(title);
        }
        return toobar;
    }

    /**
     * 返回按键事件
     *
     * @param navigationOnClickListener
     */
    public void setNavigationOnClickListener(OnClickListener navigationOnClickListener) {
        mNavigationOnClickListener = navigationOnClickListener;
    }
}