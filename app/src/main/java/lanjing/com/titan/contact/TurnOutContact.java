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
import lanjing.com.titan.request.getTransferConfigRequest;
import lanjing.com.titan.request.RransferRequest;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.getTransferConfigResponse;
import retrofit2.Call;
import retrofit2.Response;

public class TurnOutContact {
    public static class ETurnOutPresent extends BasePresent<TurnOutView> {
        //获取转账信息
        public void Convert(final Context context, int coin) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            getTransferConfigRequest request = new getTransferConfigRequest(coin);
            service.CdkeyList(token, request).enqueue(new NetCallBack<getTransferConfigResponse>() {
                @Override
                public void onSuccess(Call<getTransferConfigResponse> call, Response<getTransferConfigResponse> response) {
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

        //转账
        public void Accounts(final Context context, String coin, String toUser, String amount, String memo) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            RransferRequest request = new RransferRequest(coin, toUser, amount, memo);
            service.transfer(token, request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getAccounts(response);
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


        // 验证卖出密码
        public void dealPwdSell(final Context context, String password, String type) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            DealPwdRequest request = new DealPwdRequest(Md5Utils.MD5(password), type);
            service.dealPwd(token, request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getDealPwdSellResult(response);
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

    public interface TurnOutView extends IBaseView {
        void getConvert(Response<getTransferConfigResponse> response);

        void getAccounts(Response<ResultDTO> response);

        void getDealPwdSellResult(Response<ResultDTO> response);

        void getDataFailed();

    }
}
