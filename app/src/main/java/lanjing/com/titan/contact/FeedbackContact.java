package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.AddFeedbackRequest;
import lanjing.com.titan.request.AwardRequest;
import lanjing.com.titan.request.CancelRequest;
import lanjing.com.titan.request.FeedBackRequest;
import lanjing.com.titan.request.ModifyHeadRequest;
import lanjing.com.titan.response.AwardResponse;
import lanjing.com.titan.response.Responseuplode;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 * 建议反馈
 */
public class FeedbackContact {

    //意见反馈提交接口
    public static class FeedbackPresent extends BasePresent<IFeedbackView> {
        public void feedback(final Context context, String content, String title, String urls) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            FeedBackRequest request = new FeedBackRequest(content, title, urls);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.feedback(token, request).enqueue(new NetCallBack<ResultDTO>() {
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

        //上传图片
        public void uploadImage(final Context context, String picture, String type) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            ModifyHeadRequest request = new ModifyHeadRequest(picture, type);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.modifyHead(token, request).enqueue(new NetCallBack<Responseuplode>() {
                @Override
                public void onSuccess(Call<Responseuplode> call, Response<Responseuplode> response) {
                    if (getView() != null) {
                        getView().getmodifyHeadResult(response);
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

        //删除图片
        public void cancelImage(final Context context, String url) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            CancelRequest request = new CancelRequest(url);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.cancelFeedback(token, request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getcancelImage(response);
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

        //意见反馈修改版  新增建议反馈2
        public void addFeedback(final Context context, String content, String title, String pictures) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            AddFeedbackRequest request = new AddFeedbackRequest(content, title, pictures);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.addFeedback(token, request).enqueue(new NetCallBack<ResultDTO>() {
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

        void getmodifyHeadResult(Response<Responseuplode> response);

        void getcancelImage(Response<ResultDTO> response);

        void getDataFailed();

    }
}
