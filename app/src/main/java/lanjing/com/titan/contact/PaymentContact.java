package lanjing.com.titan.contact;

import android.content.Context;
import android.view.View;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.Md5Utils;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.DealPwdRequest;
import lanjing.com.titan.request.RequestConvertConfig;
import lanjing.com.titan.request.WithdrawRateRequest;
import lanjing.com.titan.request.WithdrawRequest;
import lanjing.com.titan.response.AgreementResponse;
import lanjing.com.titan.response.ConvertConfigResponse;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.WithdrawRateResponse;
import retrofit2.Call;
import retrofit2.Response;

public class PaymentContact {
    public static class PaymentPresent extends BasePresent<PaymentView> {

        //获取兑换信息 (这个拉取得存在只是为了获取titan余额)
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

        //拉取提币汇率信息
        public void WithdrawRate(final Context context, int coin) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            WithdrawRateRequest request = new WithdrawRateRequest(coin);
            service.WithdrawRate(token, request).enqueue(new NetCallBack<WithdrawRateResponse>() {
                @Override
                public void onSuccess(Call<WithdrawRateResponse> call, Response<WithdrawRateResponse> response) {
                    if (getView() != null) {
                        getView().getWithdrawRate(response);
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

        //提币
        public void walletWithdraw(final Context context, String coin, String address, String walletId, String num) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            WithdrawRequest request = new WithdrawRequest(coin, address, walletId, num);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.withdraw(token, request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getWalletWithdrawResult(response);
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

    public interface PaymentView extends IBaseView {

        void getConvert(Response<ConvertConfigResponse> response);

        void getWithdrawRate(Response<WithdrawRateResponse> response);

        void getDealPwdResult(Response<ResultDTO> response);

        void getWalletWithdrawResult(Response<ResultDTO> response);

        void getDataFailed();
    }
}
