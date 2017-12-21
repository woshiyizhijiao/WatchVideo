package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BaseIModel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;
import com.wsyzj.watchvideo.business.bean.NewsTitle;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/6 9:50
 * @Description: $desc$
 */
public class MainContract {
    public interface View extends BaseIView {
        void setNewsTitle(List<String> newsTitle);
    }

    public interface Model extends BaseIModel {
        Flowable<NewsTitle> getNewsTitle();
    }

    interface Presenter extends BaseIPresenter<View> {
        void getNewsTitle();
    }
}
