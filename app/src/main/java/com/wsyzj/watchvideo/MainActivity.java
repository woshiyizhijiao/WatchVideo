package com.wsyzj.watchvideo;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wsyzj.watchvideo.base.BaseActivity;
import com.wsyzj.watchvideo.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.business.main.MainContract;
import com.wsyzj.watchvideo.business.main.MainPresenter;
import com.wsyzj.watchvideo.tools.IntentUtils;
import com.wsyzj.watchvideo.widget.BasePullToRefreshView;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private MainPresenter mPresenter;


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

    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {
        pull_to_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MainActivity.this, "刷新了", Toast.LENGTH_SHORT).show();
            }
        });
        getPermissions();
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
}
