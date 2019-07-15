package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lxh.baselibray.event.BusFactory;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.WalletNameContact;
import lanjing.com.titan.eventbus.EventImpl;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Response;

/**
 * 设置钱包
 */
public class SetWalletActivity extends MvpActivity<WalletNameContact.WalletNamePresent> implements WalletNameContact.IWalletNameView {


    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_wallet_address)
    TextView tvWalletAddress;
    @BindView(R.id.tv_wallet_label)
    TextView tvWalletLabel;
    @BindView(R.id.ed_wallet_name)
    EditText edWalletName;
    @BindView(R.id.submit_btn)
    TextView submitBtn;

    @Override
    public void initData(Bundle savedInstanceState) {
        tvUserName.setText(getIntent().getStringExtra("userName"));
        tvWalletAddress.setText(getIntent().getStringExtra("address"));
        tvWalletLabel.setText(getIntent().getStringExtra("addressLabel"));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_wallet;
    }

    @Override
    protected WalletNameContact.WalletNamePresent createPresent() {
        return new WalletNameContact.WalletNamePresent();
    }

    @Override
    public void getWalletNameResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.modify_wallet_success));
            BusFactory.getBus().post(new EventImpl.UpdateWalletNameEvent());
            finish();
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
            dismissLoadingDialog();

        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
            dismissLoadingDialog();
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
        dismissLoadingDialog();
    }


    @OnClick({R.id.ed_wallet_name, R.id.submit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_wallet_name:
                edWalletName.setCursorVisible(true);//光标显示
                break;
            case R.id.submit_btn:
                String changeName = edWalletName.getText().toString().trim();
                if (ObjectUtils.isEmpty(changeName)) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.please_ed_wallet_name));
                    return;
                }
                mPresent.walletName(context, changeName);
                break;
        }
    }
}
