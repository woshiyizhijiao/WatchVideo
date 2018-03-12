package com.wsyzj.watchvideo.business.activity;

import android.os.Bundle;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.mvp.ChannelManagerContract;
import com.wsyzj.watchvideo.business.mvp.ChannelManagerPresenter;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/12
 *     desc   : 频道管理
 * </pre>
 */
public class ChannelManagerActivity extends BaseActivity implements ChannelManagerContract.View {

    private ChannelManagerPresenter mPresenter;

    @Override
    protected BasePresenter presenter() {
        mPresenter = new ChannelManagerPresenter(this);
        return mPresenter;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_channel_manager;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.getExtras(this);

    }

}
