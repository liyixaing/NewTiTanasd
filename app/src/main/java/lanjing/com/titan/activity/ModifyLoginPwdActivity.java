package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.RegexUtils;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.ModifyPwdContact;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Response;

/**
 * 修改登录密码
 */
public class ModifyLoginPwdActivity extends MvpActivity<ModifyPwdContact.ModifyPwdPresent> implements ModifyPwdContact.IModifyPwdView {

    @BindView(R.id.ed_old_pwd)
    EditText edOldPwd;
    @BindView(R.id.ed_new_pwd)
    EditText edNewPwd;
    @BindView(R.id.ed_confirm_new_pwd)
    EditText edConfirmNewPwd;
    @BindView(R.id.confirm_btn)
    TextView confirmBtn;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_login_pwd;
    }

    @Override
    protected ModifyPwdContact.ModifyPwdPresent createPresent() {
        return new ModifyPwdContact.ModifyPwdPresent();
    }

    @Override
    public void getModifyPwdResult(Response<ResultDTO> response) {
        dismissLoadingDialog();
        if(response.body().getCode() == Constant.SUCCESS_CODE){
            ToastUtils.showShortToast(context,getResources().getString(R.string.the_modification_was_successful_Please_login_again_later));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }else {
            ToastUtils.showShortToast(context,response.body().getMsg());
        }
    }

    @Override
    public void getSendCodeResult(Response<ResultDTO> response) {

    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
        dismissLoadingDialog();
    }


    @OnClick({R.id.ed_old_pwd, R.id.confirm_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_old_pwd:
                edOldPwd.setCursorVisible(true);//光标显示
                break;
            case R.id.confirm_btn:
                String oldLoginPwd = edOldPwd.getText().toString().trim();
                String newLoginPwd = edNewPwd.getText().toString().trim();
                String againNewLoginPwd = edConfirmNewPwd.getText().toString().trim();
                if(ObjectUtils.isEmpty(oldLoginPwd)){
                    ToastUtils.showShortToast(context,getResources().getString(R.string.please_enter_your_original_login_password));
                    return;
                }
                if(ObjectUtils.isEmpty(newLoginPwd)){
                    ToastUtils.showShortToast(context,getResources().getString(R.string.please_enter_your_new_login_password));
                    return;
                }
                if (!RegexUtils.isLoginPwd(newLoginPwd)) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.Passwords_must_contain_both_Numbers_and_letters_8_18_bits_long));
                    return;
                }
                if(oldLoginPwd.equals(newLoginPwd)){
                    ToastUtils.showLongToast(context, getResources().getString(R.string.the_new_login_password_cannot_be_the_same_as_the_original_password));
                    return;
                }
                if(ObjectUtils.isEmpty(againNewLoginPwd)){
                    ToastUtils.showShortToast(context,getResources().getString(R.string.please_enter_your_new_login_password_again));
                    return;
                }
                if (!RegexUtils.isLoginPwd(againNewLoginPwd)) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.Passwords_must_contain_both_Numbers_and_letters_8_18_bits_long));
                    return;
                }

                if(!newLoginPwd.equals(againNewLoginPwd)){
                    ToastUtils.showShortToast(context,getResources().getString(R.string.the_two_passwords_do_not_match));
                    return;
                }
                showLoadingDialog();
                mPresent.modifyPwd(context,"1",oldLoginPwd,"",newLoginPwd);
                break;
        }
    }
}
