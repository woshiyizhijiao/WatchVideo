package com.wsyzj.watchvideo.business.activity;

import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.widget.BaseState;

import net.youmi.android.nm.cm.ErrorCode;
import net.youmi.android.nm.sp.SpotListener;
import net.youmi.android.nm.sp.SpotManager;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2017/12/6 14:47
 * @Description: $desc$
 */
public class WebViewActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onPause() {
        super.onPause();
        SpotManager.getInstance(this).onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SpotManager.getInstance(this).onStop();
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
        return R.layout.activity_webview;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {
        /**
         * 插屏banner
         */
        SpotManager.getInstance(this).setImageType(SpotManager.IMAGE_TYPE_VERTICAL);
        SpotManager.getInstance(this).setAnimationType(SpotManager.ANIMATION_TYPE_ADVANCED);
        mNavigationView.setNavigationOnClickListener(this);
        webViewSettings();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getExtras();
        showSpot();
    }

    /**
     * 获取传参
     */
    private void getExtras() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");

        mNavigationView.setTitle(title);
        webView.loadUrl(url);
    }

    /**
     * webview的设置
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void webViewSettings() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setBlockNetworkImage(false);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setPageState(BaseState.STATE_SUCCESS);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("scheme:") || url.startsWith("scheme:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();  // 接收说有网站证书
            }
        });
    }

    /**
     * 插屏广告
     */
    private void showSpot() {
        SpotManager.getInstance(this).showSpot(this, new SpotListener() {

            @Override
            public void onShowSuccess() {
//                showToast("插屏展示成功");
            }

            @Override
            public void onShowFailed(int errorCode) {
                switch (errorCode) {
                    case ErrorCode.NON_NETWORK:
//                        showToast("网络异常");
                        break;
                    case ErrorCode.NON_AD:
//                        showToast("暂无插屏广告");
                        break;
                    case ErrorCode.RESOURCE_NOT_READY:
//                        showToast("插屏资源还没准备好");
                        break;
                    case ErrorCode.SHOW_INTERVAL_LIMITED:
//                        showToast("请勿频繁展示");
                        break;
                    case ErrorCode.WIDGET_NOT_IN_VISIBILITY_STATE:
//                        showToast("请设置插屏为可见状态");
                        break;
                    default:
//                        showToast("插屏展示失败,请稍后再试");
                        break;
                }
            }

            @Override
            public void onSpotClosed() {
//                showToast("插屏被关闭");
            }

            @Override
            public void onSpotClicked(boolean isWebPage) {
//                showToast("插屏被点击 是否是网页广告？%s" + (isWebPage ? "是" : "不是"));
            }
        });
    }

    @Override
    public void onBackPressed() {
        goBack();
    }

    @Override
    public void onClick(View v) {
        goBack();
    }

    /**
     * 返回上一级
     */
    private void goBack() {
        if (SpotManager.getInstance(this).isSpotShowing()) {
            SpotManager.getInstance(this).hideSpot();
        } else if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}