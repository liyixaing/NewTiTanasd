package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.Md5Utils;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.FeedBackListRequest;
import lanjing.com.titan.request.LoginRequest;
import lanjing.com.titan.response.FeedbackListResponse;
import lanjing.com.titan.response.LoginResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class FeedbackListContact {
    public static class FeedbackListPresent extends BasePresent<IFeedbackListView> {
        public void feedbackList(final Context context, String page, String pageSize) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            FeedBackListRequest request = new FeedBackListRequest(page, pageSize);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.feedbackList(token,request).enqueue(new NetCallBack<FeedbackListResponse>() {
                @Override
                public void onSuccess(Call<FeedbackListResponse> call, Response<FeedbackListResponse> response) {
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
        void getFeedbackListResult(Response<FeedbackListResponse> response);
        void getDataFailed();

    }
}
