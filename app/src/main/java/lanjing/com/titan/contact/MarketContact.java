package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;

import lanjing.com.titan.net.NetCallBack;

import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.request.LanguageRequest;
import lanjing.com.titan.response.MarketListResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class MarketContact {
    public static class MarketPresent extends BasePresent<IMarketView> {
        public void marketList(final Context context) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            int language = Constant.LANGAGE;
            LanguageRequest request = new LanguageRequest(language);
            service.marketList(token, request).enqueue(new NetCallBack<MarketListResponse>() {
                @Override
                public void onSuccess(Call<MarketListResponse> call, Response<MarketListResponse> response) {
                    if (getView() != null) {
                        getView().getMarketResult(response);
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

    public interface IMarketView extends IBaseView {
        void getMarketResult(Response<MarketListResponse> response);

        void getDataFailed();

    }
}
