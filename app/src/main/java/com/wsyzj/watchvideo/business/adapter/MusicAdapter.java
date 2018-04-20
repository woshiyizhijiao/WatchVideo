package com.wsyzj.watchvideo.business.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.common.http.ImageLoader;

import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/19
 *     desc   :
 * </pre>
 */
public class MusicAdapter extends BaseQuickAdapter<Music.SongListBean, BaseViewHolder> {

    private Context mContext;

    public MusicAdapter(Context context, @Nullable List<Music.SongListBean> data) {
        super(R.layout.item_music, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Music.SongListBean item) {
        ImageLoader.with(mContext, item.pic_big, R.drawable.default_cover, R.drawable.default_cover, (ImageView) helper.getView(R.id.iv_conver));
        helper.setText(R.id.tv_name, item.title);
        helper.setText(R.id.tv_desc, item.artist_name + "  " + item.album_title);
    }
}
