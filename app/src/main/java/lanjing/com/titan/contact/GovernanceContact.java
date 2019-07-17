package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.FormatRequest;
import lanjing.com.titan.response.InformationResponse;
import retrofit2.Call;
import retrofit2.Response;

public class GovernanceContact {
    public static class GovernanceCommittee extends BasePresent<IInfoCommitteeView> {
        public void initGovernance(final Context context, int type, int page, int size, int language) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            FormatRequest request = new FormatRequest(type, page, size, language);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.Information(token, request).enqueue(new NetCallBack<InformationResponse>() {
                @Override
                public void onSuccess(Call<InformationResponse> call, Response<InformationResponse> response) {
                    if (getView() != null) {
                        getView().getGovernance(response);
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

    public interface IInfoCommitteeView extends IBaseView {
        void getGovernance(Response<InformationResponse> response);

        void getDataFailed();

    }
}
