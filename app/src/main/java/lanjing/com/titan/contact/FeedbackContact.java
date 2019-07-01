package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.AwardRequest;
import lanjing.com.titan.request.FeedBackRequest;
import lanjing.com.titan.response.AwardResponse;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 * 账单明细   币币交易
 */
public class FeedbackContact {
    public static class FeedbackPresent extends BasePresent<IFeedbackView> {
        public void feedback(final Context context, String content,String title) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            FeedBackRequest request = new FeedBackRequest(content,title);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.feedback(token,request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getFeedbackResult(response);
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

    public interface IFeedbackView extends IBaseView {
        void getFeedbackResult(Response<ResultDTO> response);
        void getDataFailed();

    }
}
