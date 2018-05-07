package com.wsyzj.watchvideo.business.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadTask;
import com.lzy.okserver.task.XExecutor;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.DownloadListAdapter;
import com.wsyzj.watchvideo.business.mvp.DownloadListContract;
import com.wsyzj.watchvideo.business.mvp.DownloadListPresenter;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.utils.DrawableUtils;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;
import com.wsyzj.watchvideo.common.widget.BaseState;
import com.wsyzj.watchvideo.common.widget.BaseStateLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/05/02
 *     desc   : 下载目录
 * </pre>
 */
public class DownloadListActivity extends BaseActivity implements DownloadListContract.View, XExecutor.OnAllTaskEndListener {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    @BindView(R.id.tv_start_pause)
    TextView tv_start_pause;

    private DownloadListPresenter mPresenter;
    private DownloadListAdapter mDownloadListAdapter;
    private boolean isAllPause = true;
    private OkDownload mOkDownload;

    @Override
    protected BasePresenter presenter() {
        mPresenter = new DownloadListPresenter(this);
        return mPresenter;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_download_list;
    }

    @Override
    protected void initView() {
        mNavigationView.setTitle("下载列表");
        setPageState(BaseState.STATE_SUCCESS);
        pull_to_refresh.setEnableRefresh(false);
        pull_to_refresh.setEnableLoadMore(false);
        mOkDownload = OkDownload.getInstance();
        mOkDownload.addOnAllTaskEndListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.getDownloadList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDownloadListAdapter != null) {
            mDownloadListAdapter.unRegister();
        }
    }

    /**
     * 设置下载列表
     *
     * @param downloadList
     */
    @Override
    public void setDownloadList(List<DownloadTask> downloadList) {
        if (mDownloadListAdapter == null) {
            mDownloadListAdapter = new DownloadListAdapter(this, downloadList);
            pull_to_refresh.setLayoutManager(new LinearLayoutManager(this));
            pull_to_refresh.setAdapter(mDownloadListAdapter);
        } else {
            mDownloadListAdapter.setNewData(downloadList);
        }
    }

    /**
     * 根据列表显示对应状态
     *
     * @param downloadList
     */
    @Override
    public void setPageState(List<DownloadTask> downloadList) {
        BaseStateLayout baseStateLayout = pull_to_refresh.getBaseStateLalyout();
        if (downloadList.isEmpty()) {
            baseStateLayout.setEmptyText("还没有下载音乐哦");
            baseStateLayout.setPageState(BaseState.STATE_EMPTY);
        } else {
            baseStateLayout.setPageState(BaseState.STATE_SUCCESS);
        }
    }

    @OnClick(R.id.tv_start_pause)
    @Override
    public void bkOnClick(View view) {
        super.bkOnClick(view);
        switch (view.getId()) {
            case R.id.tv_start_pause:
                if (isAllPause) {
                    mOkDownload.startAll();
                    tv_start_pause.setText("全部暂停");
                    tv_start_pause.setCompoundDrawables(DrawableUtils.getPaddingDrawable(R.drawable.minibar_btn_pause_normal), null, null, null);
                } else {
                    mOkDownload.pauseAll();
                    tv_start_pause.setText("全部开始");
                    tv_start_pause.setCompoundDrawables(DrawableUtils.getPaddingDrawable(R.drawable.minibar_btn_play_normal), null, null, null);
                }
                isAllPause = !isAllPause;
                break;
            default:
                break;
        }
    }

    @Override
    public void onAllTaskEnd() {
        LogUtils.e("onAllTaskEnd");
    }
}



