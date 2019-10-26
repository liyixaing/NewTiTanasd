package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.LanguageRequest;
import lanjing.com.titan.response.AgreementResponse;
import lanjing.com.titan.response.ExemptionResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class ExemptionContact {
    public static class ExemptionPresent extends BasePresent<IExemptionView> {
        //协议
        public void getExemption(final Context context) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            int language = Constant.LANGAGE;
            LanguageRequest request = new LanguageRequest(language);
            service.getExemption(token, request).enqueue(new NetCallBack<ExemptionResponse>() {
                @Override
                public void onSuccess(Call<ExemptionResponse> call, Response<ExemptionResponse> response) {
                    if (getView() != null) {
                        getView().getExemptionResult(response);
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

    public interface IExemptionView extends IBaseView {
        void getExemptionResult(Response<ExemptionResponse> response);
        void getDataFailed();
    }
}
