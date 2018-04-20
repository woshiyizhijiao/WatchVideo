package com.wsyzj.watchvideo.business.mvp;

import android.os.Bundle;

import com.wsyzj.watchvideo.business.bean.NewsDetails;
import com.wsyzj.watchvideo.business.fragment.NewsChannelFragment;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.constant.Constant;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;
import com.wsyzj.watchvideo.common.widget.BaseState;

import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/02
 *     desc   :
 * </pre>
 */
public class NewsChannelPresenter extends BasePresenter<NewsChannelContract.View, NewsChannelContract.Model> implements NewsChannelContract.Presenter {

    private NewsChannelContract.View mView;
    private NewsChannelContract.Model mModel;

    private String mChannelId;
    private String mChannelName;
    private int mPage;

    private List<NewsDetails.ResultBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mContentList;

    public NewsChannelPresenter(NewsChannelContract.View view) {
        mView = view;
        mModel = new NewsChannelModel();
    }

    /**
     * 获取界面传参
     *
     * @param baseFragment
     */
    @Override
    public void getArguments(BaseFragment baseFragment) {
        Bundle arguments = baseFragment.getArguments();
        mChannelId = arguments.getString(NewsChannelFragment.BUNDLE_CHANNEL_ID);
        mChannelName = arguments.getString(NewsChannelFragment.BUNDLE_CHANNEL_NAME);
    }

    @Override
    public void getNewsList(final boolean refreshing) {
        if (refreshing) {
            mPage = 1;
        } else {
            mPage++;
        }

        BaseTSubscriber<NewsDetails> baseTSubscriber = mModel.getNewsList(mChannelId, mChannelName, mPage).subscribeWith(new BaseTSubscriber<NewsDetails>() {
            @Override
            public void onSuccess(Object data) {
                NewsDetails newsDetails = (NewsDetails) data;
                if (newsDetails.code == Constant.JingDong.JINGDONG_CODE) {
                    NewsDetails.ResultBean.ShowapiResBodyBean.PagebeanBean pagebean = newsDetails.result.showapi_res_body.pagebean;
                    int allNum = pagebean.allNum;
                    List<NewsDetails.ResultBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist = pagebean.contentlist;

                    if (refreshing) {
                        mContentList = contentlist;
                    } else {
                        mContentList.addAll(contentlist);
                    }

                    mView.setContentList(mContentList);
                    mView.setPageState(BaseState.STATE_SUCCESS);
                    mView.setLoadMoreByTotal(allNum);
                } else {
                    mView.showToast(newsDetails.msg);
                }
            }

            @Override
            public void onFailure() {

            }
        });
        mView.addDisposable(baseTSubscriber);
    }
}
