package com.wsyzj.watchvideo.business.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.lzy.okserver.download.DownloadTask;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.PlayerCatalogueAdapter;
import com.wsyzj.watchvideo.business.mvp.PlayerCatalogueContract;
import com.wsyzj.watchvideo.business.mvp.PlayerCataloguePresenter;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;
import com.wsyzj.watchvideo.common.widget.BaseState;
import com.wsyzj.watchvideo.common.widget.BaseStateLayout;

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

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private PlayerCataloguePresenter mPresenter;
    private PlayerCatalogueAdapter mPlayerCatalogueAdapter;

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
        mStateLayout.setState(BaseState.STATE_SUCCESS);
        mNavigationView.setTitle("音乐目录");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.getDownloadList();
    }

    /**
     * 设置下载列表
     *
     * @param downloadList
     */
    @Override
    public void setDownloadList(List<DownloadTask> downloadList) {
        if (mPlayerCatalogueAdapter == null) {
            mPlayerCatalogueAdapter = new PlayerCatalogueAdapter(this, downloadList);
            pull_to_refresh.setLayoutManager(new LinearLayoutManager(this));
            pull_to_refresh.setAdapter(mPlayerCatalogueAdapter);
        } else {
            mPlayerCatalogueAdapter.setNewData(downloadList);
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
            baseStateLayout.setEmptyDataText("还没有下载音乐哦");
            baseStateLayout.setState(BaseState.STATE_EMPTY);
        } else {
            baseStateLayout.setState(BaseState.STATE_SUCCESS);
        }
    }
}


