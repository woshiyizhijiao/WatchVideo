package com.wsyzj.watchvideo.common.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.IModel;
import com.wsyzj.watchvideo.common.base.mvp.IPresenter;
import com.wsyzj.watchvideo.common.base.mvp.IView;
import com.wsyzj.watchvideo.common.business.bean.WeChatChoiceness;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/5 15:10
 */
public class WeChatChoicenessContract {

    public interface View extends IView {
        void setWeChatChoicenessList(List<WeChatChoiceness.ResultBean.ListBean> beanList);

        void setLoadMoreState(int totalCount);

        void setRefreshing(boolean refreshing);
    }

    public interface Model extends IModel {
        Flowable<WeChatChoiceness> getWeChatChoiceness(int pno, int ps);
    }

    interface Presenter extends IPresenter<View> {
        void getWeChatChoiceness(boolean refreshing);
    }
}
