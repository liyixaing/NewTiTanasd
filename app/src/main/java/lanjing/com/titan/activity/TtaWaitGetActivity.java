package lanjing.com.titan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lxh.baselibray.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.contact.TTAWalletContact;
import lanjing.com.titan.response.WalletDetailResponse;
import retrofit2.Response;

public class TtaWaitGetActivity extends MvpActivity<TTAWalletContact.WalletTTAPresent> implements TTAWalletContact.IWalletTTAView {

    @BindView(R.id.tv_asset_balance)
    TextView tv_asset_balance;//资产余额
    @BindView(R.id.tv_tixian_balance)
    TextView tv_tixian_balance;//可提现余额
    @BindView(R.id.tv_titan_trading_frozen)
    TextView tv_titan_trading_frozen;//冻结余额
    @BindView(R.id.tv_titan_screen)
    TextView tv_titan_screen;//赛选
    @BindView(R.id.withdraw_c_btn)
    TextView withdraw_c_btn;//提币
    @BindView(R.id.exchange_btn)
    TextView exchange_btn;//映射

    @Override
    protected TTAWalletContact.WalletTTAPresent createPresent() {
        return new TTAWalletContact.WalletTTAPresent();

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresent.walletDetail(context, "10");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tta_waitget;
    }

    @OnClick({R.id.tv_titan_screen, R.id.withdraw_c_btn, R.id.exchange_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titan_screen://筛选
                Toast.makeText(context, "赛选", Toast.LENGTH_SHORT).show();
                break;
            case R.id.withdraw_c_btn://提币
                Toast.makeText(context, "提币", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exchange_btn://映射
                Toast.makeText(context, "映射", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void getWalletDeatilResult(Response<WalletDetailResponse> response) {

    }

    @Override
    public void getDataFailed() {

    }
}
