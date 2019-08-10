package lanjing.com.titan.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxh.baselibray.dialog.AlertDialog;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.LocalManageUtil;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.SizeUtils;
import com.lxh.baselibray.util.ToastUtils;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.LoginContact;
import lanjing.com.titan.response.LoginResponse;
import lanjing.com.titan.response.PersonResponse;
import lanjing.com.titan.view.CustomVideoView;
import retrofit2.Response;

/**
 * 引导页  可以切换语言  点击开始进入到登录页面
 */
public class SplashActivity extends MvpActivity<LoginContact.LoginPresent> implements LoginContact.ILoginView {


    @BindView(R.id.tv_language)
    TextView tvLanguage;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.welcome_bg)
    RelativeLayout welcomeBg;

    boolean isFirstIn = false;
    String adminNo;
    @BindView(R.id.videoview)
    CustomVideoView videoview;
    @BindView(R.id.btn_skip)
    TextView btnSkip;
    @BindView(R.id.guide_lay)
    RelativeLayout guideLay;

    @SuppressLint("StringFormatInvalid")
    @Override
    public void initData(Bundle savedInstanceState) {

//        new Thread() {
//            public void run() {
//                Message msg = hand.obtainMessage();
//                hand.sendMessage(msg);
//            }
//
//        }.start();


        adminNo = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);

        SPUtils.putString(Constant.DEVICE_ID, adminNo, context);

        Locale locale = getResources().getConfiguration().locale;//判断当前的语言
        if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
            welcomeBg.setBackground(getResources().getDrawable(R.drawable.splash_bg));
        } else if (locale.equals(Locale.ENGLISH)) {
            welcomeBg.setBackground(getResources().getDrawable(R.drawable.splash_en_bg));
        }

        setValue();
        LocalManageUtil.getSelectLanguage(this);


    }

//    Handler hand = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            super.handleMessage(msg);
//            if (isFristRun()) {
//                // 如果是第一次启动程序则进入引导界面  播放视频
//                guideLay.setVisibility(View.VISIBLE);
//                welcomeBg.setVisibility(View.GONE);
//                initView();
//
//            }
//
//        };
//    };

//    private void initView() {
//
//        videoview = (CustomVideoView) findViewById(R.id.videoview);
//        //设置播放载入路径
//        videoview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.guide_video));
//        //播放
//        videoview.start();
//        //循环播放
//        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                guideLay.setVisibility(View.GONE);
//                welcomeBg.setVisibility(View.VISIBLE);
//            }
//        });
//
//    }

    // 判断是否是第一次启动程序 利用 SharedPreferences 将数据保存在本地
//    private boolean isFristRun() {
//        //实例化SharedPreferences对象（第一步）
//        SharedPreferences sharedPreferences = this.getSharedPreferences(
//                "share", MODE_PRIVATE);
//        //实例化SharedPreferences.Editor对象（第二步）
//        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        if (!isFirstRun) {
//            return false;
//        } else {
//            //保存数据 （第三步）
//            editor.putBoolean("isFirstRun", false);
//            //提交当前数据 （第四步）
//            editor.commit();
//            return true;
//        }
//    }

    @SuppressLint("StringFormatInvalid")
    private void setValue() {
//        ToastUtils.showShortToast(context,LocalManageUtil.getSelectLanguage(this));
    }

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉手机顶部状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//适配华为虚拟下面导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        return R.layout.activity_splash;
    }

    @OnClick({R.id.btn_skip, R.id.tv_language, R.id.tv_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_skip://跳过引导
                videoview.stopPlayback();
                guideLay.setVisibility(View.GONE);
                welcomeBg.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_language://切换英文
                showTypeDialog();  //弹出框
                break;
            case R.id.tv_start:

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String account = SPUtils.getString(Constant.ACCOUNT, null, context);
                        String loginPwd = SPUtils.getString(Constant.LOGIN_PWD, null, context);
                        if (!ObjectUtils.isEmpty(account) && !ObjectUtils.isEmpty(loginPwd)) {
                            mPresent.login(context, account, loginPwd, adminNo);
                        } else {
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                }, 100);

                break;
        }
    }

    private void selectLanguage(int select) {
        LocalManageUtil.saveSelectLanguage(this, select);
        reStart(this);
    }

    public static void reStart(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    AlertDialog typeDialog = null;

    public void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .addDefaultAnimation()
                .setCancelable(true)
                .setText(R.id.tv_one, getResources().getString(R.string.simplified_chinese))
                .setText(R.id.tv_two, "English")
                .setContentView(R.layout.dialog_language)
                .setWidthAndHeight(SizeUtils.dp2px(context, 223), SizeUtils.dp2px(context, 81))
                .setOnClickListener(R.id.tv_one, v -> {
//                    languageText.setText(getResources().getString(R.string.simplified_chinese));//切换中文
                    selectLanguage(1);
                    typeDialog.dismiss();
                }).setOnClickListener(R.id.tv_two, v -> {
//                    languageText.setText("English");//切换英语
                    selectLanguage(2);
                    typeDialog.dismiss();
                });
        typeDialog = builder.create();
        typeDialog.show();
    }

    @Override
    protected LoginContact.LoginPresent createPresent() {
        return new LoginContact.LoginPresent();
    }

    @Override
    public void getLoginResult(Response<LoginResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            SPUtils.putString(Constant.TOKEN, response.body().getToken(), context);
            inviteCode = response.body().getInvitacode();
            mPresent.person(context);

        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        }

    }

    String inviteCode;

    @Override
    public void getPersonResult(Response<PersonResponse> response) {
        //判断有没有绑定手机号码
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            if (ObjectUtils.isEmpty(response.body().getData().getPhone())) {
                Intent intent = new Intent(context, LoginActivity.class);
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

            //判断有没有推荐人
            if (inviteCode.equals("")) {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                return;
            }
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            finish();

        } else if (response.body().getCode() ==-10){
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        }else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
    }

}
