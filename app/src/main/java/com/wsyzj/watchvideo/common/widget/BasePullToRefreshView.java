package com.wsyzj.watchvideo.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wsyzj.watchvideo.R;

/**
 * @author: wsyzj
 * @date: 2017-08-22 22:33
 * @comment: 下拉刷新
 */
public class BasePullToRefreshView extends LinearLayout implements BaseQuickAdapter.RequestLoadMoreListener {
    private SwipeRefreshLayout swipe_refresh;
    private RecyclerView recycler;
    private BaseQuickAdapter mBaseQuickAdapter;

    private BaseQuickAdapter.RequestLoadMoreListener mRequestLoadMoreListener;

    public void setRequestLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener requestLoadMoreListener) {
        mRequestLoadMoreListener = requestLoadMoreListener;
    }

    public BasePullToRefreshView(Context context) {
        super(context);
        init(context);
    }

    public BasePullToRefreshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BasePullToRefreshView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        View view = LayoutInflater.from(context).inflate(R.layout.widget_base_pull_to_refresh, null);
        swipe_refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);

        setRefreshing(true);
        addView(view);
    }

    /**
     * 设置刷新状态
     *
     * @param refreshing
     */
    public void setRefreshing(final boolean refreshing) {
        if (refreshing) {
            swipe_refresh.setRefreshing(refreshing);
        } else {
            swipe_refresh.setRefreshing(refreshing);
        }
    }

    /**
     * 下拉刷新
     *
     * @param listener
     */
    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        if (listener != null) {
            swipe_refresh.setOnRefreshListener(listener);
        }
    }

    /**
     * 设置适配器
     *
     * @param adapter
     */
    public void setAdapter(BaseQuickAdapter adapter) {
        mBaseQuickAdapter = adapter;
        recycler.setAdapter(adapter);
        adapter.setLoadMoreView(new BaseLoadMoreView());
        adapter.setOnLoadMoreListener(this, recycler);
    }

    /**
     * 添加头部
     *
     * @param header
     */
    public void addHeadView(View header) {
        mBaseQuickAdapter.addHeaderView(header);
    }

    /**
     * 添加头部到指定位置
     *
     * @param header
     */
    public void addHeadView(View header, int index) {
        mBaseQuickAdapter.addHeaderView(header, index);
    }

    /**
     * 设置布局管理器
     *
     * @param layoutManager
     */
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recycler.setLayoutManager(layoutManager);
    }

    /**
     * 下拉刷新监听
     */
    @Override
    public void onLoadMoreRequested() {
        if (mRequestLoadMoreListener != null) {
            mRequestLoadMoreListener.onLoadMoreRequested();
        }
    }

    /**
     * 设置下拉之后的监听
     */
    public void setLoadMoreState(int totalCount) {
        swipe_refresh.setRefreshing(false);
        RecyclerView.Adapter adapter = recycler.getAdapter();
        if (adapter != null && adapter instanceof BaseQuickAdapter) {
            BaseQuickAdapter quickAdapter = (BaseQuickAdapter) adapter;
            int itemCount = quickAdapter.getItemCount();
            if (totalCount > itemCount) {
                quickAdapter.loadMoreComplete();
            } else {
                quickAdapter.loadMoreEnd();
            }
        }
    }

    /**
     * 获取recycler的实例
     *
     * @return
     */
    public RecyclerView getRecycler() {
        return recycler;
    }
}
