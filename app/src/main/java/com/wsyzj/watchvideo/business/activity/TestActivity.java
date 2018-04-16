package com.wsyzj.watchvideo.business.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.wsyzj.watchvideo.R;
import com.wsyzj.watchvideo.common.base.BaseActivity;
import com.wsyzj.watchvideo.common.base.mvp.BasePresenter;
import com.wsyzj.watchvideo.common.widget.BaseState;

/**
 * @author 焦洋
 * @date 2018/2/26 11:39
 * @Description: $desc$
 */
public class TestActivity extends BaseActivity {

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


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

    }

    /**
     * 开启服务
     *
     * @param view
     */
    public void bindService(View view) {
        Intent intent = new Intent("com.wsyzj.android.offer.aidl.AIDLService");
        intent.setPackage("com.wsyzj.android.offer");
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }
}
