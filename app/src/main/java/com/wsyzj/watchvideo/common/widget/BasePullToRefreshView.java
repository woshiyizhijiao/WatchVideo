package com.wsyzj.watchvideo.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wsyzj.watchvideo.R;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/02/26
 *     desc   : 统一使用下拉刷新和加载更多
 * </pre>
 */
public class BasePullToRefreshView extends LinearLayout {

    private SmartRefreshLayout smart_refresh;
    private RecyclerView recycler_view;
    private BaseStateLayout base_state_layout;

    private BaseQuickAdapter mBaseQuickAdapter;

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
        smart_refresh = (SmartRefreshLayout) view.findViewById(R.id.smart_refresh);
        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        base_state_layout = (BaseStateLayout) view.findViewById(R.id.base_state_layout);

        addView(view);
    }

    /**
     * 禁用下拉刷新
     */
    public void setEnableRefresh(boolean enabled) {
        smart_refresh.setEnableRefresh(enabled);
    }

    /**
     * 禁用加载更多
     */
    public void setEnableLoadMore(boolean enabled) {
        smart_refresh.setEnableLoadMore(enabled);
    }

    /**
     * 设置适配器
     *
     * @param adapter
     */
    public void setAdapter(BaseQuickAdapter adapter) {
        mBaseQuickAdapter = adapter;
        recycler_view.setAdapter(adapter);
    }

    /**
     * 添加头部
     *
     * @param header
     */
    public void addHeadView(View header) {
        if (mBaseQuickAdapter != null) {
            mBaseQuickAdapter.addHeaderView(header);
        }
    }

    /**
     * 添加头部到指定位置
     *
     * @param header
     */
    public void addHeadView(View header, int index) {
        if (mBaseQuickAdapter != null) {
            mBaseQuickAdapter.addHeaderView(header, index);
        }
    }

    /**
     * 设置布局管理器
     *
     * @param layoutManager
     */
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recycler_view.setLayoutManager(layoutManager);
    }

    /**
     * 下拉刷新
     *
     * @param onRefreshListener
     */
    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        if (onRefreshListener != null) {
            smart_refresh.setOnRefreshListener(onRefreshListener);
        }
    }

    /**
     * 加载更多
     *
     * @param onLoadMoreListener
     */
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        if (onLoadMoreListener != null) {
            smart_refresh.setOnLoadMoreListener(onLoadMoreListener);
        }
    }

    /**
     * 下拉刷新和加载更多
     *
     * @param onRefreshLoadMoreListener
     */
    public void setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener onRefreshLoadMoreListener) {
        if (onRefreshLoadMoreListener != null) {
            smart_refresh.setOnRefreshLoadMoreListener(onRefreshLoadMoreListener);
        }
    }

    /**
     * 结束刷新
     */
    public void finishRefresh() {
        smart_refresh.finishRefresh();
    }

    /**
     * 根据列表的总数判断当前的加载更多状态
     *
     * @param totalCount
     */
    public void setLoadMoreByTotal(int totalCount) {
        RecyclerView.Adapter adapter = recycler_view.getAdapter();
        if (adapter != null && adapter instanceof BaseQuickAdapter) {
            BaseQuickAdapter quickAdapter = (BaseQuickAdapter) adapter;
            int itemCount = quickAdapter.getItemCount();
            if (totalCount > itemCount) {
                smart_refresh.finishLoadMore();
            } else {
                smart_refresh.finishLoadMoreWithNoMoreData();
            }
        }
    }

    /**
     * 根据每次请求的个数判断加载更多状态
     *
     * @param listSize
     * @param pageCount
     */
    public void setLoadMoreByPageCount(int listSize, int pageCount) {
        if (listSize < pageCount) {
            smart_refresh.finishLoadMoreWithNoMoreData();
        } else {
            smart_refresh.finishLoadMore();
        }
    }

    /**
     * 获取recycler的实例
     *
     * @return
     */
    public RecyclerView getRecycler() {
        return recycler_view;
    }

    public void showPageState(StateLayout baseState) {
        base_state_layout.setVisibility(View.VISIBLE);
        base_state_layout.setPageState(baseState);
    }

    public BaseStateLayout getBaseStateLalyout() {
        base_state_layout.setVisibility(View.VISIBLE);
        return base_state_layout;
    }
}
