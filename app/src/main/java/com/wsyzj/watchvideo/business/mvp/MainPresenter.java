package com.wsyzj.watchvideo.business.mvp;

import android.content.Context;
import android.content.Intent;

import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.constant.Constant;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;
import com.wsyzj.watchvideo.common.utils.StorageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/6 9:50
 * @Description: $desc$
 */
public class MainPresenter extends BasePresenter<MainContract.View, MainContract.Model>
        implements MainContract.Presenter {

    private MainContract.View mView;
    private MainContract.Model mModel;

    private List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> mNewChannels = new ArrayList<>();

    public MainPresenter(MainContract.View view) {
        mView = view;
        mModel = new MainModel();
    }

    /**
     * 获取新闻的标题
     */
    @Override
    public void getNewsChannel(Context context) {
        List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> cacheNewsChanne = StorageUtils.getCacheNewsChannelTitle();
        List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> newsChannel = StorageUtils.getNewsChannelTitles();
        if (cacheNewsChanne != null && !cacheNewsChanne.isEmpty()) {
            mNewChannels = cacheNewsChanne;
            mView.setChannelList(mNewChannels);
        } else if (newsChannel != null && newsChannel.size() > 1) {
            mNewChannels = newsChannel;
            mView.setChannelList(mNewChannels);
        } else {
            BaseTSubscriber<NewsChannel> baseTSubscriber = mModel
                    .getNewsChannel()
                    .subscribeWith(new BaseTSubscriber<NewsChannel>() {
                        @Override
                        public void onSuccess(Object data) {
                            NewsChannel newsChannel = (NewsChannel) data;
                            if (Constant.JingDong.JINGDONG_CODE == newsChannel.code) {
                                mNewChannels = getNewChannels(newsChannel.result.showapi_res_body.channelList);
                                mView.setChannelList(mNewChannels);
                                StorageUtils.putNewsChannelTitles(mNewChannels);
                            } else {
                                mView.showToast(newsChannel.msg);
                            }
                        }
                    });
            mView.addDisposable(baseTSubscriber);
        }
    }

    /**
     * 频道太多，过滤焦点频道
     *
     * @return
     */
    private List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> getNewChannels(List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> channelList) {
        List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> newChannel = new ArrayList<>();
        for (int i = 0; i < channelList.size(); i++) {
            NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = channelList.get(i);
            if (!channelListBean.name.contains("焦点")) {
                newChannel.add(channelListBean);
            }
        }

        NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = new NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean();
        channelListBean.name = "推荐";
        channelListBean.channelId = "0";
        newChannel.add(0, channelListBean);

        StorageUtils.putCacheNewsChannelTitles(newChannel);
        return newChannel;
    }

    /**
     * 改变频道
     *
     * @param data
     */
    @Override
    public void changedNesChannel(Intent data) {
        if (data != null) {
            boolean isMoved = data.getBooleanExtra("isMoved", false);
            if (isMoved) {
                mNewChannels = StorageUtils.getCacheNewsChannelTitle();
                mView.setChannelList(mNewChannels);
            }
        }
    }
}