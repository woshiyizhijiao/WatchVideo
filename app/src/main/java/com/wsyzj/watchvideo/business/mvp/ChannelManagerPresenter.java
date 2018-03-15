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

    private List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> mNewsChannel;

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
                    mNewsChannel = pooledChannelData(newsChannelTitles);

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mView.setChannelManagerData(mNewsChannel);
                        }
                    });
                }
            }
        });
    }

    /**
     * 合并数据在频道中显示
     *
     * @param newsChannelTitles
     */
    private List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> pooledChannelData(List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> newsChannelTitles) {
        List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> myChannel = new ArrayList<>();
        List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> recommendChannel = new ArrayList<>();
        for (int i = 0; i < newsChannelTitles.size(); i++) {
            NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = newsChannelTitles.get(i);
            if (channelListBean.isSelect) {
                myChannel.add(channelListBean);
            } else {
                recommendChannel.add(channelListBean);
            }
        }

        NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean myText = new NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean();
        myText.name = CHANNEL_MANAGER_MY_TEXT;

        NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean recommendText = new NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean();
        recommendText.name = CHANNEL_MANAGER_RECOMMEND_TEXT;

        myChannel.add(0, myText);
        myChannel.add(recommendText);
        myChannel.addAll(recommendChannel);

        return myChannel;
    }
}
