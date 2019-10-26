package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.lxh.baselibray.event.BusFactory;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.SetPhoneContact;
import lanjing.com.titan.eventbus.EventImpl;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.SetNewPhoneResponse;
import lanjing.com.titan.response.SetPhoneResponse;
import lanjing.com.titan.util.CountDownTimerUtils;
import retrofit2.Response;

/**
 * 绑定手机号  输入正确手机号  填写验证码  提交
 */
public class BindingPhoneActivity extends MvpActivity<SetPhoneContact.SetPhonePresent> implements SetPhoneContact.ISetPhoneView {

    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_verification_code)
    EditText edVerificationCode;
    @BindView(R.id.next_step_btn)
    TextView nextStepBtn;
    @BindView(R.id.agin_verification_code)
    TextView aginVerificationCode;
    @BindView(R.id.spinner1)
    Spinner spinner1;
    String areaCode;//区号
    @BindView(R.id.tv_home_sun)
    TextView TvHomeSun;
    @BindView(R.id.tv_used_pwd)
    TextView TvUsedPwd;
    @BindView(R.id.et_used_code)
    TextView EtUsedCode;
    String Oldphone;

    @Override
    public void initData(Bundle savedInstanceState) {
        areaCode = "86";
        Oldphone = getIntent().getStringExtra("phone");
        TvHomeSun.setText(Oldphone);
        Log.e("原手机号码", Oldphone);
        initSpinner();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_binding_phone;
    }

    @Override
    protected SetPhoneContact.SetPhonePresent createPresent() {
        return new SetPhoneContact.SetPhonePresent();
    }


    private void initSpinner() {
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) { //中国，韩国，日本，泰国，香港，台湾，新加坡，澳大利亚，美国，俄罗斯
                    case 0://中国
                        areaCode = "86";
                        break;
                    case 1://韩国
                        areaCode = "82";
                        break;
                    case 2://日本
                        areaCode = "81";
                        break;
                    case 3://泰国
                        areaCode = "66";
                        break;
                    case 4://香港
                        areaCode = "852";
                        break;
                    case 5://台湾
                        areaCode = "886";
                        break;
                    case 6://新加坡
                        areaCode = "65";
                        break;
                    case 7://澳大利亚
                        areaCode = "61";
                        break;
                    case 8://美国
                        areaCode = "1";
                        break;
                    case 9://俄罗斯
                        areaCode = "7";
                        break;
                    case 10://马来西亚
                        areaCode = "60";
                        break;
                    case 11://越南
                        areaCode = "84";
                        break;
                    case 12://柬埔寨
                        areaCode = "855";
                        break;
                    case 13://印度尼西亚
                        areaCode = "62";
                        break;
                    case 14://文莱
                        areaCode = "673";
                        break;
                    case 15://菲律宾
                        areaCode = "63";
                        break;
                    case 16://东帝汶
                        areaCode = "670";
                        break;
                    case 17://新西兰
                        areaCode = "64";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void getSetPhoneResult(Response<SetPhoneResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.binding_success_tip));
            SPUtils.putString(Constant.PHONE, edPhone.getText().toString().trim(), context);
            Intent intent = new Intent(context, SecurityCenterActivity.class);
            BusFactory.getBus().post(new EventImpl.UpdatePhoneEvent());
            startActivity(intent);

//            Intent intent = new Intent(context, ResetInformationActivity.class);
//            intent.putExtra("userId", response.body().getUserId());
//            startActivity(intent);
            finish();
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
            dismissLoadingDialog();
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
            dismissLoadingDialog();
        }
    }

    @Override
    public void getNewSetPhoneResult(Response<SetNewPhoneResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.binding_success_tip));
            SPUtils.putString(Constant.PHONE, edPhone.getText().toString().trim(), context);
//            Intent intent = new Intent(context, SecurityCenterActivity.class);
//            BusFactory.getBus().post(new EventImpl.UpdatePhoneEvent());
//            startActivity(intent);
            Intent newIntent = new Intent(context, MainActivity.class);
            startActivity(newIntent);

//            Intent intent = new Intent(context, ResetInformationActivity.class);
//            intent.putExtra("userId", response.body().getUserId());
//            startActivity(intent);

        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
            dismissLoadingDialog();
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
            dismissLoadingDialog();
        }
    }

    int asd;

    //获取验证码返回
    @Override
    public void getSendCodeResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {//18124073755
            if (asd == 1) {
                ToastUtils.showShortToast(context, getResources().getString(R.string.verification_code_sent));
                CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(TvUsedPwd, 60000, 1000);
                countDownTimerUtils.start();
            } else {
                ToastUtils.showShortToast(context, getResources().getString(R.string.verification_code_sent));
                CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(aginVerificationCode, 60000, 1000);
                countDownTimerUtils.start();
            }
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
            dismissLoadingDialog();
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
        dismissLoadingDialog();
    }

    @OnClick({R.id.tv_used_pwd, R.id.ed_phone, R.id.agin_verification_code, R.id.next_step_btn})
    public void onViewClicked(View view) {
        String phone = edPhone.getText().toString().trim();
        String phones = areaCode + phone;
        switch (view.getId()) {
            case R.id.tv_used_pwd:
                mPresent.getCode(context, Oldphone);
                asd = 1;
                return;
            case R.id.ed_phone:
                edPhone.setCursorVisible(true);//光标显示
                break;
            case R.id.agin_verification_code:
                if (ObjectUtils.isEmpty(phone)) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.the_cell_phone_number_cannot_be_empty));
                    return;
                }
                mPresent.getCode(context, phones);// 1  綁定手机号
                asd = 2;
                break;
            case R.id.next_step_btn:
                String code = edVerificationCode.getText().toString().trim();
                String oldcode = EtUsedCode.getText().toString().trim();
                String phonesun = areaCode + edPhone.getText().toString().trim();
                if (ObjectUtils.isEmpty(code) || ObjectUtils.isEmpty(oldcode) || ObjectUtils.isEmpty(phonesun)) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.codeerhome_error));//验证码或手机号码错误
                    return;
                }

                showLoadingDialog();
                mPresent.setnewPhone(context, Oldphone, oldcode, phonesun, code);
                break;
        }
    }
}
