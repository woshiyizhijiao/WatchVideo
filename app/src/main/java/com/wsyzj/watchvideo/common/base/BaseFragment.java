package com.wsyzj.watchvideo.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wsyzj.watchvideo.common.base.mvp.IPresenter;
import com.wsyzj.watchvideo.common.base.mvp.IView;
import com.wsyzj.watchvideo.common.base.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.tools.ToastUtils;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * @author: wsyzj
 * @date: 2016-10-02 12:23
 * @comment: 所有Fragment的基类
 */
public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IView {
    protected Activity mActivity;
    protected P mPresenter;
    private boolean isViewCreated;          // 控件是否初始化完成
    private boolean isLoadDataCompleted;    //数据是否已加载完毕
    private BaseProgressDialog mBaseDialog;

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
        View view = inflater.inflate(contentView(), container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isLoadDataCompleted) {
            isLoadDataCompleted = true;
            createPresenter();
            initData();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            isLoadDataCompleted = true;
            createPresenter();
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

    /**
     * mvp 显示toast
     *
     * @param message
     */
    @Override
    public void showToast(String message) {
        ToastUtils.showToast(message);
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
