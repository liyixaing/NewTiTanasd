package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import lanjing.com.titan.net.NetCallBack;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.response.ChargeResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class WalletChargeContact {
    public static class WalletChargePresent extends BasePresent<IWalletChargeView> {
        //充币
        public void walletCharge(final Context context) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.charge(token).enqueue(new NetCallBack<ChargeResponse>() {
                @Override
                public void onSuccess(Call<ChargeResponse> call, Response<ChargeResponse> response) {
                    if (getView() != null) {
                        getView().getWalletChargeResult(response);
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

    public interface IWalletChargeView extends IBaseView {
        void getWalletChargeResult(Response<ChargeResponse> response);
//        void getRegisterAgreementResult(Response<RegisterAgreementResponse> response);
        void getDataFailed();

    }
}
