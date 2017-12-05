package com.wsyzj.watchvideo.common.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.business.bean.WeChatChoiceness;
import com.wsyzj.watchvideo.common.http.BaseTSubscriber;
import com.wsyzj.watchvideo.common.tools.Constants;

import java.util.List;

/**
 * @author 焦洋
 * @date 2017/12/5 15:10
 */
public class WeChatChoicenessPresenter extends BasePresenter<WeChatChoicenessContract.View, WeChatChoicenessContract.Model> implements WeChatChoicenessContract.Presenter {

    private WeChatChoicenessContract.View mView;
    private WeChatChoicenessContract.Model mModel;
    private int mPage = 1;
    private int mPageNum = 10;
    private List<WeChatChoiceness.ResultBean.ListBean> mWeChatChs;

    public WeChatChoicenessPresenter(WeChatChoicenessContract.View view) {
        mView = view;
        mModel = new WeChatChoicenessModel();
    }

    /**
     * 获取微信精选
     */
    @Override
    public void getWeChatChoiceness(final boolean refreshing) {
        if (refreshing) {
            mPage = 1;
        } else {
            mPage++;
        }

        BaseTSubscriber<WeChatChoiceness> baseTSubscriber = mModel.getWeChatChoiceness(mPage, mPageNum)
                .subscribeWith(new BaseTSubscriber<WeChatChoiceness>() {
                    @Override
                    public void onSuccess(Object data) {
                        WeChatChoiceness chatChoiceness = (WeChatChoiceness) data;
                        if (chatChoiceness.error_code == Constants.WECHAT_CHOICENESS_ERROR_CODE) {
                            WeChatChoiceness.ResultBean result = chatChoiceness.result;
                            List<WeChatChoiceness.ResultBean.ListBean> list = result.list;

                            if (refreshing) {
                                mWeChatChs = chatChoiceness.result.list;
                            } else {
                                mWeChatChs.addAll(list);
                            }

                            mView.setWeChatChoicenessList(mWeChatChs);
                            mView.setLoadMoreState(result.totalPage);
                            mView.setRefreshing(false);
                        } else {
                            mView.showToast(chatChoiceness.reason);
                        }
                    }
                });
        mView.addDisposable(baseTSubscriber);
    }
}
