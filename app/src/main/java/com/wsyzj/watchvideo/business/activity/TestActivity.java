package com.wsyzj.watchvideo.business.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.widget.StateLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 焦洋
 * @date 2018/2/26 11:39
 * @Description: $desc$
 */
public class TestActivity extends BaseActivity {

    @BindView(R.id.btn1)
    Button btn1;

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
        setPageState(StateLayout.STATE_SUCCESS);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("onDestroy");
    }

    @Override
    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    protected void testOnClick(View view) {
        super.testOnClick(view);
        LogUtils.e("Test : bkOnClick ==" + view.getId());
        switch (view.getId()) {
            case R.id.btn1:
                mNavigationView.setNavigationIcon(R.drawable.default_cover);
                break;
            case R.id.btn2:
                mNavigationView.setNavigationText("左边文字");
                break;
            case R.id.btn3:
                mNavigationView.setNegativeText("右边文本");
                break;
            case R.id.btn4:
                mNavigationView.setNegativeImageResource(R.drawable.default_cover);
                break;
            default:
                break;
        }
    }
}
