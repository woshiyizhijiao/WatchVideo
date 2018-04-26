package com.wsyzj.watchvideo.business.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.activity.PlayerCatalogueActivity;

import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/26
 *     desc   :
 * </pre>
 */
public class PlayerCatalogueAdapter extends BaseQuickAdapter<PlayerCatalogueActivity.TestFileDown, BaseViewHolder> {

    public PlayerCatalogueAdapter(@Nullable List<PlayerCatalogueActivity.TestFileDown> data) {
        super(R.layout.item_player_catalogue, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlayerCatalogueActivity.TestFileDown item) {
        helper.setMax(R.id.pb_song, item.totalBytes);
        helper.setProgress(R.id.pb_song, item.soFarBytes);
    }
}
