package lanjing.com.titan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.RegexUtils;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.ModifyPwdContact;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.util.CountDownTimerUtils;
import retrofit2.Response;

/**
 * 修改交易密码
 */
public class ModifyDealPwdActivity extends MvpActivity<ModifyPwdContact.ModifyPwdPresent> implements ModifyPwdContact.IModifyPwdView {

    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_verification_code)
    EditText edVerificationCode;
    @BindView(R.id.agin_verification_code)
    TextView aginVerificationCode;
    @BindView(R.id.ed_confirm_new_pwd)
    EditText edConfirmNewPwd;
    @BindView(R.id.confirm_btn)
    TextView confirmBtn;

    String phone;

    @Override
    public void initData(Bundle savedInstanceState) {
        phone = SPUtils.getString(Constant.PHONE, null, context);
        edPhone.setText(phone);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_deal_pwd;
    }

    @Override
    protected ModifyPwdContact.ModifyPwdPresent createPresent() {
        return new ModifyPwdContact.ModifyPwdPresent();
    }

    @Override
    public void getModifyPwdResult(Response<ResultDTO> response) {
        dismissLoadingDialog();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.the_transaction_password_was_changed_successfully));
            finish();
        } else if (response.body().getMsg().equals("-10")) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
            dismissLoadingDialog();
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getSendCodeResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.verification_code_sent));
            //验证码倒计时
            CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(aginVerificationCode, 60000, 1000);
            countDownTimerUtils.start();
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
        dismissLoadingDialog();
    }


    @OnClick({R.id.ed_phone, R.id.agin_verification_code, R.id.confirm_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_phone:
                edPhone.setCursorVisible(true);//光标显示
                break;
            case R.id.agin_verification_code:
                if (ObjectUtils.isEmpty(phone)) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.the_cell_phone_number_cannot_be_empty));
                    return;
                }
                mPresent.getCode(context, phone);// 1  綁定手机号
                break;
            case R.id.confirm_btn:
                String newDealPwd = edConfirmNewPwd.getText().toString().toString().trim();
                String code = edVerificationCode.getText().toString().trim();
                if (ObjectUtils.isEmpty(code)) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.the_verification_code_cannot_be_empty));
                    return;
                }
                if (ObjectUtils.isEmpty(newDealPwd)) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.please_enter_your_new_deal_password));
                    return;
                }
                if (newDealPwd.length() < 6) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.please_enter_six_digit_trading_password));
                    return;
                }
                showLoadingDialog();
                mPresent.modifyPwd(context, "2", "", code, newDealPwd);
                break;
        }
    }

}
