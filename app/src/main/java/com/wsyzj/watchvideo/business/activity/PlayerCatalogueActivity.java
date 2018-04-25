package com.wsyzj.watchvideo.business.activity;

import android.os.Bundle;

import com.blankj.utilcode.util.LogUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.mvp.PlayerCatalogueContract;
import com.wsyzj.watchvideo.business.mvp.PlayerCataloguePresenter;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.BaseEventBus;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.constant.EventBusConstant;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;
import com.wsyzj.watchvideo.common.widget.BaseState;

import butterknife.BindView;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/25
 *     desc   : 音乐目录 下载
 * </pre>
 */
public class PlayerCatalogueActivity extends BaseActivity implements PlayerCatalogueContract.View {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private PlayerCataloguePresenter mPresenter;

    @Override
    protected BasePresenter presenter() {
        mPresenter = new PlayerCataloguePresenter(this);
        return mPresenter;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_player_catalogue;
    }

    @Override
    protected void initView() {
        mNavigationView.setTitle("音乐目录");
        mStateLayout.setState(BaseState.STATE_SUCCESS);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        FileDownloader
                .getImpl()
                .create("http://down.tech.sina.com.cn/download/d_load.php?d_id=49535&down_id=1&ip=42.81.45.159")
                .setWifiRequired(true)
                .setPath("music")
                .setListener(new FileDownloadLargeFileListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        LogUtils.e("pending");
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        LogUtils.e("progress" + soFarBytes + " -- " + totalBytes);
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        LogUtils.e("paused");
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        LogUtils.e("completed");
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        LogUtils.e("error");
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        LogUtils.e("warn");
                    }
                });
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(BaseEventBus event) {
        switch (event.code) {
            case EventBusConstant.EVENT_DOWN_MUSIC:
                Music.SongListBean songListBean = (Music.SongListBean) event.data;
                showToast("收到下载链接了 " + songListBean.title);
                break;
            default:
                break;
        }
    }
}
