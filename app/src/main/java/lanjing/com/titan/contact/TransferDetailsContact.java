package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.BillDetailRequest;
import lanjing.com.titan.response.BillDetailResponse;
import retrofit2.Call;
import retrofit2.Response;

public class TransferDetailsContact {
    public static class TransferPresent extends BasePresent<TransferDetailsView> {
        public void billDetail(final Context context, String logId) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            BillDetailRequest request = new BillDetailRequest(logId);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.billDetail(token, request).enqueue(new NetCallBack<BillDetailResponse>() {
                @Override
                public void onSuccess(Call<BillDetailResponse> call, Response<BillDetailResponse> response) {
                    if (getView() != null) {
                        getView().getBillDeatilResult(response);
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

    public interface TransferDetailsView extends IBaseView {
        void getBillDeatilResult(Response<BillDetailResponse> response);

        void getDataFailed();
    }
}
