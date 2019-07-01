package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import lanjing.com.titan.net.NetCallBack;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.Md5Utils;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.request.SendCodeRequest;
import lanjing.com.titan.request.UpdatePwdRequest;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class ModifyPwdContact {
    public static class ModifyPwdPresent extends BasePresent<IModifyPwdView> {
        //修改登录密码
        public void modifyPwd(final Context context, String type ,String oldpassword, String code,String newpassword) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            UpdatePwdRequest request = new UpdatePwdRequest(type, Md5Utils.MD5(oldpassword),code,Md5Utils.MD5(newpassword));
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.updatePwd(token,request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getModifyPwdResult(response);
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
        public void getCode(final Context context,String phone) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            SendCodeRequest sendCodeRequest = new SendCodeRequest(phone);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.sendCode(token,sendCodeRequest).enqueue(new NetCallBack<ResultDTO>() {
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

    public interface IModifyPwdView extends IBaseView {
        void getModifyPwdResult(Response<ResultDTO> response);
        void getSendCodeResult(Response<ResultDTO> response);
        void getDataFailed();

    }
}
