package com.wsyzj.watchvideo.business.mvp;

import com.wsyzj.watchvideo.common.base.mvp.BaseIModel;
import com.wsyzj.watchvideo.common.base.mvp.BaseIPresenter;
import com.wsyzj.watchvideo.common.base.mvp.BaseIView;

/**
 * @author 焦洋
 * @date 2017/12/19 15:00
 * @Description: $desc$
 */
public class PreviewLargeContract {

    public interface View extends BaseIView {

    }

    public interface Model extends BaseIModel {

    }

    interface Presenter extends BaseIPresenter<View> {

    }
}
