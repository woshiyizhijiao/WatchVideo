package com.wsyzj.watchvideo.business.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.MusicAdapter;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.bean.Song;
import com.wsyzj.watchvideo.business.mvp.MusicContract;
import com.wsyzj.watchvideo.business.mvp.MusicPresenter;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.util.List;

import butterknife.BindView;

/**
 * @author 焦洋
 * @date 2017/12/21 11:00
 * @Description: $desc$
 */
public class MusicFragment extends BaseFragment implements MusicContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private MusicPresenter mPresenter;
    private MusicAdapter mMusicAdapter;

    @Override
    protected BaseIPresenter presenter() {
        mPresenter = new MusicPresenter(this);
        return mPresenter;
    }

    @Override
    public int contentView() {
        return R.layout.fragment_music;
    }

    @Override
    public void initView(View view) {
        pull_to_refresh.setOnRefreshListener(this);
        pull_to_refresh.setRequestLoadMoreListener(this);
    }

    @Override
    public void initData() {
        mPresenter.getMusicList(true);
    }

    /**
     * 设置音乐列表
     *
     * @param musicList
     */
    @Override
    public void setMusicList(List<Music.SongListBean> musicList) {
        if (mMusicAdapter == null) {
            mMusicAdapter = new MusicAdapter(R.layout.item_music, musicList);
            pull_to_refresh.setLayoutManager(new LinearLayoutManager(mActivity));
            pull_to_refresh.setAdapter(mMusicAdapter);
        } else {
            mMusicAdapter.setNewData(musicList);
        }
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        pull_to_refresh.setRefreshing(refreshing);
    }

    @Override
    public void setLoadMoreState(int totalCount) {
        pull_to_refresh.setLoadMoreState(totalCount);
    }

    /**
     * 播放歌曲
     *
     * @param song
     */
    @Override
    public void setSongInfo(Song song) {

    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mPresenter.getMusicList(true);
    }

    /**
     * 下载更多
     */
    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMusicList(false);
    }
}
