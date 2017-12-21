package com.wsyzj.watchvideo.business.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.bean.Music;

import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/21 11:15
 * @Description: item_music
 */
public class MusicAdapter extends BaseQuickAdapter<Music.SongListBean, BaseViewHolder> {

    public MusicAdapter(@LayoutRes int layoutResId, @Nullable List<Music.SongListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Music.SongListBean item) {
        helper.setText(R.id.tv_index, String.valueOf(helper.getAdapterPosition() + 1));
        helper.setText(R.id.tv_song_name, item.title);
        helper.setText(R.id.tv_song_singer, item.artist_name + "   " + item.album_title);
    }
}

