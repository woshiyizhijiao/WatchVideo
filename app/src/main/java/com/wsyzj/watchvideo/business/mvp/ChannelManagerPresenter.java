package com.wsyzj.watchvideo.business.mvp;

import android.app.Activity;

import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.common.base.BaseThreadManager;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.utils.StorageUtils;

import java.util.ArrayList;
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

    public static final String CHANNEL_MANAGER_MY_TEXT = "我的频道";
    public static final String CHANNEL_MANAGER_RECOMMEND_TEXT = "推荐频道";

    private ChannelManagerContract.View mView;
    private ChannelManagerContract.Model mModel;

    public ChannelManagerPresenter(ChannelManagerContract.View view) {
        mView = view;
        mModel = new ChannelManagerModel();
    }

    /**
     * 获取频道管理的数据
     */
    @Override
    public void getChannelManagerData(Activity activity) {
        List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> newsChannelTitles = StorageUtils.getNewsChannelTitles(activity);
        BaseThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                if (newsChannelTitles != null) {
                    pooledChannelData(activity, newsChannelTitles);
                }
            }
        });
    }

    /**
     * 合并数据在频道中显示
     *
     * @param newsChannelTitles
     */
    private void pooledChannelData(Activity activity, List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> newsChannelTitles) {
        List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> myChannel = new ArrayList<>();
        List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> recommendChannel = new ArrayList<>();
        for (int i = 0; i < newsChannelTitles.size(); i++) {
            NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = newsChannelTitles.get(i);
            if (channelListBean.isMyChannel) {
                myChannel.add(channelListBean);
            } else {
                recommendChannel.add(channelListBean);
            }
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mView.setChannelManagerData(myChannel, recommendChannel);
            }
        });
    }
}
