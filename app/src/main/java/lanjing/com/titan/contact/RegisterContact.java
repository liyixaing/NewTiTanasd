package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import lanjing.com.titan.net.NetCallBack;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.Md5Utils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.request.RegisterRequest;
import lanjing.com.titan.response.RegisterResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class RegisterContact {
    public static class RegisterPresent extends BasePresent<IRegisterView> {
        public void register(final Context context, String userName, String nickName, String loginPwd, String payPwd, String invitationCode) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            RegisterRequest request = new RegisterRequest(userName, nickName, Md5Utils.MD5(loginPwd), Md5Utils.MD5(payPwd), invitationCode);
            service.register(request).enqueue(new NetCallBack<RegisterResponse>() {
                @Override
                public void onSuccess(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    if (getView() != null) {
                        getView().getRegisterResult(response);
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


//        public void registerAgreement(final Context context) {
//            ApiService service = ServiceGenerator.createService(ApiService.class);
//            String token = SPUtils.getString(Constant.TOKEN,"",context);
//            service.registerAgreement(token).enqueue(new NetCallBack<RegisterAgreementResponse>() {
//                @Override
//                public void onSuccess(Call<RegisterAgreementResponse> call, Response<RegisterAgreementResponse> response) {
//                    if (getView() != null) {
//                        getView().getRegisterAgreementResult(response);
//                    }
//                }
//
//                @Override
//                public void onFailed() {
//                    if (getView() != null) {
//                        getView().getDataFailed();
//                    }
//                }
//            });
//        }
    }

    public interface IRegisterView extends IBaseView {
        void getRegisterResult(Response<RegisterResponse> response);
//        void getRegisterAgreementResult(Response<RegisterAgreementResponse> response);
        void getDataFailed();

    }
}
