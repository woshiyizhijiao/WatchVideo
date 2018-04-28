package com.wsyzj.watchvideo.business.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.bean.Gank;
import com.wsyzj.watchvideo.common.http.ImageLoader;

import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/12 14:49
 * @Description: $desc$
 */
public class HomeAdapter extends BaseQuickAdapter<Gank.ResultsBean, BaseViewHolder> {
    private Context mContext;

    public HomeAdapter(Context context, @Nullable List<Gank.ResultsBean> data) {
        super(R.layout.item_home, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Gank.ResultsBean item) {
        ImageLoader.with(mContext, item.url, R.drawable.icon_default_error, R.drawable.icon_default_error, (ImageView) helper.getView(R.id.iv_meizhi));
    }
}
