package com.wsyzj.watchvideo.common.business.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseEvent;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.IPresenter;
import com.wsyzj.watchvideo.common.business.adapter.NewsAdapter;
import com.wsyzj.watchvideo.common.business.bean.News;
import com.wsyzj.watchvideo.common.business.mvp.NewsFragmentContract;
import com.wsyzj.watchvideo.common.business.mvp.NewsFragmentPresenter;
import com.wsyzj.watchvideo.common.tools.Constant;
import com.wsyzj.watchvideo.common.tools.EventBusUtils;
import com.wsyzj.watchvideo.common.tools.IntentUtils;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.util.List;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2017/12/6 9:47
 * @Description: 新闻的标签
 */
public class NewsFragment extends BaseFragment implements NewsFragmentContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private NewsFragmentPresenter mPresenter;
    private NewsAdapter mNewsAdapter;

    @Override
    protected IPresenter presenter() {
        mPresenter = new NewsFragmentPresenter(this);
        return mPresenter;
    }

    @Override
    public int contentView() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
        pull_to_refresh.setOnRefreshListener(this);
        pull_to_refresh.setRequestLoadMoreListener(this);
    }

    @Override
    public void initData() {
        mPresenter.getArguments(this);
        mPresenter.getNewsListByTitle(true);
    }

    /**
     * 第一个的时候隐藏下拉
     *
     * @param titleIndex
     */
    @Override
    public void firstHidePullToRefresh(int titleIndex) {
        if (titleIndex == 0) {
            setRefreshing(false);
        }
    }

    /**
     * 如果是第一个page加载完成就需要把activity上面的下拉禁止掉
     */
    @Override
    public void firstPageLoadFinish() {
        EventBusUtils.sendEvent(new BaseEvent(Constant.EventBusC.NEW_FIRST_PAGE_LOAD_FINISH));
    }

    /**
     * 设置列表数据
     *
     * @param list
     */
    @Override
    public void setNewsList(final List<News.ResultBeanX.ResultBean.ListBean> list) {
        if (mNewsAdapter == null) {
            mNewsAdapter = new NewsAdapter(mActivity, R.layout.item_news, list);
            pull_to_refresh.setLayoutManager(new LinearLayoutManager(mActivity));
            pull_to_refresh.setAdapter(mNewsAdapter);
        } else {
            mNewsAdapter.setNewData(list);
        }

        mNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                News.ResultBeanX.ResultBean.ListBean listBean = mNewsAdapter.getData().get(position);
                IntentUtils.webView(mActivity, listBean.title, listBean.url);
            }
        });
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        pull_to_refresh.setRefreshing(refreshing);
    }

    /**
     * 设置加载状态
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
        mPresenter.getNewsListByTitle(true);
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        mPresenter.getNewsListByTitle(false);
    }
}
