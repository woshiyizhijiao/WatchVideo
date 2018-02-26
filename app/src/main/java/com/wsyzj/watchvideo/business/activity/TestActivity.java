package com.wsyzj.watchvideo.business.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2018/2/26 11:39
 * @Description: $desc$
 */
public class TestActivity extends BaseActivity {

    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smart_refresh;

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    @Override
    protected BasePresenter presenter() {
        return null;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
//        smart_refresh.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshLayout) {
//                showToast("下拉刷新");
//            }
//        });
//
//        smart_refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshLayout) {
//                showToast("加载更多");
//            }
//        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        List<String> data = new ArrayList<>();
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");
        data.add("我是一坨焦");

        recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(new MyAdapter(R.layout.rv_item_test, data));

    }

    class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_name, item.toString());
        }
    }
}
