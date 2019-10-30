package lanjing.com.titan.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lxh.baselibray.dialog.AlertDialog;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.SizeUtils;
import com.lxh.baselibray.util.ToastUtils;

import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.getTransferContact;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.getTransferResponse;
import lanjing.com.titan.util.MoneyUtil;
import retrofit2.Response;

/**
 * Titan提币
 */
public class TiTanWithdrawMoney extends MvpActivity<getTransferContact.getTransfer> implements getTransferContact.IWalletDetailView {

    @BindView(R.id.confirm_btn)
    TextView confirmBtn;
    @BindView(R.id.tv_address)
    TextView TvAddress;//地址显示位置
    @BindView(R.id.tv_label)
    TextView TvLabel;//标签显示位置
    @BindView(R.id.tv_remarks)
    TextView TvRemarks;//备注显示位置
    @BindView(R.id.tv_balance)
    TextView TvBalance;//余额
    @BindView(R.id.et_tibusun)
    EditText EtTibusun;//数量输入框
    private String id;
    String taitanSum;
    Double asd = 0.0;
    int i;

    @Override
    public void initData(Bundle savedInstanceState) {
        id = getIntent().getStringExtra("id");
        taitanSum = getIntent().getStringExtra("taitanSum");
        i = taitanSum.indexOf(".");
//        asd = Double.valueOf(taitanSum);
        TvBalance.setText(taitanSum);
        if (id.equals("0")) {
            //参数为0 不做处理
        } else {
            Log.e("xiaqoiang", id);
            mPresent.Transfer(context, id);
        }
        initInput();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw_money;
    }


    @Override
    public void getTransferstaster(Response<getTransferResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            //获取数据成功
            TvAddress.setText(response.body().getData().getToAccount());
            TvLabel.setText(response.body().getData().getToTag());
            TvRemarks.setText(response.body().getData().getToMemo());

        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }

    }

    @Override
    public void getWalletWithdrawResult(Response<ResultDTO> response) {
        dismissLoadingDialog();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, response.body().getMsg());
            finish();
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }

    }

    @Override
    public void getDealPwdResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            showLoadingDialog();
            mPresent.walletWithdraw(context, "1", TvAddress.getText().toString(), TvLabel.getText().toString(), EtTibusun.getText().toString());
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else if (response.body().getCode() == 201) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.password_error));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }

    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));

    }


    @OnClick({R.id.confirm_btn, R.id.tv_select_address, R.id.iv_backretun, R.id.tc_extract_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_backretun:
                finish();
                break;
            case R.id.tv_select_address:
                Intent Select = new Intent(context, ActivitySelectAddress.class);
                Select.putExtra("taitanSum", taitanSum);
                startActivity(Select);
                finish();
                break;
            case R.id.confirm_btn:

                if (TvAddress.getText().toString().equals("")) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.please_address));
                } else if (EtTibusun.getText().toString().equals("")) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.pls_three));
                } else {
                    showPwdBuyDialog();
                }
                break;
            case R.id.tc_extract_all:
                int anInt = Integer.parseInt(taitanSum.substring(0, i));
                EtTibusun.setText(String.valueOf(anInt - 3));
                EtTibusun.setSelection(EtTibusun.length());//将光标移至文字末尾
                break;
        }

    }

    //提币二级密码输入框
    AlertDialog pwdDialog = null;

    private void showPwdBuyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_pwd)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 258), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_sure, v -> {//设置点击事件
                    EditText dealPwd = pwdDialog.getView(R.id.ed_deal_pwd);
                    String pwd = dealPwd.getText().toString();
                    mPresent.dealPwd(context, pwd, Constant.Withdrawal_of_money);
                    pwdDialog.dismiss();
                }).setOnClickListener(R.id.tx_cancel, v -> pwdDialog.dismiss());
        pwdDialog = builder.create();
        pwdDialog.show();
    }

    //判断输入框中的数量是否与已有的数量比较
    public void initInput() {
        EtTibusun.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (EtTibusun.getText().toString().equals("")) {
                    //还没有输入数据不做处理
                } else if (EtTibusun.getText().toString().length() > 8) {//为了防止用户输入数字超出int值
                    EtTibusun.setText("10000000");
                } else {
                    int zhuanhuan = Integer.parseInt(taitanSum.substring(0, i));
                    int text = Integer.parseInt(EtTibusun.getText().toString());
                    if (text > zhuanhuan) {
                        EtTibusun.setText(taitanSum.substring(0, i));
                        EtTibusun.setSelection(EtTibusun.length());//将光标移至文字末尾
                    } else {
                        //不变
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    protected getTransferContact.getTransfer createPresent() {
        return new getTransferContact.getTransfer();
    }
}
