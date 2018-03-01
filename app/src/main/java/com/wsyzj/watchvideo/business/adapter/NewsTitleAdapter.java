package com.wsyzj.watchvideo.business.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.utils.DrawableUtils;

import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/20 10:01
 * @Description: $desc$
 */
public class NewsTitleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int[] mResId;

    public NewsTitleAdapter(@LayoutRes int layoutResId, @Nullable List<String> data, int[] resId) {
        super(layoutResId, data);
        mResId = resId;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int position = helper.getAdapterPosition();

        helper.setText(R.id.tv_news_title, item);
        ((TextView) helper.getView(R.id.tv_news_title)).setCompoundDrawables(DrawableUtils.getPaddingDrawable(mResId[position]), null, null, null);
    }
}
