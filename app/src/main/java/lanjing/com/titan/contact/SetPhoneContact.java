package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;

import lanjing.com.titan.net.NetCallBack;

import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.request.SendCodeRequest;
import lanjing.com.titan.request.SetNewPhoneRequest;
import lanjing.com.titan.request.SetPhoneRequest;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.SetNewPhoneResponse;
import lanjing.com.titan.response.SetPhoneResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class SetPhoneContact {
    public static class SetPhonePresent extends BasePresent<ISetPhoneView> {
        //绑定手机号
        public void setPhone(final Context context, String phonenum, String code) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            SetPhoneRequest request = new SetPhoneRequest(phonenum, code);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.setPhone(token, request).enqueue(new NetCallBack<SetPhoneResponse>() {
                @Override
                public void onSuccess(Call<SetPhoneResponse> call, Response<SetPhoneResponse> response) {
                    if (getView() != null) {
                        getView().getSetPhoneResult(response);
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

        //绑定新手机号
        public void setnewPhone(final Context context, String oldPhone, String oldCode,String newPhone, String newColde) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            SetNewPhoneRequest request = new SetNewPhoneRequest(oldPhone, oldCode, newPhone, newColde);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.setNewPhone(token, request).enqueue(new NetCallBack<SetNewPhoneResponse>() {
                @Override
                public void onSuccess(Call<SetNewPhoneResponse> call, Response<SetNewPhoneResponse> response) {
                    if (getView() != null) {
                        getView().getNewSetPhoneResult(response);
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

        /*
        * 1绑定手机号  2  修改交易密码
        * */
        public void getCode(final Context context, String phone) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            SendCodeRequest sendCodeRequest = new SendCodeRequest(phone);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.sendCode(token, sendCodeRequest).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getSendCodeResult(response);
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

    public interface ISetPhoneView extends IBaseView {
        void getSetPhoneResult(Response<SetPhoneResponse> response);

        void getNewSetPhoneResult(Response<SetNewPhoneResponse> response);

        void getSendCodeResult(Response<ResultDTO> response);

        void getDataFailed();

    }
}
