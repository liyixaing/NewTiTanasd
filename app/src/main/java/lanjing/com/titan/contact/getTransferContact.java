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
import lanjing.com.titan.request.DealPwdRequest;
import lanjing.com.titan.request.WithdrawRequest;
import lanjing.com.titan.request.getTransferRequest;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.getTransferResponse;
import retrofit2.Call;
import retrofit2.Response;

public class getTransferContact {
    public static class getTransfer extends BasePresent<IWalletDetailView> {
        //获取地址详情
        public void Transfer(final Context context, String walletId) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            getTransferRequest request = new getTransferRequest(walletId);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.getTransferAddressDetail(token, request).enqueue(new NetCallBack<getTransferResponse>() {
                @Override
                public void onSuccess(Call<getTransferResponse> call, Response<getTransferResponse> response) {
                    if (getView() != null) {
                        getView().getTransferstaster(response);
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

    }


    public interface IWalletDetailView extends IBaseView {
        void getTransferstaster(Response<getTransferResponse> response);

        void getWalletWithdrawResult(Response<ResultDTO> response);

        void getDealPwdResult(Response<ResultDTO> response);

        void getDataFailed();

    }
}
