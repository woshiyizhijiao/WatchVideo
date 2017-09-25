package com.wsyzj.watchvideo.business.main;

import com.wsyzj.watchvideo.base.mvp.IModel;
import com.wsyzj.watchvideo.base.mvp.IPresenter;
import com.wsyzj.watchvideo.base.mvp.IView;


/**
 * @author: wsyzj
 * @date: 2017-09-17 17:18
 * @comment: //TODO
 */
public class MainContract {

    public interface View extends IView {
        void getPermissions();

        void showMissingPermissionDialog();
    }

    interface Model extends IModel {

    }

    interface Presenter extends IPresenter<View> {

    }
}
