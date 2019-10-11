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
import lanjing.com.titan.response.ChargeResponse;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class WalletWithdrawContact {
    public static class WalletWithdrawPresent extends BasePresent<IWalletWithdrawView> {
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

    public interface IWalletWithdrawView extends IBaseView {
        void getWalletWithdrawResult(Response<ResultDTO> response);

        void getDealPwdResult(Response<ResultDTO> response);//币币交易   买入验证交易密码

        void getDataFailed();

    }
}
