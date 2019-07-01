package com.lxh.baselibray.mvp;

import android.os.Bundle;

import com.lxh.baselibray.base.LazyFragment;
import com.lxh.baselibray.base.XActivity;

/**
 * Created by Administrator on 2017/7/4.
 */

public abstract class MvpActivity<P extends BasePresent> extends XActivity {
    protected P mPresent;

    @Override
    public void initBeforView(Bundle savedInstanceState) {
        mPresent=createPresent();
        mPresent.attach((IBaseView) this);
    }

    protected abstract P createPresent();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresent.detach((IBaseView) this);
    }

}
