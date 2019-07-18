package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.ViewFeedbackRequest;
import lanjing.com.titan.response.viewFeedbackResponse;
import retrofit2.Call;
import retrofit2.Response;

public class FeedbackDetaiContact {
    public static class FeedbackDetaiPresent extends BasePresent<IFeedbackListView> {
        public void feedbackList(final Context context,String pageSize) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            ViewFeedbackRequest request = new ViewFeedbackRequest(pageSize);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.viewFeedback(token,request).enqueue(new NetCallBack<viewFeedbackResponse>() {
                @Override
                public void onSuccess(Call<viewFeedbackResponse> call, Response<viewFeedbackResponse> response) {
                    if (getView() != null) {
                        getView().getFeedbackListResult(response);
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

    public interface IFeedbackListView extends IBaseView {
        void getFeedbackListResult(Response<viewFeedbackResponse> response);
        void getDataFailed();

    }
}
