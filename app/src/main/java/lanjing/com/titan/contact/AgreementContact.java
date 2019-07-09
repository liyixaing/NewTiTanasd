package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.response.AgreementResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class AgreementContact {
    public static class AgreementPresent extends BasePresent<IAgreementView> {
        //协议
        public void getAgreement(final Context context) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            service.getAgreement().enqueue(new NetCallBack<AgreementResponse>() {
                @Override
                public void onSuccess(Call<AgreementResponse> call, Response<AgreementResponse> response) {
                    if (getView() != null) {
                        getView().getAgreementResult(response);
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

    public interface IAgreementView extends IBaseView {
        void getAgreementResult(Response<AgreementResponse> response);
        void getDataFailed();
    }
}
