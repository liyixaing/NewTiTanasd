package lanjing.com.titan.api;

import lanjing.com.titan.request.AddFeedbackRequest;
import lanjing.com.titan.request.AddressRequest;
import lanjing.com.titan.request.AwardRequest;
import lanjing.com.titan.request.BillDetailRequest;
import lanjing.com.titan.request.BindContactsRequest;
import lanjing.com.titan.request.BuyOrSellRequest;
import lanjing.com.titan.request.CancelRequest;
import lanjing.com.titan.request.CoinDealRequest;
import lanjing.com.titan.request.DealPwdRequest;
import lanjing.com.titan.request.DeleteWalletRequest;
import lanjing.com.titan.request.EntrustListRequest;
import lanjing.com.titan.request.FeedBackListRequest;
import lanjing.com.titan.request.FeedBackRequest;
import lanjing.com.titan.request.FormatRequest;
import lanjing.com.titan.request.FriendListRequest;
import lanjing.com.titan.request.ImportWalletRequest;
import lanjing.com.titan.request.ImportWalletSetPwdRequest;
import lanjing.com.titan.request.InfoNoticeRequest;
import lanjing.com.titan.request.InterDealRequest;
import lanjing.com.titan.request.ListWalletImportRequest;
import lanjing.com.titan.request.LoginRequest;
import lanjing.com.titan.request.ModifyHeadRequest;
import lanjing.com.titan.request.ModifyNicknameRequest;
import lanjing.com.titan.request.RecallRequest;
import lanjing.com.titan.request.RegisterRequest;
import lanjing.com.titan.request.SaveWalletRequest;
import lanjing.com.titan.request.SendCodeRequest;
import lanjing.com.titan.request.SetHelpRequest;
import lanjing.com.titan.request.SetNewPhoneRequest;
import lanjing.com.titan.request.SetPhoneRequest;
import lanjing.com.titan.request.UpdatePwdRequest;
import lanjing.com.titan.request.UplodelRequest;
import lanjing.com.titan.request.VersionRequest;
import lanjing.com.titan.request.WalletDetailRequest;
import lanjing.com.titan.request.WithdrawRequest;
import lanjing.com.titan.request.WithdrawalRequest;
import lanjing.com.titan.request.SaveOrUpdateRequest;
import lanjing.com.titan.request.deleterRequest;
import lanjing.com.titan.request.getTransferRequest;
import lanjing.com.titan.response.AddressListResponse;
import lanjing.com.titan.response.AgreementResponse;
import lanjing.com.titan.response.AwardResponse;
import lanjing.com.titan.response.BillDetailResponse;
import lanjing.com.titan.response.ChargeResponse;
import lanjing.com.titan.response.CoinDealResponse;
import lanjing.com.titan.response.DealPwdHelpResponse;
import lanjing.com.titan.response.DealPwdKeyResponse;
import lanjing.com.titan.response.EntrustListResponse;
import lanjing.com.titan.response.ExemptionResponse;
import lanjing.com.titan.response.FeedbackListResponse;
import lanjing.com.titan.response.FriendListResponse;
import lanjing.com.titan.response.ImportWalletResponse;
import lanjing.com.titan.response.InfoNoticeResponse;
import lanjing.com.titan.response.InformationResponse;
import lanjing.com.titan.response.InterDealResponse;
import lanjing.com.titan.response.ListWalletImportResponse;
import lanjing.com.titan.response.LoginResponse;
import lanjing.com.titan.response.MarketListResponse;
import lanjing.com.titan.response.ModifyHeadResponse;
import lanjing.com.titan.response.PersonResponse;
import lanjing.com.titan.response.RegisterResponse;
import lanjing.com.titan.response.Responseuplode;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.SetHelpResponse;
import lanjing.com.titan.response.SetNewPhoneResponse;
import lanjing.com.titan.response.SetPhoneResponse;
import lanjing.com.titan.response.SixTradeResponse;
import lanjing.com.titan.response.VersionResponse;
import lanjing.com.titan.response.WalletDataResponse;
import lanjing.com.titan.response.WalletDetailResponse;
import lanjing.com.titan.response.WalletListResponse;
import lanjing.com.titan.response.WithdrawalResponse;
import lanjing.com.titan.response.getTransferResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
//    @Headers({
//            "Content-Type: application/json",
//            "Accept: application/json",
//    })


    /**
     * 获取验证码
     */
    @POST("/app/sendcode")
    Call<ResultDTO> sendCode(@Header("token") String token, @Body SendCodeRequest data);

    /**
     * 注册
     */
    @POST("/app/register")
    Call<RegisterResponse> register(@Body RegisterRequest data);

    /**
     * 设置助记词
     */
    @POST("/app/sethelp")
    Call<SetHelpResponse> setHelp(@Body SetHelpRequest data);


    /**
     * 登录
     */
    @POST("/app/login")
    Call<LoginResponse> login(@Body LoginRequest data);


    /**
     * 绑定联系人
     */
    @POST("/app/setrecommend")
    Call<ResultDTO> bindingPeople(@Header("token") String token, @Body BindContactsRequest data);

    /**
     * 导入钱包
     */
    @POST("/app/isfindpassword")
    Call<ImportWalletResponse> importWallet(@Body ImportWalletRequest data);

    /**
     * 导入钱包  修改登录密码和交易密码
     */
    @POST("/app/findpassword")
    Call<ResultDTO> importWalletSetPwd(@Body ImportWalletSetPwdRequest data);


    /**
     * 获取用户个人信息
     */
    @POST("/app/getperson")
    Call<PersonResponse> getPerson(@Header("token") String token);

    /**
     * 修改头像
     */
    @POST("/app/uploadImg")
    Call<Responseuplode> modifyHead(@Header("token") String token, @Body ModifyHeadRequest data);

    /**
     * 删除图片
     */
    @POST("/app/cancelFeedback")
    Call<ResultDTO> cancelFeedback(@Header("token") String token, @Body CancelRequest data);

    /**
     * 建议反馈2
     */
    @POST("/app/addFeedback")
    Call<ResultDTO> addFeedback(@Header("token") String token, @Body AddFeedbackRequest data);

    /**
     * 上传头像
     */
    @POST("/app/updateUserAvatar")
    Call<ResultDTO> updateUserAvatar(@Header("token") String token, @Body UplodelRequest data);

    /**
     * 修改昵称
     */
    @POST("/app/uptnickname")
    Call<ResultDTO> modifyNickname(@Header("token") String token, @Body ModifyNicknameRequest data);


    /**
     * 绑定手机号
     */
    @POST("/app/setphone")
    Call<SetPhoneResponse> setPhone(@Header("token") String token, @Body SetPhoneRequest data);

    /**
     * 新绑定手
     */
    @POST("/app/unbindPhone")
    Call<SetNewPhoneResponse> setNewPhone(@Header("token") String token, @Body SetNewPhoneRequest data);

    /**
     * 修改密码  交易和登录密码
     */
    @POST("/app/uptpassword")
    Call<ResultDTO> updatePwd(@Header("token") String token, @Body UpdatePwdRequest data);

    /**
     * 我的邀请  获取好友列表
     */
    @POST("/app/getRecommend")
    Call<FriendListResponse> friendList(@Header("token") String token, @Body FriendListRequest data);

    /**
     * 行情数据列表
     */
    @POST("/app/getmarket")
    Call<MarketListResponse> marketList(@Header("token") String token);

    /**
     * 资讯公告列表
     */
    @POST("/app/getinformation")
    Call<InfoNoticeResponse> InfoNoticeList(@Header("token") String token, @Body InfoNoticeRequest data);

    /**
     * 钱包主页数据
     */
    @POST("/app/getwellet")
    Call<WalletDataResponse> walletData(@Header("token") String token);

    /**
     * 交易密碼
     */
    @POST("/app/VerificationTradepassword")
    Call<ResultDTO> dealPwd(@Header("token") String token, @Body DealPwdRequest data);

    /**
     * 交易密碼 导出助记词
     */
    @POST("/app/gethelp")
    Call<DealPwdHelpResponse> dealPwdHelp(@Header("token") String token, @Body DealPwdRequest data);

    /**
     * 交易密碼 导出私钥
     */
    @POST("/app/getwelletkeys")
    Call<DealPwdKeyResponse> dealPwdKey(@Header("token") String token, @Body DealPwdRequest data);


    /**
     * 获取钱包详情
     */
    @POST("/app/getwelletdetail")
    Call<WalletDetailResponse> welletDetail(@Header("token") String token, @Body WalletDetailRequest data);

    /**
     * 获取账单详情
     */
    @POST("/app/gethistorydetail")
    Call<BillDetailResponse> billDetail(@Header("token") String token, @Body BillDetailRequest data);

    /**
     * 获取充币
     */
    @POST("/app/charge")
    Call<ChargeResponse> charge(@Header("token") String token);

    /**
     * 提币
     */
    @POST("/app/withdraw")
    Call<ResultDTO> withdraw(@Header("token") String token, @Body WithdrawRequest data);

//    /**
//     * 钱包列表
//     */
//    @POST("/app/getwelletlist")
//    Call< WalletListResponse> walletList(@Header("token") String token);

    /**
     * 钱包列表
     */
    @POST("/app/getwelletlist")
    Call<WalletListResponse> walletList(@Header("token") String token);


    /**
     * 钱包列表  导入钱包
     */
    @POST("/app/importwellet")
    Call<ListWalletImportResponse> importWallet(@Header("token") String token, @Body ListWalletImportRequest data);

    /**
     * 钱包列表  修改钱包名称
     */
    @POST("/app/savawelletname")
    Call<ResultDTO> savaWalletName(@Header("token") String token, @Body SaveWalletRequest data);


    /**
     * 钱包列表  删除钱包
     */
    @POST("/app/delwellet")
    Call<ResultDTO> deleteWallet(@Header("token") String token, @Body DeleteWalletRequest data);


    /**
     * 获取币币交易  智能交易
     */
    @POST("/app/IsautoTrade")
    Call<InterDealResponse> interDeal(@Header("token") String token, @Body InterDealRequest data);

    /**
     * 获取币币交易  右侧行情信息
     */
    @POST("/app/getmappingtrade")
    Call<SixTradeResponse> rigehtSixInfo(@Header("token") String token);


    /**
     * 获取币币交易  委托列表  历史列表  数据查询
     */
    @POST("/app/getusermaptradelist")
    Call<EntrustListResponse> entrustList(@Header("token") String token, @Body EntrustListRequest data);

    /**
     * 获取币币交易  撤销委托
     */
    @POST("/app/recall")
    Call<ResultDTO> recallEntrust(@Header("token") String token, @Body RecallRequest data);

    /**
     * 获取币币交易  买卖
     */
    @POST("/app/trade")
    Call<ResultDTO> buyOrSell(@Header("token") String token, @Body BuyOrSellRequest data);

    /**
     * 服务协议
     */
    @POST("/app/getAgreement")
    Call<AgreementResponse> getAgreement();

    /**
     * 免责声明
     */
    @POST("/app/getExemption")
    Call<ExemptionResponse> getExemption(@Header("token") String token);


    /**
     * 账单明细  充值提现
     */
    @POST("/app/CZTX")
    Call<WithdrawalResponse> withdrawal(@Header("token") String token, @Body WithdrawalRequest data);

    /**
     * 账单明细  币币交易
     */
    @POST("/app/bbjy")
    Call<CoinDealResponse> coinDeal(@Header("token") String token, @Body CoinDealRequest data);

    /**
     * 账单明细  奖励
     */
    @POST("/app/reward")
    Call<AwardResponse> award(@Header("token") String token, @Body AwardRequest data);

    /**
     * 治理委员
     */
    @POST("/app/getinformation")
    Call<InformationResponse> Information(@Header("token") String token, @Body FormatRequest data);

    /**
     * 建议反馈
     */
    @POST("/app/feedback")
    Call<ResultDTO> feedback(@Header("token") String token, @Body FeedBackRequest data);


    /**
     * 建议反馈  列表
     */
    @POST("/app/feedbacklist")
    Call<FeedbackListResponse> feedbackList(@Header("token") String token, @Body FeedBackListRequest data);

    /**
     * 拉去提币地址列表  @Body AddressRequest data
     */
    @POST("/app/getTransferAddressList")
    Call<AddressListResponse> addressList(@Header("token") String token, @Body AddressRequest data);

    /**
     * 添加或修改提币地址
     */
    @POST("/app/saveOrUpdateTransferAddress")
    Call<ResultDTO> saveOrUpdateTransferAddress(@Header("token") String token, @Body SaveOrUpdateRequest data);

    /**
     * 拉取提币地址详情
     */
    @POST("/app/getTransferAddressDetail")
    Call<getTransferResponse> getTransferAddressDetail(@Header("token") String token, @Body getTransferRequest data);


    /**
     * 删除提币地址
     */
    @POST("/app/deleteTransferAddress")
    Call<ResultDTO> deleteTransferAddress(@Header("token") String token, @Body deleterRequest data);

    /**
     * 版本更新
     */
    @POST("/version/findVersion")
    Call<VersionResponse> updateApp(@Body VersionRequest data);


}
