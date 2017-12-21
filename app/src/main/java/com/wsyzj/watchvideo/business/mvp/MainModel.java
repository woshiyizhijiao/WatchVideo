package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.business.bean.NewsTitle;
import com.wsyzj.watchvideo.common.http.BaseRetrofit;
import com.wsyzj.watchvideo.common.http.BaseRxSchedulers;
import com.wsyzj.watchvideo.common.tools.Constant;

import io.reactivex.Flowable;

/**
 * @author 焦洋
 * @date 2017/12/6 9:51
 * @Description: $desc$
 */
public class MainModel implements MainContract.Model {

    @Override
    public Flowable<NewsTitle> getNewsTitle() {
        return BaseRetrofit
                .jingDong()
                .getNewsTitle(Constant.JingDong.JINGDONG_KEY)
                .compose(BaseRxSchedulers.<NewsTitle>io_main());
    }
}
