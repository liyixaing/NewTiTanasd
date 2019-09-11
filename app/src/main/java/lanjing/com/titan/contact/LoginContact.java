package lanjing.com.titan.contact;

import android.content.Context;
import android.util.Log;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;

import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;

import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.Md5Utils;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.request.LoginRequest;
import lanjing.com.titan.request.VersionRequest;
import lanjing.com.titan.response.LoginResponse;
import lanjing.com.titan.response.PersonResponse;
import lanjing.com.titan.response.VersionResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class LoginContact {
    public static class LoginPresent extends BasePresent<ILoginView> {
        //登录
        public void login(final Context context, String userName, String loginPwd, String device) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            LoginRequest request = new LoginRequest(userName, Md5Utils.MD5(loginPwd), device);
            service.login(request).enqueue(new NetCallBack<LoginResponse>() {
                @Override
                public void onSuccess(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (getView() != null) {
                        getView().getLoginResult(response);
                    }
                }

                @Override
                public void onFailed() {
                    if (getView() != null) {
                        getView().getDataFailed();
                    }
                }
            });
        }

        public void person(final Context context) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.getPerson(token).enqueue(new NetCallBack<PersonResponse>() {
                @Override
                public void onSuccess(Call<PersonResponse> call, Response<PersonResponse> response) {
                    if (getView() != null) {
                        getView().getPersonResult(response);
                    }
                }

                @Override
                public void onFailed() {
                    if (getView() != null) {
                        getView().getDataFailed();
                    }
                }
            });
        }

        //版本更新
        public void updateApp(final Context context, int type, int version) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            VersionRequest request = new VersionRequest(type, version);
            service.updateApp(request).enqueue(new NetCallBack<VersionResponse>() {
                @Override
                public void onSuccess(Call<VersionResponse> call, Response<VersionResponse> response) {
                    if (getView() != null) {
                        getView().getupdateAppResult(response);
                    }
                }

                @Override
                public void onFailed() {
                    if (getView() != null) {
                        getView().getDataFailed();
                    }
                }
            });
        }


    }

    public interface ILoginView extends IBaseView {
        void getLoginResult(Response<LoginResponse> response);

        void getPersonResult(Response<PersonResponse> response);

        void getupdateAppResult(Response<VersionResponse> response);

        //        void getRegisterAgreementResult(Response<RegisterAgreementResponse> response);
        void getDataFailed();

    }
}
