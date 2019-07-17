package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import lanjing.com.titan.net.NetCallBack;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.request.InfoNoticeRequest;
import lanjing.com.titan.response.InfoNoticeResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 * 获取钱包详情数据
 */

public class InfoNoticeContact {
    public static class InfoNoticePresent extends BasePresent<IInfoNoticeView> {
        public void information(final Context context,String page,String size) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String type="2";
            InfoNoticeRequest request = new InfoNoticeRequest(type,page,size);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.InfoNoticeList(token,request).enqueue(new NetCallBack<InfoNoticeResponse>() {
                @Override
                public void onSuccess(Call<InfoNoticeResponse> call, Response<InfoNoticeResponse> response) {
                    if (getView() != null) {
                        getView().getInformationResult(response);
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

        public void notice(final Context context,String page,String size, String type) {
            ApiService service = ServiceGenerator.createService(ApiService.class);

            InfoNoticeRequest request = new InfoNoticeRequest(type,page,size);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.InfoNoticeList(token,request).enqueue(new NetCallBack<InfoNoticeResponse>() {
                @Override
                public void onSuccess(Call<InfoNoticeResponse> call, Response<InfoNoticeResponse> response) {
                    if (getView() != null) {
                        getView().getNoticeResult(response);
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

    public interface IInfoNoticeView extends IBaseView {
        void getInformationResult(Response<InfoNoticeResponse> response);
        void getNoticeResult(Response<InfoNoticeResponse> response);

        void getDataFailed();

    }
}
