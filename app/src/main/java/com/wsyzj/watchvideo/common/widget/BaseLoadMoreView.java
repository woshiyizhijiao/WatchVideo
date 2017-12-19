package com.wsyzj.watchvideo.common.widget;

import com.wsyzj.watchvideo.R;

/**
 * 创建时间 : 2017/10/20
 * 编写人 : 焦洋
 * 功能描述 :
 */

class BaseLoadMoreView extends com.chad.library.adapter.base.loadmore.LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.widget_base_load_more_view;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
