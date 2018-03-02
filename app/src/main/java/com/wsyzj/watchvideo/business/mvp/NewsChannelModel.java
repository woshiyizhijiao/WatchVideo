package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.business.bean.NewsDetails;
import com.wsyzj.watchvideo.common.constant.Constant;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.http.BaseRxSchedulers;

import io.reactivex.Flowable;

/**
 * <pre>
 *     author : 焦洋
 *     e-mail : jiao35478729@163.com
 *     time   : 2018/03/02
 *     desc   :
 * </pre>
 */
public class NewsChannelModel implements NewsChannelContract.Model {

    @Override
    public Flowable<NewsDetails> getNewsList(String channelId, String channelName, int page) {
        return BaseRetrofit
                .jingDongNewsChannel()
                .getNewsList(Constant.JingDong.JINGDONG_KEY, channelId, channelName, "", page)
                .compose(BaseRxSchedulers.<NewsDetails>io_main());
    }
}
