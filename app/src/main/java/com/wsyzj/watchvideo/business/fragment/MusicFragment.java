package com.wsyzj.watchvideo.business.fragment;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.adapter.MusicAdapter;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.mvp.MusicContract;
import com.wsyzj.watchvideo.business.mvp.MusicPresenter;
import com.wsyzj.watchvideo.business.service.PlayerManager;
import com.wsyzj.watchvideo.business.service.PlayerService;
import com.wsyzj.watchvideo.business.widget.PlayerControllerView;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/19
 *     desc   :
 * </pre>
 */
public class MusicFragment extends BaseFragment implements MusicContract.View, OnRefreshLoadMoreListener {

    private final static String[] REQUEST_PERMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    @BindView(R.id.player_controller)
    PlayerControllerView player_controller;

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PlayerManager.getInstance().removeOnPlayerEventListener(player_controller);
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
            PlayerManager.getInstance().addOnPlayerEventListener(player_controller);
            player_controller.setPlaySong(PlayerManager.getInstance().getPlaySong());
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(position);
                } else {
                    mPresenter.downMusic(position);
                }
            }
        });
        builder.show();
    }

    /**
     * 获取用户权限
     */
    public void requestPermissions(int position) {
        new RxPermissions(mActivity).request(REQUEST_PERMISSION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            mPresenter.downMusic(position);
                        } else {
                            showToast("");
                        }
                    }
                });
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

    /**
     * 播放音乐
     *
     * @param songListBean
     */
    @Override
    public void addAndPlay(Music.SongListBean songListBean) {
        PlayerManager.getInstance().addAndPlay(songListBean);
    }
}
