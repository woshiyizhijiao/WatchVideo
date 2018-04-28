package com.wsyzj.watchvideo.business.mvp;

import com.lzy.okgo.db.DownloadManager;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadTask;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/25
 *     desc   :
 * </pre>
 */
public class PlayerCataloguePresenter extends BasePresenter<PlayerCatalogueContract.View, PlayerCatalogueContract.Model> implements
        PlayerCatalogueContract.Presenter {

    private List<DownloadTask> mDownloadList;

    public PlayerCataloguePresenter(PlayerCatalogueContract.View view) {
        mView = view;
        mModel = new PlayerCatalogueModel();
    }

    @Override
    public void getDownloadList() {
        mDownloadList = OkDownload.restore(DownloadManager.getInstance().getAll());
        mView.setDownloadList(mDownloadList);
        mView.setPageState(mDownloadList);
    }
}
