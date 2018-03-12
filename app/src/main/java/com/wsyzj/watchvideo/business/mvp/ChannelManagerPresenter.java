package com.wsyzj.watchvideo.business.mvp;

import android.app.Activity;
import android.content.Intent;

import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/12
 *     desc   :
 * </pre>
 */
public class ChannelManagerPresenter extends BasePresenter<ChannelManagerContract.View, ChannelManagerContract.Model> implements ChannelManagerContract.Presenter {

    private ChannelManagerContract.View mView;
    private ChannelManagerContract.Model mModel;
    private List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> mNewsChannel;

    public ChannelManagerPresenter(ChannelManagerContract.View view) {
        mView = view;
        mModel = new ChannelManagerModel();
    }

    @Override
    public void getExtras(Activity activity) {
        Intent intent = activity.getIntent();
        mNewsChannel = (List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean>) intent.getSerializableExtra("newsChannel");
    }
}
