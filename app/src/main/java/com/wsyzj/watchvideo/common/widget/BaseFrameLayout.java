package com.wsyzj.watchvideo.common.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.utils.UiUtils;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/10
 *     desc   :
 * </pre>
 */
public abstract class BaseFrameLayout extends FrameLayout {

    public static final int STATE_UNKOWN = 0;     // 未知
    public static final int STATE_LOADING = 1;    // 加载中
    public static final int STATE_ERROR = 2;      // 错误
    public static final int STATE_EMPTY = 3;      // 空界面
    public static final int STATE_SUCCESS = 4;    // 加载成功
    private int mPageState = STATE_UNKOWN;

    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSuccessView;

    public BaseFrameLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mLoadingView = createLoadingView();
        if (mLoadingView == null) {
            addView(mLoadingView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }

        mErrorView = createErrorView();
        if (mErrorView == null) {
            addView(mErrorView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }

        mEmptyView = createEmptyView();
        if (mEmptyView == null) {
            addView(mEmptyView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }

        showPage();
    }

    public void showPage() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(mPageState == STATE_UNKOWN || mPageState == STATE_LOADING ? View.VISIBLE : View.GONE);
        }

        if (mErrorView != null) {
            mErrorView.setVisibility(mPageState == STATE_ERROR ? View.VISIBLE : View.GONE);
        }

        if (mEmptyView != null) {
            mEmptyView.setVisibility(mPageState == STATE_EMPTY ? View.VISIBLE : View.GONE);
        }

        if (mPageState == STATE_SUCCESS) {
            if (mSuccessView == null) {
//                mSuccessView = createSuccessView();
                addView(mSuccessView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            }
            mSuccessView.setVisibility(View.VISIBLE);
        } else {
            if (mSuccessView != null) {
                mSuccessView.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 创建加载中的界面
     *
     * @return
     */
    private View createLoadingView() {
        View loadingView = UiUtils.inflate(R.layout.layout_state_loading);
        return loadingView;
    }

    /**
     * 创建异常的界面
     *
     * @return
     */
    private View createErrorView() {
        View errorView = UiUtils.inflate(R.layout.layout_state_error);
        return errorView;
    }

    /**
     * 空界面
     *
     * @return
     */
    private View createEmptyView() {
        View emptyView = UiUtils.inflate(R.layout.layout_state_empty);
        return emptyView;
    }

    public abstract View createSuccessView();
}
