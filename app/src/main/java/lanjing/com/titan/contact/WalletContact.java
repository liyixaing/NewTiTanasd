package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import lanjing.com.titan.net.NetCallBack;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.request.DeleteWalletRequest;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.WalletListResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 * 钱包列表
 */

public class WalletContact {
    public static class WalletPresent extends BasePresent<IWalletView> {

        public void walletList(final Context context) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.walletList(token).enqueue(new NetCallBack<WalletListResponse>() {
                @Override
                public void onSuccess(Call<WalletListResponse> call, Response<WalletListResponse> response) {
                    if (getView() != null) {
                        getView().getWalletListResult(response);
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
        //删除钱包
        public void deleteWallet(final Context context,String tokens) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            DeleteWalletRequest request = new DeleteWalletRequest(tokens);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.deleteWallet(token,request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getDeleteWalletResult(response);
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

    public interface IWalletView extends IBaseView {
        void getWalletListResult(Response<WalletListResponse> response);
        void getDeleteWalletResult(Response<ResultDTO> response);

        void getDataFailed();

    }
}
