package com.wsyzj.watchvideo.business.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.http.ImageLoader;
import com.wsyzj.watchvideo.common.utils.UiUtils;

import java.util.List;


/**
 * @author 焦洋
 * @date 2017/12/19 15:17
 * @Description: $desc$
 */
public class PreviewLargeAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mGankData;

    public PreviewLargeAdapter(Context context, List<String> gankData) {
        mContext = context;
        mGankData = gankData;
    }

    @Override
    public int getCount() {
        return mGankData == null ? 0 : mGankData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String url = mGankData.get(position);

        View view = UiUtils.inflate(R.layout.item_preview_large);
        PhotoView pv_gank = (PhotoView) view.findViewById(R.id.pv_gank);
        ImageLoader.with(mContext, url, pv_gank);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
