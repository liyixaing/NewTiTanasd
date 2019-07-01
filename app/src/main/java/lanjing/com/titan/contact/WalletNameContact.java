package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import lanjing.com.titan.net.NetCallBack;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.request.SaveWalletRequest;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 * 钱包名称
 */

public class WalletNameContact {
    public static class WalletNamePresent extends BasePresent<IWalletNameView> {
        public void walletName(final Context context,String welletname) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            SaveWalletRequest request = new SaveWalletRequest(welletname);
            String token = SPUtils.getString(Constant.TOKEN2,"",context);
            service.savaWalletName(token,request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getWalletNameResult(response);
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

    public interface IWalletNameView extends IBaseView {
        void getWalletNameResult(Response<ResultDTO> response);
        void getDataFailed();

    }
}
