package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.business.bean.NewsDetails;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BaseIModel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;

import java.util.List;

import io.reactivex.Flowable;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/02
 *     desc   : 新闻频道
 * </pre>
 */
public class NewsChannelContract {

    public interface View extends BaseIView {
        void setContentList(List<NewsDetails.ResultBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist);

        void setLoadMoreByTotal(int totalCount);
    }

    public interface Model extends BaseIModel {
        Flowable<NewsDetails> getNewsList(String channelId, String channelName, int page);
    }

    interface Presenter extends BaseIPresenter<View> {
        void getArguments(BaseFragment baseFragment);

        void getNewsList(boolean refreshing);
    }
}
