package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.lxh.baselibray.base.XActivity;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.RegexUtils;
import com.lxh.baselibray.util.ToastUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
//                Intent intent = new Intent(context,AgreementActivity.class);
//                startActivity(intent);
                break;
            case R.id.create_btn:
                String userName = edUserName.getText().toString().trim();
                String nickName = edNickName.getText().toString().trim();
                String loginPwd = edLoginPwd.getText().toString().trim();
                String confirmLoginPwd = edConfirmLoginPwd.getText().toString().trim();
                String dealPwd = edDealPwd.getText().toString().trim();
                String confirmDealPwd = edConfirmDealPwd.getText().toString().trim();
                String inviteCode = edInviteCode.getText().toString().trim();
                if (validateRegister(userName, nickName, loginPwd, confirmLoginPwd, dealPwd, confirmDealPwd))
                    return;
                showLoadingDialog();
                mPresent.register(context, userName, nickName, loginPwd, dealPwd, inviteCode);
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
//            ToastUtils.showShortToast(context, getResources().getString(R.string.create_success));//提示语（不需要输出）
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
//                    Intent intent = new Intent(context,RegisterWalletActivity.class);
//                    List<String> wordList = response.body().getHelps();
//                    intent.putExtra("wordList",(Serializable) (wordList));
//                    intent.putExtra("key",response.body().getUserkey());
//                    startActivity(intent);
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

    private boolean validateRegister(String userName, String nickName, String logPwd, String confirmLogPwd, String dealPwd, String confirmDealPwd) {
        if (ObjectUtils.isEmpty(userName)) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.user_name_cannot_be_empty));
            return true;
        }
        if (!RegexUtils.isAccount(userName)) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.account_must_contain_both_Numbers_and_letters_8_18_bits_long));
            return true;
        }
        if (ObjectUtils.isEmpty(nickName)) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.nick_name_cannot_be_empty));
            return true;
        }
        if (ObjectUtils.isEmpty(logPwd)) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.the_login_password_cannot_be_empty));
            return true;
        }
        if (!RegexUtils.isLoginPwd(logPwd)) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.Passwords_must_contain_both_Numbers_and_letters_8_18_bits_long));
            return true;
        }
        if (ObjectUtils.isEmpty(confirmLogPwd)) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.confirm_the_login_password_cannot_be_empty));
        }

        if (!logPwd.equals(confirmLogPwd)) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.the_two_passwords_do_not_match));
            return true;
        }

        if (ObjectUtils.isEmpty(dealPwd)) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.the_transaction_password_cannot_be_empty));
            return true;
        }
        if (dealPwd.length() < 6) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.please_enter_six_digit_trading_password));
            return true;
        }
        if (ObjectUtils.isEmpty(confirmDealPwd)) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.confirm_the_transaction_password_cannot_be_empty));
        }
        if (!edDealPwd.getText().toString().equals(edConfirmDealPwd)){
           ToastUtils.showLongToast(context, getResources().getString(R.string.the_two_passwords_do_not_match));
        }

        if (!iAgreeCheckbox.isChecked()) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.agree_to_continue_after_the_agreement));
            return true;
        }

        return false;
    }
}
