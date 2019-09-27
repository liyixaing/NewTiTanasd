package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.CdkeyListRequest;
import lanjing.com.titan.response.CdkeyListResponse;
import retrofit2.Call;
import retrofit2.Response;

public class StartCodeContact {
    public static class StartCodePresent extends BasePresent<StartCodeContact.StartCodeView> {

        //获取邀请码清单
        public void CdkeyList(final Context context, int page, int size) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            CdkeyListRequest request = new CdkeyListRequest(page, size);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.historydetail(token, request).enqueue(new NetCallBack<CdkeyListResponse>() {
                @Override
                public void onSuccess(Call<CdkeyListResponse> call, Response<CdkeyListResponse> response) {
                    if (getView() != null) {
                        getView().getCdkeyList(response);
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

    public interface StartCodeView extends IBaseView {
        void getCdkeyList(Response<CdkeyListResponse> response);

        void getDataFailed();

    }
}
