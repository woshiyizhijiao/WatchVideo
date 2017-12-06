package com.wsyzj.watchvideo.common.business.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.business.bean.News;
import com.wsyzj.watchvideo.common.http.ImageLoader;

import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/6 14:02
 * @Description: $desc$
 */
public class NewsAdapter extends BaseQuickAdapter<News.ResultBeanX.ResultBean.ListBean, BaseViewHolder> {

    private Context mContext;

    public NewsAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<News.ResultBeanX.ResultBean.ListBean> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, News.ResultBeanX.ResultBean.ListBean item) {
        ImageLoader.with(mContext, item.pic, (ImageView) helper.getView(R.id.iv_pic));
        helper.setText(R.id.tv_title, item.title);
        helper.setText(R.id.tv_src, item.src);
    }
}
