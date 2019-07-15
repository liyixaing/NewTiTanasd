package lanjing.com.titan.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    public void initData(Bundle savedInstanceState) {
        id = getIntent().getStringExtra("id");
        taitanSum = getIntent().getStringExtra("taitanSum");
        TvBalance.setText(taitanSum);
        if (id.equals("0")) {
            //参数为0 不做处理
        } else {
            Log.e("xiaqoiang", id);
            mPresent.Transfer(context, id);
        }
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
            ToastUtils.showShortToast(context, "账号已在另一设备登陆");
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
            mPresent.walletWithdraw(context, TvAddress.getText().toString(), TvLabel.getText().toString(), TvRemarks.getText().toString());
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }

    }

    @Override
    public void getDataFailed() {

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
                    ToastUtils.showShortToast(context, "请选择提币地址");
                } else if (EtTibusun.getText().toString().equals("")) {
                    ToastUtils.showShortToast(context, "请输入提币数量");
                } else {
                    showPwdBuyDialog();
                }
                break;
            case R.id.tc_extract_all:
                initjudge();
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

    //提取全部判断
    public void initjudge() {
        Double asd = Double.valueOf(taitanSum);
        Log.e("xiaoqiang", String.valueOf(asd));
        Log.e("xiaoqiang", taitanSum);
        if (asd - 3 > 100) {
            EtTibusun.setText(MoneyUtil.priceFormat(taitanSum));
        } else {
            ToastUtils.showShortToast(context, "余额不足");

        }
    }

    @Override
    protected getTransferContact.getTransfer createPresent() {
        return new getTransferContact.getTransfer();
    }
}
