package com.wsyzj.watchvideo.common.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.business.bean.NewsTitle;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;
import com.wsyzj.watchvideo.common.tools.Constant;

/**
 * @author 焦洋
 * @date 2017/12/6 9:50
 * @Description: $desc$
 */
public class NewsActivityPresenter extends BasePresenter<NewsActivityContract.View, NewsActivityContract.Model>
        implements NewsActivityContract.Presenter {

    private NewsActivityContract.View mView;
    private NewsActivityContract.Model mModel;

    public NewsActivityPresenter(NewsActivityContract.View view) {
        mView = view;
        mModel = new NewsActivityModel();
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
                }
            }
        });
        mView.addDisposable(baseTSubscriber);
    }
}