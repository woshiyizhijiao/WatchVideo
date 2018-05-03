package com.wsyzj.watchvideo.business.fragment;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.mvp.MusicListContract;
import com.wsyzj.watchvideo.business.mvp.MusicListPresenter;
import com.wsyzj.watchvideo.common.base.BaseFragment;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.utils.UiUtils;
import com.wsyzj.watchvideo.common.widget.BasePullToRefreshView;

import butterknife.BindView;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/05/02
 *     desc   :
 * </pre>
 */
public class MusicListFragment extends BaseFragment implements MusicListContract.View {

    @BindView(R.id.pull_to_refresh)
    BasePullToRefreshView pull_to_refresh;

    private MusicListPresenter mPresenter;

    @Override
    protected BaseIPresenter presenter() {
        mPresenter = new MusicListPresenter(this);
        return mPresenter;
    }

    @Override
    public int contentView() {
        return R.layout.fragment_music_list;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        setMusicList();
    }

    /**
     * 设置音乐榜单
     */
    private void setMusicList() {
        String[] online_music_list_title = UiUtils.getStringArray(R.array.online_music_list_title);
        String[] online_music_list_type = UiUtils.getStringArray(R.array.online_music_list_type);
        for (int i = 0; i < online_music_list_title.length; i++) {

        }
    }
}