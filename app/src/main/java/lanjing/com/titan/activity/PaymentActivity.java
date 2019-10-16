package lanjing.com.titan.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.contact.PaymentContact;
import lanjing.com.titan.response.AgreementResponse;
import retrofit2.Response;

/**
 * 支付界面
 * extends MvpActivity<ModifyPwdContact.ModifyPwdPresent> implements ModifyPwdContact.IModifyPwdView
 */
public class PaymentActivity extends MvpActivity<PaymentContact.PaymentPresent> implements PaymentContact.PaymentView {

    @BindView(R.id.iv_backretun)
    ImageView iv_backretun;//返回
    @BindView(R.id.tv_address)
    TextView tv_address;//文本输入的支付地址
    @BindView(R.id.tv_label)
    TextView tv_label;//地址标签
    @BindView(R.id.confirm_btn)
    TextView confirm_btn;



    @Override
    protected PaymentContact.PaymentPresent createPresent() {
        return new PaymentContact.PaymentPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        String rawResult = getIntent().getStringExtra("rawResult");
        Log.e("扫描内容", rawResult);
        String[] all = rawResult.split(",");
        tv_address.setText(all[1]);//支付地址赋值
        tv_label.setText(all[2]);//标签赋值

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }


    @OnClick({R.id.iv_backretun, R.id.confirm_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_backretun://返回
                finish();
                break;
            case R.id.confirm_btn:
                ToastUtils.showLongToast(context, "确认支付");
                break;
        }
    }

    @Override
    public void getAgreementResult(Response<AgreementResponse> response) {

    }

    @Override
    public void getDataFailed() {

    }
}
