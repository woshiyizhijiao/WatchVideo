package com.wsyzj.watchvideo.common.business.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseEvent;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.IPresenter;
import com.wsyzj.watchvideo.common.business.adapter.HomeAdapter;
import com.wsyzj.watchvideo.common.business.bean.Gank;
import com.wsyzj.watchvideo.common.business.mvp.HomeContract;
import com.wsyzj.watchvideo.common.business.mvp.HomePresenter;
import com.wsyzj.watchvideo.common.tools.Constant;
import com.wsyzj.watchvideo.common.tools.EventBusUtils;
import com.wsyzj.watchvideo.common.tools.UiUtils;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.util.List;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2017/12/12 14:02
 * @Description: 主页
 */
public class HomeFragment extends BaseFragment implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private HomePresenter mPresenter;
    private HomeAdapter mHomeAdapter;

    @Override
    protected IPresenter presenter() {
        mPresenter = new HomePresenter(this);
        return mPresenter;
    }

    @Override
    public int contentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        setRefreshing(false);
        pull_to_refresh.setOnRefreshListener(this);
        pull_to_refresh.setRequestLoadMoreListener(this);
    }

    @Override
    public void initData() {
        mPresenter.getGankData(true);
    }

    @Override
    public void firstPageLoadFinish() {
        EventBusUtils.sendEvent(new BaseEvent(Constant.EventBusC.NEW_FIRST_PAGE_LOAD_FINISH));
    }

    /**
     * 设置福利的数据
     *
     * @param results
     */
    @Override
    public void setGankData(List<Gank.ResultsBean> results) {
        if (mHomeAdapter == null) {
            mHomeAdapter = new HomeAdapter(mActivity, R.layout.item_home, results);
            pull_to_refresh.setLayoutManager(new GridLayoutManager(mActivity, 2));
            pull_to_refresh.setAdapter(mHomeAdapter);
            addHeadView();
        } else {
            mHomeAdapter.setNewData(results);
        }
    }

    /**
     * 获取头部添加的View
     *
     * @return
     */
    private void addHeadView() {
        if (mHomeAdapter.getHeaderLayoutCount() == 0) {
            View headView = UiUtils.inflate(R.layout.item_header_home);
            pull_to_refresh.addHeadView(headView);
        }
    }

    /**
     * @param refreshing
     */
    @Override
    public void setRefreshing(boolean refreshing) {
        pull_to_refresh.setRefreshing(refreshing);
    }

    /**
     * 上拉/下拉的状态
     *
     * @param totalCount
     */
    @Override
    public void setLoadMoreState(int totalCount) {
        pull_to_refresh.setLoadMoreState(totalCount);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mPresenter.getGankData(true);
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        mPresenter.getGankData(false);
    }
}
