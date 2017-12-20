package com.wsyzj.watchvideo.business.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.HomeAdapter;
import com.wsyzj.watchvideo.business.adapter.HomeDouBanAdapter;
import com.wsyzj.watchvideo.business.adapter.NewsTitleAdapter;
import com.wsyzj.watchvideo.business.bean.DouBan;
import com.wsyzj.watchvideo.business.bean.Gank;
import com.wsyzj.watchvideo.business.bean.MeiRiYiWen;
import com.wsyzj.watchvideo.business.mvp.HomeContract;
import com.wsyzj.watchvideo.business.mvp.HomePresenter;
import com.wsyzj.watchvideo.common.base.BaseEvent;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.tools.Constant;
import com.wsyzj.watchvideo.common.tools.EventBusUtils;
import com.wsyzj.watchvideo.common.tools.IntentUtils;
import com.wsyzj.watchvideo.common.tools.UiUtils;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2017/12/12 14:02
 * @Description: 推荐
 */
public class HomeFragment extends BaseFragment implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private HomePresenter mPresenter;
    private HomeAdapter mHomeAdapter;
    private View mHeadView;

    @Override
    protected BaseIPresenter presenter() {
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
        pull_to_refresh.setOnRefreshListener(this);
        pull_to_refresh.setRequestLoadMoreListener(this);
    }

    @Override
    public void initData() {
        setGankData(null);
        mPresenter.getMeiRiYiWen(mActivity);
        mPresenter.getTheatersList();
        mPresenter.getGankData(true);
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
    public void setGankData(final List<Gank.ResultsBean> results) {
        if (mHomeAdapter == null) {
            mHomeAdapter = new HomeAdapter(mActivity, R.layout.item_home, results);
            pull_to_refresh.setLayoutManager(new GridLayoutManager(mActivity, 2));
            pull_to_refresh.setAdapter(mHomeAdapter);
            addHeadView();
        } else {
            mHomeAdapter.setNewData(results);
        }

        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtils.previewLarge(mActivity, position, results);
            }
        });
    }

    /**
     * 获取头部添加的View
     *
     * @return
     */

    private void addHeadView() {
        if (mHomeAdapter.getHeaderLayoutCount() == 0) {
            mHeadView = UiUtils.inflate(R.layout.item_header_home);
            addNewsTitleData();
            pull_to_refresh.addHeadView(mHeadView);
        }
    }

    /**
     * 添加几个新闻标题的数据
     */
    private void addNewsTitleData() {
        final String[] titles = {"今日头条", "新浪", "网易", "腾讯新闻", "糗事百科", "内涵段子"};
        final String[] urls = {
                "http://www.qq.com/",
                "https://www.toutiao.com/",
                "https://www.toutiao.com/",
                "http://www.qq.com/",
                "https://www.toutiao.com/",
                "https://www.toutiao.com/",
        };

        if (mHeadView != null) {
            RecyclerView rv_news = (RecyclerView) mHeadView.findViewById(R.id.rv_news);
            NewsTitleAdapter adapter = new NewsTitleAdapter(R.layout.item_news_title, Arrays.asList(titles));
            rv_news.setLayoutManager(new GridLayoutManager(mActivity, 3));
            rv_news.setAdapter(adapter);

            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    IntentUtils.webView(mActivity, titles[position], urls[position]);
                }
            });
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
