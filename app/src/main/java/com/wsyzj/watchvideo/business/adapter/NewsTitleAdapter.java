package com.wsyzj.watchvideo.business.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;

import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/20 10:01
 * @Description: $desc$
 */
public class NewsTitleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public NewsTitleAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_news_title, item);
    }
}
