package com.wsyzj.watchvideo.business.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.ChannelManagerAdapter;
import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.business.helper.ItemDragHelperCallback;
import com.wsyzj.watchvideo.business.mvp.ChannelManagerContract;
import com.wsyzj.watchvideo.business.mvp.ChannelManagerPresenter;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.utils.IntentUtils;

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
        mBaseNavigationView.setTitle("频道管理");
        return R.layout.activity_channel_manager;
    }

    @Override
    protected void initView() {
        mBaseNavigationView.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.setResultChannelManager(ChannelManagerActivity.this, mChannelManagerAdapter != null && mChannelManagerAdapter.putChannelData());
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.getChannelManagerData(this);
    }

    /**
     * 设置频道管理数据
     *
     * @param myChannel
     * @param recommendChannel
     */
    @Override
    public void setChannelManagerData(List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> myChannel, List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> recommendChannel) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        recycler_view.setLayoutManager(gridLayoutManager);
        if (mChannelManagerAdapter == null) {
            ItemDragHelperCallback callback = new ItemDragHelperCallback();
            ItemTouchHelper helper = new ItemTouchHelper(callback);
            helper.attachToRecyclerView(recycler_view);

            mChannelManagerAdapter = new ChannelManagerAdapter(helper, myChannel, recommendChannel);
            recycler_view.setAdapter(mChannelManagerAdapter);
        } else {
            mChannelManagerAdapter.refreshData(myChannel, recommendChannel);
        }

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = mChannelManagerAdapter.getItemViewType(position);
                return itemViewType == ChannelManagerAdapter.TYPE_MY_TEXT || itemViewType == ChannelManagerAdapter.TYPE_RECOMMEND_TEXT ? 4 : 1;
            }
        });
    }
}
