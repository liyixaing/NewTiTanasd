package lanjing.com.titan.net;

import android.content.Intent;

import com.google.gson.Gson;
import com.lxh.baselibray.BaseApplication;
import com.lxh.baselibray.base.BaseResponce;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.ToastUtils;

import java.util.Locale;

import lanjing.com.titan.activity.LoginActivity;
import lanjing.com.titan.constant.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class NetCallBack<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response != null && response.body() != null && response.isSuccessful()) {
            BaseResponce baseResponce = new Gson().fromJson(new Gson().toJson(response.body()), BaseResponce.class);
            if (baseResponce.getCode() == 405) {
                //弹出提示用户帐号在其它地方登录

            } else if (baseResponce.getCode() == 500) {
                Locale locale = BaseApplication.getContext().getResources().getConfiguration().locale;//判断当前的语言
                if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                    ToastUtils.showShortToast(BaseApplication.getContext(), "账号信息过期或异地登录，请重新登录！");
                } else if (locale.equals(Locale.ENGLISH)) {
                    ToastUtils.showShortToast(BaseApplication.getContext(), "Account abnormal or user information expired, please login again!");
                }
                SPUtils.putString(Constant.TOKEN,null,BaseApplication.getContext());
                SPUtils.putInt(Constant.USER_ID, -1, BaseApplication.getContext());
                SPUtils.putString(Constant.ACCOUNT,null,BaseApplication.getContext());
                SPUtils.putString(Constant.LOGIN_PWD,null,BaseApplication.getContext());
                SPUtils.putString(Constant.PHONE, null, BaseApplication.getContext());
                SPUtils.putString(Constant.INVITE_CODE, null, BaseApplication.getContext());
                SPUtils.putString(Constant.LOGIN_PWD, null, BaseApplication.getContext());
                SPUtils.getString(Constant.NICK_NAME, null, BaseApplication.getContext());
                SPUtils.putString(Constant.DEVICE_ID,null,BaseApplication.getContext());
                SPUtils.putBoolean(Constant.AUTOLOGIN, false, BaseApplication.getContext());
                Intent intent = new Intent(BaseApplication.getContext(), LoginActivity.class);
                BaseApplication.getContext().startActivity(intent);

            } else {
                onSuccess(call, response);
            }
        } else {
            onFailed();
        }
    }


    @Override
    public void onFailure(Call<T> call, Throwable t) {

        onFailed();
    }

    public abstract void onSuccess(Call<T> call, Response<T> response);

    public abstract void onFailed();


}
