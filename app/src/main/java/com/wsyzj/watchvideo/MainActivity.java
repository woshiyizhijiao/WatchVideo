package com.wsyzj.watchvideo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.business.adapter.VpAdapter;
import com.wsyzj.watchvideo.common.business.fragment.KaiYanFragment;
import com.wsyzj.watchvideo.common.business.fragment.MusicFragment;
import com.wsyzj.watchvideo.common.business.fragment.WeChatChoicenessFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private String[] mTabs = {"微信精选", "视频", "音乐",};

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
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initTab();
        initVpData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
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
        fragments.add(new WeChatChoicenessFragment());
        fragments.add(new KaiYanFragment());
        fragments.add(new MusicFragment());

        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), this, fragments, mTabs);
        viewPager.setAdapter(vpAdapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
