package com.wsyzj.watchvideo.common.business.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.business.bean.KaiYan;
import com.wsyzj.watchvideo.common.http.ImageLoader;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * author : 焦洋
 * time   : 2017/11/2  14:21
 * desc   : KaiYanAdapter
 */
public class KaiYanAdapter extends BaseQuickAdapter<KaiYan.ItemListBean, BaseViewHolder> {

    private Context mContext;

    public KaiYanAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<KaiYan.ItemListBean> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, KaiYan.ItemListBean item) {
        KaiYan.ItemListBean.DataBean data = item.data;
        String playUrl = data.playUrl == null ? "" : data.playUrl;
        String title = data.title == null ? "" : data.title;
        String detail = data.cover == null ? "" : data.cover.detail;

        JZVideoPlayerStandard jzVideoPlayer = (JZVideoPlayerStandard) helper.getView(R.id.jzVideoPlayer);
        jzVideoPlayer.setUp(playUrl, JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, title);
        ImageLoader.with(mContext, detail, jzVideoPlayer.thumbImageView);
        jzVideoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
