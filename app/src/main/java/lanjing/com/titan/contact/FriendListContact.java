package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import lanjing.com.titan.net.NetCallBack;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.request.FriendListRequest;
import lanjing.com.titan.response.FriendListResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class FriendListContact {
    public static class FriendListPresent extends BasePresent<IFriendListView> {
        public void friendList(final Context context, String page, String size) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            FriendListRequest request = new FriendListRequest(page, size);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.friendList(token,request).enqueue(new NetCallBack<FriendListResponse>() {
                @Override
                public void onSuccess(Call<FriendListResponse> call, Response<FriendListResponse> response) {
                    if (getView() != null) {
                        getView().getFriendListResult(response);
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

    public interface IFriendListView extends IBaseView {
        void getFriendListResult(Response<FriendListResponse> response);
//        void getRegisterAgreementResult(Response<RegisterAgreementResponse> response);
        void getDataFailed();

    }
}
