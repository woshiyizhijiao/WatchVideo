package com.wsyzj.watchvideo.common.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseActivity;


/**
 * @author: wsyzj
 * @date: 2017-03-18 10:38
 * @comment: 统一的标题布局
 */
public class BaseTitleView extends LinearLayout {

    private Context mContext;
    private View base_layout;
    private Toolbar tooBar;
    private TextView tv_title;

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
        tooBar = (Toolbar) base_layout.findViewById(R.id.tooBar);
        tv_title = (TextView) base_layout.findViewById(R.id.tv_title);


        if (mContext instanceof BaseActivity) {
            final BaseActivity activity = (BaseActivity) mContext;
            activity.setSupportActionBar(tooBar);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            tooBar.setNavigationOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();
                }
            });
        }
        addView(base_layout);
    }

    /**
     * 隐藏
     */
    public View hide() {
        base_layout.setVisibility(View.GONE);
        return base_layout;
    }

    public Toolbar setNavigationIcon(int resId) {
        tooBar.setNavigationIcon(resId);
        return tooBar;
    }

    public Toolbar setNavigationIcon(Drawable icon) {
        tooBar.setNavigationIcon(icon);
        return tooBar;
    }

    public TextView setTitle(String title) {
        tv_title.setText(title);
        return tv_title;
    }

    public void setNavigationOnClickListener(OnClickListener navigationOnClickListener) {
        if (navigationOnClickListener != null) {
            navigationOnClickListener.onClick(tooBar);
        }
    }
}
