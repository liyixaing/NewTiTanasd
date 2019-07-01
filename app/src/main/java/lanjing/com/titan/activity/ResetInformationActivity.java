package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
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
import lanjing.com.titan.contact.ImportWalletSetPwdContact;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Response;

/**
 * 重置信息
 */
public class ResetInformationActivity extends MvpActivity<ImportWalletSetPwdContact.ImportWalletSetPwdPresent> implements ImportWalletSetPwdContact.IImportWalletSetPwdView {

    @BindView(R.id.ed_login_pwd)
    EditText edLoginPwd;
    @BindView(R.id.ed_confirm_login_pwd)
    EditText edConfirmLoginPwd;
    @BindView(R.id.ed_deal_pwd)
    EditText edDealPwd;
    @BindView(R.id.ed_confirm_deal_pwd)
    EditText edConfirmDealPwd;
    @BindView(R.id.submit_btn)
    TextView submitBtn;

    String userId;
    @Override
    public void initData(Bundle savedInstanceState) {
        userId = getIntent().getStringExtra("userId");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_reset_information;
    }

    @Override
    protected ImportWalletSetPwdContact.ImportWalletSetPwdPresent createPresent() {
        return new ImportWalletSetPwdContact.ImportWalletSetPwdPresent();
    }

    @Override
    public void getImportWalletSetPwdResult(Response<ResultDTO> response) {
        dismissLoadingDialog();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context,getResources().getString(R.string.successfully_set));
            Intent intent = new Intent(context,LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
        dismissLoadingDialog();
    }

    @OnClick({R.id.ed_login_pwd, R.id.submit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_login_pwd:
                edLoginPwd.setCursorVisible(true);//光标显示
                break;
            case R.id.submit_btn:
                String logPwd = edLoginPwd.getText().toString().trim();
                String comfirmLogPwd = edConfirmLoginPwd.getText().toString().trim();
                String dealPwd = edDealPwd.getText().toString().trim();
                String comfirmDealPwd = edConfirmDealPwd.getText().toString().trim();
                if (validate(logPwd,comfirmLogPwd,dealPwd,comfirmDealPwd))
                    return;
                showLoadingDialog();
                mPresent.importWalletSetPwd(context,Integer.parseInt(userId),logPwd,dealPwd);
                break;
        }
    }

    private boolean validate(String logPwd,String confirmLogPwd, String dealPwd, String confirmDealPwd) {
        if (ObjectUtils.isEmpty(logPwd)) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.the_login_password_cannot_be_empty));
            return true;
        }
        if (!RegexUtils.isLoginPwd(logPwd)) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.Passwords_must_contain_both_Numbers_and_letters_8_18_bits_long));
            return true;
        }
        if(ObjectUtils.isEmpty(confirmLogPwd)){
            ToastUtils.showLongToast(context,getResources().getString(R.string.confirm_the_login_password_cannot_be_empty));
            return true;
        }

        if(!logPwd.equals(confirmLogPwd)){
            ToastUtils.showLongToast(context, getResources().getString(R.string.the_two_passwords_do_not_match));
            return true;
        }

        if (ObjectUtils.isEmpty(dealPwd)) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.the_transaction_password_cannot_be_empty));
            return true;
        }
        if(ObjectUtils.isEmpty(confirmDealPwd)){
            ToastUtils.showLongToast(context,getResources().getString(R.string.confirm_the_transaction_password_cannot_be_empty));
            return true;
        }

        return false;
    }
}
