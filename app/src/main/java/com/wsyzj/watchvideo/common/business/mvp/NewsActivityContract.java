package com.wsyzj.watchvideo.common.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.IModel;
import com.wsyzj.watchvideo.common.base.mvp.IPresenter;
import com.wsyzj.watchvideo.common.base.mvp.IView;
import com.wsyzj.watchvideo.common.business.bean.NewsTitle;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/6 9:50
 * @Description: $desc$
 */
public class NewsActivityContract {
    public interface View extends IView {
        void setNewsTitle(List<String> newsTitle);
    }

    public interface Model extends IModel {
        Flowable<NewsTitle> getNewsTitle();
    }

    interface Presenter extends IPresenter<View> {
        void getNewsTitle();
    }
}
