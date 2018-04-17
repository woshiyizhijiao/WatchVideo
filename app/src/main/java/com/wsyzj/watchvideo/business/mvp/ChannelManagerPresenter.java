package com.wsyzj.watchvideo.business.mvp;

import android.app.Activity;

import com.wsyzj.watchvideo.business.bean.ChannelDb;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

import org.litepal.crud.DataSupport;

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

        List<ChannelDb> all = DataSupport.findAll(ChannelDb.class);
        if (all == null) {
            return;
        }

        List<ChannelDb> myChannel = new ArrayList<>();
        List<ChannelDb> recommendChannel = new ArrayList<>();

        for (int i = 0; i < all.size(); i++) {
            ChannelDb channelDb = all.get(i);
            if (channelDb.isChannel) {
                myChannel.add(channelDb);
            } else {
                recommendChannel.add(channelDb);
            }
        }

        mView.setChannelManagerData(myChannel, recommendChannel);
    }
}
