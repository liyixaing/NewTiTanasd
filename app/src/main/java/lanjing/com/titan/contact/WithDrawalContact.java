package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.BindContactsRequest;
import lanjing.com.titan.request.WithdrawalRequest;
import lanjing.com.titan.response.CoinLogListResponse;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.WithdrawalResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 * 账单明细   充值提现
 */
public class WithDrawalContact {
    public static class WithDrawalPresent extends BasePresent<IWithDrawalView> {
        public void withDrawal(final Context context, String coin, String type, String page, String size) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            WithdrawalRequest request = new WithdrawalRequest(coin, type, page, size);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.withdrawal(token, request).enqueue(new NetCallBack<WithdrawalResponse>() {
                @Override
                public void onSuccess(Call<WithdrawalResponse> call, Response<WithdrawalResponse> response) {
                    if (getView() != null) {
                        getView().getWithDrawalResult(response);
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

        public void CoinLogList(final Context context, String coin, String type, String page, String size) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            WithdrawalRequest request = new WithdrawalRequest(coin, type, page, size);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.CoinLogList(token, request).enqueue(new NetCallBack<CoinLogListResponse>() {
                @Override
                public void onSuccess(Call<CoinLogListResponse> call, Response<CoinLogListResponse> response) {
                    if (getView() != null) {
                        getView().getCoinLogList(response);
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

    public interface IWithDrawalView extends IBaseView {
        void getWithDrawalResult(Response<WithdrawalResponse> response);

        void getCoinLogList(Response<CoinLogListResponse> response);

        void getDataFailed();

    }
}
