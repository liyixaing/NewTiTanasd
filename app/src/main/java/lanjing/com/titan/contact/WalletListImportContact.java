package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;

import lanjing.com.titan.net.NetCallBack;

import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.request.ListWalletImportRequest;
import lanjing.com.titan.response.ListWalletImportResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 * 钱包列表导入钱包
 */

public class WalletListImportContact {
    public static class WalletListImportPresent extends BasePresent<IWalletListImportView> {
        public void importWalletlist(final Context context, String help, String keys, String device) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            ListWalletImportRequest request = new ListWalletImportRequest(help, keys, device);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.importWallet(token, request).enqueue(new NetCallBack<ListWalletImportResponse>() {
                @Override
                public void onSuccess(Call<ListWalletImportResponse> call, Response<ListWalletImportResponse> response) {
                    if (getView() != null) {
                        getView().getImportWalletListResult(response);
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

    public interface IWalletListImportView extends IBaseView {
        void getImportWalletListResult(Response<ListWalletImportResponse> response);

        void getDataFailed();

    }
}
