package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/25
 *     desc   :
 * </pre>
 */
public class DownloadListPresenter extends BasePresenter<DownloadListContract.View, DownloadListContract.Model> implements
        DownloadListContract.Presenter {

//    private List<DownloadTask> mDownloadList;

    public DownloadListPresenter(DownloadListContract.View view) {
        mView = view;
        mModel = new DownloadListModel();
    }

//    @Override
//    public void getDownloadList() {
//        mDownloadList = OkDownload.restore(DownloadManager.getInstance().getAll());
//        mView.setDownloadList(mDownloadList);
//        mView.setPageState(mDownloadList);
//    }
}
