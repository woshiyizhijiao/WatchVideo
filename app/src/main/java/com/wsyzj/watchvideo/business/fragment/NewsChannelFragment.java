package com.wsyzj.watchvideo.business.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.NewsChannelAdapter;
import com.wsyzj.watchvideo.business.bean.NewsDetails;
import com.wsyzj.watchvideo.business.mvp.NewsChannelContract;
import com.wsyzj.watchvideo.business.mvp.NewsChannelPresenter;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.business.utils.IntentUtils;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;
import com.wsyzj.watchvideo.common.widget.BaseStateLayout;

import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/02
 *     desc   : 新闻频道
 * </pre>
 */
public class NewsChannelFragment extends BaseFragment implements NewsChannelContract.View, OnRefreshListener, OnRefreshLoadMoreListener, BaseStateLayout.OnStateErrorListener, BaseStateLayout.OnStateEmptyListener {

    public final static String BUNDLE_CHANNEL_ID = "bundle_channel_id";
    public final static String BUNDLE_CHANNEL_NAME = "bundle_channel_name";

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private NewsChannelPresenter mPresenter;
    private NewsChannelAdapter mNewsChannelAdapter;

    @Override
    protected BaseIPresenter presenter() {
        mPresenter = new NewsChannelPresenter(this);
        return mPresenter;
    }

    @Override
    public int contentView() {
        return R.layout.fragment_news_channel;
    }

    @Override
    public void initView() {
        pull_to_refresh.setOnRefreshLoadMoreListener(this);
        mStateLayout.setOnStateErrorListener(this);
        mStateLayout.setOnStateEmptyListener(this);
    }

    @Override
    public void initData() {
        mPresenter.getArguments(this);
        mPresenter.getNewsList(true);
    }

    /**
     * 设置列表的数据
     *
     * @param contentlist
     */
    @Override
    public void setContentList(final List<NewsDetails.ResultBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist) {
        if (mNewsChannelAdapter == null) {
            mNewsChannelAdapter = new NewsChannelAdapter(mActivity, contentlist);
            pull_to_refresh.setLayoutManager(new LinearLayoutManager(mActivity));
            pull_to_refresh.setAdapter(mNewsChannelAdapter);
        } else {
            mNewsChannelAdapter.setNewData(contentlist);
        }

        mNewsChannelAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsDetails.ResultBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean contentlistBean = mNewsChannelAdapter.getData().get(position);
                IntentUtils.webView(mActivity, contentlistBean.title, contentlistBean.link);
            }
        });
    }

    @Override
    public void setLoadMoreByTotal(int totalCount) {
        pull_to_refresh.finishRefresh();
        pull_to_refresh.setLoadMoreByTotal(totalCount);
    }

    /**
     * 下拉刷新
     *
     * @param refreshLayout
     */
    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        mPresenter.getNewsList(true);
    }

    /**
     * 加载更多
     *
     * @param refreshLayout
     */
    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        mPresenter.getNewsList(false);
    }

    /**
     * 异常回调
     */
    @Override
    public void onStateError() {
        mPresenter.getNewsList(true);
    }

    /**
     * 空数据回调
     */
    @Override
    public void onStateEmpty() {
        mPresenter.getNewsList(true);
    }
}
