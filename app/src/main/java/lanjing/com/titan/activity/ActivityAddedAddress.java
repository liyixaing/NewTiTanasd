package lanjing.com.titan.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.CountDownTimerUtils;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.SaveOrUpdateContact;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.SendCodeRequest;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Response;

/**
 * 添加提币地址
 */
public class ActivityAddedAddress extends MvpActivity<SaveOrUpdateContact.saveOrUpdatePresent> implements SaveOrUpdateContact.saveOrUpdateView {

    @BindView(R.id.et_currency)
    EditText EtCurrency;//提币地址输入框
    @BindView(R.id.et_label)
    EditText EtLabel;//地址标签输入框
    @BindView(R.id.et_remarks)
    EditText EtRemarks;//备注输入框
    @BindView(R.id.tv_home)
    TextView TvHome;//手机号码显示位置
    @BindView(R.id.ed_code_pwd)
    EditText EdCodePwd;//验证码输入框
    @BindView(R.id.tv_getcode)
    TextView TvGetcode;//获取验证码按钮

    String Phone;

    @Override
    public void initData(Bundle savedInstanceState) {
        Phone = SPUtils.getString(Constant.PHONE, null, context);
        TvHome.setText("手机号\u3000" + Phone);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_added_address;
    }

    @OnClick({R.id.tv_confirm, R.id.tv_getcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                if (EtCurrency.getText().toString().equals("")) {
                    ToastUtils.showShortToast(context, "请输入提币地址");
                } else if (EtLabel.getText().toString().equals("")) {
                    ToastUtils.showShortToast(context, "请输入地址标签");
                } else if (EtRemarks.getText().toString().equals("")) {
                    ToastUtils.showShortToast(context, "请输入备注消息");
                } else if (EdCodePwd.getText().toString().equals("")) {
                    ToastUtils.showShortToast(context, "请输入验证码");
                } else {
                    mPresent.walletWithdraw(context, EtCurrency.getText().toString(), EtLabel.getText().toString(), EdCodePwd.getText().toString());
                }
                return;
            case R.id.tv_getcode://获取验证码按钮
                InitGetCode();
                CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(TvGetcode, 60000, 1000);
                countDownTimerUtils.start();
                return;
        }
    }

    //获取验证码
    public void InitGetCode() {
        String token = SPUtils.getString(Constant.TOKEN, "", context);
        ApiService service = ServiceGenerator.createService(ApiService.class);
        SendCodeRequest sendCodeRequest = new SendCodeRequest(Phone);
        service.sendCode(token, sendCodeRequest).enqueue(new NetCallBack<ResultDTO>() {
            @Override
            public void onSuccess(retrofit2.Call<ResultDTO> call, retrofit2.Response<ResultDTO> response) {
                Log.e("xiaoqaing", String.valueOf(response.body().getCode()));
                Log.e("xiaoqaing", response.body().getMsg());
                if (response.body().getCode() == 200) {
                } else {
                    ToastUtils.showShortToast(context, response.body().getMsg());
                }
            }

            @Override
            public void onFailed() {
            }
        });

    }

    @Override
    protected SaveOrUpdateContact.saveOrUpdatePresent createPresent() {
        return new SaveOrUpdateContact.saveOrUpdatePresent();
    }

    @Override
    public void getSaveorupdeat(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            finish();
        } else if (response.body().getMsg().equals("-10")) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }


    }


    //提币返回事件
    @Override
    public void getWalletWithdrawResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, response.body().getMsg());
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
}
