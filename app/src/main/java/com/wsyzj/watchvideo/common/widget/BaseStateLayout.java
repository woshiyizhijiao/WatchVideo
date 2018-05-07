package com.wsyzj.watchvideo.common.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.utils.UiUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private Context mContext;
    private boolean isSuccess;      // 防止出现，界面加载成功，再次加载时没有网络，又显示异常
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
        ButterKnife.bind(this, mStateView);
        addView(mStateView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight()));
        setErrorNetState();
    }

    @OnClick({R.id.tv_error, R.id.tv_empty})
    public void bkOnClick(View view) {
        setPageState(BaseState.STATE_LOADING);
        boolean connected = NetworkUtils.isConnected();

        switch (view.getId()) {
            case R.id.tv_error:
                if (mOnStateErrorListener != null && connected) {
                    mOnStateErrorListener.onStateError();
                }

                if (!connected && !isSuccess) {
                    tv_error.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setPageState(BaseState.STATE_ERROR);
                        }
                    }, NO_NETWORK_ANIM_TIME);
                }
                break;
            case R.id.tv_empty:
                if (mOnStateEmptyListener != null && connected) {
                    mOnStateEmptyListener.onStateEmpty();
                }

                if (!connected) {
                    tv_empty.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setPageState(BaseState.STATE_EMPTY);
                        }
                    }, NO_NETWORK_ANIM_TIME);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 设置空数据文本描述
     */
    public void setEmptyText(CharSequence charSequence) {
        if (tv_empty != null) {
            tv_empty.setText(charSequence);
        }
    }

    /**
     * 空界面与顶部的距离
     */
    public void setEmptyTopMargin(int px) {
        if (tv_empty != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            params.topMargin = px;
            tv_empty.setLayoutParams(params);
        }
    }

    /**
     * 设置异常文本
     */
    public void setErrorText(CharSequence charSequence) {
        if (tv_error != null) {
            tv_error.setText(charSequence);
        }
    }

    /**
     * 异常界面与顶部的距离
     */
    public void setErrorTopMargin(int px) {
        if (tv_error != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            params.topMargin = px;
            tv_error.setLayoutParams(params);
        }
    }

    /**
     * 设置异常网络状态
     */
    private void setErrorNetState() {
        if (!NetworkUtils.isConnected()) {
            setPageState(BaseState.STATE_ERROR);
        }
    }

    /**
     * 根据不同状态显示不同布局
     *
     * @param baseState
     */
    public void setPageState(BaseState baseState) {
        switch (baseState) {
            case STATE_LOADING:
                showLoadingPage();
                break;
            case STATE_ERROR:
                showErrorPage();
                break;
            case STATE_EMPTY:
                showEmptyPage();
                break;
            case STATE_SUCCESS:
                showSuccessPage();
                break;
            default:
                break;
        }
    }

    /**
     * 加载中
     */
    private void showLoadingPage() {
        pb_loading.setVisibility(View.VISIBLE);
        tv_error.setVisibility(View.INVISIBLE);
        tv_empty.setVisibility(View.INVISIBLE);
    }

    /**
     * 异常界面
     */
    private void showErrorPage() {
        if (isSuccess) {
            return;
        }
        pb_loading.setVisibility(View.INVISIBLE);
        tv_error.setVisibility(View.VISIBLE);
        tv_empty.setVisibility(View.INVISIBLE);
    }

    /**
     * 空界面
     */
    private void showEmptyPage() {
        pb_loading.setVisibility(View.INVISIBLE);
        tv_error.setVisibility(View.INVISIBLE);
        tv_empty.setVisibility(View.VISIBLE);
    }

    /**
     * 成功状态
     */
    private void showSuccessPage() {
        isSuccess = true;
        pb_loading.setVisibility(View.INVISIBLE);
        tv_error.setVisibility(View.INVISIBLE);
        tv_empty.setVisibility(View.INVISIBLE);
        mStateView.setVisibility(View.GONE);
    }

    public interface OnStateErrorListener {
        void onStateError();
    }

    public interface OnStateEmptyListener {
        void onStateEmpty();
    }
}
