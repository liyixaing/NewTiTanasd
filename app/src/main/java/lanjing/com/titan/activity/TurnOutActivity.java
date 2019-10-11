package lanjing.com.titan.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import lanjing.com.titan.contact.TurnOutContact;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.getTransferConfigResponse;
import lanjing.com.titan.util.MoneyUtil;
import retrofit2.Response;


/**
 * 转出
 */
public class TurnOutActivity extends MvpActivity<TurnOutContact.ETurnOutPresent> implements TurnOutContact.TurnOutView {
    @Override
    protected TurnOutContact.ETurnOutPresent createPresent() {
        return new TurnOutContact.ETurnOutPresent();
    }

    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.et_exchange_num)
    EditText et_exchange_num;//接收id的输入框
    @BindView(R.id.et_exchange_sun)
    EditText et_exchange_sun;//转出数量输入框
    @BindView(R.id.tv_exchange)
    TextView tv_exchange;//转出按钮
    @BindView(R.id.tv_allsun)
    TextView tv_allsun;//全部转出


    String coin;
    String sun;
    int i;

    @Override
    public void initData(Bundle savedInstanceState) {
        coin = getIntent().getStringExtra("coin");
        sun = getIntent().getStringExtra("sun");
        tv_num.setText(sun);
        i = sun.indexOf(".");
        initInput();
        //mPresent.Convert(context, Integer.parseInt(coin));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_turn_out;
    }

    @OnClick({R.id.tv_exchange, R.id.tv_allsun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_allsun:
                i = sun.indexOf(".");
                et_exchange_sun.setText(sun.substring(0, i));
                break;
            case R.id.tv_exchange:
                if (et_exchange_num.getText().toString().equals("")) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.Please_input));
                } else if (et_exchange_sun.getText().toString().equals("")) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.Transfer_amount100));
                } else {
                    showPwdSellDialog();//调用弹窗
                }
                break;
        }
    }

    //判断输入框的最大输入限制
    public void initInput() {
        et_exchange_sun.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_exchange_sun.getText().toString().equals("")) {
                    //还没有输入数据不做处理
                } else {
                    int zhuanhuan = Integer.parseInt(sun.substring(0, i));
                    int text = Integer.parseInt(et_exchange_sun.getText().toString());
                    if (text > zhuanhuan) {
                        et_exchange_sun.setText(sun.substring(0, i));
                        et_exchange_sun.setSelection(et_exchange_sun.length());//将光标移至文字末尾
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

    //输入二级密码弹出框
    AlertDialog pwdSellDialog = null;

    private void showPwdSellDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_pwd)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 258), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_sure, v -> {//设置点击事件
                    EditText dealPwd = pwdSellDialog.getView(R.id.ed_deal_pwd);
                    String pwd = dealPwd.getText().toString();
                    mPresent.dealPwdSell(context, pwd, Constant.Transfer_accounts);
                    pwdSellDialog.dismiss();
                }).setOnClickListener(R.id.tx_cancel, v -> pwdSellDialog.dismiss());
        pwdSellDialog = builder.create();
        pwdSellDialog.show();

    }

    //提交转账返回
    @Override
    public void getConvert(Response<getTransferConfigResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showLongToast(context, response.body().getMsg());
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }

    }

    //转账按钮点击返回
    @Override
    public void getAccounts(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showLongToast(context, response.body().getMsg());
            finish();
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }
    }

    //验证码输入返回事件
    @Override
    public void getDealPwdSellResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            mPresent.Accounts(context, coin, et_exchange_num.getText().toString(),
                    et_exchange_sun.getText().toString(), "");

        } else if (response.body().getCode() == 201) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.password_error));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }

    }


    //错误信息反馈
    @Override
    public void getDataFailed() {
        ToastUtils.showLongToast(context, getResources().getString(R.string.not_login));

    }
}
