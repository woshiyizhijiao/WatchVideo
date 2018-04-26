package com.wsyzj.watchvideo.business.mvp;

import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.util.LogUtils;
import com.wsyzj.watchvideo.business.bean.ChannelDb;
import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.constant.Constant;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/6 9:50
 * @Description: $desc$
 */
public class MainPresenter extends BasePresenter<MainContract.View, MainContract.Model> implements MainContract.Presenter {

    private MainContract.View mView;
    private MainContract.Model mModel;

    private List<ChannelDb> mChannel;

    public MainPresenter(MainContract.View view) {
        mView = view;
        mModel = new MainModel();
    }

    /**
     * 获取新闻的标题，先从数据库中读取缓存
     *
     * @param context
     */
    @Override
    public void getNewsChannel(Context context) {
        List<ChannelDb> all = DataSupport.findAll(ChannelDb.class);
        if (all != null && all.size() != 0) {
            mChannel = all;
            mView.setChannelList(mChannel);
        } else {
            BaseTSubscriber<NewsChannel> baseTSubscriber = mModel
                    .getNewsChannel()
                    .subscribeWith(new BaseTSubscriber<NewsChannel>() {
                        @Override
                        public void onSuccess(Object data) {
                            NewsChannel newsChannel = (NewsChannel) data;
                            if (Constant.JingDong.JINGDONG_CODE == newsChannel.code) {
                                mChannel = getNewestChannel(newsChannel.result.showapi_res_body.channelList);
                                mView.setChannelList(mChannel);
                            } else {
                                mView.showToast(newsChannel.msg);
                            }
                        }

                        @Override
                        public void onFailure(Throwable throwable) {

                        }
                    });
            mView.addDisposable(baseTSubscriber);
        }
    }

    /**
     * 获取最新频道
     */
    private List<ChannelDb> getNewestChannel(List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> channelList) {
        List<ChannelDb> channelDbList = new ArrayList<>();

        ChannelDb channel0 = new ChannelDb();
        channel0.name = "推荐";
        channel0.channelId = "0";
        channel0.isChannel = true;
        channelDbList.add(channel0);

        ChannelDb music = new ChannelDb();
        music.name = "音乐";
        music.channelId = "0";
        music.isChannel = true;
        channelDbList.add(music);

        if (channelList == null) {
            return channelDbList;
        }

        ChannelDb channelDb;
        for (int i = 0; i < channelList.size(); i++) {
            NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = channelList.get(i);
            if (channelListBean.name.contains("最新")) {
                channelDb = new ChannelDb();
                channelDb.name = channelListBean.name;
                channelDb.channelId = channelListBean.channelId;
                channelDb.isChannel = true;
                channelDbList.add(channelDb);
            }
        }

        /**
         * 保存在数据库中
         */
        DataSupport.saveAllAsync(channelDbList).listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {
                LogUtils.e("数据保存成功" + success);
            }
        });

        return channelDbList;
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
                List<ChannelDb> all = DataSupport.findAll(ChannelDb.class);
                mView.setChannelList(all);
            }
        }
    }
}