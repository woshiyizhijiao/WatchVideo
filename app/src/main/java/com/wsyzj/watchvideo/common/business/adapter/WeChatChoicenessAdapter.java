package com.wsyzj.watchvideo.common.business.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.business.bean.WeChatChoiceness;
import com.wsyzj.watchvideo.common.http.ImageLoader;

import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/5 16:47
 * 微信精选
 */
public class WeChatChoicenessAdapter extends BaseQuickAdapter<WeChatChoiceness.ResultBean.ListBean, BaseViewHolder> {

    private Context mContext;

    public WeChatChoicenessAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<WeChatChoiceness.ResultBean.ListBean> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WeChatChoiceness.ResultBean.ListBean item) {
        helper.setText(R.id.tv_title, item.title);
        helper.setText(R.id.tv_source, item.source);
        ImageLoader.with(mContext, item.firstImg, (ImageView) helper.getView(R.id.iv_first));
    }
}
