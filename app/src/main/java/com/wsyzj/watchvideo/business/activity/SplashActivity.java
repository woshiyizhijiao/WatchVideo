package com.wsyzj.watchvideo.business.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.BaseApp;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.tools.Constant;
import com.wsyzj.watchvideo.common.tools.IntentUtils;

import net.youmi.android.AdManager;
import net.youmi.android.nm.cm.ErrorCode;
import net.youmi.android.nm.sp.SpotManager;
import net.youmi.android.nm.sp.SpotRequestListener;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 创建时间 : 2017/10/23
 * 编写人 : 焦洋
 * 功能描述 :
 */

public class SplashActivity extends BaseActivity {

    /**
     * 需要的权限
     */
    private final static String[] REQUEST_PERMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private boolean isToAuthorize;  // 是否拒绝授权

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.black);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.init();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isToAuthorize) {
            getPermissions();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SpotManager.getInstance(this).onDestroy();
    }

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

    /**
     * 6.0以上需要申请权限，6.0
     */
    private void enterMain() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            getPermissions();
        } else {
            showBanner();
        }
//            }
//        }, 1500);
    }

    /**
     * 获取用户权限
     */
    public void getPermissions() {
        new RxPermissions(this).request(REQUEST_PERMISSION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            showBanner();
                        } else {
                            // 拒绝权限需提示用户开启
                            showMissingPermissionDialog();
                        }
                    }
                });
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

    /**
     * 展示广告(开屏广告)
     */
    private void showBanner() {
        SpotManager.getInstance(this).requestSpot(new SpotRequestListener() {
            @Override
            public void onRequestSuccess() {
                showToast("展示广告成功");
                IntentUtils.main(SplashActivity.this);
                finish();
            }

            @Override
            public void onRequestFailed(int errorCode) {
                switch (errorCode) {
                    case ErrorCode.NON_NETWORK:
                        showToast("网络异常");
                        IntentUtils.main(SplashActivity.this);
                        finish();
                        break;
                    case ErrorCode.NON_AD:
                        showToast("暂无视频广告");
                        IntentUtils.main(SplashActivity.this);
                        finish();
                        break;
                    default:
                        showToast("请稍后再试");
                        IntentUtils.main(SplashActivity.this);
                        finish();
                        break;
                }
            }
        });
    }
}
