package lanjing.com.titan.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lxh.baselibray.BaseApplication;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.LoginContact;
import lanjing.com.titan.response.LoginResponse;
import lanjing.com.titan.response.PersonResponse;
import retrofit2.Response;

/**
 * 登录
 */
public class LoginActivity extends MvpActivity<LoginContact.LoginPresent> implements LoginContact.ILoginView, SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.ed_user_name)
    EditText edUserName;
    @BindView(R.id.sb)
    SeekBar sb;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.login_btn)
    TextView loginBtn;
    @BindView(R.id.tv_create_wallet)
    TextView tvLanguage;
    @BindView(R.id.tv_import_wallet)
    TextView tvStart;
    @BindView(R.id.ed_login_pwd)
    EditText edLoginPwd;
    //    LoadToast lt;
    String adminNo;

    @Override
    public void initData(Bundle savedInstanceState) {

//        SharedPreferences pref = getSharedPreferences("first_pref", MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putBoolean("isFirstIn", false);
//        editor.commit();


        sb.setOnSeekBarChangeListener(this);

        adminNo = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);

        SPUtils.putString(Constant.DEVICE_ID, adminNo, context);


//        final String text = "登录中";
//        lt = new LoadToast(this)
//                .setProgressColor(Color.RED)
//                .setText(text)
//                .setTranslationY(100)
//                .setBorderColor(Color.LTGRAY);
//
//        //lt.success();
//        final ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
//
//        View v = new View(this);
//        v.setBackgroundColor(Color.RED);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.ed_user_name, R.id.login_btn, R.id.tv_create_wallet, R.id.tv_import_wallet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_user_name:
                edUserName.setCursorVisible(true);//光标显示
                break;
            case R.id.login_btn:
                String userName = edUserName.getText().toString().trim();
                String loginPwd = edLoginPwd.getText().toString().trim();
                if (ObjectUtils.isEmpty(userName)) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.user_name_cannot_be_empty));
                    return;
                }
                if (ObjectUtils.isEmpty(loginPwd)) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.the_login_password_cannot_be_empty));
                    return;
                }
                if (sb.getProgress() != 100) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.please_right_validation));
                    return;
                }


                showLoadingDialog();
//                lt.show();
                String device = SPUtils.getString(Constant.DEVICE_ID, "", context);
                mPresent.login(context, userName, loginPwd, device);
                break;
            case R.id.tv_create_wallet:
                Intent create_wallet = new Intent(context, RegisterActivity.class);
                startActivity(create_wallet);
                break;
            case R.id.tv_import_wallet:
                Intent import_wallet = new Intent(context, ImportWalletActivity.class);
                startActivity(import_wallet);
                break;
        }
    }

    /**
     * seekBar进度变化时回调
     *
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getProgress() == seekBar.getMax()) {
            tv.setVisibility(View.VISIBLE);
            tv.setTextColor(Color.WHITE);
            tv.setText(R.string.finish_validation);
        } else {
            tv.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * seekBar开始触摸时回调
     *
     * @param seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * seekBar停止触摸时回调
     *
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getProgress() != seekBar.getMax()) {
            seekBar.setProgress(0);
            tv.setVisibility(View.VISIBLE);
            tv.setTextColor(Color.GRAY);
            tv.setText(R.string.right_validation);
        }
    }

    @Override
    protected LoginContact.LoginPresent createPresent() {
        return new LoginContact.LoginPresent();
    }

    String inviteCode;

    @Override
    public void getLoginResult(Response<LoginResponse> response) {
        dismissLoadingDialog();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {

            SPUtils.putString(Constant.ACCOUNT, edUserName.getText().toString().trim(), context);
            SPUtils.putString(Constant.LOGIN_PWD, edLoginPwd.getText().toString().trim(), context);
            SPUtils.putString(Constant.TOKEN, response.body().getToken(), context);
            SPUtils.putBoolean(Constant.AUTOLOGIN, true, this);
            inviteCode = response.body().getInvitacode();
            mPresent.person(context);
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    //登录返回里面得到token  ，然后通过token请求个人数据得到phone
    @Override
    public void getPersonResult(Response<PersonResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            if (ObjectUtils.isEmpty(response.body().getData().getPhonenum())) {
                Intent intent = new Intent(context, RegisterBindingPhoneActivity.class);
                intent.putExtra("code", inviteCode);
                startActivity(intent);
                return;
            }
//            if(response.body().getData().getPhonenum().equals("")){
//                Intent intent = new Intent(context, RegisterBindingPhoneActivity.class);
//                intent.putExtra("code",inviteCode);
//                startActivity(intent);
//                return;
//            }

            if (inviteCode.equals("")) {
                Intent intent = new Intent(context, ImportWalletBindActivity.class);
                startActivity(intent);
                return;
            }
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            //英文版输出中文的原因是输出的是接口中的massage消息
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
        dismissLoadingDialog();
//        lt.error();
    }

    private long timeMillis;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - timeMillis) > 2000) {
                ToastUtils.showShortToast(context, getResources().getString(R.string.press_exit_again));
                timeMillis = System.currentTimeMillis();
            } else {
                BaseApplication.getActivityManage().finishAll();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
