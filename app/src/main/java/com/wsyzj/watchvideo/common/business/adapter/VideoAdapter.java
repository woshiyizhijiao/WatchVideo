package com.wsyzj.watchvideo.common.business.adapter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.business.bean.KaiYan;
import com.wsyzj.watchvideo.common.http.ImageLoader;

import java.util.LinkedHashMap;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * author : 焦洋
 * time   : 2017/11/2  14:21
 * desc   :
 */
public class VideoAdapter extends BaseQuickAdapter<KaiYan.DataListBean, BaseViewHolder> {

    private Context mContext;

    public VideoAdapter(Context context, @LayoutRes int layoutResId, @Nullable List<KaiYan.DataListBean> data) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, KaiYan.DataListBean item) {
        JZVideoPlayerStandard jzVideoPlayer = (JZVideoPlayerStandard) helper.getView(R.id.jzVideoPlayer);

        if (item.contList != null && item.contList.size() != 0) {
            KaiYan.DataListBean.ContListBean contListBean = item.contList.get(0);
            String coverVideo = contListBean.coverVideo;
            String name = contListBean.name;
            String pic = contListBean.pic;
            String praiseTimes = contListBean.praiseTimes;
            String nodeInfoName = contListBean.nodeInfo.name;

            helper.setText(R.id.tv_name, nodeInfoName);
            helper.setText(R.id.tv_praiseTimes, praiseTimes);

            ImageLoader.with(mContext, pic, jzVideoPlayer.thumbImageView);
            jzVideoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
            JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;  //纵向

            LinkedHashMap map = new LinkedHashMap();
            map.put("标清", coverVideo);
            map.put("普清", coverVideo);
            map.put("高清", coverVideo);
            jzVideoPlayer.setUp(map, 0, JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, name);
        } else {
            jzVideoPlayer.setUp("", JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
        }
    }
}
