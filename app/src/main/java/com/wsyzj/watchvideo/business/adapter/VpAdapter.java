package com.wsyzj.watchvideo.business.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.wsyzj.watchvideo.common.base.BaseFragment;

import java.util.List;

/**
 * 创建时间 : 2017/10/25
 * 编写人 : 焦洋
 * 功能描述 :
 */

public class VpAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<BaseFragment> mFragments;
    private List<String> mTabs;

    public VpAdapter(FragmentManager fm, Context context, List<BaseFragment> fragments, List<String> tabs) {
        super(fm);
        mContext = context;
        mFragments = fragments;
        mTabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position);
    }
}
