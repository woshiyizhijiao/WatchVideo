package com.wsyzj.watchvideo.business.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jaeger.library.StatusBarUtil;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.PreviewLargeAdapter;
import com.wsyzj.watchvideo.business.bean.Gank;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.utils.UiUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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

    @BindView(R.id.tv_save_photo)
    TextView tv_save_photo;

    private int mCurrentPos;
    private List<Gank.ResultsBean> mGankData;

    @Override
    protected BasePresenter presenter() {
        return null;
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.black));
    }

    @Override
    protected int contentView() {
        baseTitleView.hide();
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

    @OnClick(R.id.fl_back)
    public void bkOnClick(View view) {
        switch (view.getId()) {
            case R.id.fl_back:
                finish();
                break;
            default:
                break;
        }
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
                .setFontSize(ConvertUtils.sp2px(20))
                .append("/" + String.valueOf(mGankData.size()))
                .setFontSize(ConvertUtils.sp2px(20))
                .create());
    }

    /**
     * 保存图片到相册
     */
    @OnClick(R.id.tv_save_photo)
    public void savePhotoToSDCard() {
        showProgress();
        String url = mGankData.get(view_pager.getCurrentItem()).url;
        Glide.with(this).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                boolean isSuccess = saveImageToGallery(PreviewLargeActivity.this, resource);
                if (!isSuccess) {
                    showToast("保存失败，请重试！");
                }
                dismissProgress();
            }
        });
    }

    /**
     * 保存图片到本地
     *
     * @param context
     * @param bmp
     * @return
     */
    public static boolean saveImageToGallery(Context context, Bitmap bmp) {
        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dearxy";
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
