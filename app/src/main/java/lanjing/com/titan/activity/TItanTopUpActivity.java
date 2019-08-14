package lanjing.com.titan.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ToastUtils;
import com.lxh.baselibray.view.TitleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.WalletChargeContact;
import lanjing.com.titan.response.ChargeResponse;
import retrofit2.Response;

/**
 * TITAN  充币
 */
public class TItanTopUpActivity extends MvpActivity<WalletChargeContact.WalletChargePresent> implements WalletChargeContact.IWalletChargeView {

    @BindView(R.id.tv_copy_top_up_address)
    TextView tvCopyTopUpAddress;
    @BindView(R.id.tv_top_up_address)
    TextView tvTopUpAddress;
    @BindView(R.id.tv_copy_address_label)
    TextView tvCopyAddressLabel;
    @BindView(R.id.tv_address_label)
    TextView tvAddressLabel;
    @BindView(R.id.title_lay)
    TitleView title_lay;

    private ClipboardManager myClipboard;
    private ClipData myClip;
    String coin;

    @Override
    public void initData(Bundle savedInstanceState) {
        coin = getIntent().getStringExtra("coin");
        if (coin.equals("1")) {
            title_lay.setTitleText(getResources().getString(R.string.titan_top_up));
        }else {
            title_lay.setTitleText(getResources().getString(R.string.bar_top_up));
        }
        mPresent.walletCharge(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_titan_top_up;
    }

    @Override
    protected WalletChargeContact.WalletChargePresent createPresent() {
        return new WalletChargeContact.WalletChargePresent();
    }

    @Override
    public void getWalletChargeResult(Response<ChargeResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            tvTopUpAddress.setText(response.body().getAddress());
            tvAddressLabel.setText(response.body().getKeys());
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }

    @OnClick({R.id.tv_copy_top_up_address, R.id.tv_copy_address_label})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_copy_top_up_address:
                myClipboard = (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
                String address = tvTopUpAddress.getText().toString();
                myClip = ClipData.newPlainText("text", address);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShortToast(context, address + getResources().getString(R.string.over_copy));
                break;
            case R.id.tv_copy_address_label:
                myClipboard = (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
                String label = tvAddressLabel.getText().toString();
                myClip = ClipData.newPlainText("text", label);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShortToast(context, label + getResources().getString(R.string.over_copy));
                break;
        }
    }
}
