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
public class PlayerCataloguePresenter extends BasePresenter<PlayerCatalogueContract.View, PlayerCatalogueContract.Model> implements
        PlayerCatalogueContract.Presenter {

    private PlayerCatalogueContract.View mView;
    private PlayerCatalogueContract.Model mModel;

    public PlayerCataloguePresenter(PlayerCatalogueContract.View view) {
        mView = view;
        mModel = new PlayerCatalogueModel();
    }
}
