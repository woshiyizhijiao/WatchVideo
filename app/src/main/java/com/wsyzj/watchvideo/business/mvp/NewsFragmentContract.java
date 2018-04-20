package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BaseIModel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;
import com.wsyzj.watchvideo.business.bean.News;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/6 9:50
 * @Description: $desc$
 */
public class NewsFragmentContract {
    public interface View extends BaseIView {

        void setNewsList(List<News.ResultBeanX.ResultBean.ListBean> list);

        void setLoadMoreByTotal(int totalCount);
    }

    public interface Model extends BaseIModel {
        Flowable<News> getNewsListByTitle(String channel, int start, int num);
    }

    interface Presenter extends BaseIPresenter<View> {
        void getArguments(BaseFragment fragment);

        void getNewsListByTitle(boolean refreshing);
    }
}
