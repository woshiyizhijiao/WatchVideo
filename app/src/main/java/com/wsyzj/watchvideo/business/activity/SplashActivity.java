package com.wsyzj.watchvideo.business.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.BaseApp;
import com.wsyzj.watchvideo.business.utils.IntentUtils;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.constant.Constant;
import com.wsyzj.watchvideo.common.widget.BaseState;

import net.youmi.android.AdManager;
import net.youmi.android.nm.cm.ErrorCode;
import net.youmi.android.nm.sp.SplashViewSettings;
import net.youmi.android.nm.sp.SpotListener;
import net.youmi.android.nm.sp.SpotManager;

import butterknife.BindView;
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
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
    };

    @BindView(R.id.rl_splash)
    RelativeLayout rl_splash;

    private boolean isToAuthorize;  // 是否拒绝授权

    @Override
    protected void setStatusBar() {
//        super.setStatusBar();
        StatusBarUtil.setColor(this, Color.BLACK);
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
        mNavigationView.hide();
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        setPageState(BaseState.STATE_SUCCESS);
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
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            getPermissions();
        } else {
            showBanner();
        }
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

        // 对开屏进行设置
        SplashViewSettings splashViewSettings = new SplashViewSettings();
        // 设置是否展示失败自动跳转，默认自动跳转
//        splashViewSettings.setAutoJumpToTargetWhenShowFailed(false);
        // 设置跳转的窗口类
        splashViewSettings.setTargetClass(MainActivity.class);
        // 设置开屏的容器
        splashViewSettings.setSplashViewContainer(rl_splash);

        // 展示开屏广告
        SpotManager.getInstance(this).showSplash(this, splashViewSettings, new SpotListener() {

            @Override
            public void onShowSuccess() {

            }

            @Override
            public void onShowFailed(int errorCode) {
                switch (errorCode) {
                    case ErrorCode.NON_NETWORK:
//                        showToast("网络异常");
                        break;
                    case ErrorCode.NON_AD:
//                        showToast("暂无开屏广告");
                        break;
                    case ErrorCode.RESOURCE_NOT_READY:
//                        showToast("开屏资源还没准备好");
                        break;
                    case ErrorCode.SHOW_INTERVAL_LIMITED:
//                        showToast("开屏展示间隔限制");
                        break;
                    case ErrorCode.WIDGET_NOT_IN_VISIBILITY_STATE:
//                        showToast("开屏控件处在不可见状态");
                        break;
                    default:
//                        showToast("errorCode: %d" + errorCode);
                        break;
                }
            }

            @Override
            public void onSpotClosed() {
//                showToast("开屏被关闭");
            }

            @Override
            public void onSpotClicked(boolean isWebPage) {
//                showToast("开屏被点击");
//                showToast("开屏被点击 + 是否是网页广告？%s" + (isWebPage ? "是" : "不是"));
            }
        });
    }
}
