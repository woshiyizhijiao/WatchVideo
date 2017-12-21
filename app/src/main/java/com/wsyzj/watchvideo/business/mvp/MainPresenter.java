package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.business.bean.NewsTitle;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;
import com.wsyzj.watchvideo.common.tools.Constant;

/**
 * @author 焦洋
 * @date 2017/12/6 9:50
 * @Description: $desc$
 */
public class MainPresenter extends BasePresenter<MainContract.View, MainContract.Model>
        implements MainContract.Presenter {

    private MainContract.View mView;
    private MainContract.Model mModel;

    public MainPresenter(MainContract.View view) {
        mView = view;
        mModel = new MainModel();
    }

    /**
     * 获取新闻的标题
     */
    @Override
    public void getNewsTitle() {
        BaseTSubscriber<NewsTitle> baseTSubscriber = mModel.getNewsTitle().subscribeWith(new BaseTSubscriber<NewsTitle>() {
            @Override
            public void onSuccess(Object data) {
                NewsTitle newsTitle = (NewsTitle) data;
                if (newsTitle.code == Constant.JingDong.JINGDONG_CODE) {
                    NewsTitle.ResultBean result = newsTitle.result;
                    if (result.status == Constant.JingDong.JINGDONG_STATUS) {
                        mView.setNewsTitle(result.result);
                    }
                } else {
                    mView.setNewsTitle(null);
                }
            }
        });
        mView.addDisposable(baseTSubscriber);
    }
}