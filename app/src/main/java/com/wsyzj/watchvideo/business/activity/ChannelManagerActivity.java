package com.wsyzj.watchvideo.business.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.ChannelManagerAdapter;
import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.business.mvp.ChannelManagerContract;
import com.wsyzj.watchvideo.business.mvp.ChannelManagerPresenter;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/12
 *     desc   : 频道管理
 * </pre>
 */
public class ChannelManagerActivity extends BaseActivity implements ChannelManagerContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    private ChannelManagerPresenter mPresenter;
    private ChannelManagerAdapter mChannelManagerAdapter;

    @Override
    protected BasePresenter presenter() {
        mPresenter = new ChannelManagerPresenter(this);
        return mPresenter;
    }

    @Override
    protected int contentView() {
        baseTitleView.setTitle("频道管理");
        return R.layout.activity_channel_manager;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.getChannelManagerData(this);
    }

    /**
     * 设置频道管理数据
     *
     * @param channelManagerData
     */
    @Override
    public void setChannelManagerData(List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> channelManagerData) {
        if (mChannelManagerAdapter == null) {
            mChannelManagerAdapter = new ChannelManagerAdapter(recycler_view, channelManagerData);
            recycler_view.setLayoutManager(new GridLayoutManager(this, 4));
            recycler_view.setAdapter(mChannelManagerAdapter);
        } else {
            mChannelManagerAdapter.setNewData(channelManagerData);
        }
        mChannelManagerAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                int itemType = mChannelManagerAdapter.getData().get(position).getItemType();

                if (itemType == NewsChannel.TYPE_MY_TEXT) {
                    return 4;
                } else if (itemType == NewsChannel.TYPE_RECOMMEND_TEXT) {
                    return 4;
                } else if (itemType == NewsChannel.TYPE_MY_CHANNEL) {
                    return 1;
                } else if (itemType == NewsChannel.TYPE_RECOMMEND_CHANNEL) {
                    return 1;
                } else {
                    return 1;
                }
            }
        });
    }
}
