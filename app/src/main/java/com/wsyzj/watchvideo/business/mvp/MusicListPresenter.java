package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/05/02
 *     desc   :
 * </pre>
 */
public class MusicListPresenter extends BasePresenter<MusicListContract.View, MusicListContract.Model> implements MusicListContract.Presenter {

    private MusicListContract.View mView;
    private MusicListContract.Model mModel;

    public MusicListPresenter(MusicListContract.View view) {
        mView = view;
        mModel = new MusicListModel();
    }
}
