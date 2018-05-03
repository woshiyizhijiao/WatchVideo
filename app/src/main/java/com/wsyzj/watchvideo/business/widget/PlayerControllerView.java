package com.wsyzj.watchvideo.business.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.business.bean.Music;
import com.wsyzj.watchvideo.business.listener.OnPlayerEventListener;
import com.wsyzj.watchvideo.business.service.PlayerManager;
import com.wsyzj.watchvideo.common.http.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/05/02
 *     desc   : 封装音乐播放的控制器，
 * </pre>
 */
public class PlayerControllerView extends LinearLayout implements OnPlayerEventListener {

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

    private Context mContext;

    public PlayerControllerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PlayerControllerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayerControllerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.widget_player_controller, null);
        ButterKnife.bind(this, view);

        addView(view);
    }

    @OnClick({R.id.iv_play, R.id.iv_play_next, R.id.iv_catalogue})
    public void bkOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_play:
                PlayerManager.getInstance().playPause();
                break;
            case R.id.iv_play_next:
                PlayerManager.getInstance().next();
                break;
            case R.id.iv_catalogue:
                break;
            default:
                break;
        }
    }

    /**
     * 添加并且播放
     *
     * @param songListBean
     */
    public void addAndPlay(Music.SongListBean songListBean) {
        PlayerManager.getInstance().addAndPlay(songListBean);
    }

    /**
     * 设置播放音乐的信息
     *
     * @param songListBean
     */
    public void setPlaySong(Music.SongListBean songListBean) {
        if (songListBean == null) {
            return;
        }
        iv_play.setImageResource(R.drawable.ic_play_bar_btn_play);
        tv_name.setText(songListBean.title);
        tv_desc.setText(songListBean.artist_name);
        ImageLoader.with(mContext, songListBean.pic_big, R.drawable.default_cover, R.drawable.default_cover, iv_conver);
    }

    /**
     * 切换歌曲
     *
     * @param songListBean
     */
    @Override
    public void onPlayerChanged(Music.SongListBean songListBean) {
        setPlaySong(songListBean);
    }

    /**
     * 开始
     */
    @Override
    public void onPlayerStart() {
        iv_play.setImageResource(R.drawable.ic_play_bar_btn_pause);
    }

    /**
     * 暂停
     */
    @Override
    public void onPlayerPasue() {
        iv_play.setImageResource(R.drawable.ic_play_bar_btn_play);
    }

    /**
     * 进度变化
     *
     * @param progress
     */
    @Override
    public void onPlayerProgress(int progress) {
        pb_song.setMax(PlayerManager.getInstance().getDuration());
        pb_song.setProgress(progress);
    }
}
