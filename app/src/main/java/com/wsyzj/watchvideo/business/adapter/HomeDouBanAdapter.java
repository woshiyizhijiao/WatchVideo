package com.wsyzj.watchvideo.business.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.bean.DouBan;
import com.wsyzj.watchvideo.common.base.http.ImageLoader;

import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/13 15:23
 * @Description: $desc$
 */
public class HomeDouBanAdapter extends BaseQuickAdapter<DouBan.SubjectsBean, BaseViewHolder> {
    private Context mContext;

    public HomeDouBanAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<DouBan.SubjectsBean> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DouBan.SubjectsBean item) {
        ImageLoader.with(mContext, item.images.medium, (ImageView) helper.getView(R.id.iv_conver));
    }
}
