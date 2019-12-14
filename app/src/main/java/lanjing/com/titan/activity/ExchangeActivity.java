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

/**
 * 兑换
 */
public class ExchangeActivity extends MvpActivity<ExchangeContact.ExchangePresent> implements ExchangeContact.IWalletDetailView {

    @BindView(R.id.et_exchange_num)
    EditText EtExchangeNum;
    @BindView(R.id.tv_num)
    TextView TvNum;
    @BindView(R.id.tv_rate)
    TextView TvRate;
    String num;
    int i;
    int sourceCoin = 1;//原币种
    int targetCoin = 2;//兑换币种

    @Override
    protected ExchangeContact.ExchangePresent createPresent() {
        return new ExchangeContact.ExchangePresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresent.Convert(context, sourceCoin, targetCoin);

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
                    ToastUtils.showShortToast(context, getResources().getString(R.string.input_sun));
                } else {
                    showPwdBuyDialog();
                }
                break;
            case R.id.tv_all:
                i = num.indexOf(".");
                EtExchangeNum.setText(num.substring(0, i));
                EtExchangeNum.setSelection(EtExchangeNum.length());//将光标移至文字末尾
                break;
        }
    }

    //闪兑二级密码弹出框
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
                    mPresent.dealPwd(context, pwd, Constant.Flash_exchange);
                    pwdDialog.dismiss();
                }).setOnClickListener(R.id.tx_cancel, v -> pwdDialog.dismiss());
        pwdDialog = builder.create();
        pwdDialog.show();

    }

    //判断输入框中的数量是否与已有的数量比较
    public void initInput() {
        EtExchangeNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (EtExchangeNum.getText().toString().equals("")) {
                    //还没有输入数据不做处理
                } else {
                    int zhuanhuan = Integer.parseInt(num.substring(0, i));
                    //使用double的原因是因为数字如果超出int类型的长度的话会异常
                    double text = Double.valueOf(EtExchangeNum.getText().toString());
                    if (text > zhuanhuan) {
                        EtExchangeNum.setText(num.substring(0, i));
                        EtExchangeNum.setSelection(EtExchangeNum.length());//将光标移至文字末尾
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

    //拉取兑换配置
    @Override
    public void getConvert(Response<ConvertConfigResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            String Reference = getResources().getString(R.string.exchange_ratesad);
            num = MoneyUtil.formatFour(response.body().getData().getUser_titan_amount());
            TvNum.setText(MoneyUtil.formatFour(response.body().getData().getUser_titan_amount()));
            initInput();
            i = num.indexOf(".");
            String rath = Reference + "1" + "TITAN ≈ " + MoneyUtil.formatFour(response.body().getData().getConvert_rate()) + "TITANC";
            TvRate.setText(rath);
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
            mPresent.convertCoin(context, "1", EtExchangeNum.getText().toString(), "2");
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
}
