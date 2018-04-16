package com.wsyzj.watchvideo.business.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.VpAdapter;
import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.business.fragment.HomeFragment;
import com.wsyzj.watchvideo.business.fragment.NewsChannelFragment;
import com.wsyzj.watchvideo.business.mvp.MainTestContract;
import com.wsyzj.watchvideo.business.mvp.MainTestPresenter;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.widget.BaseState;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/16
 *     desc   :
 * </pre>
 */
public class MainTestActivity extends BaseActivity implements MainTestContract.View {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private MainTestPresenter mPresenter;
    private VpAdapter mVpAdapter;

    @Override
    protected BasePresenter presenter() {
        mPresenter = new MainTestPresenter(this);
        return mPresenter;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mNavigationView.hide();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.getNewsChannel(this);
    }

    @Override
    public void setChannelList(List<NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean> channelList) {
        List<BaseFragment> fragments = new ArrayList<>();

        if (channelList != null) {
            for (int i = 0; i < channelList.size(); i++) {
                NewsChannel.ResultBean.ShowapiResBodyBean.ChannelListBean channelListBean = channelList.get(i);
                tabLayout.addTab(tabLayout.newTab().setText(channelListBean.name));
                if (TextUtils.equals("推荐", channelListBean.name)) {
                    fragments.add(new HomeFragment());
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString(NewsChannelFragment.BUNDLE_CHANNEL_ID, channelListBean.channelId);
                    bundle.putString(NewsChannelFragment.BUNDLE_CHANNEL_NAME, channelListBean.name);

                    NewsChannelFragment newsFragment = new NewsChannelFragment();
                    newsFragment.setArguments(bundle);
                    fragments.add(newsFragment);
                }
            }
        }

        if (mVpAdapter == null) {
            mVpAdapter = new VpAdapter(getSupportFragmentManager(), this, fragments, channelList);
            viewPager.setAdapter(mVpAdapter);
        } else {
            mVpAdapter.refreshData(fragments, channelList);
        }
        setPageState(BaseState.STATE_SUCCESS);
    }
}
