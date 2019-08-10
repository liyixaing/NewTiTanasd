package lanjing.com.titan.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lxh.baselibray.dialog.AlertDialog;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.SizeUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.lxh.baselibray.view.TitleView;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.WalletWithdrawContact;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.util.MoneyUtil;
import retrofit2.Response;

/**
 * TITAN  提币
 */
public class TItanWithdrawActivity extends MvpActivity<WalletWithdrawContact.WalletWithdrawPresent> implements WalletWithdrawContact.IWalletWithdrawView {


    @BindView(R.id.ed_tibi_address)
    EditText edTibiAddress;

    @BindView(R.id.ed_num)
    EditText edNum;
    @BindView(R.id.confirm_btn)
    TextView confirmBtn;
    @BindView(R.id.ed_address_id)
    EditText edAddressId;
    private boolean flag = true;
    String balance;
    BigDecimal totalAmount;
    double keyongDouble;
    @BindView(R.id.title_lay)
    TitleView titleLay;
    @BindView(R.id.tv_service_fee)
    TextView tvServiceFee;
    @BindView(R.id.tv_balance)
    TextView TvBalance;
    String dasd;

    @Override
    public void initData(Bundle savedInstanceState) {
        balance = getIntent().getStringExtra("balance");
        dasd = getIntent().getStringExtra("taitanSum");
        TvBalance.setText("可用余额: " + dasd);
        initEdit();
    }

    private void initEdit() {
        edNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString()) && flag) {
                    BigDecimal result;
                    String temp = s.toString();
                    int posDot = temp.indexOf(".");
                    try {
                        if (posDot == s.length() - 1) {
                            return;
                        }
                        result = new BigDecimal(s.toString());
                    } catch (Exception e) {
                        return;
                    }

                    keyongDouble = MoneyUtil.stringToDouble(balance);
                    totalAmount = BigDecimal.valueOf(keyongDouble);//获取币的数量
                    //不允许超出最大值
                    if (result.compareTo(totalAmount) == 1) {
                        ToastUtils.showShortToast(context, getResources().getString(R.string.current_maximum_input) + totalAmount);
                        edNum.setText("");
                    }

                    //保留两位小数
                    flag = false;
                    s.clear();
                    if (posDot > 0 && temp.length() - posDot - 1 > 2) {
                        temp = temp.substring(0, posDot + 3);
                    }
                    s.append(temp);
                    flag = true;
                }
//                updateMoney();
            }
        });
    }

    //计算转出金额所代表的美金
//    private void updateMoney() {
//        String count = edNum.getText().toString().trim();
//        try {
//            String fee_service = (
//                    new BigDecimal(count).multiply(new BigDecimal(0.002))) + "";
//            tvServiceFee.setText(MoneyUtil.formatFour(fee_service)+" TITAN");
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_titan_withdraw;
    }

    @Override
    protected WalletWithdrawContact.WalletWithdrawPresent createPresent() {
        return new WalletWithdrawContact.WalletWithdrawPresent();
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
            String address = edTibiAddress.getText().toString().trim();
            String walletId = edAddressId.getText().toString().trim();
            String num = edNum.getText().toString().trim();
            showLoadingDialog();
            mPresent.walletWithdraw(context, "1", address, walletId, num);
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

    @OnClick({R.id.ed_tibi_address, R.id.confirm_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_tibi_address://光标显示
                edTibiAddress.setCursorVisible(true);
                break;
            case R.id.confirm_btn:
                String address = edTibiAddress.getText().toString().trim();
                String walletId = edAddressId.getText().toString().trim();
                String num = edNum.getText().toString().trim();
                if (ObjectUtils.isEmpty(address)) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.please_ed_wallet_address));
                    return;
                }
                if (ObjectUtils.isEmpty(walletId)) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.please_ed_wallet_id));
                    return;
                }
                if (ObjectUtils.isEmpty(num)) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.please_ed_tibi_num));
                    return;
                }
                if (Integer.parseInt(num) < 100) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.minimum_withdrawal));
                    return;
                }
                showPwdBuyDialog();
                break;
        }
    }

    //弹出 买入 密码框
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
                    mPresent.dealPwd(context, pwd);
                    pwdDialog.dismiss();
                }).setOnClickListener(R.id.tx_cancel, v -> pwdDialog.dismiss());
        pwdDialog = builder.create();
        pwdDialog.show();

    }

}
