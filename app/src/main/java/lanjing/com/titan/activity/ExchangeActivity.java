package lanjing.com.titan.activity;

import android.os.Bundle;
import android.text.Editable;
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

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.ExchangeContact;
import lanjing.com.titan.response.ConvertConfigResponse;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.util.MoneyUtil;
import retrofit2.Response;

public class ExchangeActivity extends MvpActivity<ExchangeContact.ExchangePresent> implements ExchangeContact.IWalletDetailView {

    @BindView(R.id.et_exchange_num)
    EditText EtExchangeNum;
    @BindView(R.id.tv_num)
    TextView TvNum;
    @BindView(R.id.tv_rate)
    TextView TvRate;
    String num;
    int i;

    @Override
    protected ExchangeContact.ExchangePresent createPresent() {
        return new ExchangeContact.ExchangePresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        num = getIntent().getStringExtra("num");
         i=num.indexOf(".");
        TvNum.setText(num);
        mPresent.Convert(context);
        inputEdit();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_exchange;
    }


    @OnClick({R.id.tv_exchange, R.id.tv_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_exchange:
                if (EtExchangeNum.getText().toString().equals("")) {
                    ToastUtils.showShortToast(context, "请输入数量");
                } else {
                    showPwdBuyDialog();
                }
                break;
            case R.id.tv_all:
                EtExchangeNum.setText(num.substring(0, i));
                EtExchangeNum.setSelection(EtExchangeNum.length());//将光标移至文字末尾
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

    private void inputEdit() {
        //输入框监听事件
        EtExchangeNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //发生改变前
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //正在发生改变

                if (EtExchangeNum.getText().toString().equals("")) {
                    //输入框为空 不做处理
                } else {
                    int zhuanhuan = Integer.parseInt(num.substring(0, i));
                    int text = Integer.parseInt(EtExchangeNum.getText().toString());
                    if (text > zhuanhuan) {
                        EtExchangeNum.setText(num.substring(0, i));
                        EtExchangeNum.setSelection(EtExchangeNum.length());//将光标移至文字末尾
                    }else {
                        //不变
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //发生改变后
            }
        });
    }

    //拉取兑换配置
    @Override
    public void getConvert(Response<ConvertConfigResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            String asd = getResources().getString(R.string.exchange_ratesad);
            TvRate.setText(asd + response.body().getData().getConvert_switch()
                    + "TITAN ≈ " + MoneyUtil.formatFour(response.body().getData().getConvert_rate()) + "BAR");

        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }

    }

    //提交兑换返回
    @Override
    public void getconvertCoin(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, response.body().getMsg());
            finish();
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }

    }

    //二级密码验证返回
    @Override
    public void getDealPwdResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            mPresent.convertCoin(context, "1", EtExchangeNum.getText().toString(), "5");
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }

    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }


}
