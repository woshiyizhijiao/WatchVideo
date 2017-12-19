package com.wsyzj.watchvideo.business.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.common.http.ImageLoader;

import java.util.List;

/**
 * 创建时间 : 2017/10/19
 * 编写人 : 焦洋
 * 功能描述 : 音乐适配器
 */

public class MusicAdapter extends BaseQuickAdapter<Music.SongListBean, BaseViewHolder> {
    private Context mContext;

    public MusicAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<Music.SongListBean> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Music.SongListBean item) {
        helper.setText(R.id.tv_name, item.album_title);
        ImageLoader.with(mContext, item.pic_big, R.drawable.default_cover, R.drawable.default_cover, (ImageView) helper.getView(R.id.img_cover));
    }
}
