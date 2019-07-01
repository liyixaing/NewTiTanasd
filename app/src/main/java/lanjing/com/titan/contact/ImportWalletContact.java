package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import lanjing.com.titan.net.NetCallBack;
import com.lxh.baselibray.net.ServiceGenerator;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.request.ImportWalletRequest;
import lanjing.com.titan.response.ImportWalletResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class ImportWalletContact {
    public static class ImportWalletPresent extends BasePresent<IImportWalletView> {
        public void importWallet(final Context context, String help, String keys) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            ImportWalletRequest request = new ImportWalletRequest(help, keys);
            service.importWallet(request).enqueue(new NetCallBack<ImportWalletResponse>() {
                @Override
                public void onSuccess(Call<ImportWalletResponse> call, Response<ImportWalletResponse> response) {
                    if (getView() != null) {
                        getView().getImportWalletResult(response);
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

    public interface IImportWalletView extends IBaseView {
        void getImportWalletResult(Response<ImportWalletResponse> response);
        void getDataFailed();

    }
}
