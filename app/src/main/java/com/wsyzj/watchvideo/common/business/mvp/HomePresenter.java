package com.wsyzj.watchvideo.common.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.business.bean.Gank;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;

import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/12 14:05
 * @Description: $desc$
 */
public class HomePresenter extends BasePresenter<HomeContract.View, HomeContract.Model> implements HomeContract.Presenter {

    private HomeContract.View mView;
    private HomeContract.Model mModel;

    private String mType = "福利";
    private int mPageNumber = 10;
    private int mPage = 1;
    private List<Gank.ResultsBean> mGankData;
    private boolean isFirstLoad = true;

    public HomePresenter(HomeContract.View view) {
        mView = view;
        mModel = new HomeModel();
    }

    /**
     * gank数据
     */
    @Override
    public void getGankData(final boolean refreshing) {
        if (refreshing) {
            mPage = 1;
        } else {
            mPage++;
        }

        BaseTSubscriber<Gank> baseTSubscriber = mModel.getGankData(mType, mPageNumber, mPage)
                .subscribeWith(new BaseTSubscriber<Gank>() {
                    @Override
                    public void onSuccess(Object data) {
                        Gank gank = (Gank) data;
                        if (!gank.error) {
                            List<Gank.ResultsBean> results = gank.results;

                            if (refreshing) {
                                mGankData = results;
                            } else {
                                mGankData.addAll(results);
                            }

                            if (mPageNumber == results.size()) {
                                mView.setLoadMoreState(999);
                            } else {
                                mView.setLoadMoreState(0);
                            }

                            mView.setGankData(mGankData);
                        }
                        mView.setRefreshing(false);

                        if (isFirstLoad) {
                            mView.firstPageLoadFinish();
                            isFirstLoad = false;
                        }
                    }
                });
        mView.addDisposable(baseTSubscriber);
    }
}

