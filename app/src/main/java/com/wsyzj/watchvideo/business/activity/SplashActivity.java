package com.wsyzj.watchvideo.business.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;

import com.gyf.barlibrary.ImmersionBar;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.BaseApp;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.tools.Constant;
import com.wsyzj.watchvideo.common.tools.IntentUtils;

import net.youmi.android.AdManager;

/**
 * 创建时间 : 2017/10/23
 * 编写人 : 焦洋
 * 功能描述 :
 */

public class SplashActivity extends BaseActivity {

    private boolean isToAuthorize;  // 是否拒绝授权

    @Override
    protected BasePresenter presenter() {
        return null;
    }

    @Override
    protected int contentView() {
        baseTitleView.hide();
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initThreeSdk();
        enterMain();
    }

    /**
     * 初始化第三方sdk
     */
    private void initThreeSdk() {
        AdManager.getInstance(this).init(Constant.Youmi.APP_ID, Constant.Youmi.APP_SECRET, BaseApp.isDebug);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.fullScreen(true).init();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isToAuthorize) {
            getPermissions();
        }
    }

    /**
     * 6.0以上需要申请权限，6.0
     */
    private void enterMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
//                    getPermissions();
//                } else {
                IntentUtils.news(SplashActivity.this);
                finish();
//                }
            }
        }, 1000);
    }

    /**
     * 获取用户权限
     */
    public void getPermissions() {
//        new RxPermissions(this).request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(@NonNull Boolean aBoolean) throws Exception {
//                        if (aBoolean) {
//                            IntentUtils.news(SplashActivity.this);
//                            finish();
//                        } else {
//                            // 拒绝权限需提示用户开启
//                            showMissingPermissionDialog();
//                        }
//                    }
//                });
    }

    public void showMissingPermissionDialog() {
        isToAuthorize = true;
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle(R.string.main_help);
        builder.setMessage(R.string.main_string_help_text);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.main_quit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        builder.setPositiveButton(R.string.main_settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                IntentUtils.systemSettings(SplashActivity.this);
            }
        });

        builder.setCancelable(false);
        builder.show();
    }
}
