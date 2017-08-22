package com.wsyzj.watchvideo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wsyzj.watchvideo.R;

/**
 * @author: wsyzj
 * @date: 2017-08-22 22:33
 * @comment: 下拉刷新
 */
public class BasePullToRefreshView extends LinearLayout {
    private SwipeRefreshLayout swipe_refresh;
    private RecyclerView recycler;

    public BasePullToRefreshView(Context context) {
        super(context);
        initViews(context);
    }

    public BasePullToRefreshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public BasePullToRefreshView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        View view = LayoutInflater.from(context).inflate(R.layout.widget_base_pull_to_refresh, null);
        swipe_refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        addView(view);
    }

    /**
     * 设置刷新状态
     *
     * @param refreshing
     */
    public void setRefreshing(boolean refreshing) {
        swipe_refresh.setRefreshing(refreshing);
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

}
