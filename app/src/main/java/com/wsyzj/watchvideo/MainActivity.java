package com.wsyzj.watchvideo;

import android.os.Bundle;

import com.wsyzj.watchvideo.base.BaseActivity;
import com.wsyzj.watchvideo.base.mvp.BasePresenter;

public class MainActivity extends BaseActivity {

    @Override
    protected BasePresenter presenter() {
        return null;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {

    }
}
