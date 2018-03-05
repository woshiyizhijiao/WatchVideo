package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.constant.Constant;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;

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

    public MainPresenter(MainContract.View view) {
        mView = view;
        mModel = new MainModel();
    }

    /**
     * 获取新闻的标题
     */
    @Override
    public void getNewsChannel() {
        BaseTSubscriber<NewsChannel> baseTSubscriber = mModel
                .getNewsChannel()
                .subscribeWith(new BaseTSubscriber<NewsChannel>() {
                    @Override
                    public void onSuccess(Object data) {
                        NewsChannel newsChannel = (NewsChannel) data;
                        if (Constant.JingDong.JINGDONG_CODE == newsChannel.code) {
                            mView.setChannelList(getNewChannels(newsChannel.result.showapi_res_body.channelList));
                        } else {
                            mView.showToast(newsChannel.msg);
                        }
                    }
                });
        mView.addDisposable(baseTSubscriber);
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
        return newChannel;
    }
}