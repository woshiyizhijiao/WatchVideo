package com.wsyzj.watchvideo.business.activity;

import android.os.Bundle;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.mvp.OneLineContract;
import com.wsyzj.watchvideo.business.mvp.OneLinePresenter;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/05/02
 *     desc   : 在线音乐
 * </pre>
 */
public class OneLineActivity extends BaseActivity implements OneLineContract.View {

    private OneLinePresenter mPresenter;

    @Override
    protected BasePresenter presenter() {
        mPresenter = new OneLinePresenter(this);
        return mPresenter;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_one_line;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
