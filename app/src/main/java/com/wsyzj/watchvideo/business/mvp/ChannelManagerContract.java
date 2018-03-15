package com.wsyzj.watchvideo.business.mvp;

import android.app.Activity;

import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIModel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;

import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/12
 *     desc   :
 * </pre>
 */
public class ChannelManagerContract {

    public interface View extends BaseIView {
        void setChannelManagerData(List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> channelManagerData);
    }

    public interface Model extends BaseIModel {

    }

    interface Presenter extends BaseIPresenter<View> {
        void getChannelManagerData(Activity activity);
    }
}
