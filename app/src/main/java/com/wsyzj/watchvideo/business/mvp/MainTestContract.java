package com.wsyzj.watchvideo.business.mvp;

import android.content.Context;

import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIModel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;

import java.util.List;

import io.reactivex.Flowable;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/16
 *     desc   :
 * </pre>
 */
public class MainTestContract {
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
