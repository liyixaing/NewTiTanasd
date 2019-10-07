package lanjing.com.titan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.TurnOutContact;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.getTransferConfigResponse;
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
    EditText et_exchange_sun;//转出数量
    @BindView(R.id.tv_exchange)
    TextView tv_exchange;//转出按钮
    @BindView(R.id.tv_allsun)
    TextView tv_allsun;//全部转出


    String coin;
    String sun;

    @Override
    public void initData(Bundle savedInstanceState) {
        coin = getIntent().getStringExtra("coin");
        sun = getIntent().getStringExtra("sun");
        tv_num.setText(sun);
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
                et_exchange_sun.setText(sun);
                break;
            case R.id.tv_exchange:
                if (et_exchange_num.getText().toString().equals("")) {
                    ToastUtils.showLongToast(context, "请输入接收人");
                } else if (et_exchange_sun.getText().toString().equals("")) {
                    ToastUtils.showLongToast(context, "请输入转出数量");
                } else {
                    mPresent.Accounts(context, coin, et_exchange_num.getText().toString(),
                            et_exchange_sun.getText().toString(), "");
                }
                break;
        }
    }

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
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }
    }


    //错误信息反馈
    @Override
    public void getDataFailed() {
        ToastUtils.showLongToast(context, getResources().getString(R.string.not_login));

    }
}
