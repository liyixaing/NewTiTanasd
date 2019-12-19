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
import lanjing.com.titan.request.MappingRequest;
import lanjing.com.titan.request.WalletDetailRequest;
import lanjing.com.titan.request.getMappingRequest;
import lanjing.com.titan.response.GetMappingResponse;
import lanjing.com.titan.response.MappingResponse;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.WalletDetailResponse;
import retrofit2.Call;
import retrofit2.Response;

public class MappingContact {
    public static class MappingPresent extends BasePresent<IWalletDetailView> {
        public void mappignDetail(final Context context, int sourceCoin, int targetCoin) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            MappingRequest request = new MappingRequest(sourceCoin, targetCoin);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.MappingConfig(token, request).enqueue(new NetCallBack<MappingResponse>() {
                @Override
                public void onSuccess(Call<MappingResponse> call, Response<MappingResponse> response) {
                    if (getView() != null) {
                        getView().getmappingmanagerResult(response);
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

        public void getMappingpresent(final Context context, int sourceCoin, String sourceAmount, int targetCoin) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            getMappingRequest request = new getMappingRequest(sourceCoin, sourceAmount, targetCoin);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.getmapping(token, request).enqueue(new NetCallBack<GetMappingResponse>() {
                @Override
                public void onSuccess(Call<GetMappingResponse> call, Response<GetMappingResponse> response) {
                    if (getView() != null) {
                        getView().getmappingResult(response);
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

        public void dealPwdBuy(final Context context, String password, String type) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            DealPwdRequest request = new DealPwdRequest(Md5Utils.MD5(password), type);
            service.dealPwd(token, request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getDealPwdBuyResult(response);
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
        void getmappingmanagerResult(Response<MappingResponse> response);

        void getmappingResult(Response<GetMappingResponse> response);

        void getDealPwdBuyResult(Response<ResultDTO> response);

        void getDataFailed();
    }
}
