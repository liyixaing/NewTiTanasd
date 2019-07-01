package com.lxh.baselibray;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.lxh.baselibray.util.ActivityManage;
import com.lxh.baselibray.util.LocalManageUtil;

public class BaseApplication extends Application {
    private static ActivityManage activityManage;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        activityManage=new ActivityManage();
        context = getApplicationContext();
        LocalManageUtil.setApplicationLanguage(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        //保存系统选择语言
        LocalManageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(LocalManageUtil.setLocal(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //保存系统选择语言
        LocalManageUtil.onConfigurationChanged(getApplicationContext());
    }



    public static ActivityManage getActivityManage() {
        return activityManage;
    }

    public static Context getContext(){
        return context;
    }
}

