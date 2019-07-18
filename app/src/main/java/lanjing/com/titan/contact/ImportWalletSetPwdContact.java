package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import lanjing.com.titan.net.NetCallBack;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.Md5Utils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.request.ImportWalletSetPwdRequest;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class ImportWalletSetPwdContact {
    public static class ImportWalletSetPwdPresent extends BasePresent<IImportWalletSetPwdView> {
        public void importWalletSetPwd(final Context context, Integer userId,String loginpassword, String tradepassword, String verificationCode) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            ImportWalletSetPwdRequest request = new ImportWalletSetPwdRequest(userId, Md5Utils.MD5(loginpassword),Md5Utils.MD5(tradepassword), verificationCode);
            service.importWalletSetPwd(request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getImportWalletSetPwdResult(response);
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

    public interface IImportWalletSetPwdView extends IBaseView {
        void getImportWalletSetPwdResult(Response<ResultDTO> response);
        void getDataFailed();

    }
}
