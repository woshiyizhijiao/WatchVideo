package com.wsyzj.watchvideo.common.business.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.IPresenter;
import com.wsyzj.watchvideo.common.business.adapter.WeChatChoicenessAdapter;
import com.wsyzj.watchvideo.common.business.bean.WeChatChoiceness;
import com.wsyzj.watchvideo.common.business.mvp.WeChatChoicenessContract;
import com.wsyzj.watchvideo.common.business.mvp.WeChatChoicenessPresenter;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.util.List;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2017/12/5 15:10
 */
public class WeChatChoicenessFragment extends BaseFragment implements WeChatChoicenessContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private WeChatChoicenessPresenter mPresenter;
    private WeChatChoicenessAdapter mAdapter;

    @Override
    protected IPresenter presenter() {
        mPresenter = new WeChatChoicenessPresenter(this);
        return mPresenter;
    }

    @Override
    public int contentView() {
        return R.layout.fragment_wechat_choiceness;
    }

    @Override
    public void initView() {
        pull_to_refresh.setOnRefreshListener(this);
        pull_to_refresh.setRequestLoadMoreListener(this);
    }

    @Override
    public void initData() {
        mPresenter.getWeChatChoiceness(true);
    }

    @Override
    public void setWeChatChoicenessList(List<WeChatChoiceness.ResultBean.ListBean> beanList) {
        if (mAdapter == null) {
            mAdapter = new WeChatChoicenessAdapter(mActivity, R.layout.item_wechat_choiceness, beanList);
            pull_to_refresh.setLayoutManager(new LinearLayoutManager(mActivity));
            pull_to_refresh.setAdapter(mAdapter);
        } else {
            mAdapter.setNewData(beanList);
        }
    }

    @Override
    public void setLoadMoreState(int totalCount) {
        pull_to_refresh.setLoadMoreState(totalCount);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        pull_to_refresh.setRefreshing(refreshing);
    }

    /**
     * 刷新数据
     */
    @Override
    public void onRefresh() {
        mPresenter.getWeChatChoiceness(true);
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        mPresenter.getWeChatChoiceness(false);
    }
}
