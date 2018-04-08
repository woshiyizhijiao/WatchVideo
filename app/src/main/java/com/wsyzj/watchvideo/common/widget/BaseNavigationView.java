package com.wsyzj.watchvideo.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.utils.StorageUtils;
import com.wsyzj.watchvideo.common.utils.UiUtils;


/**
 * @author: wsyzj
 * @date: 2017-03-18 10:38
 * @comment: 统一的标题布局
 */
public class BaseNavigationView extends LinearLayout implements View.OnClickListener {

    private Context mContext;
    private View base_root;
    private RelativeLayout rl_navigation;
    private FrameLayout fl_navigation;
    private ImageView iv_navigation;

    private OnClickListener mNavigationOnClickListener;
    private TextView tv_title;

    public BaseNavigationView(Context context) {
        super(context);
        init(context);
    }

    public BaseNavigationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseNavigationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        base_root = LayoutInflater.from(mContext).inflate(R.layout.widget_base_navigation, null);
        rl_navigation = (RelativeLayout) base_root.findViewById(R.id.rl_navigation);
        fl_navigation = (FrameLayout) base_root.findViewById(R.id.fl_navigation);
        iv_navigation = (ImageView) base_root.findViewById(R.id.iv_navigation);
        tv_title = (TextView) base_root.findViewById(R.id.tv_title);

        setNavigationBackgroundColor(UiUtils.getColor(StorageUtils.getColorPrimary()));

        fl_navigation.setOnClickListener(this);
        setOrientation(LinearLayout.VERTICAL);
        addView(base_root);
    }

    /**
     * 隐藏整个BaseTitleView
     */
    public View hide() {
        base_root.setVisibility(View.GONE);
        return base_root;
    }

    /**
     * 设置背景
     *
     * @param color
     * @return
     */
    public RelativeLayout setNavigationBackgroundColor(int color) {
        rl_navigation.setBackgroundColor(color);
        return rl_navigation;
    }

    /**
     * 设置导航的图标
     *
     * @param drawabeId
     * @return
     */
    public ImageView setNavigationIcon(int drawabeId) {
        iv_navigation.setImageResource(drawabeId);
        return iv_navigation;
    }

    /**
     * 设置标题
     */
    public TextView setTitle(CharSequence title) {
        tv_title.setText(title);
        return tv_title;
    }

    /**
     * 返回按键事件
     *
     * @param navigationOnClickListener
     */
    public void setNavigationOnClickListener(OnClickListener navigationOnClickListener) {
        mNavigationOnClickListener = navigationOnClickListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_navigation:
                if (mNavigationOnClickListener != null) {
                    mNavigationOnClickListener.onClick(v);
                } else {
                    if (mContext instanceof BaseActivity) {
                        BaseActivity activity = (BaseActivity) mContext;
                        activity.finish();
                    }
                }
                break;
            default:
                break;
        }
    }
}
