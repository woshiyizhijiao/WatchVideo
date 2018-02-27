package com.wsyzj.watchvideo.common.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.jaeger.library.StatusBarUtil;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.tools.EventBusUtils;
import com.wsyzj.watchvideo.common.tools.UiUtils;
import com.wsyzj.watchvideo.common.widget.BaseTitleView;

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
    public BaseTitleView baseTitleView;
    public BaseProgressDialog mBaseDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 固定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        createPresenter();
        setStatusBar();
        layout();
        initView();
        initData(savedInstanceState);
        registerEventBus();
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
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.colorPrimary));
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
        baseTitleView = new BaseTitleView(this);
        LinearLayout.LayoutParams title_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        parentView.addView(baseTitleView, title_lp);

        // 填充内容布局
        View contentView = View.inflate(this, contentView(), null);
        LinearLayout.LayoutParams contetnView_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        parentView.addView(contentView, contetnView_lp);
        ButterKnife.bind(this);
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

    /**
     * mvp 显示toast
     *
     * @param message
     */
    @Override
    public void showToast(String message) {
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

}
