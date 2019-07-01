package com.lxh.baselibray.mvp;


import android.os.Bundle;

import com.lxh.baselibray.base.LazyFragment;
import com.lxh.baselibray.base.XFragment;

public abstract class MvpFragment<P extends BasePresent> extends XFragment {
    protected P mPresent;
    @Override
    public void initBeforView(Bundle savedInstanceState) {
        mPresent=createPresent();
        mPresent.attach((IBaseView) this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresent!=null){
            mPresent.detach((IBaseView) this);
        }
    }

    protected abstract P createPresent();


}
