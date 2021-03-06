package com.wsyzj.watchvideo.business.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.HomeAdapter;
import com.wsyzj.watchvideo.business.adapter.HomeDouBanAdapter;
import com.wsyzj.watchvideo.business.adapter.NewsTitleAdapter;
import com.wsyzj.watchvideo.business.bean.DouBan;
import com.wsyzj.watchvideo.business.bean.Gank;
import com.wsyzj.watchvideo.business.bean.MeiRiYiWen;
import com.wsyzj.watchvideo.business.mvp.HomeContract;
import com.wsyzj.watchvideo.business.mvp.HomePresenter;
import com.wsyzj.watchvideo.business.utils.IntentUtils;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.utils.UiUtils;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;
import com.wsyzj.watchvideo.common.widget.BaseStateLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2017/12/12 14:02
 * @Description: 推荐
 */
public class HomeFragment extends BaseFragment implements HomeContract.View, OnRefreshListener, OnRefreshLoadMoreListener, BaseStateLayout.OnStateEmptyListener, BaseStateLayout.OnStateErrorListener {

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
    public void initView() {
        pull_to_refresh.setOnRefreshLoadMoreListener(this);
        mStateLayout.setOnStateEmptyListener(this);
        mStateLayout.setOnStateErrorListener(this);
    }

    @Override
    public void initData() {
        setGankData(null);
        mPresenter.getMeiRiYiWen(mActivity);
        mPresenter.getTheatersList();
        mPresenter.getGankData(true);
    }

    /**
     * 设置福利的数据(刚开始调用一下把头部布局添加进来)
     *
     * @param results
     */
    @Override
    public void setGankData(final List<Gank.ResultsBean> results) {
        if (mHomeAdapter == null) {
            mHomeAdapter = new HomeAdapter(mActivity, results);
            pull_to_refresh.setLayoutManager(new GridLayoutManager(mActivity, 2));
            pull_to_refresh.setAdapter(mHomeAdapter);
            addHeadView();
        } else {
            mHomeAdapter.setNewData(results);
        }

        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtils.previewLarge(mActivity, position, getPreviewLargeUrl(results));
            }
        });
    }

    /**
     * 获取预览大图的url
     *
     * @param results
     * @return
     */
    private ArrayList<String> getPreviewLargeUrl(List<Gank.ResultsBean> results) {
        ArrayList list = new ArrayList();
        for (int i = 0; i < results.size(); i++) {
            list.add(results.get(i).url);
        }
        return list;
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
        final String[] titles = {"搜狐新闻", "腾讯新闻", "网易新闻", "全民k歌", "糗事百科", "内涵段子"};
        int[] resId = {R.drawable.icon_souhu_title, R.drawable.icon_tengxun_title, R.drawable.icon_wangyi_title,
                R.drawable.icon_kg_title, R.drawable.icon_qiushibaike_title, R.drawable.icon_neihanduanzi_title};
        final String[] urls = {
                "http://news.sohu.com/",
                "http://news.qq.com/",
                "http://news.163.com/",
                "http://kg.qq.com/",
                "https://www.qiushibaike.com/",
                "http://neihanshequ.com/",
        };

        if (mHeadView != null) {
            RecyclerView rv_news = (RecyclerView) mHeadView.findViewById(R.id.rv_news);
            NewsTitleAdapter adapter = new NewsTitleAdapter(Arrays.asList(titles), resId);
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

            final HomeDouBanAdapter douBanAdapter = new HomeDouBanAdapter(mActivity, subjects);
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
     * 上拉/下拉的状态
     *
     * @param totalCount
     */
    @Override
    public void setLoadMoreState(int totalCount) {
        pull_to_refresh.setLoadMoreByTotal(totalCount);
        pull_to_refresh.finishRefresh();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        mPresenter.getGankData(true);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        mPresenter.getGankData(false);
    }

    /**
     * 空数据回调
     */
    @Override
    public void onStateEmpty() {
        mPresenter.getMeiRiYiWen(mActivity);
        mPresenter.getTheatersList();
        mPresenter.getGankData(true);
    }

    /**
     * 异常回调
     */
    @Override
    public void onStateError() {
        mPresenter.getMeiRiYiWen(mActivity);
        mPresenter.getTheatersList();
        mPresenter.getGankData(true);
    }
}
