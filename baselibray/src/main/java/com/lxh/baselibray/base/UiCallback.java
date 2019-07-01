package com.lxh.baselibray.base;

import android.os.Bundle;

/**
 * Created by wanglei on 2016/12/1.
 */

public interface UiCallback {
    void  initBeforView(Bundle savedInstanceState);

    void initData(Bundle savedInstanceState);

    int getLayoutId();

    boolean useEventBus();
}
