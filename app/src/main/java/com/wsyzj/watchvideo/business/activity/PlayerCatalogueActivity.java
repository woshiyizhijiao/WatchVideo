package com.wsyzj.watchvideo.business.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.blankj.utilcode.util.LogUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadQueueSet;
import com.liulishuo.filedownloader.FileDownloader;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.PlayerCatalogueAdapter;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.mvp.PlayerCatalogueContract;
import com.wsyzj.watchvideo.business.mvp.PlayerCataloguePresenter;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.BaseEventBus;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.constant.EventBusConstant;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;
import com.wsyzj.watchvideo.common.widget.BaseState;

import java.util.ArrayList;
import java.util.List;

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

    private String[] URLS = {
            "http://113.207.16.84/dd.myapp.com/16891/2E53C25B6BC55D3330AB85A1B7B57485.apk?mkey=5630b43973f537cf&f=cf87&fsname=com.htshuo.htsg_3.0.1_49.apk&asr=02f1&p=.apk",
            "http://7xjww9.com1.z0.glb.clouddn.com/Hopetoun_falls.jpg",
            "http://dg.101.hk/1.rar",
            "http://180.153.105.144/dd.myapp.com/16891/E2F3DEBB12A049ED921C6257C5E9FB11.apk",
            "http://mirror.internode.on.net/pub/test/10meg.test4"};

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private PlayerCataloguePresenter mPresenter;
    private PlayerCatalogueAdapter mCatalogueAdapter;
    private List<TestFileDown> mTestFileDowns = new ArrayList<>();
    private List<BaseDownloadTask> mDownloadTasks = new ArrayList<>();

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
        test();
//        test1();
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FileDownloader.getImpl().pauseAll();
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

    private void test1() {
        FileDownloader.getImpl().create("http://7xjww9.com1.z0.glb.clouddn.com/Hopetoun_falls.jpg")
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        LogUtils.e("pending " + totalBytes + "/" + soFarBytes);
                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                        LogUtils.e("connected " + totalBytes + "/" + soFarBytes);
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        LogUtils.e("progress " + totalBytes + "/" + soFarBytes);
                    }

                    @Override
                    protected void blockComplete(BaseDownloadTask task) {
                        LogUtils.e("blockComplete ");
                    }

                    @Override
                    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
                        LogUtils.e("retry ");
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        LogUtils.e("completed ");
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        LogUtils.e("paused " + totalBytes + "/" + soFarBytes);
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        e.printStackTrace();
                        LogUtils.e("error " + e.getMessage());
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        LogUtils.e("warn ");
                    }
                }).start();
    }

    private void test() {
        FileDownloadListener queueTarget = new FileDownloadListener() {

            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                LogUtils.e("pending  : " + task.getId() + " -- " + totalBytes + "/" + soFarBytes);
            }

            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                LogUtils.e(Thread.currentThread().getName() + "progress  : " + task.getId() + " -- " + totalBytes + "/" + soFarBytes);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mCatalogueAdapter != null) {
                            int position = (int) task.getTag();
                            mTestFileDowns.get(position).soFarBytes = soFarBytes;
                            mTestFileDowns.get(position).totalBytes = totalBytes;
                            mCatalogueAdapter.setNewData(mTestFileDowns);
                        }
                    }
                });
            }

            @Override
            protected void completed(BaseDownloadTask task) {
                LogUtils.e("completed  : " + task.getId());
            }

            @Override
            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                LogUtils.e("paused  : " + task.getId() + "progress  : " + task.getId() + " -- " + totalBytes + "/" + soFarBytes);
            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {
                LogUtils.e("error  : " + task.getId() + e.getMessage());
            }

            @Override
            protected void warn(BaseDownloadTask task) {
                LogUtils.e("warn  : " + task.getId());
            }
        };
        FileDownloadQueueSet queueSet = new FileDownloadQueueSet(queueTarget);

        for (int i = 0; i < URLS.length; i++) {
            BaseDownloadTask task = FileDownloader.getImpl().create(URLS[i]).setTag(i);
            TestFileDown testFileDown = new TestFileDown(task);
            mDownloadTasks.add(task);
            mTestFileDowns.add(testFileDown);
        }

        queueSet.downloadTogether(mDownloadTasks);
//        queueSet.disableCallbackProgressTimes();
//        queueSet.setAutoRetryTimes(1);
        queueSet.start();

        mCatalogueAdapter = new PlayerCatalogueAdapter(mTestFileDowns);
        pull_to_refresh.setLayoutManager(new LinearLayoutManager(this));
        pull_to_refresh.setAdapter(mCatalogueAdapter);
    }

    public class TestFileDown {
        public int soFarBytes;
        public int totalBytes;
        public BaseDownloadTask baseDownloadTask;

        public TestFileDown(BaseDownloadTask baseDownloadTask) {
            this.baseDownloadTask = baseDownloadTask;
        }
    }
}
