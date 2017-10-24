package com.wsyzj.watchvideo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.business.adapter.MusicAdapter;
import com.wsyzj.watchvideo.common.business.bean.Music;
import com.wsyzj.watchvideo.common.business.bean.Song;
import com.wsyzj.watchvideo.common.business.main.MainContract;
import com.wsyzj.watchvideo.common.business.main.MainPresenter;
import com.wsyzj.watchvideo.common.tools.LogUtils;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private MainPresenter mPresenter;
    private MusicAdapter mAdapter;
    private MediaPlayer mMediaPlayer;

    @Override
    protected BasePresenter presenter() {
        mPresenter = new MainPresenter(this);
        return mPresenter;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        pull_to_refresh.setOnRefreshListener(this);
        pull_to_refresh.setRequestLoadMoreListener(this);
    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {
        mPresenter.getMusicList(true);
    }

    /**
     * 设置音乐列表数据
     *
     * @param musicList
     */
    @Override
    public void setMusicList(List<Music.SongListBean> musicList) {
        if (mAdapter == null) {
            mAdapter = new MusicAdapter(this, R.layout.item_music_adapter, musicList);
            pull_to_refresh.setAdapter(mAdapter);
            pull_to_refresh.setLayoutManager(new LinearLayoutManager(this));
            mAdapter.setOnItemClickListener(this);
        } else {
            mAdapter.setNewData(musicList);
        }
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mPresenter.getMusicList(true);
    }


    @Override
    public void setRefreshing(boolean refreshing) {
        pull_to_refresh.setRefreshing(refreshing);
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMusicList(false);
    }

    /**
     * 加载更多的状态
     *
     * @param totalCount
     * @param currentCount
     */
    @Override
    public void setLoadMoreState(int totalCount, int currentCount) {
        pull_to_refresh.setLoadMoreState(totalCount, currentCount);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Music.SongListBean bean = mAdapter.getData().get(position);
        mPresenter.getMusicPlayPath(bean.song_id);
    }

    /**
     * 设置歌曲信息播放
     *
     * @param song
     */
    @Override
    public void setSongInfo(Song song) {
        LogUtils.e("歌曲信息为" + song.bitrate.toString());
        play(song.bitrate.file_link);
    }

    private void play(String path) {
        try {
            if (mMediaPlayer == null) {
                mMediaPlayer = new MediaPlayer();
            }
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
