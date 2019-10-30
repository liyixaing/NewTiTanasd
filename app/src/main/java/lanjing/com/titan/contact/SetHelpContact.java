package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;

import lanjing.com.titan.net.NetCallBack;

import com.lxh.baselibray.net.ServiceGenerator;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.request.SetHelpRequest;
import lanjing.com.titan.response.SetHelpResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class SetHelpContact {
    public static class SetHelpPresent extends BasePresent<ISetHelpView> {
        public void setHelp(final Context context, String userKey, String help) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            SetHelpRequest request = new SetHelpRequest(userKey, help);
            service.setHelp(request).enqueue(new NetCallBack<SetHelpResponse>() {
                @Override
                public void onSuccess(Call<SetHelpResponse> call, Response<SetHelpResponse> response) {
                    if (getView() != null) {
                        getView().getSetHelpResult(response);
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

    public interface ISetHelpView extends IBaseView {
        void getSetHelpResult(Response<SetHelpResponse> response);

        void getDataFailed();

    }
}
