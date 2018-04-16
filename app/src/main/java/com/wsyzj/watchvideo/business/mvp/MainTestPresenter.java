package com.wsyzj.watchvideo.business.mvp;

import android.content.Context;

import com.blankj.utilcode.util.LogUtils;
import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.constant.Constant;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/16
 *     desc   :
 * </pre>
 */
public class MainTestPresenter extends BasePresenter<MainTestContract.View, MainTestContract.Model> implements MainTestContract.Presenter {

    private MainTestContract.View mView;
    private MainTestContract.Model mModel;

    private List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> mNewestChannel;

    public MainTestPresenter(MainTestContract.View view) {
        mView = view;
        mModel = new MainTestModel();
    }

    @Override
    public void getNewsChannel(Context context) {
//        List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> all = DataSupport.findAll(NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean.class);
//
//        LogUtils.e(all.size());

        BaseTSubscriber<NewsChannel> baseTSubscriber = mModel
                .getNewsChannel()
                .subscribeWith(new BaseTSubscriber<NewsChannel>() {
                    @Override
                    public void onSuccess(Object data) {
                        NewsChannel newsChannel = (NewsChannel) data;
                        if (Constant.JingDong.JINGDONG_CODE == newsChannel.code) {
                            mNewestChannel = getNewestChannel(newsChannel.result.showapi_res_body.channelList);
                            mView.setChannelList(mNewestChannel);
                        } else {
                            mView.showToast(newsChannel.msg);
                        }
                    }
                });
        mView.addDisposable(baseTSubscriber);
    }

    /**
     * 获取最新频道
     */
    private List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> getNewestChannel(List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> channelList) {
        List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> newest = new ArrayList<>();

        NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean recommend = new NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean();
        recommend.name = "推荐";
        recommend.channelId = "0";
        boolean save = recommend.save();
        newest.add(0, recommend);


        if (channelList == null) {
            return newest;
        }

        for (int i = 0; i < channelList.size(); i++) {
            NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = channelList.get(i);
            if (channelListBean.name.contains("最新")) {
                newest.add(channelListBean);
            }
        }

        DataSupport.saveAllAsync(newest).listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {
                LogUtils.e("保存 " + success);
            }
        });

        return newest;
    }
}
