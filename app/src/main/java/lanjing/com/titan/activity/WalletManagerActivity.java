package lanjing.com.titan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lxh.baselibray.dialog.AlertDialog;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.SizeUtils;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.ExportContact;
import lanjing.com.titan.response.DealPwdHelpResponse;
import lanjing.com.titan.response.DealPwdKeyResponse;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Response;

/**
 * 钱包管理
 */
public class WalletManagerActivity extends MvpActivity<ExportContact.ExportPresent> implements ExportContact.IExportView {

    @BindView(R.id.tv_wallet_address)
    TextView tvWalletAddress;
    @BindView(R.id.tv_address_tip)
    TextView tvAddressTip;
    @BindView(R.id.prompt_words_export_btn)
    TextView promptWordsExportBtn;
    @BindView(R.id.secret_key_export_btn)
    TextView secretKeyExportBtn;

    @Override
    public void initData(Bundle savedInstanceState) {
        tvWalletAddress.setText(getIntent().getStringExtra("walletAddress"));
        tvAddressTip.setText(getIntent().getStringExtra("labelAddress"));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wallet_manager;
    }

    @Override
    protected ExportContact.ExportPresent createPresent() {
        return new ExportContact.ExportPresent();
    }


    //导出助记词
    AlertDialog pwdHelpDialog = null;

    private void showPwdHelpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_pwd)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 258), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_sure, v -> {//设置点击事件
                    EditText dealPwd = pwdHelpDialog.getView(R.id.ed_deal_pwd);
                    String pwd = dealPwd.getText().toString();
//                    pwd = Md5Utils.MD5(pwd).toUpperCase();
                    showLoadingDialog();
                    mPresent.exportHelp(context, pwd, Constant.Auxiliaries);
                    pwdHelpDialog.dismiss();
                }).setOnClickListener(R.id.tx_cancel, v -> pwdHelpDialog.dismiss());
        pwdHelpDialog = builder.create();
        pwdHelpDialog.show();

    }


    //导出私钥
    private void showPwdKeyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_pwd)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 258), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_sure, v -> {//设置点击事件
                    EditText dealPwd = pwdHelpDialog.getView(R.id.ed_deal_pwd);
                    String pwd = dealPwd.getText().toString();
//                    pwd = Md5Utils.MD5(pwd).toUpperCase();
                    showLoadingDialog();
                    mPresent.exportKey(context, pwd, Constant.Privatekey);
                    pwdHelpDialog.dismiss();
                }).setOnClickListener(R.id.tx_cancel, v -> pwdHelpDialog.dismiss());
        pwdHelpDialog = builder.create();
        pwdHelpDialog.show();

    }

    @OnClick({R.id.prompt_words_export_btn, R.id.secret_key_export_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.prompt_words_export_btn://导出助记词
                showPwdHelpDialog();
                break;
            case R.id.secret_key_export_btn://导出私钥
                showPwdKeyDialog();
                break;
        }
    }


    //验证导出助记词交易密码
    @Override
    public void getExportHelpResult(Response<DealPwdHelpResponse> response) {
        dismissLoadingDialog();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            Intent help = new Intent(context, ExportWordActivity.class);
            help.putExtra("word", response.body().getHelp());
            startActivity(help);
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else if (response.body().getCode() == 201) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.password_error));
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }
    }

    //验证导出私钥交易密码
    @Override
    public void getExportKeyResult(Response<DealPwdKeyResponse> response) {
        dismissLoadingDialog();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.successful));
            Intent key = new Intent(context, ExportKeyActivity.class);
            key.putExtra("key", response.body().getWelletkey());
            startActivity(key);
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else if (response.body().getCode() == 201) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.password_error));
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }
    }

    //二级密码验证返回
    @Override
    public void getDealPwdResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showLongToast(context, response.body().getMsg());

        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        dismissLoadingDialog();
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }


}
