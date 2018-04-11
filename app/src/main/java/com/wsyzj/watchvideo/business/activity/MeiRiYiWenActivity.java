package com.wsyzj.watchvideo.business.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.BaseProgressDialog;
import com.wsyzj.watchvideo.common.base.BaseThreadManager;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 焦洋
 * @date 2017/12/15 11:08
 * @Description: 每日一文
 */
public class MeiRiYiWenActivity extends BaseActivity {

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_author)
    TextView tv_author;

    @BindView(R.id.tv_content)
    TextView tv_content;

    @Override
    protected BasePresenter presenter() {
        return null;
    }

    @Override
    protected int contentView() {
        mNavigationView.setTitle("每日一文");
        return R.layout.activity_meiriyiwen;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getIntentExtras();
    }

    /**
     * 获取界面传参
     */
    private void getIntentExtras() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String content = intent.getStringExtra("content");

        tv_title.setText(title);
        tv_author.setText("作者: " + author);
        tv_content.setText(Html.fromHtml(content));
    }

    /**
     * 随机一文
     */
    @OnClick(R.id.btn_random_article)
    public void randomArticle() {
        final BaseProgressDialog dialog = new BaseProgressDialog(this);
        dialog.show();

        BaseThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("https://meiriyiwen.com/random").get();
                    final Element article_show = document.getElementById("article_show");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            dialog.dismiss();
                            String h1 = article_show.select("h1").text();
                            String article_author = article_show.getElementsByClass("article_author").text();
                            article_author = TextUtils.isEmpty(article_author) ? "佚名" : article_author;
                            String article_text = article_show.getElementsByClass("article_text").toString();

                            tv_title.setText(TextUtils.isEmpty(h1) ? "" : h1);
                            tv_author.setText(article_author);
                            tv_content.setText(Html.fromHtml(article_text));

                            scrollView.post(new Runnable() {
                                @Override
                                public void run() {
                                    // 滚动到顶部
                                    scrollView.fullScroll(ScrollView.FOCUS_UP);
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    dialog.dismiss();
                    e.printStackTrace();
                }
            }
        });
    }
}
