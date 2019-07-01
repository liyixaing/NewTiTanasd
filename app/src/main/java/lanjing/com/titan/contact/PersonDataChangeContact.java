package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import lanjing.com.titan.net.NetCallBack;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.request.ModifyHeadRequest;
import lanjing.com.titan.request.ModifyNicknameRequest;
import lanjing.com.titan.response.ModifyHeadResponse;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */

public class PersonDataChangeContact {
    public static class PersonDataChangePresent extends BasePresent<IPersonDataChangeView> {
        //修改头像
        public void modifyHead(final Context context,String picture) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            ModifyHeadRequest request = new ModifyHeadRequest(picture);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.modifyHead(token,request).enqueue(new NetCallBack<ModifyHeadResponse>() {
                @Override
                public void onSuccess(Call<ModifyHeadResponse> call, Response<ModifyHeadResponse> response) {
                    if (getView() != null) {
                        getView().getmodifyHeadResult(response);
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
        //修改昵称
        public void modifyNickname(final Context context,String nickName) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            ModifyNicknameRequest request = new ModifyNicknameRequest(nickName);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.modifyNickname(token,request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getmodifyNicknameResult(response);
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

    public interface IPersonDataChangeView extends IBaseView {
        void getmodifyHeadResult(Response<ModifyHeadResponse> response);
        void getmodifyNicknameResult(Response<ResultDTO> response);
        void getDataFailed();

    }
}
