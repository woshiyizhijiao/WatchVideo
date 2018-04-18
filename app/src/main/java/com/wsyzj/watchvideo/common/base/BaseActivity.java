package com.wsyzj.watchvideo.common.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.jaeger.library.StatusBarUtil;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.utils.EventBusUtils;
import com.wsyzj.watchvideo.common.utils.StorageUtils;
import com.wsyzj.watchvideo.common.utils.UiUtils;
import com.wsyzj.watchvideo.common.widget.BaseNavigationView;
import com.wsyzj.watchvideo.common.widget.BaseState;
import com.wsyzj.watchvideo.common.widget.BaseStateLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;


/**
 * @author: wsyzj
 * @date: 2017-03-18 10:06
 * @comment: 所有Activity的基类
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseIView {

    private P mPresenter;
    private BaseProgressDialog mBaseDialog;
    public BaseNavigationView mNavigationView;
    public BaseStateLayout mStateLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basicConfig();
        setStatusBar();
        createPresenter();
        layout();
        initView();
        initData(savedInstanceState);
        registerEventBus();
    }

    /**
     * 基本配置
     */
    private void basicConfig() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  // 固定竖屏
    }

    /**
     * MVP模式的Presenter
     */
    private void createPresenter() {
        mPresenter = presenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 设置沉浸式
     */
    protected void setStatusBar() {
        int colorPrimary = StorageUtils.getColorPrimary();
        StatusBarUtil.setColor(this, UiUtils.getColor(colorPrimary));
    }

    /**
     * 设置统一的标题布局
     */
    private void layout() {
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();

        LinearLayout parentView = new LinearLayout(this);
        parentView.setOrientation(LinearLayout.VERTICAL);
        viewGroup.addView(parentView);

        // 统一的标题布局
        mNavigationView = new BaseNavigationView(this);
        LinearLayout.LayoutParams titleLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        parentView.addView(mNavigationView, titleLp);

        // 添加状态布局
        mStateLayout = new BaseStateLayout(this);
        FrameLayout.LayoutParams stateLp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        parentView.addView(mStateLayout, stateLp);

        // 填充内容布局
        View contentView = View.inflate(this, contentView(), null);
        LinearLayout.LayoutParams contetnViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        parentView.addView(contentView, contetnViewLp);
        ButterKnife.bind(this, contentView);
    }

    /**
     * 是否使用EventBus
     *
     * @return true 代表使用， 默认为false
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    private void registerEventBus() {
        if (isRegisterEventBus()) {
            EventBusUtils.register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(BaseEventBus event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(BaseEventBus event) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseRetrofit.clear(getPackageName() + "." + getClass().getSimpleName());
        if (mBaseDialog != null) {
            mBaseDialog.dismiss();
            mBaseDialog = null;
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (isRegisterEventBus()) {
            EventBusUtils.unregister(this);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_act_translate_exit_in, R.anim.anim_act_translate_exit_out);
    }

    /**
     * 显示progress
     */
    @Override
    public void showProgress() {
        if (mBaseDialog == null) {
            mBaseDialog = new BaseProgressDialog(this);
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
    public void setPageState(BaseState baseState) {
        if (mStateLayout != null) {
            mStateLayout.setState(baseState);
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
        BaseRetrofit.add(getPackageName() + "." + getClass().getSimpleName(), disposable);
    }

    protected abstract P presenter();

    protected abstract int contentView();                               // 创建布局

    protected abstract void initView();                                // 初始化控件

    protected abstract void initData(Bundle savedInstanceState);       // 初始化数据

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (UiUtils.isShouldHideKeyboard(v, ev)) {
                InputMethodManager imm =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm == null) return super.dispatchTouchEvent(ev);
                imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
