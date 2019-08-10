package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.AwardRequest;
import lanjing.com.titan.request.CoinDealRequest;
import lanjing.com.titan.request.WithdrawalRequest;
import lanjing.com.titan.response.AwardResponse;
import lanjing.com.titan.response.CoinDealResponse;
import lanjing.com.titan.response.CoinLogListResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 * 账单明细   币币交易
 */
public class AwardContact {
    public static class AwardPresent extends BasePresent<IAwardView> {
        public void award(final Context context, String page, String size) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            AwardRequest request = new AwardRequest(page, size);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.award(token, request).enqueue(new NetCallBack<AwardResponse>() {
                @Override
                public void onSuccess(Call<AwardResponse> call, Response<AwardResponse> response) {
                    if (getView() != null) {
                        getView().getAwardResult(response);
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

        public void CoinLogList(final Context context, String coin, String type, String page, String size) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            WithdrawalRequest request = new WithdrawalRequest(coin, type, page, size);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.CoinLogList(token, request).enqueue(new NetCallBack<CoinLogListResponse>() {
                @Override
                public void onSuccess(Call<CoinLogListResponse> call, Response<CoinLogListResponse> response) {
                    if (getView() != null) {
                        getView().getCoinLogList(response);
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

    public interface IAwardView extends IBaseView {
        void getAwardResult(Response<AwardResponse> response);

        void getCoinLogList(Response<CoinLogListResponse> response);

        void getDataFailed();

    }
}
