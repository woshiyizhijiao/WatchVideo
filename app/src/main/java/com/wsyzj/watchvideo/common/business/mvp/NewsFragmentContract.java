package com.wsyzj.watchvideo.common.business.mvp;

import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.IModel;
import com.wsyzj.watchvideo.common.base.mvp.IPresenter;
import com.wsyzj.watchvideo.common.base.mvp.IView;
import com.wsyzj.watchvideo.common.business.bean.News;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/6 9:50
 * @Description: $desc$
 */
public class NewsFragmentContract {
    public interface View extends IView {
        void firstHidePullToRefresh(int titleIndex);

        void setNewsList(List<News.ResultBeanX.ResultBean.ListBean> list);

        void setRefreshing(boolean refreshing);

        void setLoadMoreState(int totalCount);
    }

    public interface Model extends IModel {
        Flowable<News> getNewsListByTitle(String channel, int start, int num);
    }

    interface Presenter extends IPresenter<View> {
        void getArguments(BaseFragment fragment);

        void getNewsListByTitle(boolean refreshing);
    }
}
