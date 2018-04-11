package com.wsyzj.watchvideo.common.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.utils.StorageUtils;
import com.wsyzj.watchvideo.common.utils.UiUtils;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/10
 *     desc   : 根据界面状态加载不同的布局
 * </pre>
 */
public class BaseStateLayout extends FrameLayout {

    private ProgressBar pb_loading;
    private TextView tv_error;
    private TextView tv_empty;
    private View mStateView;
    private View view_state_bar;

    public BaseStateLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BaseStateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseStateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mStateView = UiUtils.inflate(R.layout.widget_base_state_layout);
        view_state_bar = mStateView.findViewById(R.id.view_state_bar);
        pb_loading = (ProgressBar) mStateView.findViewById(R.id.pb_loading);
        tv_error = (TextView) mStateView.findViewById(R.id.tv_error);
        tv_empty = (TextView) mStateView.findViewById(R.id.tv_empty);

        int colorPrimary = UiUtils.getColor(StorageUtils.getColorPrimary());
        view_state_bar.setBackgroundColor(colorPrimary);
        view_state_bar.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight()));

        addView(mStateView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight()));
    }

    /**
     * 根据不同状态显示不同布局
     *
     * @param baseState
     */
    public void setState(BaseState baseState) {
        switch (baseState) {
            case STATE_UNkNOWN:
                pb_loading.setVisibility(View.VISIBLE);
                tv_error.setVisibility(View.INVISIBLE);
                tv_empty.setVisibility(View.INVISIBLE);
                break;
            case STATE_ERROR:
                pb_loading.setVisibility(View.INVISIBLE);
                tv_error.setVisibility(View.VISIBLE);
                tv_empty.setVisibility(View.INVISIBLE);
                break;
            case STATE_EMPTY:
                pb_loading.setVisibility(View.INVISIBLE);
                tv_error.setVisibility(View.INVISIBLE);
                tv_empty.setVisibility(View.VISIBLE);
                break;
            case STATE_SUCCESS:
                pb_loading.setVisibility(View.INVISIBLE);
                tv_error.setVisibility(View.INVISIBLE);
                tv_empty.setVisibility(View.INVISIBLE);
                view_state_bar.setVisibility(View.INVISIBLE);
                mStateView.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
