package com.wsyzj.watchvideo.common.business.mvp;

import android.app.Activity;

import com.wsyzj.watchvideo.common.base.mvp.IModel;
import com.wsyzj.watchvideo.common.base.mvp.IPresenter;
import com.wsyzj.watchvideo.common.base.mvp.IView;
import com.wsyzj.watchvideo.common.business.bean.DouBan;
import com.wsyzj.watchvideo.common.business.bean.Gank;
import com.wsyzj.watchvideo.common.business.bean.MeiRiYiWen;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/12 14:04
 * @Description: 主页
 */
public class HomeContract {

    public interface View extends IView {

        void firstPageLoadFinish();

        void setGankData(List<Gank.ResultsBean> results);

        void setRefreshing(boolean refreshing);

        void setLoadMoreState(int totalCount);

        void setMeiRiYiWenData(MeiRiYiWen meiRiYiWen);

        void setTheatersList(List<DouBan.SubjectsBean> subjects);
    }

    public interface Model extends IModel {
        Flowable<Gank> getGankData(String type, int pageNumber, int page);

        Flowable<DouBan> getTheatersList(int start, int count);
    }

    interface Presenter extends IPresenter<View> {
        void getGankData(boolean refreshing);

        void getMeiRiYiWen(Activity activity);

        void getTheatersList();
    }
}
