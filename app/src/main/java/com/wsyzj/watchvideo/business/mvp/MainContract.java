package com.wsyzj.watchvideo.business.mvp;

import android.content.Context;

import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIModel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/6 9:50
 * @Description: $desc$
 */
public class MainContract {
    public interface View extends BaseIView {
        void setChannelList(List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> channelList);
    }

    public interface Model extends BaseIModel {
        Flowable<NewsChannel> getNewsChannel();
    }

    interface Presenter extends BaseIPresenter<View> {
        void getNewsChannel(Context context);
    }
}
