package lanjing.com.titan.activity;

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
import lanjing.com.titan.contact.MappingContact;
import lanjing.com.titan.response.GetMappingResponse;
import lanjing.com.titan.response.MappingResponse;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.util.MoneyUtil;
import retrofit2.Response;

/**
 * 映射界面
 */
public class MappingActivity extends MvpActivity<MappingContact.MappingPresent> implements MappingContact.IWalletDetailView {

    @BindView(R.id.tv_exchange)
    TextView tv_exchange;//映射按钮
    @BindView(R.id.tv_num)
    TextView tv_num;//titan数量
    @BindView(R.id.tv_rate)
    TextView tv_rate;//参考汇率
    @BindView(R.id.tv_all)
    TextView tv_all;//映射全部
    @BindView(R.id.et_exchange_num)
    EditText et_exchange_num;//映射数量输入

    @Override
    protected MappingContact.MappingPresent createPresent() {
        return new MappingContact.MappingPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //测试获取数据
        mPresent.mappignDetail(context, 1, 10);

    }


    //添加布局
    @Override
    public int getLayoutId() {
        return R.layout.activity_mapping;
    }

    @OnClick({R.id.tv_exchange, R.id.tv_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_exchange://映射按钮
                if (et_exchange_num.getText().toString().equals("")) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.input_sun));
                } else {
                    showPwdBuyDialog();
                }
                break;
            case R.id.tv_all://映射全部
                et_exchange_num.setText(MoneyUtil.formatFouras(tv_num.getText().toString()));
                break;
        }

    }


    //映射 密码框
    AlertDialog pwdBuyDialog = null;

    private void showPwdBuyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_pwd)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 258), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_sure, v -> {//设置点击事件
                    EditText dealPwd = pwdBuyDialog.getView(R.id.ed_deal_pwd);
                    String pwd = dealPwd.getText().toString();
                    mPresent.dealPwdBuy(context, pwd, Constant.Flash_exchange);
                    pwdBuyDialog.dismiss();
                }).setOnClickListener(R.id.tx_cancel, v -> pwdBuyDialog.dismiss());
        pwdBuyDialog = builder.create();
        pwdBuyDialog.show();

    }


    //拉取映射配置
    @Override
    public void getmappingmanagerResult(Response<MappingResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            tv_num.setText(MoneyUtil.formatFour(response.body().getData().getSource_coin_amount() + ""));//可用titan数量
            tv_rate.setText(getResources().getString(R.string.exchange_ratesad) +
                    "1 TITAN ≈ " + response.body().getData().getMapping_rate() + "TTA");//参考汇率

        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }

    }

    /**
     * 映射
     *
     * @param response
     */
    @Override
    public void getmappingResult(Response<GetMappingResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showLongToast(context, response.body().getMsg());
            finish();
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }

    }

    /**
     * 验证密码
     *
     * @param response
     */
    @Override
    public void getDealPwdBuyResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            mPresent.getMappingpresent(context, 1, et_exchange_num.getText().toString(), 10);
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }

    }

    //接口返回错误信息
    @Override
    public void getDataFailed() {
        ToastUtils.showLongToast(context, getResources().getString(R.string.network_error));

    }
}
