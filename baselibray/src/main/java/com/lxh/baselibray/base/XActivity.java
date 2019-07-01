package com.lxh.baselibray.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jaeger.library.StatusBarUtil;
import com.lxh.baselibray.BaseApplication;
import com.lxh.baselibray.R;
import com.lxh.baselibray.event.BusFactory;
import com.lxh.baselibray.kit.KnifeKit;
import com.lxh.baselibray.util.LocalManageUtil;

import butterknife.Unbinder;


public abstract class XActivity extends AppCompatActivity implements UiCallback {
    protected Activity context;
    private Unbinder unbinder;
    private Dialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforView(savedInstanceState);
        this.context = this;

        BaseApplication.getActivityManage().addActivity(this);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            unbinder = KnifeKit.bind(this);
        }
     //   StatusBarUtil.setTranslucent(this);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.statusbar_color));
        initData(savedInstanceState);
    }

    @Override
    public void initBeforView(Bundle savedInstanceState) {
        //空实现
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (useEventBus()) {
            BusFactory.getBus().register(this);
        }
    }


    @Override
    public boolean useEventBus() {
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusFactory.getBus().unregister(this);
        if (unbinder!=null){
            unbinder.unbind();
        }

    }

    public void showLoadingDialog(){
        if (mDialog == null) {
            mDialog = new Dialog(context, R.style.loading_dialog);
        }
        mDialog.setContentView(R.layout.dialog_loading);
        mDialog.setCancelable(true);
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.show();
    }

    public void dismissLoadingDialog(){
        if (mDialog != null) {
            mDialog.dismiss();
        }
        mDialog = null;
    }







    //语言
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalManageUtil.setLocal(newBase));
    }
}
