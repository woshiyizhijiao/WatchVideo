package com.wsyzj.watchvideo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.wsyzj.watchvideo.base.BaseActivity;
import com.wsyzj.watchvideo.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.widget.BasePullToRefreshView;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

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
        pull_to_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MainActivity.this, "刷新了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
