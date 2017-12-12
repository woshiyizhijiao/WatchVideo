package com.wsyzj.watchvideo.common.business.mvp;

import android.os.Bundle;

import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.business.activity.NewsActivity;
import com.wsyzj.watchvideo.common.business.bean.News;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;
import com.wsyzj.watchvideo.common.tools.Constant;

import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/6 9:50
 * @Description: $desc$
 */
public class NewsFragmentPresenter extends BasePresenter<NewsFragmentContract.View, NewsFragmentContract.Model> implements NewsFragmentContract.Presenter {

    private NewsFragmentContract.View mView;
    private NewsFragmentContract.Model mModel;

    private int mStart;
    private int mNum;
    private int mTitleIndex;
    private String mCurrentTitle;
    private List<News.ResultBeanX.ResultBean.ListBean> mNewsList;

    public NewsFragmentPresenter(NewsFragmentContract.View view) {
        mView = view;
        mModel = new NewsFragmentModel();
    }

    /**
     * 获取传递参数
     *
     * @param fragment
     */
    @Override
    public void getArguments(BaseFragment fragment) {
        Bundle arguments = fragment.getArguments();
        mTitleIndex = arguments.getInt(NewsActivity.BUNDLE_TITLE_INDEX, 0);
        mCurrentTitle = arguments.getString(NewsActivity.BUNDLE_CURRENT_TITLE, "");
    }

    /**
     * 获取每个标题下的列表
     */
    @Override
    public void getNewsListByTitle(final boolean refreshing) {
        if (refreshing) {
            mStart = 0;
        } else {
            mStart++;
        }

        BaseTSubscriber<News> baseTSubscriber = mModel.getNewsListByTitle(mCurrentTitle, mStart, mNum)
                .subscribeWith(new BaseTSubscriber<News>() {
                    @Override
                    public void onSuccess(Object data) {
                        News news = (News) data;
                        if (news.code == Constant.JingDong.JINGDONG_CODE) {
                            News.ResultBeanX result = news.result;
                            if (result.status == Constant.JingDong.JINGDONG_STATUS) {
                                List<News.ResultBeanX.ResultBean.ListBean> list = result.result.list;
                                if (refreshing) {
                                    mNewsList = list;
                                } else {
                                    mNewsList.addAll(list);
                                }

                                if (list != null && list.size() == mNum) {
                                    mView.setLoadMoreState(1000);
                                } else {
                                    mView.setLoadMoreState(0);
                                }

                                mView.setNewsList(mNewsList);
                            }
                        }
                        mView.setRefreshing(false);
                    }
                });
        mView.addDisposable(baseTSubscriber);
    }
}