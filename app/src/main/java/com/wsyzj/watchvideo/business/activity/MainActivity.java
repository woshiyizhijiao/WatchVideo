package com.wsyzj.watchvideo.business.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.ColorPrimaryAdapter;
import com.wsyzj.watchvideo.business.adapter.VpAdapter;
import com.wsyzj.watchvideo.business.bean.NewsChannel;
import com.wsyzj.watchvideo.business.fragment.HomeFragment;
import com.wsyzj.watchvideo.business.fragment.NewsChannelFragment;
import com.wsyzj.watchvideo.business.mvp.MainContract;
import com.wsyzj.watchvideo.business.mvp.MainPresenter;
import com.wsyzj.watchvideo.business.utils.DataUtils;
import com.wsyzj.watchvideo.business.utils.IntentUtils;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.BaseEventBus;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.BaseThreadManager;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.constant.Constant;
import com.wsyzj.watchvideo.common.constant.EventBusConstant;
import com.wsyzj.watchvideo.common.http.ImageLoader;
import com.wsyzj.watchvideo.common.utils.StorageUtils;
import com.wsyzj.watchvideo.common.utils.UiUtils;
import com.wsyzj.watchvideo.common.widget.BaseState;
import com.wsyzj.watchvideo.common.widget.BaseStateLayout;

import net.youmi.android.nm.sp.SpotManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 焦洋
 * @date 2017/12/6 10:03
 * @Description: 主界面
 */
public class MainActivity extends BaseActivity implements MainContract.View, TabLayout.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener, BaseStateLayout.OnStateEmptyListener, BaseStateLayout.OnStateErrorListener {

    private static final int CODE_CHANNEL_MANAGER = 0;

    private static final Integer[] mColorPrimary = {R.color.ce91e63, R.color.c9c27b0, R.color.c673ab7, R.color.c2196f3, R.color.c03a9f4, R.color.c00bcd4, R.color.c009688,
            R.color.colorPrimary, R.color.c8bc34a, R.color.ccddc39, R.color.cffc107, R.color.cff9800, R.color.cff5722, R.color.C795548, R.color.c607d8b, R.color.c9e9e9e};

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.nav_view)
    NavigationView nav_view;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipe_refresh;

    @BindView(R.id.ll_tabLayout)
    LinearLayout ll_tabLayout;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ImageView iv_nav_header_bg;
    private TextView tv_nav_header_text;

    private MainPresenter mPresenter;
    private VpAdapter mVpAdapter;

    @Override
    protected BasePresenter presenter() {
        mPresenter = new MainPresenter(this);
        return mPresenter;
    }

    @Override
    protected int contentView() {
        mNavigationView.hide();
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        swipe_refresh.setRefreshing(true);
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setupWithViewPager(viewPager);
        nav_view.setNavigationItemSelectedListener(this);
        mStateLayout.setOnStateEmptyListener(this);
        mStateLayout.setOnStateErrorListener(this);

        View nav_header_main = nav_view.getHeaderView(0);
        iv_nav_header_bg = (ImageView) nav_header_main.findViewById(R.id.iv_nav_header_bg);
        tv_nav_header_text = (TextView) nav_header_main.findViewById(R.id.tv_nav_header_text);

        int colorPrimary = UiUtils.getColor(StorageUtils.getColorPrimary());
        toolbar.setBackgroundColor(colorPrimary);
        ll_tabLayout.setBackgroundColor(colorPrimary);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.getNewsChannel(this);
        pgyUpdateApp();
        setNavHeaderBg();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SpotManager.getInstance(this).onAppExit();
    }

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case CODE_CHANNEL_MANAGER:
                    mPresenter.changedNesChannel(data);
                    break;
                default:
                    break;
            }
        }
    }

    @OnClick({R.id.fl_channel_manager})
    public void bkOnClick(View view) {
        switch (view.getId()) {
            case R.id.fl_channel_manager:
                IntentUtils.channelManager(this, CODE_CHANNEL_MANAGER);
                break;
            default:
                break;
        }
    }

    /**
     * 设置新闻标题
     *
     * @param channelList
     */
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

    /**
     * 设置侧滑bg图片
     */
    private void setNavHeaderBg() {
        ImageLoader.with(this, DataUtils.getNavHeaderBgStr(), iv_nav_header_bg);
        tv_nav_header_text.setText(DataUtils.getNavHeaderBgText());
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
    protected void receiveEvent(BaseEventBus event) {
        super.receiveEvent(event);
        switch (event.code) {
            case EventBusConstant.NEW_FIRST_PAGE_LOAD_FINISH:
                swipe_refresh.setEnabled(false);
                break;
            default:
                break;
        }
    }

    /**
     * 蒲公英更新
     */
    private void pgyUpdateApp() {
        PgyUpdateManager.register(MainActivity.this, new UpdateManagerListener() {
            @Override
            public void onUpdateAvailable(final String result) {
                final AppBean appBean = getAppBeanFromString(result);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(appBean.getVersionName() + "新版本更新")
                        .setMessage(appBean.getReleaseNote())
                        .setNegativeButton(
                                "确定",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startDownloadTask(MainActivity.this, appBean.getDownloadURL());
                                    }
                                }).show();
            }

            @Override
            public void onNoUpdateAvailable() {
//                showToast("更新检测失败");
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_service) {
            navLifeDialog();
        } else if (id == R.id.nav_game) {
            IntentUtils.webView(this, "小游戏", Constant.URL_GAME_H5);
        } else if (id == R.id.nav_skin_peeler) {
            changedColorPrimary();
        } else if (id == R.id.nav_clear_cache) {
            navClearCache();
        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 生活服务弹窗
     */
    private void navLifeDialog() {
        final String[] titles = {"百度一下", "翻译", "快递查询", "天气查询", "列车时刻查询", "手机归属地查询", "简繁体字转换"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(titles, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        IntentUtils.webView(MainActivity.this, titles[which], Constant.URL_SERVICE_BAIDU);
                        break;
                    case 1:
                        IntentUtils.webView(MainActivity.this, titles[which], Constant.URL_SERVICE_TRANSLATE);
                        break;
                    case 2:
                        IntentUtils.webView(MainActivity.this, titles[which], Constant.URL_SERVICE_EXPRESSAGE);
                        break;
                    case 3:
                        IntentUtils.webView(MainActivity.this, titles[which], Constant.URL_SERVICE_WEATHER);
                        break;
                    case 4:
                        IntentUtils.webView(MainActivity.this, titles[which], Constant.URL_SERVICE_RAILWAY_TICKET);
                        break;
                    case 5:
                        IntentUtils.webView(MainActivity.this, titles[which], Constant.URL_SERVICE_PHONT_QUERY);
                        break;
                    case 6:
                        IntentUtils.webView(MainActivity.this, titles[which], Constant.URL_SERVICE_JIANFANZH);
                        break;
                    default:
                        break;
                }
            }
        });
        builder.show();
    }

    /**
     * 更换皮肤
     */
    private void changedColorPrimary() {
        View headView = UiUtils.inflate(R.layout.dialog_color_primary);
        RecyclerView rv_color_primary = (RecyclerView) headView.findViewById(R.id.rv_color_primary);

        rv_color_primary.setLayoutManager(new GridLayoutManager(this, 4));
        ColorPrimaryAdapter colorPrimaryAdapter = new ColorPrimaryAdapter(this, Arrays.asList(mColorPrimary));
        rv_color_primary.setAdapter(colorPrimaryAdapter);

        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setView(headView);
        alertDialog.show();

        colorPrimaryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StorageUtils.putColorPrimary(mColorPrimary[position]);
                int color = UiUtils.getColor(mColorPrimary[position]);

                setStatusBar();
                ll_tabLayout.setBackgroundColor(color);
                toolbar.setBackgroundColor(color);

                alertDialog.dismiss();
            }
        });
    }

    /**
     * 清除缓存
     */
    private void navClearCache() {
        BaseThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                CacheUtils instance = CacheUtils.getInstance();
                long cacheSize = instance.getCacheSize();
                instance.clear();
                showToast("共清除" + cacheSize + "M缓存");
            }
        });
    }

    @Override
    public void onStateEmpty() {
        LogUtils.e("空界面");
        mPresenter.getNewsChannel(this);
    }

    @Override
    public void onStateError() {
        LogUtils.e("异常界面");
        mPresenter.getNewsChannel(this);
    }
}
