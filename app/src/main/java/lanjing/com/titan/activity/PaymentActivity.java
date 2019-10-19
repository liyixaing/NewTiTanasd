package lanjing.com.titan.activity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lxh.baselibray.dialog.AlertDialog;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.SizeUtils;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.PaymentContact;
import lanjing.com.titan.response.AgreementResponse;
import lanjing.com.titan.response.ConvertConfigResponse;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.WithdrawRateResponse;
import lanjing.com.titan.util.MoneyUtil;
import retrofit2.Response;

/**
 * 支付界面
 */
public class PaymentActivity extends MvpActivity<PaymentContact.PaymentPresent> implements PaymentContact.PaymentView {

    @BindView(R.id.iv_backretun)
    ImageView iv_backretun;//返回
    @BindView(R.id.tv_address)
    TextView tv_address;//文本输入的支付地址
    @BindView(R.id.tv_label)
    TextView tv_label;//地址标签
    @BindView(R.id.confirm_btn)
    TextView confirm_btn;
    @BindView(R.id.et_tibusun)
    EditText et_tibusun;//数量输入框
    @BindView(R.id.tc_extract_all)
    TextView tc_extract_all;//提取全部按钮
    @BindView(R.id.tv_balance)
    TextView tv_balance;//余额显示
    @BindView(R.id.rb_one)
    RadioButton rb_one;//选择CNY
    @BindView(R.id.rb_zero)
    RadioButton rb_zero;//选择USD
    @BindView(R.id.tv_result)
    TextView tv_result;//显示结果

    int i;
    String num;
    int coin = 1;
    //定义一个int类型用来判断选择CNY还是选择usd
    int type = 0;//0为cny 1为usd

    String price_usd;//USD汇率
    String price_cny;//CNY汇率
    String price_usdt;//USDT汇率

    double sun, Divisor, cny, usd;//计算汇率参数

    @Override
    protected PaymentContact.PaymentPresent createPresent() {
        return new PaymentContact.PaymentPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresent.Convert(context, 1, 2);//获取titan余额显示
        mPresent.WithdrawRate(context, coin);
        String rawResult = getIntent().getStringExtra("rawResult");//接收扫描结果
        String[] all = rawResult.split(",");
        tv_address.setText(all[1]);//支付地址赋值
        tv_label.setText(all[2]);//标签赋值


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }


    @OnClick({R.id.iv_backretun, R.id.confirm_btn, R.id.tc_extract_all, R.id.rb_one, R.id.rb_zero})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_backretun://返回
                finish();
                break;
            case R.id.tc_extract_all:
                i = num.indexOf(".");
                et_tibusun.setText(num.substring(0, i));
                break;
            case R.id.confirm_btn:
                if (et_tibusun.getText().toString().equals("")) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.The_number_of_withdraw));
                } else {
                    showPwdBuyDialog();
                }
                break;
            case R.id.rb_one:
                type = 0;
                initCoin();
                break;
            case R.id.rb_zero:

                type = 1;
                initCoin();
                break;
        }
    }

    //选择币种判断
    public void initCoin() {
        //先判断输入框中是否有内容
        if (et_tibusun.getText().toString().equals("")) {
            //输入框为空 可以不做处理  但是这里还是简单的输出一下吧
        } else {
            Divisor = Double.parseDouble(et_tibusun.getText().toString());
            cny = Double.parseDouble(price_cny);
            usd = Double.parseDouble(price_usd);
            if (type == 0) {//0为cny
                sun = Divisor / cny;
                tv_result.setText("≈" + MoneyUtil.formatFouras(sun + "") + "TITAN");
            } else {
                sun = Divisor / usd;
                tv_result.setText("≈" + MoneyUtil.formatFouras(sun + "") + "TITAN");
            }
        }
    }

    //判断输入框数量
    public void initInput() {
        et_tibusun.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (et_tibusun.getText().toString().equals("")) {
                    //输入框为空不做处理
                    tv_result.setVisibility(View.GONE);
                } else {
                    tv_result.setVisibility(View.VISIBLE);
                    int zhuanhuan = Integer.parseInt(num.substring(0, i));//总数
                    double text = Double.valueOf(et_tibusun.getText().toString());//输入数量
                    if (text > zhuanhuan) {
                        et_tibusun.setText(num.substring(0, i));
                        et_tibusun.setSelection(et_tibusun.length());//将光标移至文字末尾
                    } else {
                        //输入数字比总数小
                        initCoin();
                    }


                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //输入框改变后
            }
        });
    }

    //提币的二级密码弹出框
    AlertDialog pwdDialog = null;

    private void showPwdBuyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_pwd)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 258), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_sure, v -> {//设置点击事件
                    EditText dealPwd = pwdDialog.getView(R.id.ed_deal_pwd);
                    if (dealPwd.getText().toString().equals("")) {
                        ToastUtils.showLongToast(context, getResources().getString(R.string.the_payment_password));
                    } else {
                        String pwd = dealPwd.getText().toString();
                        mPresent.dealPwd(context, pwd, Constant.Withdrawal_of_money);
                        pwdDialog.dismiss();
                    }

                }).setOnClickListener(R.id.tx_cancel, v -> pwdDialog.dismiss());
        pwdDialog = builder.create();
        pwdDialog.show();

    }


    //获取配置信息（只是为了获取titan余额的）
    @Override
    public void getConvert(Response<ConvertConfigResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            num = MoneyUtil.formatFour(response.body().getData().getUser_titan_amount());
            tv_balance.setText(MoneyUtil.formatFour(response.body().getData().getUser_titan_amount()));
            i = num.indexOf(".");
            initInput();
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }

    }


    //获取提币汇率回调
    @Override
    public void getWithdrawRate(Response<WithdrawRateResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            price_usd = response.body().getData().getPrice_usd();
            price_cny = response.body().getData().getPrice_cny();
            price_usdt = response.body().getData().getPrice_usdt();
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }

    }

    @Override
    public void getDealPwdResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            //密码正确
            mPresent.walletWithdraw(context, "1", tv_address.getText().toString(),
                    tv_label.getText().toString(), MoneyUtil.formatFouras(sun + ""));
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }
    }


    //提币返回回调
    @Override
    public void getWalletWithdrawResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showLongToast(context, response.body().getMsg());
            finish();
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }

    }


    //错误回调
    @Override
    public void getDataFailed() {
        ToastUtils.showLongToast(context, getResources().getString(R.string.network_error));
    }
}
