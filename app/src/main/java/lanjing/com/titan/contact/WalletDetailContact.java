package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;

import lanjing.com.titan.net.NetCallBack;

import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.request.HistoryListRequest;
import lanjing.com.titan.request.WalletDetailRequest;
import lanjing.com.titan.response.HistoryListResponse;
import lanjing.com.titan.response.WalletDetailResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 * 获取钱包详情数据
 */

public class WalletDetailContact {
    public static class WalletDetailPresent extends BasePresent<IWalletDetailView> {
        public void walletDetail(final Context context, String coin) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            WalletDetailRequest request = new WalletDetailRequest(coin);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.welletDetail(token, request).enqueue(new NetCallBack<WalletDetailResponse>() {
                @Override
                public void onSuccess(Call<WalletDetailResponse> call, Response<WalletDetailResponse> response) {
                    if (getView() != null) {
                        getView().getWalletDeatilResult(response);
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

        public void historylist(final Context context, String coin, String type, String page, String size) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            HistoryListRequest request = new HistoryListRequest(coin, type, page, size);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.gethistorylist(token, request).enqueue(new NetCallBack<HistoryListResponse>() {
                @Override
                public void onSuccess(Call<HistoryListResponse> call, Response<HistoryListResponse> response) {
                    if (getView() != null) {
                        getView().gethistorylist(response);
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
        void getWalletDeatilResult(Response<WalletDetailResponse> response);

        void gethistorylist(Response<HistoryListResponse> response);

        void getDataFailed();

    }
}
