package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;

import lanjing.com.titan.net.NetCallBack;

import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.request.BillDetailRequest;
import lanjing.com.titan.request.SellOrderRequest;
import lanjing.com.titan.response.BillDetailResponse;
import lanjing.com.titan.response.SellOrderDetailResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 * 获取钱包详情数据
 */

public class BillDetailContact {
    public static class BillDetailPresent extends BasePresent<IBillDetailView> {
        public void billDetail(final Context context, String logId) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            BillDetailRequest request = new BillDetailRequest(logId);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.billDetail(token, request).enqueue(new NetCallBack<BillDetailResponse>() {
                @Override
                public void onSuccess(Call<BillDetailResponse> call, Response<BillDetailResponse> response) {
                    if (getView() != null) {
                        getView().getBillDeatilResult(response);
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

        public void SellOrderDetail(final Context context, String orderId) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            SellOrderRequest request = new SellOrderRequest(orderId);
            String token = SPUtils.getString(Constant.TOKEN, "", context);
            service.getSellOrderDetail(token, request).enqueue(new NetCallBack<SellOrderDetailResponse>() {
                @Override
                public void onSuccess(Call<SellOrderDetailResponse> call, Response<SellOrderDetailResponse> response) {
                    if (getView() != null) {
                        getView().getSellOrderDetail(response);
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

    public interface IBillDetailView extends IBaseView {
        void getBillDeatilResult(Response<BillDetailResponse> response);

        void getSellOrderDetail(Response<SellOrderDetailResponse> response);

        void getDataFailed();

    }
}
