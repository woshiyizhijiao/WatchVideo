package com.wsyzj.watchvideo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.base.mvp.IView;
import com.wsyzj.watchvideo.widget.BaseTitleView;

import butterknife.ButterKnife;


/**
 * @author: wsyzj
 * @date: 2017-03-18 10:06
 * @comment: 所有Activity的基类
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {

    private ImmersionBar mImmersionBar;
    private P mPresenter;
    public BaseTitleView baseTitleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initImmersionBar();
        createPresenter();
        layout();
        initViews();
        initDatas(savedInstanceState);
    }

    /**
     * 初始化沉浸式
     */
    private void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.colorPrimaryDark);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.init();
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
     * MVP模式的Presenter
     */
    private void createPresenter() {
        mPresenter = presenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    protected abstract P presenter();

    protected abstract int contentView();                               // 创建布局

    protected abstract void initViews();                                // 初始化控件

    protected abstract void initDatas(Bundle savedInstanceState);       // 初始化数据

}
