package com.wsyzj.watchvideo.business.adapter;

import android.support.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.utils.UiUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/23
 *     desc   :
 * </pre>
 */
public class SkinPeelerAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public SkinPeelerAdapter(@Nullable List<Integer> data) {
        super(R.layout.rv_item_skin_peeler, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        CircleImageView circle_color = helper.getView(R.id.circle_color);
        circle_color.setBorderColor(UiUtils.getColor(item));
        circle_color.setBorderWidth(180);

        LogUtils.e("   -----   ");
    }
}
