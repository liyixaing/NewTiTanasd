package lanjing.com.titan.activity;

import android.os.Bundle;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ToastUtils;

import lanjing.com.titan.R;
import lanjing.com.titan.contact.PaymentCode;
import lanjing.com.titan.response.FriendListResponse;
import retrofit2.Response;

/**
 * 收款码界面
 */
public class PaymentCodeActivity extends MvpActivity<PaymentCode.FriendListPresent> implements PaymentCode.IFriendListView {


    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment_code;
    }

    @Override
    public void getFriendListResult(Response<FriendListResponse> response) {

    }

    @Override
    public void getDataFailed() {

    }

    @Override
    protected PaymentCode.FriendListPresent createPresent() {
        return new PaymentCode.FriendListPresent();
    }
}
