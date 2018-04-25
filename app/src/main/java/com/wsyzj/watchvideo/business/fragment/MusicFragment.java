package com.wsyzj.watchvideo.business.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.MusicAdapter;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.listener.OnPlayerEventListener;
import com.wsyzj.watchvideo.business.mvp.MusicContract;
import com.wsyzj.watchvideo.business.mvp.MusicPresenter;
import com.wsyzj.watchvideo.business.service.PlayerManager;
import com.wsyzj.watchvideo.business.service.PlayerService;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.http.ImageLoader;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/19
 *     desc   :
 * </pre>
 */
public class MusicFragment extends BaseFragment implements MusicContract.View, OnRefreshLoadMoreListener, OnPlayerEventListener {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    @BindView(R.id.iv_conver)
    ImageView iv_conver;

    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.tv_desc)
    TextView tv_desc;

    @BindView(R.id.iv_play)
    ImageView iv_play;

    @BindView(R.id.pb_song)
    ProgressBar pb_song;

    private MusicPresenter mPresenter;
    private MusicAdapter mMusicAdapter;
    private PlayerService mPlayerService;

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
    public void initView() {
        pull_to_refresh.setOnRefreshLoadMoreListener(this);
    }

    @Override
    public void initData() {
        bindPlayerService();
        mPresenter.getMusicList(true);
    }

    /**
     * 初始化音乐服务
     */
    public void bindPlayerService() {
        Intent intent = new Intent(mActivity, PlayerService.class);
        mActivity.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlayerService.PlayerBinder playerBinder = (PlayerService.PlayerBinder) service;
            mPlayerService = playerBinder.getService();
            setPlaySong(PlayerManager.get().getPlaySong());
            PlayerManager.get().addOnPlayerEventListener(MusicFragment.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mConnection != null) {
            mActivity.unbindService(mConnection);
        }
    }

    /**
     * 设置音乐列表
     *
     * @param musicList
     */
    @Override
    public void setMusicList(List<Music.SongListBean> musicList) {
        if (mMusicAdapter == null) {
            mMusicAdapter = new MusicAdapter(mActivity, musicList);
            pull_to_refresh.setLayoutManager(new LinearLayoutManager(mActivity));
            pull_to_refresh.setAdapter(mMusicAdapter);
        } else {
            mMusicAdapter.setNewData(musicList);
        }

        mMusicAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.getMusicPlayPath(position);
            }
        });

        mMusicAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showMoreAction(position);
            }

        });
    }

    private static final String[] mItems = {"下载"};

    /**
     * 设置更多按钮
     */
    private void showMoreAction(int position) {
        Music.SongListBean songListBean = mPresenter.mMusicList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle(songListBean.title);
        builder.setItems(mItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    /**
     * 下拉刷新完成
     */
    @Override
    public void finishRefresh() {
        pull_to_refresh.finishRefresh();
    }

    /**
     * 设置加载更多状态
     *
     * @param listSize
     * @param pageCount
     */
    @Override
    public void setLoadMoreByPageCount(int listSize, int pageCount) {
        pull_to_refresh.setLoadMoreByPageCount(listSize, pageCount);
    }

    /**
     * 加载更多
     *
     * @param refreshLayout
     */
    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        mPresenter.getMusicList(false);
    }

    /**
     * 刷新列表
     *
     * @param refreshLayout
     */
    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        mPresenter.getMusicList(true);
    }

    @OnClick({R.id.iv_play, R.id.iv_play_next, R.id.iv_catalogue})
    public void bkOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_play:
                play();
                break;
            case R.id.iv_play_next:
                PlayerManager.get().next();
                break;
            case R.id.iv_catalogue:
                break;
            default:
                break;
        }
    }

    /**
     * 播放音乐
     *
     * @param songListBean
     */
    @Override
    public void addAndPlay(Music.SongListBean songListBean) {
        PlayerManager.get().addAndPlay(songListBean);
    }

    /**
     * 设置播放音乐的信息
     *
     * @param songListBean
     */
    @Override
    public void setPlaySong(Music.SongListBean songListBean) {
        if (songListBean == null) {
            return;
        }
        iv_play.setImageResource(R.drawable.ic_play_bar_btn_play);
        tv_name.setText(songListBean.title);
        tv_desc.setText(songListBean.artist_name);
        ImageLoader.with(mActivity, songListBean.pic_big, R.drawable.default_cover, R.drawable.default_cover, iv_conver);
    }

    /**
     * 播放或者暂停
     */
    private void play() {
        PlayerManager.get().playPause();
    }

    /**
     * @param songListBean
     */
    @Override
    public void onPlayerChanged(Music.SongListBean songListBean) {
        setPlaySong(songListBean);
    }

    @Override
    public void onPlayerStart() {
        iv_play.setImageResource(R.drawable.ic_play_bar_btn_pause);
    }

    @Override
    public void onPlayerPasue() {
        iv_play.setImageResource(R.drawable.ic_play_bar_btn_play);
    }

    @Override
    public void onPlayerProgress(int progress) {
        pb_song.setMax(PlayerManager.get().getDuration());
        pb_song.setProgress(progress);
    }
}
