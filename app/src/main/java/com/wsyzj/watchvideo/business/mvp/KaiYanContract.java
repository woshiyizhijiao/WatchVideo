package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BaseIModel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;
import com.wsyzj.watchvideo.business.bean.KaiYan;

import java.util.List;

import io.reactivex.Flowable;

/**
 * author : 焦洋
 * time   : 2017/11/2  12:18
 * desc   : KaiYanContract
 */
public class KaiYanContract {

    public interface View extends BaseIView {
        void setVideoList(List<KaiYan.DataListBean> itemList);

    }

    public interface Model extends BaseIModel {
        Flowable<KaiYan> getKaiYanList();
    }

    public interface Presenter extends BaseIPresenter<View> {
        void getKaiYanList();
    }
}
