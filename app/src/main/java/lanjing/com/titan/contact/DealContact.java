package lanjing.com.titan.contact;

import android.content.Context;

import com.lxh.baselibray.mvp.BasePresent;
import com.lxh.baselibray.mvp.IBaseView;
import lanjing.com.titan.net.NetCallBack;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.Md5Utils;
import com.lxh.baselibray.util.SPUtils;

import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.request.BuyOrSellRequest;
import lanjing.com.titan.request.DealPwdRequest;
import lanjing.com.titan.request.EntrustListRequest;
import lanjing.com.titan.request.InterDealRequest;
import lanjing.com.titan.request.RecallRequest;
import lanjing.com.titan.response.EntrustListResponse;
import lanjing.com.titan.response.InterDealResponse;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.SixTradeResponse;
import lanjing.com.titan.response.WalletDataResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by chenxi on 2019/4/12.
 */
//币币交易 主方法

public class DealContact {
    public static class DealPresent extends BasePresent<IDealView> {
        //智能交易
        public void interDeal(final Context context,Integer isAuto) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            InterDealRequest request = new InterDealRequest(isAuto);
            service.interDeal(token,request).enqueue(new NetCallBack<InterDealResponse>() {
                @Override
                public void onSuccess(Call<InterDealResponse> call, Response<InterDealResponse> response) {
                    if (getView() != null) {
                        getView().getInterDealResult(response);
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

        //右侧行情数据  两位显示
        public void rightSixList(final Context context) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.rigehtSixInfo(token).enqueue(new NetCallBack<SixTradeResponse>() {
                @Override
                public void onSuccess(Call<SixTradeResponse> call, Response<SixTradeResponse> response) {
                    if (getView() != null) {
                        getView().getDealSixResult(response);
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

        //右侧行情数据  一位显示
        public void rightSixListOne(final Context context) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.rigehtSixInfo(token).enqueue(new NetCallBack<SixTradeResponse>() {
                @Override
                public void onSuccess(Call<SixTradeResponse> call, Response<SixTradeResponse> response) {
                    if (getView() != null) {
                        getView().getDealSixOneResult(response);
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
        //右侧行情数据  零位显示
        public void rightSixListZero(final Context context) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.rigehtSixInfo(token).enqueue(new NetCallBack<SixTradeResponse>() {
                @Override
                public void onSuccess(Call<SixTradeResponse> call, Response<SixTradeResponse> response) {
                    if (getView() != null) {
                        getView().getDealSixZeroResult(response);
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

        //币币交易 TITAN的   可用  可用数量
        public void walletDataTitan(final Context context) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.walletData(token).enqueue(new NetCallBack<WalletDataResponse>() {
                @Override
                public void onSuccess(Call<WalletDataResponse> call, Response<WalletDataResponse> response) {
                    if (getView() != null) {
                        getView().getWalletDataTitanResult(response);
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
        //币币交易 USD   的   可用  可用数量
        public void walletDataUsd(final Context context) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            service.walletData(token).enqueue(new NetCallBack<WalletDataResponse>() {
                @Override
                public void onSuccess(Call<WalletDataResponse> call, Response<WalletDataResponse> response) {
                    if (getView() != null) {
                        getView().getWalletDataUsdResult(response);
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



        //币币交易   委托列表
        public void entrustList(final Context context,String page,String size,String state) {
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

        //币币交易   撤销委托
        public void recallEntrust(final Context context,String id) {
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            RecallRequest request = new RecallRequest(id);
            service.recallEntrust(token,request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if (getView() != null) {
                        getView().getRecallEntrustResult(response);
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

        //币币交易  买卖单
        public void buyOrSell(final Context context,String coinNum,String type){
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            BuyOrSellRequest request = new BuyOrSellRequest(coinNum,type);
            service.buyOrSell(token,request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if(getView() != null){
                        getView().getBuyOrSellResult(response);
                    }
                }

                @Override
                public void onFailed() {
                    if (getView() != null){
                        getView().getDataFailed();
                    }
                }
            });
        }




        //币币交易  验证买入密码
        public void dealPwdBuy(final Context context,String password){
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            DealPwdRequest request = new DealPwdRequest(Md5Utils.MD5(password));
            service.dealPwd(token,request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if(getView() != null){
                        getView().getDealPwdBuyResult(response);
                    }
                }

                @Override
                public void onFailed() {
                    if (getView() != null){
                        getView().getDataFailed();
                    }
                }
            });
        }

        //币币交易  验证卖出密码
        public void dealPwdSell(final Context context,String password){
            ApiService service = ServiceGenerator.createService(ApiService.class);
            String token = SPUtils.getString(Constant.TOKEN,"",context);
            DealPwdRequest request = new DealPwdRequest(Md5Utils.MD5(password));
            service.dealPwd(token,request).enqueue(new NetCallBack<ResultDTO>() {
                @Override
                public void onSuccess(Call<ResultDTO> call, Response<ResultDTO> response) {
                    if(getView() != null){
                        getView().getDealPwdSellResult(response);
                    }
                }

                @Override
                public void onFailed() {
                    if (getView() != null){
                        getView().getDataFailed();
                    }
                }
            });
        }

    }

    public interface IDealView extends IBaseView {
        void getInterDealResult(Response<InterDealResponse> response);//钱包主页数据获取
        void getWalletDataTitanResult(Response<WalletDataResponse> response);//钱包主页数据获取  TItan  可用
        void getWalletDataUsdResult(Response<WalletDataResponse> response);//钱包主页数据获取   Usd  可用
        void getDealSixResult(Response<SixTradeResponse> response);//右侧列表数据
        void getDealSixOneResult(Response<SixTradeResponse> response);//右侧列表数据 1 位
        void getDealSixZeroResult(Response<SixTradeResponse> response);//右侧列表数据  0位
        void getEntrustListResult(Response<EntrustListResponse> response);//币币交易   委托列表   包括历史委托
        void getRecallEntrustResult(Response<ResultDTO> response);//币币交易   撤销委托
        void getDealPwdBuyResult(Response<ResultDTO> response);//币币交易   买入验证交易密码
        void getDealPwdSellResult(Response<ResultDTO> response);//币币交易   卖出验证交易密码
        void getBuyOrSellResult(Response<ResultDTO> response);//币币交易   买卖单

        void getDataFailed();

    }
}
