package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import lanjing.com.titan.net.NetCallBack;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.request.EntrustListRequest;
import lanjing.com.titan.response.EntrustListResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */
//绑定联系人
public class HistoryContact {
    public static class HistoryPresent extends BasePresent<IHistoryView> {
        //币币交易   历史列表
        public void historyList(final Context context,String page,String size,String state) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            EntrustListRequest request = new EntrustListRequest(page,size,state);
            service.entrustList(token,request).enqueue(new NetCallBack<EntrustListResponse>() {
                @Override
                public void onSuccess(Call<EntrustListResponse> call, Response<EntrustListResponse> response) {
                    if (getView() != null) {
                        getView().getEntrustListResult(response);
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

    public interface IHistoryView extends IBaseView {
        void getEntrustListResult(Response<EntrustListResponse> response);//币币交易   委托列表   包括历史委托
        void getDataFailed();

    }
}
