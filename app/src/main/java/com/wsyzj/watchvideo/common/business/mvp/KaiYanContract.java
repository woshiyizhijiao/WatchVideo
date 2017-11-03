package com.wsyzj.watchvideo.common.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.IModel;
import com.wsyzj.watchvideo.common.base.mvp.IPresenter;
import com.wsyzj.watchvideo.common.base.mvp.IView;
import com.wsyzj.watchvideo.common.business.bean.KaiYan;

import java.util.List;

import io.reactivex.Flowable;

/**
 * author : 焦洋
 * time   : 2017/11/2  12:18
 * desc   : KaiYanContract
 */
public class KaiYanContract {

    public interface View extends IView {
        void setVideoList(List<KaiYan.DataListBean> itemList);

        void setRefreshing(boolean refreshing);
    }

    public interface Model extends IModel {
        Flowable<KaiYan> getKaiYanList();
    }

    public interface Presenter extends IPresenter<View> {
        void getKaiYanList();
    }
}
