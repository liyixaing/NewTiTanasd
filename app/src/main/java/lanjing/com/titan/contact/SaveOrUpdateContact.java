package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.SaveOrUpdateRequest;
import lanjing.com.titan.request.WithdrawRequest;
import lanjing.com.titan.request.WithdrawalRequest;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.WithdrawalResponse;
import retrofit2.Call;
import retrofit2.Response;

public class SaveOrUpdateContact {
    public static class saveOrUpdatePresent extends BasePresent<saveOrUpdateView> {
        public void SaveorupdeatDetail(final Context context, String phone, String vcode, String address, String tag, String remark) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            SaveOrUpdateRequest request = new SaveOrUpdateRequest(phone, vcode, address, tag, remark);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.saveOrUpdateTransferAddress(token, request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getSaveorupdeat(response);
                    }
                }

                @Override
                public void onFailed() {
                    getView().getDataFailed();
                }
            });

        }


        //提币
        public void walletWithdraw(final Context context, String address, String walletId, String num) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            WithdrawRequest request = new WithdrawRequest("1", address, walletId, num);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.withdraw(token, request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getWalletWithdrawResult(response);
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

    public interface saveOrUpdateView extends IBaseView {
        void getSaveorupdeat(Response<ResultDTO> response);

        void getWalletWithdrawResult(Response<ResultDTO> response);

        void getDataFailed();

    }
}
