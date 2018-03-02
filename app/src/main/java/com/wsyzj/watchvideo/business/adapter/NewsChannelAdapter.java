package com.wsyzj.watchvideo.business.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.bean.NewsDetails;
import com.wsyzj.watchvideo.common.http.ImageLoader;

import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/02
 *     desc   : 视频频道列表适配器
 * </pre>
 */
public class NewsChannelAdapter extends BaseMultiItemQuickAdapter<NewsDetails.ResultBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean, BaseViewHolder> {

    private Context mContext;

    public NewsChannelAdapter(Context context, List<NewsDetails.ResultBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data) {
        super(data);
        mContext = context;
        addItemType(NewsDetails.TYPE_TEXT, R.layout.rv_item_news_channel_type_text);
        addItemType(NewsDetails.TYPE_IMGS, R.layout.rv_item_news_channel_type_imgs);
    }


    @Override
    protected void convert(BaseViewHolder helper, NewsDetails.ResultBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean item) {
        switch (helper.getItemViewType()) {
            case NewsDetails.TYPE_TEXT:
                helper.setText(R.id.tv_title, item.title);
                helper.setText(R.id.tv_source, item.source);
                break;
            case NewsDetails.TYPE_IMGS:
                helper.setText(R.id.tv_title, item.title);
                helper.setText(R.id.tv_source, item.source);
                LinearLayout ll_imgs = helper.getView(R.id.ll_imgs);
                ll_imgs.removeAllViews();

                int size = item.imageurls.size();
                for (int i = 0; i < size; i++) {
                    ImageView imageView = new ImageView(mContext);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ConvertUtils.dp2px(160), ConvertUtils.dp2px(140));
                    imageView.setLayoutParams(lp);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    ImageLoader.with(mContext, item.imageurls.get(i).url, imageView);
                    ll_imgs.addView(imageView);
                }
                break;
            default:
                break;
        }
    }
}
