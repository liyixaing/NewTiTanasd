package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;

import lanjing.com.titan.net.NetCallBack;

import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.request.ActiveRequest;
import lanjing.com.titan.request.InfoNoticeRequest;
import lanjing.com.titan.request.VersionRequest;
import lanjing.com.titan.response.ActiveResponse;
import lanjing.com.titan.response.InfoNoticeResponse;
import lanjing.com.titan.response.PersonResponse;
import lanjing.com.titan.response.VersionResponse;
import lanjing.com.titan.response.WalletDataResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class WalletDataContact {
    public static class WalletDataPresent extends BasePresent<IWalletDataView> {

        public void walletData(final Context context) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.walletData(token).enqueue(new NetCallBack<WalletDataResponse>() {
                @Override
                public void onSuccess(Call<WalletDataResponse> call, Response<WalletDataResponse> response) {
                    if (getView() != null) {
                        getView().getWalletDataResult(response);
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

        public void notice(final Context context, String page, String size) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String type = "1";
            InfoNoticeRequest request = new InfoNoticeRequest(type, page, size);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.InfoNoticeList(token, request).enqueue(new NetCallBack<InfoNoticeResponse>() {
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

        public void person(final Context context) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.getPerson(token).enqueue(new NetCallBack<PersonResponse>() {
                @Override
                public void onSuccess(Call<PersonResponse> call, Response<PersonResponse> response) {
                    if (getView() != null) {
                        getView().getPersonResult(response);
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

        public void updateApp(final Context context, int type, int version) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            VersionRequest request = new VersionRequest(type, version);
            service.updateApp(request).enqueue(new NetCallBack<VersionResponse>() {
                @Override
                public void onSuccess(Call<VersionResponse> call, Response<VersionResponse> response) {
                    if (getView() != null) {
                        getView().getupdateAppResult(response);
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

        //激活
        public void ActiveCode(final Context context, String cdkey) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            ActiveRequest request = new ActiveRequest(cdkey);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.Active(token, request).enqueue(new NetCallBack<ActiveResponse>() {
                @Override
                public void onSuccess(Call<ActiveResponse> call, Response<ActiveResponse> response) {
                    if (getView() != null) {
                        getView().getActiveCode(response);
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

    public interface IWalletDataView extends IBaseView {
        void getWalletDataResult(Response<WalletDataResponse> response);

        void getNoticeResult(Response<InfoNoticeResponse> response);

        void getPersonResult(Response<PersonResponse> response);

        void getupdateAppResult(Response<VersionResponse> response);

        void getActiveCode(Response<ActiveResponse> response);

        void getDataFailed();

    }
}
