package com.wsyzj.watchvideo.business.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Progress;
import com.lzy.okserver.download.DownloadListener;
import com.lzy.okserver.download.DownloadTask;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.common.http.ImageLoader;

import java.io.File;
import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/26
 *     desc   :
 * </pre>
 */
public class PlayerCatalogueAdapter extends BaseQuickAdapter<DownloadTask, BaseViewHolder> {

    private Context mContext;

    public PlayerCatalogueAdapter(Context context, @Nullable List<DownloadTask> data) {
        super(R.layout.item_player_catalogue, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DownloadTask item) {
        Progress progress = item.progress;
        Music.SongListBean songListBean = (Music.SongListBean) progress.extra1;

        if (songListBean != null) {
            helper.setText(R.id.tv_name, songListBean.title);
            helper.setText(R.id.tv_desc, songListBean.artist_name + "  " + songListBean.album_title);
            helper.addOnClickListener(R.id.iv_more_action);
            ImageLoader.with(mContext, songListBean.pic_big, R.drawable.default_cover, R.drawable.default_cover, helper.getView(R.id.iv_conver));

            double percent = progress.currentSize / progress.totalSize;
            helper.setMax(R.id.pb_progress, 100);
            helper.setProgress(R.id.pb_progress, (int) (percent * 100));

            LogUtils.e(percent + "  " + progress.currentSize + "/" + progress.totalSize + "  " + progress.status);
        }

        item.register(new MyDownloadListener(progress.tag));
        item.restart();
    }

    private class MyDownloadListener extends DownloadListener {

        public MyDownloadListener(Object tag) {
            super(tag);
        }

        @Override
        public void onStart(Progress progress) {
            LogUtils.e("onStart");
        }

        @Override
        public void onProgress(Progress progress) {
            LogUtils.e("onProgress");
        }

        @Override
        public void onError(Progress progress) {
            LogUtils.e("onError");
            Throwable exception = progress.exception;
            if (exception != null) {
                exception.printStackTrace();
            }
        }

        @Override
        public void onFinish(File file, Progress progress) {
            LogUtils.e("onFinish");
        }

        @Override
        public void onRemove(Progress progress) {
            LogUtils.e("onRemove");
        }
    }
}
