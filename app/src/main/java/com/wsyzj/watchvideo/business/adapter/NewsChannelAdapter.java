package com.wsyzj.watchvideo.business.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.bean.NewsDetails;
import com.wsyzj.watchvideo.common.http.ImageLoader;
import com.wsyzj.watchvideo.common.utils.IntentUtils;

import java.util.ArrayList;
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
        addItemType(NewsDetails.TYPE_SINGLE, R.layout.rv_item_news_channel_type_single);
        addItemType(NewsDetails.TYPE_IMGS, R.layout.rv_item_news_channel_type_imgs);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsDetails.ResultBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean item) {
        helper.setText(R.id.tv_title, item.title);
        helper.setText(R.id.tv_source, item.source);
        helper.setText(R.id.tv_pubDate, countPubDate(item.pubDate));

        switch (helper.getItemViewType()) {
            case NewsDetails.TYPE_TEXT:
                break;
            case NewsDetails.TYPE_SINGLE:
                ImageLoader.with(mContext, item.imageurls.get(0).url, (ImageView) helper.getView(R.id.iv_conver));
                break;
            case NewsDetails.TYPE_IMGS:
                LinearLayout ll_imgs = helper.getView(R.id.ll_imgs);
                ll_imgs.removeAllViews();

                int size = item.imageurls.size();
                final List<NewsDetails.ResultBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean.ImageurlsBean> imageurls = item.imageurls;
                for (int i = 0; i < size; i++) {
                    ImageView imageView = new ImageView(mContext);
                    LinearLayout.MarginLayoutParams lp = new LinearLayout.MarginLayoutParams(ConvertUtils.dp2px(133), ConvertUtils.dp2px(100));
                    lp.leftMargin = ConvertUtils.dp2px(10);
                    lp.rightMargin = (i == size - 1) ? ConvertUtils.dp2px(10) : 0;
                    imageView.setLayoutParams(lp);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    ImageLoader.with(mContext, imageurls.get(i).url, R.drawable.icon_default_error, R.drawable.icon_default_error, imageView);
                    ll_imgs.addView(imageView);

                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            IntentUtils.previewLarge((Activity) mContext, finalI, getPreviewLargeUrl(imageurls));
                        }
                    });
                }
                break;
            default:
                break;
        }
    }

    /**
     * 计算发布时间
     */
    private String countPubDate(String pubDate) {
        long second = 1000;
        long minute = second * 60;
        long hour = minute * 24;
        long month = hour * 30;
        long year = month * 12;

        long nowMills = TimeUtils.getNowMills();
        long pubDateMillis = TimeUtils.string2Millis(pubDate);

        long time = nowMills - pubDateMillis;
        if (time < minute) {
            return "刚刚";
        } else if (time < hour) {
            return (time / minute) + "分钟前";
        } else if (time < month) {
            return time / hour + "小时前";
        } else if (time < year) {
            return time / month + "月前";
        } else {
            return "未知";
        }
    }

    /**
     * 获取预览大图的url
     *
     * @param imageurls
     * @return
     */
    private ArrayList<String> getPreviewLargeUrl(List<NewsDetails.ResultBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean.ImageurlsBean> imageurls) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < imageurls.size(); i++) {
            NewsDetails.ResultBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean.ImageurlsBean imageurlsBean = imageurls.get(i);
            list.add(imageurlsBean.url);
        }
        return list;
    }
}
