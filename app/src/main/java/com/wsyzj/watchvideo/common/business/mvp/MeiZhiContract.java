package com.wsyzj.watchvideo.common.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.IModel;
import com.wsyzj.watchvideo.common.base.mvp.IPresenter;
import com.wsyzj.watchvideo.common.base.mvp.IView;

/**
 * @author 焦洋
 * @date 2017/12/1 10:42
 */
public class MeiZhiContract {

    public interface View extends IView {

    }

    public interface Model extends IModel {

    }

    interface Presenter extends IPresenter<View> {

    }

}
