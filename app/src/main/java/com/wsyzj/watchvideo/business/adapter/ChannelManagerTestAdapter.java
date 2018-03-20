package com.wsyzj.watchvideo.business.adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.business.helper.OnItemMoveListener;
import com.wsyzj.watchvideo.common.utils.UiUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/19
 *     desc   :
 * </pre>
 */
public class ChannelManagerTestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemMoveListener {

    public static final int TYPE_MY_TEXT = 0;
    private static final int TYPE_MY_CHANNEL = 1;
    public static final int TYPE_RECOMMEND_TEXT = 2;
    private static final int TYPE_RECOMMEND_CHANNEL = 3;
    private static final int MY_TEXT_COUNT = 1;           // 我的标题文字单独占一行，个数为1
    private static final int RECOMMEND_TEXT_COUNT = 1;    // 推荐标题文字，个数为1
    private static final long ANIM_TIME = 360L;
    private static final long SPACE_TIME = 100;           // 判断点击间隔

    private boolean isEditMode;
    private ItemTouchHelper mItemTouchHelper;
    private List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> mMyChannel;
    private List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> mRecommendChannel;

    private long mTouchStartTime;

    public ChannelManagerTestAdapter(ItemTouchHelper itemTouchHelper, List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> myChannel, List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> recommendChannel) {
        mItemTouchHelper = itemTouchHelper;
        mMyChannel = myChannel;
        mRecommendChannel = recommendChannel;
    }

    public void refreshData(List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> myChannel, List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> recommendChannel) {
        mMyChannel = myChannel;
        mRecommendChannel = recommendChannel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        int myCount = mMyChannel.isEmpty() ? 0 : mMyChannel.size();
        int recommendCount = mRecommendChannel.isEmpty() ? 0 : mRecommendChannel.size();
        return myCount + MY_TEXT_COUNT + recommendCount + RECOMMEND_TEXT_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_MY_TEXT;
        } else if (position > 0 && position < mMyChannel.size() + 1) {
            return TYPE_MY_CHANNEL;
        } else if (mMyChannel.size() + 1 == position) {
            return TYPE_RECOMMEND_TEXT;
        } else {
            return TYPE_RECOMMEND_CHANNEL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_MY_TEXT) {
            view = UiUtils.inflate(R.layout.rv_item_channel_manager_type_text);
            MyTextViewHolder holder = new MyTextViewHolder(view);
            return holder;
        } else if (viewType == TYPE_MY_CHANNEL) {
            view = UiUtils.inflate(R.layout.rv_item_channel_manager_type_my_channel);
            MyChannelViewHolder holder = new MyChannelViewHolder(view);
            setMyChannelView(parent, holder);
            return holder;
        } else if (viewType == TYPE_RECOMMEND_TEXT) {
            view = UiUtils.inflate(R.layout.rv_item_channel_manager_type_text);
            RecommendTextViewHolder holder = new RecommendTextViewHolder(view);
            return holder;
        } else {
            view = UiUtils.inflate(R.layout.rv_item_channel_manager_type_recommend_channel);
            RecommendChannelViewHolder holder = new RecommendChannelViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyTextViewHolder) {
            MyTextViewHolder myTextVh = (MyTextViewHolder) holder;
            setMyTextData(myTextVh);
        } else if (holder instanceof MyChannelViewHolder) {
            MyChannelViewHolder myChannelVh = (MyChannelViewHolder) holder;
            setMyChannelData(myChannelVh);
        } else if (holder instanceof RecommendTextViewHolder) {
            RecommendTextViewHolder recommendTextVh = (RecommendTextViewHolder) holder;
            setRecommendTextData(recommendTextVh);
        } else {
            RecommendChannelViewHolder recommendChannelVh = (RecommendChannelViewHolder) holder;
            setRecommendChannelData(recommendChannelVh);
        }
    }

    /**
     * 我的频道文字
     *
     * @param holder
     */
    private void setMyTextData(MyTextViewHolder holder) {
        TextView tv_channel_name = holder.tv_channel_name;
        TextView tv_edit = holder.tv_edit;

        setEditText(tv_edit);
        tv_channel_name.setText("我的频道");
        tv_edit.setVisibility(View.VISIBLE);

        tv_edit.setOnClickListener(new View.OnClickListener() {
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
     * @param holder
     */
    private void setMyChannelData(MyChannelViewHolder holder) {
        int position = holder.getAdapterPosition();
        NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = mMyChannel.get(position - 1);
        ImageView iv_delete = holder.iv_delete;
        TextView tv_channel_name = holder.tv_channel_name;

        iv_delete.setVisibility(isEditMode && position != 1 ? View.VISIBLE : View.INVISIBLE);
        tv_channel_name.setText(channelListBean.name.replace("最新", ""));
        tv_channel_name.setTextColor(position == 1 ? UiUtils.getColor(R.color.c999999) : UiUtils.getColor(R.color.c222222));
    }

    /**
     * 我的频道
     *
     * @param parent
     * @param holder
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setMyChannelView(ViewGroup parent, MyChannelViewHolder holder) {
        TextView tv_channel_name = holder.tv_channel_name;

        // 点击
        tv_channel_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (isEditMode) {
                    RecyclerView recycler = (RecyclerView) parent;
                    RecyclerView.LayoutManager manager = recycler.getLayoutManager();

                    View targetView = manager.findViewByPosition(mMyChannel.size() + MY_TEXT_COUNT + RECOMMEND_TEXT_COUNT);
                    View currentView = manager.findViewByPosition(position);

                    if (recycler.indexOfChild(targetView) >= 0) {
                        int targetX, targetY;

                        int spanCount = ((GridLayoutManager) manager).getSpanCount();
                        if ((mMyChannel.size() - MY_TEXT_COUNT) % spanCount == 0) {
                            View preTargetView = manager.findViewByPosition(mMyChannel.size() + MY_TEXT_COUNT);
                            targetX = preTargetView.getLeft();
                            targetY = preTargetView.getTop();
                        } else {
                            targetX = targetView.getLeft();
                            targetY = targetView.getTop();
                        }
                        moveMyToRecommend(holder);
                        startDeleteAnim(recycler, currentView, targetX, targetY);
                    } else {
                        moveMyToRecommend(holder);
                    }
                } else {
                    moveMyToRecommend(holder);
                }
            }
        });

        /**
         * 长按拖动
         */
        tv_channel_name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!isEditMode) {
                    startEditMode(parent);
                    mItemTouchHelper.startDrag(holder);
                }
                return true;
            }
        });

        tv_channel_name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isEditMode) {
                    switch (MotionEventCompat.getActionMasked(event)) {
                        case MotionEvent.ACTION_DOWN:
                            mTouchStartTime = System.currentTimeMillis();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if (System.currentTimeMillis() - mTouchStartTime > SPACE_TIME) {
                                mItemTouchHelper.startDrag(holder);
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            mTouchStartTime = 0;
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    /**
     * 其他频道文字
     *
     * @param holder
     */
    private void setRecommendTextData(RecommendTextViewHolder holder) {
        TextView tv_edit = holder.tv_edit;
        TextView tv_channel_name = holder.tv_channel_name;

        tv_channel_name.setText("推荐频道");
        tv_edit.setVisibility(View.INVISIBLE);
    }

    /**
     * 其他频道
     *
     * @param holder
     */
    private void setRecommendChannelData(RecommendChannelViewHolder holder) {
        CardView card_recommend = holder.card_recommend;
        TextView tv_channel_name = holder.tv_channel_name;

        int position = holder.getAdapterPosition() - mMyChannel.size() - (MY_TEXT_COUNT + RECOMMEND_TEXT_COUNT);
        if (position >= 0) {
            NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = mRecommendChannel.get(position);
            tv_channel_name.setText(channelListBean.name.replace("最新", ""));
        }

        card_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
     * 我的频道 -> 推荐频道
     *
     * @param holder
     */
    private void moveMyToRecommend(MyChannelViewHolder holder) {
        int position = holder.getAdapterPosition();
        int startPosition = position - MY_TEXT_COUNT;

        if (position == 1 || startPosition > mMyChannel.size() - 1) {
            return;
        }

        NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = mMyChannel.get(startPosition);
        mMyChannel.remove(startPosition);
        mRecommendChannel.add(0, channelListBean);
        notifyItemMoved(position, mMyChannel.size() + MY_TEXT_COUNT + RECOMMEND_TEXT_COUNT);
    }

    /**
     * 开始增删动画
     */
    private void startDeleteAnim(RecyclerView recycler, View currentView, float targetX, float targetY) {
        ViewGroup viewGroup = (ViewGroup) recycler.getParent();
        ImageView mirrorView = addMirrorView(viewGroup, recycler, currentView);
        TranslateAnimation translateAnim = getTranslateAnim(targetX - currentView.getLeft(), targetY - currentView.getTop());
        currentView.setVisibility(View.INVISIBLE);
        mirrorView.startAnimation(translateAnim);

        translateAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewGroup.removeView(mirrorView);
                if (currentView.getVisibility() == View.INVISIBLE) {
                    currentView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 动画过程中添加一个镜像，我们看到的效果感觉还是targetview移动一样，其实是两个
     *
     * @return
     */
    private ImageView addMirrorView(ViewGroup parent, RecyclerView recycler, View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        final ImageView mirrorView = new ImageView(recycler.getContext());
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        mirrorView.setImageBitmap(bitmap);
        view.setDrawingCacheEnabled(false);
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);
        int[] parenLocations = new int[2];
        recycler.getLocationOnScreen(parenLocations);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(bitmap.getWidth(), bitmap.getHeight());
        params.setMargins(locations[0], locations[1] - parenLocations[1], 0, 0);
        parent.addView(mirrorView, params);
        return mirrorView;
    }

    /**
     * @return
     */
    private TranslateAnimation getTranslateAnim(float targetX, float targetY) {
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.ABSOLUTE, targetX,
                Animation.RELATIVE_TO_SELF, 0f, Animation.ABSOLUTE, targetY);
        translateAnimation.setDuration(ANIM_TIME);
        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }

    /**
     * 长按的时候开启编辑模式
     */
    private void startEditMode(ViewGroup viewGroup) {
        RecyclerView recyclerView = (RecyclerView) viewGroup;

        isEditMode = true;
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            ImageView iv_delete = (ImageView) childAt.findViewById(R.id.iv_delete);
            if (i != 1 && iv_delete != null) {
                iv_delete.setVisibility(View.VISIBLE);
            }
        }

        View childAt = recyclerView.getChildAt(0);
        TextView textView = (TextView) childAt.findViewById(R.id.tv_edit);
        textView.setText("完成");
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = mMyChannel.get(fromPosition - MY_TEXT_COUNT);
        mMyChannel.remove(fromPosition - MY_TEXT_COUNT);
        mMyChannel.add(toPosition - MY_TEXT_COUNT, channelListBean);
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * 我的标题 适配器----------------------------------------------------------------------------------
     */
    static class MyTextViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_channel_name)
        TextView tv_channel_name;

        @BindView(R.id.tv_edit)
        TextView tv_edit;

        MyTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 我的频道
     */
    static class MyChannelViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_channel_name)
        TextView tv_channel_name;

        @BindView(R.id.iv_delete)
        ImageView iv_delete;

        MyChannelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 推荐频道
     */
    static class RecommendTextViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_channel_name)
        TextView tv_channel_name;

        @BindView(R.id.tv_edit)
        TextView tv_edit;

        RecommendTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 推荐频道
     */
    static class RecommendChannelViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_recommend)
        CardView card_recommend;

        @BindView(R.id.tv_channel_name)
        TextView tv_channel_name;

        RecommendChannelViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
