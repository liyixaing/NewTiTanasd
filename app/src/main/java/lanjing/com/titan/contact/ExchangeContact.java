package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.Md5Utils;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.ConvertRequest;
import lanjing.com.titan.request.DealPwdRequest;
import lanjing.com.titan.request.RequestConvertConfig;
import lanjing.com.titan.response.ConvertConfigResponse;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Call;
import retrofit2.Response;

public class ExchangeContact {
    public static class ExchangePresent extends BasePresent<IWalletDetailView> {

        //获取兑换信息
        public void Convert(final Context context, int sourceCoin, int targetCoin) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            RequestConvertConfig request = new RequestConvertConfig(sourceCoin, targetCoin);
            service.ConvertConfig(token, request).enqueue(new NetCallBack<ConvertConfigResponse>() {
                @Override
                public void onSuccess(Call<ConvertConfigResponse> call, Response<ConvertConfigResponse> response) {
                    if (getView() != null) {
                        getView().getConvert(response);
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

        //提交兑换
        public void convertCoin(final Context context, String sourceCoin, String sourceAmount, String targetCoin) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            ConvertRequest request = new ConvertRequest(sourceCoin, sourceAmount, targetCoin);
            service.convertCoin(token, request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getconvertCoin(response);
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


        //提币  验证交易密码
        public void dealPwd(final Context context, String password) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            DealPwdRequest request = new DealPwdRequest(Md5Utils.MD5(password));
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

    public interface IWalletDetailView extends IBaseView {
        void getConvert(Response<ConvertConfigResponse> response);

        void getconvertCoin(Response<ResultDTO> response);

        void getDealPwdResult(Response<ResultDTO> response);

        void getDataFailed();

    }
}
