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

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.wsyzj.watchvideo.R;
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

    // 判断网络状态时，有个默认的加载动画
    private static final int NO_NETWORK_ANIM_TIME = 300;

    private ProgressBar pb_loading;
    private TextView tv_error;
    private TextView tv_empty;
    private View mStateView;

    private OnStateErrorListener mOnStateErrorListener;
    private OnStateEmptyListener mOnStateEmptyListener;

    public void setOnStateErrorListener(OnStateErrorListener onStateErrorListener) {
        mOnStateErrorListener = onStateErrorListener;
    }

    public void setOnStateEmptyListener(OnStateEmptyListener onStateEmptyListener) {
        mOnStateEmptyListener = onStateEmptyListener;
    }

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
        pb_loading = (ProgressBar) mStateView.findViewById(R.id.pb_loading);
        tv_error = (TextView) mStateView.findViewById(R.id.tv_error);
        tv_empty = (TextView) mStateView.findViewById(R.id.tv_empty);

        addView(mStateView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight()));
        setNetState();

        /**
         * 点击异常状态回调(需有网络回调)
         */
        tv_error.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setState(BaseState.STATE_LOADING);
                boolean connected = NetworkUtils.isConnected();

                if (mOnStateErrorListener != null && connected) {
                    mOnStateErrorListener.onStateError();
                }

                if (!connected) {
                    tv_error.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setState(BaseState.STATE_ERROR);
                        }
                    }, NO_NETWORK_ANIM_TIME);
                }
            }
        });

        /**
         * 点击空界面回调(需有网络)
         */
        tv_empty.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setState(BaseState.STATE_LOADING);
                boolean connected = NetworkUtils.isConnected();

                if (mOnStateEmptyListener != null && connected) {
                    mOnStateEmptyListener.onStateEmpty();
                }

                if (!connected) {
                    tv_empty.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setState(BaseState.STATE_ERROR);
                        }
                    }, NO_NETWORK_ANIM_TIME);
                }
            }
        });
    }

    /**
     * 没网，显示没有网络的布局
     */
    private void setNetState() {
        if (NetworkUtils.isConnected()) {
            setState(BaseState.STATE_ERROR);
        }
    }

    /**
     * 根据不同状态显示不同布局
     *
     * @param baseState
     */
    public void setState(BaseState baseState) {
        switch (baseState) {
            case STATE_LOADING:
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
                mStateView.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    public interface OnStateErrorListener {
        void onStateError();
    }

    public interface OnStateEmptyListener {
        void onStateEmpty();
    }
}
