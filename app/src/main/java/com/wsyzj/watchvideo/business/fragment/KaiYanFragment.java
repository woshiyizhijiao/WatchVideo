package com.wsyzj.watchvideo.business.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.VideoAdapter;
import com.wsyzj.watchvideo.business.bean.KaiYan;
import com.wsyzj.watchvideo.business.mvp.KaiYanContract;
import com.wsyzj.watchvideo.business.mvp.KaiYanPresenter;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.util.List;

import butterknife.BindView;

/**
 * author : 焦洋
 * time   : 2017/11/2  12:17
 * desc   :
 */
public class KaiYanFragment extends BaseFragment implements KaiYanContract.View, OnRefreshListener {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private KaiYanPresenter mPresenter;
    private VideoAdapter mKaiYanAdapter;

    @Override
    protected BaseIPresenter presenter() {
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
    }

    @Override
    public void initData() {
        mPresenter.getKaiYanList();
    }

    /**
     * 刷新列表
     */
    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        mPresenter.getKaiYanList();
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

}
