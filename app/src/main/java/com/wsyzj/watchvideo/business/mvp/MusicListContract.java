package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BaseIModel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/05/02
 *     desc   :
 * </pre>
 */
public class MusicListContract {

    public interface View extends BaseIView {

    }

    public interface Model extends BaseIModel {

    }

    interface Presenter extends BaseIPresenter<View> {

    }
}
