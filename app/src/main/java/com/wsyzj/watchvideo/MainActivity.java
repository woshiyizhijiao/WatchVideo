package com.wsyzj.watchvideo;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.business.adapter.TestAdapter;
import com.wsyzj.watchvideo.common.business.bean.Music;
import com.wsyzj.watchvideo.common.business.main.MainContract;
import com.wsyzj.watchvideo.common.business.main.MainPresenter;
import com.wsyzj.watchvideo.common.tools.IntentUtils;
import com.wsyzj.watchvideo.common.tools.LogUtils;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements MainContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private MainPresenter mPresenter;
    private TestAdapter mAdapter;
    private List<Music> mMusics;

    @Override
    protected BasePresenter presenter() {
        mPresenter = new MainPresenter(this);
        return mPresenter;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        pull_to_refresh.setOnRefreshListener(this);
        pull_to_refresh.setRequestLoadMoreListener(this);
    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {
        getPermissions();
        testData();
    }

    /**
     * 获取用户权限
     */
    @Override
    public void getPermissions() {
        new RxPermissions(this).request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            // 权限获取成功
                        } else {
                            showMissingPermissionDialog();
                        }
                    }
                });
    }

    @Override
    public void showMissingPermissionDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(R.string.main_help);
        builder.setMessage(R.string.main_string_help_text);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.main_quit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton(R.string.main_settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                IntentUtils.systemSettings(MainActivity.this);
            }
        });

        builder.setCancelable(false);
        builder.show();
    }

    @Override
    public void onRefresh() {
//        Toast.makeText(MainActivity.this, "刷新了", Toast.LENGTH_SHORT).show();
    }

    private void testData() {
        mMusics = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            Music music = new Music();
            mMusics.add(music);
        }

        mAdapter = new TestAdapter(this, R.layout.test_adapter, mMusics);
        pull_to_refresh.setAdapter(mAdapter);
        pull_to_refresh.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Music music = mMusics.get(position);
    }

    @Override
    public void onLoadMoreRequested() {
        LogUtils.e("加载到底部了");
    }
}
