package com.wsyzj.watchvideo.business.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.PreviewLargeAdapter;
import com.wsyzj.watchvideo.business.bean.Gank;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.tools.DisplayUtils;
import com.wsyzj.watchvideo.common.tools.SpanUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2017/12/19 14:58
 * @Description: 预览大图
 */
public class PreviewLargeActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.view_pager)
    ViewPager view_pager;

    @BindView(R.id.tv_current_count)
    TextView tv_current_count;

    private int mCurrentPos;
    private List<Gank.ResultsBean> mGankData;

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.black);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.init();
    }

    @Override
    protected BasePresenter presenter() {
        return null;
    }

    @Override
    protected int contentView() {
        baseTitleView.setTbBackgroundColor(R.color.black);
        baseTitleView.setNavigationIcon(R.drawable.ic_clear);
        return R.layout.activity_preview_large;
    }

    @Override
    protected void initView() {
        view_pager.addOnPageChangeListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getIntentExtras();
        setPreviewLargeVpData();
    }

    /**
     * 获取传参
     */
    private void getIntentExtras() {
        Intent intent = getIntent();
        mCurrentPos = intent.getIntExtra("position", 0);
        mGankData = (List<Gank.ResultsBean>) intent.getSerializableExtra("ganks");
    }

    /**
     * 设置大图的预览
     */
    private void setPreviewLargeVpData() {
        PreviewLargeAdapter adapter = new PreviewLargeAdapter(this, mGankData);
        view_pager.setAdapter(adapter);
        view_pager.setCurrentItem(mCurrentPos);
        setCurrentCount(mCurrentPos);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrentCount(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 设置当前索引
     */
    private void setCurrentCount(int position) {
        tv_current_count.setText(new SpanUtils()
                .append(String.valueOf(position + 1))
                .setFontSize(DisplayUtils.sp2px(this, 20))
                .append("/" + String.valueOf(mGankData.size()))
                .setFontSize(DisplayUtils.sp2px(this, 20))
                .create());
    }
}
