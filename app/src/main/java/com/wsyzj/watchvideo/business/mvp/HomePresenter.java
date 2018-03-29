package com.wsyzj.watchvideo.business.mvp;

import android.app.Activity;

import com.blankj.utilcode.util.LogUtils;
import com.wsyzj.watchvideo.common.base.BaseThreadManager;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.business.bean.DouBan;
import com.wsyzj.watchvideo.business.bean.Gank;
import com.wsyzj.watchvideo.business.bean.MeiRiYiWen;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/12 14:05
 * @Description: $desc$
 */
public class HomePresenter extends BasePresenter<HomeContract.View, HomeContract.Model> implements HomeContract.Presenter {

    private HomeContract.View mView;
    private HomeContract.Model mModel;

    private String mType = "福利";
    private int mPageNumber = 10;
    private int mPage = 1;
    private boolean isFirstLoad = true;
    public List<Gank.ResultsBean> mGankData;

    public HomePresenter(HomeContract.View view) {
        mView = view;
        mModel = new HomeModel();
    }

    /**
     * gank数据
     */
    @Override
    public void getGankData(final boolean refreshing) {
        if (refreshing) {
            mPage = 1;
        } else {
            mPage++;
        }

        BaseTSubscriber<Gank> baseTSubscriber = mModel.getGankData(mType, mPageNumber, mPage)
                .subscribeWith(new BaseTSubscriber<Gank>() {
                    @Override
                    public void onSuccess(Object data) {
                        Gank gank = (Gank) data;
                        if (!gank.error) {
                            List<Gank.ResultsBean> results = gank.results;

                            if (refreshing) {
                                mGankData = results;
                            } else {
                                mGankData.addAll(results);
                            }

                            if (mPageNumber == results.size()) {
                                mView.setLoadMoreState(999);
                            } else {
                                mView.setLoadMoreState(0);
                            }

                            mView.setGankData(mGankData);
                        }
                        setFirstPageLoadFinish();
                    }
                });
        mView.addDisposable(baseTSubscriber);
    }

    /**
     * 第一个界面的数据加载完场
     */
    private void setFirstPageLoadFinish() {
        if (isFirstLoad) {
            mView.firstPageLoadFinish();
            isFirstLoad = false;
        }
    }

    /**
     * 每日一文
     */
    @Override
    public void getMeiRiYiWen(final Activity activity) {
        BaseThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("https://meiriyiwen.com/").get();
                    Element article_show = document.getElementById("article_show");

                    final MeiRiYiWen meiRiYiWen = new MeiRiYiWen();
                    meiRiYiWen.title = article_show.select("h1").text();
                    meiRiYiWen.author = article_show.getElementsByClass("article_author").text();
                    meiRiYiWen.content = article_show.getElementsByClass("article_text").toString();
                    meiRiYiWen.digest = article_show.getElementsByClass("article_text").text();

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mView.setMeiRiYiWenData(meiRiYiWen);
                            setFirstPageLoadFinish();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 设置豆瓣电影
     */
    @Override
    public void getTheatersList() {
        BaseTSubscriber<DouBan> baseTSubscriber = mModel
                .getTheatersList(1, 20).subscribeWith(new BaseTSubscriber<DouBan>() {
                    @Override
                    public void onSuccess(Object data) {
                        DouBan douBan = (DouBan) data;
                        List<DouBan.SubjectsBean> subjects = douBan.subjects;
                        if (subjects != null) {
                            mView.setTheatersList(subjects);
                            setFirstPageLoadFinish();
                        }
                    }
                });
        mView.addDisposable(baseTSubscriber);
    }
}

