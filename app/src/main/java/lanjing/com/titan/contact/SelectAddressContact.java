package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.AddressRequest;
import lanjing.com.titan.response.AddressListResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * 获取钱包列表
 */
public class SelectAddressContact {
    public static class SelectAddressPresent extends BasePresent<SelectView> {
        public void walletList(final Context context, String page, String size) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            AddressRequest request = new AddressRequest(page, size);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.addressList(token, request).enqueue(new NetCallBack<AddressListResponse>() {
                @Override
                public void onSuccess(Call<AddressListResponse> call, Response<AddressListResponse> response) {
                    if (getView() != null) {
                        getView().getSelect(response);
                    }
                }

                @Override
                public void onFailed() {
                    getView().getDataFailed();

                }
            });
        }

    }

    public interface SelectView extends IBaseView {
        void getSelect(Response<AddressListResponse> response);

        void getDataFailed();

    }
}
