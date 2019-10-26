package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lxh.baselibray.BaseApplication;
import com.lxh.baselibray.base.XActivity;
import com.lxh.baselibray.dialog.AlertDialog;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.CountDownTimerUtils;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.SizeUtils;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.SendCodeRequest;
import lanjing.com.titan.response.ResultDTO;

/**
 * 安全中心  用户可以绑定手机号  修改交易密码  登录密码   重置信息
 */
public class SecurityCenterActivity extends XActivity {

    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_phone_tx)
    TextView tvPhoneTx;
    @BindView(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @BindView(R.id.tv_deal_pwd)
    TextView tvDealPwd;
    String phone;
    @BindView(R.id.login_out_btn)
    TextView loginOutBtn;

    @Override
    public void initData(Bundle savedInstanceState) {
        phone = SPUtils.getString(Constant.PHONE, null, context);
        if (phone == null) {
            tvPhoneTx.setText(R.string.binding);
        } else {
            tvPhoneTx.setText(phone);
        }

    }

    @Override
    protected void onResume() {
        phone = SPUtils.getString(Constant.PHONE, null, context);
        if (phone == null) {
            tvPhoneTx.setText(R.string.binding);
        } else {
            tvPhoneTx.setText(phone);
        }
        super.onResume();
    }

    //切换手机号
    AlertDialog changPhoneDialog = null;

    private EditText tv_Code;
    private TextView tv_msg_code;

    private void showChangPhoneDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_change_phone_tip)//载入布局文件
                .setText(R.id.tv_home_sun, phone)
                .setWidthAndHeight(SizeUtils.dp2px(context, 250), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_cancel, v -> {//取消切换手机号
                    changPhoneDialog.dismiss();
                }).setOnClickListener(R.id.tx_sure, v -> {//确定切换手机号

                    tv_Code = changPhoneDialog.getView(R.id.et_code);
                    //判断输入框状态
                    if (tv_Code.length() == 0) {
                        ToastUtils.showShortToast(context, getResources().getString(R.string.please_verification_code));
                    } else {
                        //当验证码输入框不为空时直接跳转进修改密码
                        Intent intentPhone = new Intent(context, BindingPhoneActivity.class);
                        startActivity(intentPhone);
                        changPhoneDialog.dismiss();
                    }
                })
                .setOnClickListener(R.id.tv_msg_code, v -> {//发送验证码按钮
                    tv_msg_code = changPhoneDialog.getView(R.id.tv_msg_code);
                    InitGetCode();
                    CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(tv_msg_code, 60000, 1000);
                    countDownTimerUtils.start();


                });
        changPhoneDialog = builder.create();
        changPhoneDialog.show();

    }

    //获取验证码
    public void InitGetCode() {
        String token = SPUtils.getString(Constant.TOKEN, "", context);
        Log.e("token", token);
        Log.e("phone", phone);
        ApiService service = ServiceGenerator.createService(ApiService.class);
        SendCodeRequest sendCodeRequest = new SendCodeRequest(phone);
        service.sendCode(token, sendCodeRequest).enqueue(new NetCallBack<ResultDTO>() {
            @Override
            public void onSuccess(retrofit2.Call<ResultDTO> call, retrofit2.Response<ResultDTO> response) {
                if (response.body().getCode() == 200) {
                } else {
                    ToastUtils.showShortToast(context, response.body().getMsg());
                }
            }

            @Override
            public void onFailed() {
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_security_center;
    }


    @OnClick({R.id.tv_phone, R.id.tv_login_pwd, R.id.tv_deal_pwd, R.id.login_out_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_phone://绑定手机号
                if (phone != null) {
//                    showChangPhoneDialog();//弹出选择框
                    Intent intentPhone = new Intent(context, BindingPhoneActivity.class);
                    intentPhone.putExtra("phone", phone);
                    startActivity(intentPhone);
                    return;
                }
                Intent intentPhone = new Intent(context, BindingPhoneActivity.class);
                intentPhone.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentPhone);
                break;
            case R.id.tv_login_pwd://修改登录密码
                Intent loginPwd = new Intent(context, ModifyLoginPwdActivity.class);
                startActivity(loginPwd);
                break;
            case R.id.tv_deal_pwd://修改交易密码
                Intent dealPwd = new Intent(context, ModifyDealPwdActivity.class);
                if (ObjectUtils.isEmpty(phone)) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.please_bill_phone));
                    return;
                }
                startActivity(dealPwd);
                break;
            case R.id.login_out_btn:
                showloginOutDialog();
                break;
        }
    }

    private void clearData() {
        SPUtils.putString(Constant.TOKEN, null, context);
        SPUtils.putInt(Constant.USER_ID, -1, context);
        SPUtils.putString(Constant.ACCOUNT, null, context);
        SPUtils.putString(Constant.LOGIN_PWD, null, context);
        SPUtils.putString(Constant.PHONE, null, context);
        SPUtils.putString(Constant.INVITE_CODE, null, context);
        SPUtils.putString(Constant.LOGIN_PWD, null, context);
        SPUtils.getString(Constant.NICK_NAME, null, context);
        SPUtils.putString(Constant.DEVICE_ID, null, context);
        SPUtils.putBoolean(Constant.AUTOLOGIN, false, context);
        SPUtils.putInt(Constant.ISAUTO, -1, context);
    }


    //退出登录时弹窗
    AlertDialog loginOutDialog = null;

    private void showloginOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_login_out)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 250), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_cancel, v -> {//取消退出登录
                    loginOutDialog.dismiss();
                }).setOnClickListener(R.id.tx_sure, v -> {//确定退出登录
                    clearData();
                    BaseApplication.getActivityManage().finishAll();
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    loginOutDialog.dismiss();
                });
        loginOutDialog = builder.create();
        loginOutDialog.show();

    }

}
