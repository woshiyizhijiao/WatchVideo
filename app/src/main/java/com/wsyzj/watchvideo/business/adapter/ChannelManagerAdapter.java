package com.wsyzj.watchvideo.business.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.common.utils.UiUtils;

import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/14
 *     desc   :
 * </pre>
 */
public class ChannelManagerAdapter extends BaseMultiItemQuickAdapter<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean, BaseViewHolder> {

    private static final int MY_TEXT_COUNT = 1;
    private static final int RECOMMEND_TEXT_COUNT = 1;

    private RecyclerView recycler_view;
    private boolean isEditMode = true;  // 是否为编辑状态

    public ChannelManagerAdapter(RecyclerView recyclerView, List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> data) {
        super(data);
        recycler_view = recyclerView;
        addItemType(NewsChannel.TYPE_MY_TEXT, R.layout.rv_item_channel_manager_type_text);
        addItemType(NewsChannel.TYPE_MY_CHANNEL, R.layout.rv_item_channel_manager_type_my_channel);
        addItemType(NewsChannel.TYPE_RECOMMEND_TEXT, R.layout.rv_item_channel_manager_type_text);
        addItemType(NewsChannel.TYPE_RECOMMEND_CHANNEL, R.layout.rv_item_channel_manager_type_recommend_channel);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean item) {
        int itemViewType = helper.getItemViewType();
        helper.setText(R.id.tv_channel_name, item.name.replace("最新", ""));

        helper.getAdapterPosition();
        helper.getLayoutPosition();

        switch (itemViewType) {
            case NewsChannel.TYPE_MY_TEXT:
                typeMyText(helper, item);
                break;
            case NewsChannel.TYPE_MY_CHANNEL:
                typeMyChannel(helper, item);
                break;
            case NewsChannel.TYPE_RECOMMEND_TEXT:
                typeRecommendText(helper, item);
                break;
            case NewsChannel.TYPE_RECOMMEND_CHANNEL:
                typeRecommendChannel(helper, item);
                break;
            default:
                break;
        }
    }

    /**
     * 我的频道文字
     *
     * @param helper
     * @param item
     */
    private void typeMyText(BaseViewHolder helper, NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean item) {
        TextView tv_edit = helper.getView(R.id.tv_edit);
        setEditText(tv_edit);
        helper.setVisible(R.id.tv_edit, true);

        helper.getView(R.id.tv_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEditMode = !isEditMode;
                setEditText(tv_edit);
                notifyDataSetChanged();
            }
        });
    }

    /**
     * 我的频道
     *
     * @param helper
     * @param item
     */
    private void typeMyChannel(BaseViewHolder helper, NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean item) {
        int adapterPosition = helper.getAdapterPosition();
        helper.setVisible(R.id.iv_delete, adapterPosition != 1 && isEditMode);
        helper.setTextColor(R.id.tv_channel_name, adapterPosition == 1 ? UiUtils.getColor(R.color.c999999) : UiUtils.getColor(R.color.c222222));

        helper.getView(R.id.tv_channel_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditMode) {
                    RecyclerView.LayoutManager layoutManager = recycler_view.getLayoutManager();

                } else {

                }
            }
        });
    }

    /**
     * 其他频道文字
     *
     * @param helper
     * @param item
     */
    private void typeRecommendText(BaseViewHolder helper, NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean item) {
        helper.setVisible(R.id.tv_edit, false);
    }

    /**
     * 其他频道
     *
     * @param helper
     * @param item
     */
    private void typeRecommendChannel(BaseViewHolder helper, NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean item) {
        int adapterPosition = helper.getAdapterPosition();
        helper.getView(R.id.card_recommend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveRecommendToMy(adapterPosition);
            }
        });
    }

    /**
     * 设置编辑按钮文字
     */
    private void setEditText(TextView textView) {
        textView.setText(isEditMode ? "完成" : "编辑");
    }

    /**
     * 我的频道>>推荐频道
     *
     * @param adapterPosition
     */
    private void moveMyToRecommend(int adapterPosition) {
        NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = mData.get(adapterPosition);
        channelListBean.isMyChannel = false;
        mData.remove(adapterPosition);
        mData.add(channelListBean);
        notifyDataSetChanged();
    }

    /**
     * 推荐频道>>我的频道
     */
    private void moveRecommendToMy(int adapterPosition) {
        NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = mData.get(adapterPosition);
        channelListBean.isMyChannel = true;
        mData.remove(adapterPosition);
        mData.add(MY_TEXT_COUNT + 1, channelListBean);
        notifyDataSetChanged();
    }
}
