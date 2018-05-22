package com.wsyzj.watchvideo.business.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.utils.StorageUtils;
import com.wsyzj.watchvideo.common.utils.UiUtils;

import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/23
 *     desc   :
 * </pre>
 */
public class ColorPrimaryAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    private Context mContext;

    public ColorPrimaryAdapter(Context context, @Nullable List<Integer> data) {
        super(R.layout.rv_item_color_primary, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        int primary = StorageUtils.getColorPrimary();
        View view_gradient = helper.getView(R.id.view_gradient);
        view_gradient.setBackgroundDrawable(getGradientDrawable(item));
        helper.setVisible(R.id.iv_current_color, primary == item);
    }


    /**
     * 动态的添加圆形的背景
     */
    private GradientDrawable getGradientDrawable(int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(180);
        gradientDrawable.setColor(UiUtils.getColor(color));

        return gradientDrawable;
    }
}
