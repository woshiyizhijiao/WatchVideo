package com.wsyzj.watchvideo.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wsyzj.watchvideo.R;


/**
 * @author: wsyzj
 * @date: 2017-03-18 10:38
 * @comment: 统一的标题布局
 */
public class BaseTitleView extends LinearLayout {

    private Context mContext;
    private View base_layout;
    private Toolbar tooBar;

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
        addView(base_layout);
    }
}
