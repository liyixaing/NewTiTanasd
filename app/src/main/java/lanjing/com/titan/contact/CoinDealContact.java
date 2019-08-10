package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.CoinDealRequest;
import lanjing.com.titan.response.CoinDealResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 * 账单明细   币币交易
 */
public class CoinDealContact {
    public static class CoinDealPresent extends BasePresent<ICoinDealView> {
        public void coinDeal(final Context context, String page, String size) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            CoinDealRequest request = new CoinDealRequest(page, size);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.coinDeal(token, request).enqueue(new NetCallBack<CoinDealResponse>() {
                @Override
                public void onSuccess(Call<CoinDealResponse> call, Response<CoinDealResponse> response) {
                    if (getView() != null) {
                        getView().getCoinDealResult(response);
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

    public interface ICoinDealView extends IBaseView {
        void getCoinDealResult(Response<CoinDealResponse> response);

        void getDataFailed();

    }
}
