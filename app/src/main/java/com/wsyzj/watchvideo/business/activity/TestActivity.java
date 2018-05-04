package com.wsyzj.watchvideo.business.activity;

import android.os.Bundle;
import android.view.View;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.utils.UiUtils;
import com.wsyzj.watchvideo.common.widget.BaseState;

import butterknife.OnClick;

/**
 * @author 焦洋
 * @date 2018/2/26 11:39
 * @Description: $desc$
 */
public class TestActivity extends BaseActivity {

    @Override
    protected BasePresenter presenter() {
        return null;
    }

    @Override
    protected int contentView() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        setPageState(BaseState.STATE_SUCCESS);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        UiUtils.showSoftInput(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    @Override
    public void bkOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                mNavigationView.setNavigationIcon(R.drawable.default_cover);
                break;
            case R.id.btn2:
                mNavigationView.setNavigationText("左边");
                break;
            case R.id.btn3:
                mNavigationView.setNegativeText("右边");
                break;
            case R.id.btn4:
                mNavigationView.setNegativeImageResource(R.drawable.default_cover);
                break;
        }
    }

}
