package com.wsyzj.watchvideo.business.mvp;

import android.app.Activity;

import com.wsyzj.watchvideo.business.bean.DouBan;
import com.wsyzj.watchvideo.business.bean.Gank;
import com.wsyzj.watchvideo.business.bean.MeiRiYiWen;
import com.wsyzj.watchvideo.common.base.mvp.BaseIModel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/12 14:04
 * @Description: 主页
 */
public class HomeContract {

    public interface View extends BaseIView {

        void setGankData(List<Gank.ResultsBean> results);

        void setLoadMoreState(int totalCount);

        void setMeiRiYiWenData(MeiRiYiWen meiRiYiWen);

        void setTheatersList(List<DouBan.SubjectsBean> subjects);
    }

    public interface Model extends BaseIModel {
        Flowable<Gank> getGankData(String type, int pageNumber, int page);

        Flowable<DouBan> getTheatersList(int start, int count);
    }

    interface Presenter extends BaseIPresenter<View> {
        void getGankData(boolean refreshing);

        void getMeiRiYiWen(Activity activity);

        void getTheatersList();
    }
}
