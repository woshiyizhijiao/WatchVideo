package com.wsyzj.watchvideo.common.business.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.BaseEvent;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.business.adapter.VpAdapter;
import com.wsyzj.watchvideo.common.business.fragment.HomeFragment;
import com.wsyzj.watchvideo.common.business.fragment.NewsFragment;
import com.wsyzj.watchvideo.common.business.mvp.NewsActivityContract;
import com.wsyzj.watchvideo.common.business.mvp.NewsActivityPresenter;
import com.wsyzj.watchvideo.common.tools.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2017/12/6 10:03
 * @Description: $desc$
 */
public class NewsActivity extends BaseActivity implements NewsActivityContract.View, TabLayout.OnTabSelectedListener {

    public final static String BUNDLE_TITLE_INDEX = "bundle_title_index";
    public final static String BUNDLE_CURRENT_TITLE = "bundle_current_title";

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private NewsActivityPresenter mPresenter;

    @Override
    protected BasePresenter presenter() {
        mPresenter = new NewsActivityPresenter(this);
        return mPresenter;
    }

    @Override
    protected int contentView() {
        baseTitleView.hide();
        return R.layout.activity_news;
    }

    @Override
    protected void initView() {
        swipe_refresh.setRefreshing(true);
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.getNewsTitle();
    }

    /**
     * 设置新闻标题
     *
     * @param newsTitle
     */
    @Override
    public void setNewsTitle(List<String> newsTitle) {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());

        if (newsTitle != null) {
            for (int i = 0; i < newsTitle.size(); i++) {
                tabLayout.addTab(tabLayout.newTab().setText(newsTitle.get(i)));
                Bundle bundle = new Bundle();
                bundle.putInt(BUNDLE_TITLE_INDEX, i);
                bundle.putString(BUNDLE_CURRENT_TITLE, newsTitle.get(i));

                NewsFragment newsFragment = new NewsFragment();
                newsFragment.setArguments(bundle);
                fragments.add(newsFragment);
            }

            newsTitle.add(0, "主页");
        }

        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), this, fragments, newsTitle);
        viewPager.setAdapter(vpAdapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(BaseEvent event) {
        super.receiveEvent(event);
        switch (event.code) {
            case Constant.EventBusC.NEW_FIRST_PAGE_LOAD_FINISH:
                swipe_refresh.setEnabled(false);
                break;
            default:
                break;
        }
    }
}
