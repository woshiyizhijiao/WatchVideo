package com.wsyzj.watchvideo.business.fragment;

import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.HomeAdapter;
import com.wsyzj.watchvideo.business.adapter.HomeDouBanAdapter;
import com.wsyzj.watchvideo.business.bean.DouBan;
import com.wsyzj.watchvideo.business.bean.Gank;
import com.wsyzj.watchvideo.business.bean.MeiRiYiWen;
import com.wsyzj.watchvideo.business.mvp.HomeContract;
import com.wsyzj.watchvideo.business.mvp.HomePresenter;
import com.wsyzj.watchvideo.business.widget.photo.PreviewLayout;
import com.wsyzj.watchvideo.common.base.BaseEvent;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.IPresenter;
import com.wsyzj.watchvideo.common.base.widget.BasePullToRefreshView;
import com.wsyzj.watchvideo.common.tools.Constant;
import com.wsyzj.watchvideo.common.tools.EventBusUtils;
import com.wsyzj.watchvideo.common.tools.IntentUtils;
import com.wsyzj.watchvideo.common.tools.UiUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2017/12/12 14:02
 * @Description: 主页
 */
public class HomeFragment extends BaseFragment implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private HomePresenter mPresenter;
    private HomeAdapter mHomeAdapter;
    private FrameLayout mContentContainer;
    private View mHeadView;
    private PreviewLayout mPreviewLayout;
    private GridLayoutManager mGridLayoutManager;
    private int[] mPadding = new int[4];
    private int mSolidWidth = 0;
    private int mSolidHeight = 0;
    private Rect mRVBounds = new Rect();
    private int mStatusBarHeight = UiUtils.getStatusBarHeight();

    @Override
    protected IPresenter presenter() {
        mPresenter = new HomePresenter(this);
        return mPresenter;
    }

    @Override
    public int contentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        setRefreshing(false);
        mContentContainer = (FrameLayout) view.findViewById(android.R.id.content);
        pull_to_refresh.getRecycler().setHasFixedSize(true);
        pull_to_refresh.setOnRefreshListener(this);
        pull_to_refresh.setRequestLoadMoreListener(this);
        pull_to_refresh.getRecycler().addOnScrollListener(new MyRecyclerOnScrollListener());
        pull_to_refresh.getRecycler().addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPreviewLayout = new PreviewLayout(mActivity);
                mPreviewLayout.setData(mPresenter.mGankData, position);
                mPreviewLayout.startScaleUpAnimation();
                mContentContainer.addView(mPreviewLayout);
            }
        });
    }

    @Override
    public void initData() {
        setGankData(null);
        mPresenter.getMeiRiYiWen(mActivity);
        mPresenter.getTheatersList();
        mPresenter.getGankData(true);
    }

    private class MyRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                assembleDataList();
            }
        }
    }

    private void assembleDataList() {
        computeBoundsForward(mGridLayoutManager.findFirstCompletelyVisibleItemPosition());
        computeBoundsBackward(mGridLayoutManager.findFirstCompletelyVisibleItemPosition());
    }

    /**
     * 从第一个完整可见item顺序遍历
     */
    private void computeBoundsForward(int firstCompletelyVisiblePos) {
        for (int i = firstCompletelyVisiblePos; i < mPresenter.mGankData.size(); i++) {
            View itemView = mGridLayoutManager.findViewByPosition(i);
            Rect bounds = new Rect();

            if (itemView != null) {
                ImageView thumbView = (ImageView) itemView.findViewById(R.id.iv_meizhi);

                thumbView.getGlobalVisibleRect(bounds);

                if (mSolidWidth * mSolidHeight == 0) {
                    mPadding[0] = thumbView.getPaddingLeft();
                    mPadding[1] = thumbView.getPaddingTop();
                    mPadding[2] = thumbView.getPaddingRight();
                    mPadding[3] = thumbView.getPaddingBottom();
                    mSolidWidth = bounds.width();
                    mSolidHeight = bounds.height();
                }

                bounds.left = bounds.left + mPadding[0];
                bounds.top = bounds.top + mPadding[1];
                bounds.right = bounds.left + mSolidWidth - mPadding[2];
                bounds.bottom = bounds.top + mSolidHeight - mPadding[3];
            } else {
                bounds.left = i % 3 * mSolidWidth + mPadding[0];
                bounds.top = mRVBounds.bottom + mPadding[1];
                bounds.right = bounds.left + mSolidWidth - mPadding[2];
                bounds.bottom = bounds.top + mSolidHeight - mPadding[3];
            }
            bounds.offset(0, -mStatusBarHeight);

            mPresenter.mGankData.get(i).bounds = bounds;
        }
    }

    /**
     * 从第一个完整可见item逆序遍历，如果初始位置为0，则不执行方法内循环
     */
    private void computeBoundsBackward(int firstCompletelyVisiblePos) {
        for (int i = firstCompletelyVisiblePos - 1; i >= 0; i--) {
            View itemView = mGridLayoutManager.findViewByPosition(i);
            Rect bounds = new Rect();

            if (itemView != null) {
                ImageView thumbView = (ImageView) itemView.findViewById(R.id.iv_meizhi);

                thumbView.getGlobalVisibleRect(bounds);

                bounds.left = bounds.left + mPadding[0];
                bounds.bottom = bounds.bottom - mPadding[3];
                bounds.right = bounds.left + mSolidWidth - mPadding[2];
                bounds.top = bounds.bottom - mSolidHeight + mPadding[1];
            } else {
                bounds.left = i % 3 * mSolidWidth + mPadding[0];
                bounds.bottom = mRVBounds.top - mPadding[3];
                bounds.right = bounds.left + mSolidWidth - mPadding[2];
                bounds.top = bounds.bottom - mSolidHeight + mPadding[1];
            }
            bounds.offset(0, -mStatusBarHeight);

            mPresenter.mGankData.get(i).bounds = bounds;
        }
    }

    @Override
    public void firstPageLoadFinish() {
        EventBusUtils.sendEvent(new BaseEvent(Constant.EventBusC.NEW_FIRST_PAGE_LOAD_FINISH));
    }

    /**
     * 设置福利的数据(刚开始调用一下把头部布局添加进来)
     *
     * @param results
     */
    @Override
    public void setGankData(List<Gank.ResultsBean> results) {
        if (mHomeAdapter == null) {
            mHomeAdapter = new HomeAdapter(mActivity, R.layout.item_home, results);
            mGridLayoutManager = new GridLayoutManager(mActivity, 2);
            pull_to_refresh.setLayoutManager(new GridLayoutManager(mActivity, 2));
            pull_to_refresh.setAdapter(mHomeAdapter);
            addHeadView();
        } else {
            mHomeAdapter.setNewData(results);
        }
    }

    /**
     * 获取头部添加的View
     *
     * @return
     */

    private void addHeadView() {
        if (mHomeAdapter.getHeaderLayoutCount() == 0) {
            mHeadView = UiUtils.inflate(R.layout.item_header_home);
            pull_to_refresh.addHeadView(mHeadView);
        }
    }

    /**
     * 设置每日一文数据
     *
     * @param meiRiYiWen
     */
    @Override
    public void setMeiRiYiWenData(final MeiRiYiWen meiRiYiWen) {
        if (mHeadView != null) {
            LinearLayout ll_meiriyiwen = (LinearLayout) mHeadView.findViewById(R.id.ll_meiriyiwen);
            TextView tv_title = (TextView) mHeadView.findViewById(R.id.tv_title);
            TextView tv_desc = (TextView) mHeadView.findViewById(R.id.tv_desc);
            TextView tv_author = (TextView) mHeadView.findViewById(R.id.tv_author);

            tv_title.setText(meiRiYiWen.title);
            tv_desc.setText(meiRiYiWen.digest);
            tv_author.setText(meiRiYiWen.author);

            ll_meiriyiwen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.meiRiYiWen(mActivity, meiRiYiWen.title, meiRiYiWen.author, meiRiYiWen.content);
                }
            });
        }
    }

    /**
     * 设置豆瓣电影数据
     */
    @Override
    public void setTheatersList(final List<DouBan.SubjectsBean> subjects) {
        if (mHeadView != null) {
            RecyclerView rv_douban = (RecyclerView) mHeadView.findViewById(R.id.rv_douban);

            final HomeDouBanAdapter douBanAdapter = new HomeDouBanAdapter(mActivity, R.layout.item_home_douban, subjects);
            rv_douban.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
            rv_douban.setAdapter(douBanAdapter);

            douBanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    DouBan.SubjectsBean subjectsBean = douBanAdapter.getData().get(position);
                    IntentUtils.webView(mActivity, subjectsBean.title, subjectsBean.alt);
                }
            });
        }
    }

    /**
     * @param refreshing
     */
    @Override
    public void setRefreshing(boolean refreshing) {
        pull_to_refresh.setRefreshing(refreshing);
    }

    /**
     * 上拉/下拉的状态
     *
     * @param totalCount
     */
    @Override
    public void setLoadMoreState(int totalCount) {
        pull_to_refresh.setLoadMoreState(totalCount);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mPresenter.getGankData(true);
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        mPresenter.getGankData(false);
    }
}
