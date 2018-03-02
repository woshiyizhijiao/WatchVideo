package com.wsyzj.watchvideo.business.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.wsyzj.watchvideo.business.bean.NewsChannel;
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
    private List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> mChannel;

    public VpAdapter(FragmentManager fm, Context context, List<BaseFragment> fragments, List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> channelList) {
        super(fm);
        mContext = context;
        mFragments = fragments;
        mChannel = channelList;
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
        String name = mChannel.get(position).name;
        if (name.contains("焦点")) {
            return name.replace("焦点", "");
        } else if (name.contains("最新")) {
            return name.replace("最新", "");
        } else {
            return name;
        }
    }
}
