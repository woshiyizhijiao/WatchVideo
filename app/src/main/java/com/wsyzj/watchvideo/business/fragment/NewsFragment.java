package com.wsyzj.watchvideo.business.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.NewsAdapter;
import com.wsyzj.watchvideo.business.bean.News;
import com.wsyzj.watchvideo.business.mvp.NewsFragmentContract;
import com.wsyzj.watchvideo.business.mvp.NewsFragmentPresenter;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.business.utils.IntentUtils;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.util.List;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2017/12/6 9:47
 * @Description: 新闻的标签
 */
public class NewsFragment extends BaseFragment implements NewsFragmentContract.View, OnRefreshListener, OnRefreshLoadMoreListener {

    public final static String BUNDLE_TITLE_INDEX = "bundle_title_index";
    public final static String BUNDLE_CURRENT_TITLE = "bundle_current_title";

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private NewsFragmentPresenter mPresenter;
    private NewsAdapter mNewsAdapter;

    @Override
    protected BaseIPresenter presenter() {
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
        pull_to_refresh.setOnRefreshLoadMoreListener(this);
    }

    @Override
    public void initData() {
        mPresenter.getArguments(this);
        mPresenter.getNewsListByTitle(true);
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
                IntentUtils.webView(mActivity, listBean.title, listBean.weburl);
            }
        });
    }

    /**
     * 设置加载状态
     *
     * @param totalCount
     */
    @Override
    public void setLoadMoreState(int totalCount) {
        pull_to_refresh.setLoadMoreState(totalCount);
        pull_to_refresh.finishRefresh();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        mPresenter.getNewsListByTitle(true);
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        mPresenter.getNewsListByTitle(false);
    }
}
