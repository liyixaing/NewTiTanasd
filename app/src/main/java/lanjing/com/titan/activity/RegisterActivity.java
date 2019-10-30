package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.RegexUtils;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.RegisterContact;
import lanjing.com.titan.response.RegisterResponse;
import retrofit2.Response;

/**
 * 注册（创建钱包）
 */
public class RegisterActivity extends MvpActivity<RegisterContact.RegisterPresent> implements RegisterContact.IRegisterView {


    @BindView(R.id.ed_user_name)
    EditText edUserName;
    @BindView(R.id.ed_nick_name)
    EditText edNickName;
    @BindView(R.id.ed_login_pwd)
    EditText edLoginPwd;
    @BindView(R.id.ed_confirm_login_pwd)
    EditText edConfirmLoginPwd;
    @BindView(R.id.ed_deal_pwd)
    EditText edDealPwd;
    @BindView(R.id.ed_confirm_deal_pwd)
    EditText edConfirmDealPwd;
    @BindView(R.id.ed_invite_code)
    EditText edInviteCode;
    @BindView(R.id.i_agree_checkbox)
    CheckBox iAgreeCheckbox;
    @BindView(R.id.agreement)
    TextView agreement;
    @BindView(R.id.create_btn)
    TextView createBtn;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.ed_user_name, R.id.i_agree_checkbox, R.id.agreement, R.id.create_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_user_name:
                edUserName.setCursorVisible(true);//光标显示
                break;
            case R.id.i_agree_checkbox:
                break;
            case R.id.agreement:
                Intent intent = new Intent(context,AgreementActivity.class);
                startActivity(intent);
                break;
            case R.id.create_btn://注册按钮点击执行
                if (edUserName.getText().toString().equals("")) {//判断是否输入用户名
                    ToastUtils.showShortToast(context, getResources().getString(R.string.user_name_cannot_be_empty));
                } else if (!RegexUtils.isAccount(edUserName.getText().toString())) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.account_must_contain_both_Numbers_and_letters_8_18_bits_long));
                } else if (edNickName.getText().toString().equals("")) {//判断是否输入昵称
                    ToastUtils.showShortToast(context, getResources().getString(R.string.nick_name_cannot_be_empty));
                } else if (edLoginPwd.getText().toString().equals("")) {//判断是否输入登陆密码
                    ToastUtils.showShortToast(context, getResources().getString(R.string.the_login_password_cannot_be_empty));
                } else if (!RegexUtils.isLoginPwd(edLoginPwd.getText().toString())) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.Passwords_must_contain_both_Numbers_and_letters_8_18_bits_long));
                } else if (edConfirmLoginPwd.getText().toString().equals("")) {//判断是否输入登陆验证密码
                    ToastUtils.showShortToast(context, getResources().getString(R.string.confirm_the_login_password_cannot_be_empty));
                } else if (!edLoginPwd.getText().toString().equals(edConfirmLoginPwd.getText().toString())) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.the_two_passwords_do_not_match));
                } else if (edDealPwd.getText().toString().equals("")) {//判断是否输入交易密码
                    ToastUtils.showLongToast(context, getResources().getString(R.string.the_transaction_password_cannot_be_empty));
                } else if (edDealPwd.length() < 6) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.please_enter_six_digit_trading_password));
                } else if (edConfirmDealPwd.getText().toString().equals("")) {//判断是否输入确认交易密码
                    ToastUtils.showShortToast(context, getResources().getString(R.string.confirm_the_transaction_password_cannot_be_empty));
                } else if (!edDealPwd.getText().toString().equals(edConfirmDealPwd.getText().toString())) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.the_two_transaction_passwords_do_not_match));
                } else if (edInviteCode.getText().toString().equals("")) {//判断是否输入邀请码
                    ToastUtils.showLongToast(context, getResources().getString(R.string.invitation_code));
                } else if (!iAgreeCheckbox.isChecked()) {//判断是否勾选协议
                    ToastUtils.showShortToast(context, getResources().getString(R.string.agree_to_continue_after_the_agreement));
                } else {
                    //获取请求
                    mPresent.register(context, edUserName.getText().toString(), edNickName.getText().toString(),
                            edLoginPwd.getText().toString(), edDealPwd.getText().toString(), edInviteCode.getText().toString());
                }
                break;
        }
    }

    @Override
    protected RegisterContact.RegisterPresent createPresent() {
        return new RegisterContact.RegisterPresent();
    }

    //注册返回
    @Override
    public void getRegisterResult(Response<RegisterResponse> response) {
        dismissLoadingDialog();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.registration_successful_later_enter_the_login_page_to_log_in));
                    finish();
                }
            }, 1000);
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
}
