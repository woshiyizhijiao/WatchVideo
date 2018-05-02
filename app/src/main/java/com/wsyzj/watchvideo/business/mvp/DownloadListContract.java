package com.wsyzj.watchvideo.business.mvp;

import com.lzy.okserver.download.DownloadTask;
import com.wsyzj.watchvideo.common.base.mvp.BaseIModel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;

import java.util.List;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/04/25
 *     desc   :
 * </pre>
 */
public class DownloadListContract {
    public interface View extends BaseIView {
        void setDownloadList(List<DownloadTask> downloadList);

        void setPageState(List<DownloadTask> downloadList);
    }

    public interface Model extends BaseIModel {

    }

    interface Presenter extends BaseIPresenter<View> {
        void getDownloadList();
    }
}
