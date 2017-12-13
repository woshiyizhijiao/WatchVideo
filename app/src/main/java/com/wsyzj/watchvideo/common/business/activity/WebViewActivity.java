package com.wsyzj.watchvideo.common.business.activity;

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
import android.widget.ProgressBar;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2017/12/6 14:47
 * @Description: $desc$
 */
public class WebViewActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.webView)
    WebView webView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

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
        baseTitleView.setNavigationOnClickListener(this);
        webViewSettings();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getExtras();
    }

    /**
     * 获取传参
     */
    private void getExtras() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");

        baseTitleView.setTitle(title);
        webView.loadUrl(url);
    }

    /**
     * webview的设置
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void webViewSettings() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setBlockNetworkImage(false);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);
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

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
