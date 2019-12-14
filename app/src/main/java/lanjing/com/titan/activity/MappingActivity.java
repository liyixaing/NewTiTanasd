package lanjing.com.titan.activity;

import android.os.Bundle;

import com.lxh.baselibray.mvp.MvpActivity;

import lanjing.com.titan.R;
import lanjing.com.titan.contact.MappingContact;

/**
 * 映射界面
 */
public class MappingActivity extends MvpActivity<MappingContact.MappingPresent> implements MappingContact.IWalletDetailView {

    @Override
    protected MappingContact.MappingPresent createPresent() {
        return new MappingContact.MappingPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }


    //添加布局
    @Override
    public int getLayoutId() {
        return R.layout.activity_mapping;
    }


    //接口返回错误信息
    @Override
    public void getDataFailed() {

    }
}
