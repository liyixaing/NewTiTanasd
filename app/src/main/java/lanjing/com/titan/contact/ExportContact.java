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
import lanjing.com.titan.request.DealPwdRequest;
import lanjing.com.titan.response.DealPwdHelpResponse;
import lanjing.com.titan.response.DealPwdKeyResponse;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class ExportContact {
    public static class ExportPresent extends BasePresent<IExportView> {
        //导出助记词验证交易密码
        public void exportHelp(final Context context, String password, String type) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            DealPwdRequest request = new DealPwdRequest(Md5Utils.MD5(password), type);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.dealPwdHelp(token, request).enqueue(new NetCallBack<DealPwdHelpResponse>() {
                @Override
                public void onSuccess(Call<DealPwdHelpResponse> call, Response<DealPwdHelpResponse> response) {
                    if (getView() != null) {
                        getView().getExportHelpResult(response);
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

        //导出私钥验证交易密码
        public void exportKey(final Context context, String password, String type) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            DealPwdRequest request = new DealPwdRequest(Md5Utils.MD5(password), type);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.dealPwdKey(token, request).enqueue(new NetCallBack<DealPwdKeyResponse>() {
                @Override
                public void onSuccess(Call<DealPwdKeyResponse> call, Response<DealPwdKeyResponse> response) {
                    if (getView() != null) {
                        getView().getExportKeyResult(response);
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

        // 验证交易密码
        public void dealPwd(final Context context, String password, String type) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            DealPwdRequest request = new DealPwdRequest(Md5Utils.MD5(password), type);
            service.dealPwd(token, request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getDealPwdResult(response);
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

    public interface IExportView extends IBaseView {
        void getExportHelpResult(Response<DealPwdHelpResponse> response);

        void getExportKeyResult(Response<DealPwdKeyResponse> response);

        void getDealPwdResult(Response<ResultDTO> response);

        void getDataFailed();

    }
}
