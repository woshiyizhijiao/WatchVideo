package com.wsyzj.watchvideo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.business.adapter.VpAdapter;
import com.wsyzj.watchvideo.common.business.fragment.MusicFragment;
import com.wsyzj.watchvideo.common.http.BaseEntity;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.http.BaseRetrofitApi;
import com.wsyzj.watchvideo.common.http.BaseRxSchedulers;
import com.wsyzj.watchvideo.common.http.BaseSubscriber;
import com.wsyzj.watchvideo.common.test.City;
import com.wsyzj.watchvideo.common.tools.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private String[] mTabs = {"音乐", "吃屎"};

    @Override
    protected BasePresenter presenter() {
        return null;
    }

    @Override
    protected int contentView() {
        baseTitleView.hide();
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initTab();
        initVpData();
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        for (int x = 0; x < mTabs.length; x++) {
            tabLayout.addTab(tabLayout.newTab().setText(mTabs[x]));
        }
    }

    /**
     * 设置tab
     */
    private void initVpData() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new MusicFragment());
        fragments.add(new MusicFragment());

        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), this, fragments, mTabs);
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

    public void onClick(View view) {
        BaseSubscriber<City> baseSubscriber = BaseRetrofit
                .getInstance()
                .create(BaseRetrofitApi.class)
                .region()
                .compose(BaseRxSchedulers.<BaseEntity<City>>io_main(this))
                .subscribeWith(new BaseSubscriber<City>() {
                    @Override
                    public void onSuccess(City city) {
                        LogUtils.e(city.getData().get(0).getBIANMA());
                    }
                });
        addDisposable(baseSubscriber);
    }
}
