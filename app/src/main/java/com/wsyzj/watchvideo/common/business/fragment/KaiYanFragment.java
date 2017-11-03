package com.wsyzj.watchvideo.common.business.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.IPresenter;
import com.wsyzj.watchvideo.common.business.adapter.VideoAdapter;
import com.wsyzj.watchvideo.common.business.bean.KaiYan;
import com.wsyzj.watchvideo.common.business.mvp.KaiYanContract;
import com.wsyzj.watchvideo.common.business.mvp.KaiYanPresenter;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;

/**
 * author : 焦洋
 * time   : 2017/11/2  12:17
 * desc   :
 */
public class KaiYanFragment extends BaseFragment implements KaiYanContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerView.OnChildAttachStateChangeListener {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private KaiYanPresenter mPresenter;
    private VideoAdapter mKaiYanAdapter;

    @Override
    protected IPresenter presenter() {
        mPresenter = new KaiYanPresenter(this);
        return mPresenter;
    }

    @Override
    public int contentView() {
        return R.layout.fragment_kaiyan;
    }

    @Override
    public void initView() {
        pull_to_refresh.setOnRefreshListener(this);
        pull_to_refresh.getRecycler().addOnChildAttachStateChangeListener(this);
    }

    @Override
    public void initData() {
        mPresenter.getKaiYanList();
    }

    /**
     * 刷新列表
     */
    @Override
    public void onRefresh() {
        mPresenter.getKaiYanList();
    }

    /**
     * 下拉状态
     *
     * @param refreshing
     */
    @Override
    public void setRefreshing(boolean refreshing) {
        pull_to_refresh.setRefreshing(refreshing);
    }

    /**
     * 设置开眼列表
     *
     * @param itemList
     */
    @Override
    public void setVideoList(List<KaiYan.DataListBean> itemList) {
        if (mKaiYanAdapter == null) {
            mKaiYanAdapter = new VideoAdapter(mActivity, R.layout.item_kaiyan, itemList);
            pull_to_refresh.setLayoutManager(new LinearLayoutManager(mActivity));
            pull_to_refresh.setAdapter(mKaiYanAdapter);
        } else {
            mKaiYanAdapter.setNewData(itemList);
        }
        pull_to_refresh.setLoadMoreState(0);
    }

    @Override
    public void onChildViewAttachedToWindow(View view) {

    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {
        JZVideoPlayer.releaseAllVideos();
    }
}
