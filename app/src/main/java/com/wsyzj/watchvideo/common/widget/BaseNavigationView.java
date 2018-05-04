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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author: wsyzj
 * @date: 2017-03-18 10:38
 * @comment: 统一的标题布局
 */
public class BaseNavigationView extends LinearLayout {

    @BindView(R.id.rl_navigation)
    RelativeLayout rl_navigation;

    @BindView(R.id.fl_navigation)
    FrameLayout fl_navigation;

    @BindView(R.id.iv_navigation)
    ImageView iv_navigation;

    @BindView(R.id.tv_navigation)
    TextView tv_navigation;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_negative)
    TextView tv_negative;

    @BindView(R.id.fl_negative)
    FrameLayout fl_negative;

    @BindView(R.id.iv_negative)
    ImageView iv_negative;

    private Context mContext;
    private View base_root;

    private OnClickListener mNavigationOnClickListener;

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

        ButterKnife.bind(this, base_root);
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
     * @param resId
     * @return
     */
    public ImageView setNavigationIcon(int resId) {
        iv_navigation.setImageResource(resId);
        fl_navigation.setVisibility(View.VISIBLE);
        tv_navigation.setVisibility(View.GONE);
        return iv_navigation;
    }

    /**
     * 设置导航文字
     *
     * @param charSequence
     * @return
     */
    public TextView setNavigationText(CharSequence charSequence) {
        tv_navigation.setText(charSequence);
        tv_navigation.setVisibility(View.VISIBLE);
        fl_navigation.setVisibility(View.GONE);
        return tv_navigation;
    }

    /**
     * 设置标题
     */
    public TextView setTitle(CharSequence title) {
        tv_title.setText(title);
        return tv_title;
    }

    /**
     * 设置右边文字
     *
     * @return
     */
    public TextView setNegativeText(CharSequence charSequence) {
        tv_negative.setText(charSequence);
        tv_negative.setVisibility(View.VISIBLE);
        fl_negative.setVisibility(View.GONE);
        return tv_negative;
    }

    /**
     * 设置右边图片
     *
     * @param resId
     * @return
     */
    public ImageView setNegativeImageResource(int resId) {
        iv_negative.setImageResource(resId);
        fl_negative.setVisibility(View.VISIBLE);
        tv_negative.setVisibility(View.GONE);
        return iv_negative;
    }

    /**
     * 返回按键事件
     *
     * @param navigationOnClickListener
     */
    public void setNavigationOnClickListener(OnClickListener navigationOnClickListener) {
        mNavigationOnClickListener = navigationOnClickListener;
    }

    @OnClick(R.id.fl_navigation)
    public void bkOnClick(View v) {
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
