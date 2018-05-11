package com.wsyzj.watchvideo.business.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.othershe.dutil.data.DownloadData;
import com.wsyzj.watchvideo.R;

import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/26
 *     desc   :
 * </pre>
 */
public class DownloadListAdapter extends BaseQuickAdapter<DownloadData, BaseViewHolder> {

    private Context mContext;

    public DownloadListAdapter(Context context, @Nullable List<DownloadData> data) {
        super(R.layout.item_download_list, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DownloadData item) {
//        Progress progress = item.progress;
//        Music.SongListBean songListBean = (Music.SongListBean) progress.extra1;
//
//        helper.setText(R.id.tv_name, songListBean.title + "(" + songListBean.artist_name + ")");
//        refreshProgress(progress, helper);
//        item.register(new MyDownloadListener(progress.tag, helper));
//
//        ImageView iv_state = helper.getView(R.id.iv_state);
//        iv_state.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (progress.status) {
//                    case Progress.PAUSE:
//                    case Progress.NONE:
//                    case Progress.ERROR:
//                        item.start();
//                        break;
//                    case Progress.LOADING:
//                        item.pause();
//                        break;
//                    case Progress.FINISH:
//                        item.restart();
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });

//        DownloadManger.getInstance(mContext).setOnDownloadCallback(item, new DownloadCallback() {
//            @Override
//            public void onStart(long l, long l1, float v) {
//                helper.setText(R.id.tv_speed, "准备中");
//            }
//
//            @Override
//            public void onProgress(long currentSize, long totalSize, float progress) {
//                helper.setText(R.id.tv_speed, "下载中");
//                helper.setText(R.id.tv_speed, byte2FitMemorySize(5000));
//
//                double percent = (double) currentSize / totalSize;
//                helper.setMax(R.id.pb_progress, 100);
//                helper.setProgress(R.id.pb_progress, (int) (percent * 100));
//            }
//
//            @Override
//            public void onPause() {
//                helper.setText(R.id.tv_speed, "暂停");
//            }
//
//            @Override
//            public void onCancel() {
//                helper.setText(R.id.tv_speed, "取消");
//            }
//
//            @Override
//            public void onFinish(File file) {
//                helper.setText(R.id.tv_speed, "完成");
//            }
//
//            @Override
//            public void onWait() {
//                helper.setText(R.id.tv_speed, "等待中");
//            }
//
//            @Override
//            public void onError(String s) {
//                helper.setText(R.id.tv_speed, "出错");
//            }
//        });
    }
//
//    /**
//     * 刷新进度
//     */
//    public void refreshProgress(Progress progress, BaseViewHolder helper) {
//        double percent = (double) progress.currentSize / progress.totalSize;
//        helper.setMax(R.id.pb_progress, 100);
//        helper.setProgress(R.id.pb_progress, (int) (percent * 100));
//        helper.setText(R.id.tv_current_total, byte2FitMemorySize(progress.currentSize) + "/" + byte2FitMemorySize(progress.totalSize));
//
//        ImageView iv_state = helper.getView(R.id.iv_state);
//        switch (progress.status) {
//            case Progress.NONE:
//                iv_state.setImageResource(R.drawable.ic_run_radio_download_song);
//                helper.setText(R.id.tv_speed, "下载");
//                break;
//            case Progress.WAITING:
//                iv_state.setImageResource(R.drawable.ic_run_radio_download_song);
//                helper.setText(R.id.tv_speed, "等待");
//                break;
//            case Progress.LOADING:
//                iv_state.setImageResource(R.drawable.minibar_btn_pause_normal);
//                helper.setText(R.id.tv_speed, byte2FitMemorySize(progress.speed));
//                break;
//            case Progress.PAUSE:
//                iv_state.setImageResource(R.drawable.ic_run_radio_download_song);
//                helper.setText(R.id.tv_speed, "暂停");
//                break;
//            case Progress.ERROR:
//                iv_state.setImageResource(R.drawable.ic_run_radio_download_song);
//                helper.setText(R.id.tv_speed, "出错");
//                break;
//            case Progress.FINISH:
//                iv_state.setImageResource(R.drawable.ic_run_radio_download_song);
//                helper.setText(R.id.tv_speed, "完成");
//                break;
//            default:
//                break;
//        }
//    }
//
//    @SuppressLint("DefaultLocale")
//    public static String byte2FitMemorySize(final long byteSize) {
//        if (byteSize < 0) {
//            return "shouldn't be less than zero!";
//        } else if (byteSize < MemoryConstants.KB) {
//            return String.format("%.2fB", (double) byteSize);
//        } else if (byteSize < MemoryConstants.MB) {
//            return String.format("%.2fKB", (double) byteSize / MemoryConstants.KB);
//        } else if (byteSize < MemoryConstants.GB) {
//            return String.format("%.2fMB", (double) byteSize / MemoryConstants.MB);
//        } else {
//            return String.format("%.2fGB", (double) byteSize / MemoryConstants.GB);
//        }
//    }
//
//    public void unRegister() {
//        Map<String, DownloadTask> taskMap = OkDownload.getInstance().getTaskMap();
//        for (DownloadTask task : taskMap.values()) {
//            task.unRegister(task.progress.tag);
//        }
//    }
//
//    private class MyDownloadListener extends DownloadListener {
//        private BaseViewHolder mHelper;
//
//        public MyDownloadListener(Object tag, BaseViewHolder helper) {
//            super(tag);
//            mHelper = helper;
//        }
//
//        @Override
//        public void onStart(Progress progress) {
////            LogUtils.e("onStart");
//        }
//
//        @Override
//        public void onProgress(Progress progress) {
//            if (mHelper != null) {
//                refreshProgress(progress, mHelper);
//            }
//        }
//
//        @Override
//        public void onError(Progress progress) {
//            Throwable exception = progress.exception;
//            if (exception != null) {
//                exception.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onFinish(File file, Progress progress) {
//            setNewData(OkDownload.restore(DownloadManager.getInstance().getAll()));
//        }
//
//        @Override
//        public void onRemove(Progress progress) {
//
//        }
//    }
}
