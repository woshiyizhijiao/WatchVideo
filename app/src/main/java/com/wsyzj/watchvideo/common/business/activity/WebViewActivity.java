package com.wsyzj.watchvideo.common.business.activity;

import android.content.Intent;
import android.os.Bundle;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.widget.BaseWebView;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2017/12/6 14:47
 * @Description: $desc$
 */
public class WebViewActivity extends BaseActivity {

    @BindView(R.id.webView)
    BaseWebView webView;

    @Override
    protected BasePresenter presenter() {
        return null;
    }

    @Override
    protected int contentView() {
        baseTitleView.setNavigationIcon(R.drawable.toolbar_back);
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {

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
}
