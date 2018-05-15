package com.wsyzj.watchvideo.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.utils.UiUtils;
import com.wsyzj.watchvideo.common.widget.BaseStateLayout;
import com.wsyzj.watchvideo.common.widget.StateLayout;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * @author: wsyzj
 * @date: 2016-10-02 12:23
 * @comment: 所有Fragment的基类
 */
public abstract class BaseFragment<P extends BaseIPresenter> extends Fragment implements BaseIView {

    protected P mPresenter;
    protected Activity mActivity;

    private boolean isViewCreated;          // 控件是否初始化完成
    private boolean isLoadDataCompleted;    //数据是否已加载完毕

    private BaseProgressDialog mBaseDialog;
    public BaseStateLayout mStateLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseRetrofit.clear(mActivity.getPackageName() + "." + getClass().getSimpleName());
        if (mBaseDialog != null) {
            mBaseDialog.dismiss();
            mBaseDialog = null;
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isViewCreated = true;

        LinearLayout parentView = new LinearLayout(mActivity);
        parentView.setOrientation(LinearLayout.VERTICAL);

        // 状态布局
        mStateLayout = new BaseStateLayout(mActivity);
        parentView.addView(mStateLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // 内容布局
        View view = UiUtils.inflate(contentView());
        parentView.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ButterKnife.bind(this, view);
        return parentView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isLoadDataCompleted) {
            isLoadDataCompleted = true;
            createPresenter();
            initView();
            initData();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            isLoadDataCompleted = true;
            createPresenter();
            initView();
            initData();
        }
    }

    /**
     * Presenter的初始化操作
     */
    private void createPresenter() {
        mPresenter = presenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 显示progress
     */
    @Override
    public void showProgress() {
        if (mBaseDialog == null) {
            mBaseDialog = new BaseProgressDialog(mActivity);
        }
        mBaseDialog.show();
    }

    /**
     * 隐藏progress
     */
    @Override
    public void dismissProgress() {
        if (mBaseDialog != null && mBaseDialog.isShowing()) {
            mBaseDialog.dismiss();
        }
    }

    @Override
    public void setPageState(StateLayout baseState) {
        if (mStateLayout != null) {
            mStateLayout.setPageState(baseState);
        }
    }

    /**
     * mvp 显示toast
     *
     * @param message
     */
    @Override
    public void showToast(final String message) {
        ToastUtils.showShort(message);
    }

    /**
     * 网络请求进行统一管理
     *
     * @param disposable
     */
    @Override
    public void addDisposable(Disposable disposable) {
        BaseRetrofit.add(mActivity.getPackageName() + "." + getClass().getSimpleName(), disposable);
    }

    protected abstract P presenter();          // 使用mvp模式加载

    public abstract int contentView();

    public abstract void initView();

    public abstract void initData();
}
