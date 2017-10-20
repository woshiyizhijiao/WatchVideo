package com.wsyzj.watchvideo.common.business.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.common.business.bean.Music;

import java.util.List;

/**
 * 创建时间 : 2017/10/19
 * 编写人 : 焦洋
 * 功能描述 :
 */

public class TestAdapter extends BaseQuickAdapter<Music, BaseViewHolder> {
    private Context mContext;

    public TestAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<Music> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Music item) {
//        helper.setText(R.id.tv_name, item.name);
//        ImageLoader.with(mContext, item.url, R.drawable.default_cover, R.drawable.default_cover, (ImageView) helper.getView(R.id.img_cover));
    }
}
