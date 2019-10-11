package lanjing.com.titan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.RegexUtils;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.ModifyPwdContact;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.util.CountDownTimerUtils;
import retrofit2.Response;

/**
 * 修改交易密码
 */
public class ModifyDealPwdActivity extends MvpActivity<ModifyPwdContact.ModifyPwdPresent> implements ModifyPwdContact.IModifyPwdView {

    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_verification_code)
    EditText edVerificationCode;
    @BindView(R.id.agin_verification_code)
    TextView aginVerificationCode;
    @BindView(R.id.ed_confirm_new_pwd)
    EditText edConfirmNewPwd;
    @BindView(R.id.confirm_btn)
    TextView confirmBtn;
//    @BindView(R.id.spinner1)
//    Spinner spinner1;
//    String areaCode;

    String phone;
    @Override
    public void initData(Bundle savedInstanceState) {
//        areaCode = "86";
//        initSpinner();
        phone = SPUtils.getString(Constant.PHONE, null, context);
        edPhone.setText(phone);
    }

//    private void initSpinner(){
//        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position){ //中国，韩国，日本，泰国，香港，台湾，新加坡，澳大利亚，美国，俄罗斯
//                    case 0://中国
//                        areaCode = "86";
//                        break;
//                    case 1://韩国
//                        areaCode = "82";
//                        break;
//                    case 2://日本
//                        areaCode = "81";
//                        break;
//                    case 3://泰国
//                        areaCode = "66";
//                        break;
//                    case 4://香港
//                        areaCode = "852";
//                        break;
//                    case 5://台湾
//                        areaCode = "886";
//                        break;
//                    case 6://新加坡
//                        areaCode = "65";
//                        break;
//                    case 7://澳大利亚
//                        areaCode = "61";
//                        break;
//                    case 8://美国
//                        areaCode = "1";
//                        break;
//                    case 9://俄罗斯
//                        areaCode = "7";
//                        break;
//                    case 10://马来西亚
//                        areaCode = "60";
//                        break;
//                    case 11://越南
//                        areaCode = "84";
//                        break;
//                    case 12://柬埔寨
//                        areaCode = "855";
//                        break;
//                    case 13://印度尼西亚
//                        areaCode = "62";
//                        break;
//                    case 14://文莱
//                        areaCode = "673";
//                        break;
//                    case 15://菲律宾
//                        areaCode = "63";
//                        break;
//                    case 16://东帝汶
//                        areaCode = "670";
//                        break;
//                    case 17://新西兰
//                        areaCode = "64";
//                        break;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_deal_pwd;
    }

    @Override
    protected ModifyPwdContact.ModifyPwdPresent createPresent() {
        return new ModifyPwdContact.ModifyPwdPresent();
    }

    @Override
    public void getModifyPwdResult(Response<ResultDTO> response) {
        dismissLoadingDialog();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.the_transaction_password_was_changed_successfully));
            finish();
        } else if (response.body().getMsg().equals("-10")){
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
            dismissLoadingDialog();
        }else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getSendCodeResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.verification_code_sent));
            //验证码倒计时
            CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(aginVerificationCode, 60000, 1000);
            countDownTimerUtils.start();
        } else if (response.body().getCode() == -10){
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        }else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
        dismissLoadingDialog();
    }


    @OnClick({R.id.ed_phone, R.id.agin_verification_code, R.id.confirm_btn})
    public void onViewClicked(View view) {

//        String phone = edPhone.getText().toString().trim();
//        String phones = areaCode+phone.replace(" ","");
        switch (view.getId()) {
            case R.id.ed_phone:
                edPhone.setCursorVisible(true);//光标显示
                break;
            case R.id.agin_verification_code:
                if (ObjectUtils.isEmpty(phone)) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.the_cell_phone_number_cannot_be_empty));
                    return;
                }

                mPresent.getCode(context, phone);// 1  綁定手机号
                break;
            case R.id.confirm_btn:
                String newDealPwd = edConfirmNewPwd.getText().toString().toString().trim();
                String code = edVerificationCode.getText().toString().trim();
                if (ObjectUtils.isEmpty(code)) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.the_verification_code_cannot_be_empty));
                    return;
                }
                if (ObjectUtils.isEmpty(newDealPwd)) {
                    ToastUtils.showLongToast(context, getResources().getString(R.string.please_enter_your_new_deal_password));
                    return;
                }
                if(newDealPwd.length()<6){
                    ToastUtils.showShortToast(context,getResources().getString(R.string.please_enter_six_digit_trading_password));
                    return;
                }

                showLoadingDialog();
                mPresent.modifyPwd(context, "2", "", code, newDealPwd);
                break;
        }
    }

}
