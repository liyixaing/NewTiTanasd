package lanjing.com.titan.activity;

import android.os.Bundle;

import com.lxh.baselibray.mvp.MvpActivity;

import lanjing.com.titan.R;
import lanjing.com.titan.contact.TTAWalletContact;

public class TtaWaitGetActivity extends MvpActivity<TTAWalletContact.WalletTTAPresent> implements TTAWalletContact.IWalletTTAView {

    @Override
    protected TTAWalletContact.WalletTTAPresent createPresent() {
        return new TTAWalletContact.WalletTTAPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tta_waitget;
    }
}
