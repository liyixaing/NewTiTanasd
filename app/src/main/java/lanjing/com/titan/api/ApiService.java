package lanjing.com.titan.api;

import lanjing.com.titan.request.AwardRequest;
import lanjing.com.titan.request.BillDetailRequest;
import lanjing.com.titan.request.BindContactsRequest;
import lanjing.com.titan.request.BuyOrSellRequest;
import lanjing.com.titan.request.CoinDealRequest;
import lanjing.com.titan.request.DealPwdRequest;
import lanjing.com.titan.request.DeleteWalletRequest;
import lanjing.com.titan.request.EntrustListRequest;
import lanjing.com.titan.request.FeedBackListRequest;
import lanjing.com.titan.request.FeedBackRequest;
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
import lanjing.com.titan.request.VersionRequest;
import lanjing.com.titan.request.WalletDetailRequest;
import lanjing.com.titan.request.WithdrawRequest;
import lanjing.com.titan.request.WithdrawalRequest;
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
import lanjing.com.titan.response.InterDealResponse;
import lanjing.com.titan.response.ListWalletImportResponse;
import lanjing.com.titan.response.LoginResponse;
import lanjing.com.titan.response.MarketListResponse;
import lanjing.com.titan.response.ModifyHeadResponse;
import lanjing.com.titan.response.PersonResponse;
import lanjing.com.titan.response.RegisterResponse;
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
    @POST("/app/uploaduserpicture")
    Call<ModifyHeadResponse> modifyHead(@Header("token") String token, @Body ModifyHeadRequest data);

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
     * 版本更新
     */
    @POST("/version/findVersion")
    Call<VersionResponse> updateApp(@Body VersionRequest data);


//    /**
//     * 获取验证码
//     */
//    @POST("/user/sendSMS")
//    Call<ResultDTO> sendCode(@Body SendCodeRequest data);
//

//
//    /**
//     * 自动登录
//     */
//    @POST("/user/login")
//    Call<ResultDTO> antuLogin(@Body LoginRequest data);
//

//
//    /**
//     * 注册协议
//     */
//    @POST("/user/protocol")
//    Call<RegisterAgreementResponse> registerAgreement(@Header("token") String token);
//
//    /**
//     * 忘记登录密码
//     */
//    @POST("/user/forgetLoginPwd")
//    Call<ResultDTO> forgetLoginPwd(@Body ForgetLoginPwdRequest request);
//
//    /**
//     * 行情
//     */
//    @POST("/notice/list")
//    Call<MarketResponse> market(@Header("token") String token, @Body MarketRequest data);
//
//    /**
//     * 资讯和公告
//     */
//    @POST("/notice/list")
//    Call<InformationAndNoticeResponse> informationAndNotice(@Header("token") String token, @Body MarketRequest data);
//
//
//    /**
//     * 修改个人资料  头像  昵称
//     */
//    @POST("/user/updateUserInfo")
//    Call<ModifyPersonDetailResponse> updateUserInfo(@Header("token") String token, @Body UpdateUserInfoRequest data);
//
//    /**
//     * 我的信鸽  首页
//     */
//    @POST("/homingPigeon/home")
//    Call<MyPigeonResponse> myPigeon(@Header("token") String token, @Body RequestDTO data);
//
//    /**
//     * 我的信鸽  交易记录
//     */
//    @POST("/homingPigeon/transferList")
//    Call<MyPigeonTransferListResponse> myPigeonTransferList(@Header("token") String token, @Body MyPigeonTransferListRequest data);
//
//    /**
//     * 我的信鸽  详情
//     */
//    @POST("/homingPigeon/homingPigeonlist")
//    Call<MyPigeonDetailResponse> myPigeonDetail(@Header("token") String token, @Body MyPigeonDetailRequest data);
//
//    /**
//     * 我的信鸽  信鸽收益记录
//     */
//    @POST("/homingPigeon/incomeList")
//    Call<MyPigeonIncomeListResponse> myPigeonIncomeList(@Header("token") String token, @Body MyPigeonIncomeListRequest data);
//
//
//    /**
//     * 我的信鸽  信鸽收益记录  转出
//     */
//    @POST("/homingPigeon/transferOutHome")
//    Call<MyPigeonGetServiceChargeResponse> myPigeonGetServiceCharge(@Header("token") String token, @Body MyPigeonGetServiceChargeRequest data);
//
//    /**
//     * 我的信鸽  信鸽收益记录  转出
//     */
//    @POST("/homingPigeon/transferOut")
//    Call<ResultDTO> myPigeonIncomeOut(@Header("token") String token, @Body MyPigeonIncomeOutRequest data);
//
//    /**
//     * 我的信鸽  信鸽收益记录  转换
//     */
//    @POST("/homingPigeon/transferChange")
//    Call<ResultDTO> myPigeonIncomeChange(@Header("token") String token, @Body MyPigeonIncomeChangeRequest data);
//
//    /**
//     * 我的社区  主页
//     */
//    @POST("/community/home")
//    Call<MyCommunityResponse> myCommunity(@Header("token") String token, @Body RequestDTO data);
//
//    /**
//     * 我的社区  我的推广
//     */
//    @POST("/community/myInvite")
//    Call<MyCommunityInviteResponse> myCommunityInvite(@Header("token") String token, @Body MyCommunityInviteRequest data);
//
//    /**
//     * 我的社区  团队业绩
//     */
//    @POST("/community/myGroup")  //请求实体和推广是一样的，所以公用
//    Call<MyCommunityGroupResponse> myCommunityGroup(@Header("token") String token, @Body MyCommunityInviteRequest data);
//
//    /**
//     * 我的社区  收益明细
//     */
//    @POST("/community/inComeList")
//    Call<MyCommunityIncomeDeatilResponse> myCommunityinComeDetail(@Header("token") String token, @Body MyCommunityIncomeDetailRequest data);
//
//
//    /**
//     * 我的社区  收益提取  页面数据获取
//     */
//    @POST("/community/incomePickUpHome")
//    Call<MyCommunityIncomeToExtractDataResponse> myCommunityInComeToExtractData(@Header("token") String token, @Body RequestDTO data);
//
//
//    /**
//     * 我的社区  收益提取  方法
//     */
//    @POST("/community/incomePickUp")
//    Call<ResultDTO> myCommunityInComeToExtract(@Header("token") String token, @Body MyCommunityIncomeToExtractRequest data);
//
//
//    /**
//     * 我的社区  收益理财  页面数据获取
//     */
//    @POST("/community/inComeFinancingHome")
//    Call<MyCommunityInComeFinancingHomeResponse> myCommunityInComeFinancingPageData(@Header("token") String token, @Body RequestDTO data);
//
//    /**
//     * 我的社区  收益理财  方法
//     */
//    @POST("/community/inComeFinancing")
//    Call<ResultDTO> myCommunityInComeFinancing(@Header("token") String token, @Body MyCommunityInComeFinancingRequest data);
//
//
//    /**
//     * 我的社区  游戏币兑换
//     */
//    @POST("/community/exchangeGameCurrency")
//    Call<ResultDTO> myCommunityExchangeGameCurrency(@Header("token") String token, @Body MyCommunityExchangeGameCurrencyRequest data);
//
//    /**
//     * 修改手机号
//     */
//    @POST("/user/updatePhone")
//    Call<ResultDTO> modifyPhone(@Header("token") String token, @Body ModifyPhoneRequest data);
//
//    /**
//     * 实名认证
//     */
//    @POST("/user/verifyIdCard")
//    Call<RealNameResponse> realName(@Header("token") String token, @Body RealNameRequest data);
//
//    /**
//     * 保存支付方式   银行卡   微信    支付宝
//     */
//    @POST("/user/savePayInfo")
//    Call<ResultDTO> addPay(@Header("token") String token, @Body AddPayRequest data);
//
//    /**
//     * 查看支付方式   银行卡   微信    支付宝
//     */
//    @POST("/user/queryPayInfo")
//    Call<QueryPayResponse> queryPay(@Header("token") String token, @Body QueryPayRequest data);
//
//    /**
//     * 保存反馈问题
//     */
//    @POST("/user/saveFeedback")
//    Call<ResultDTO> saveQuestion(@Header("token") String token, @Body SaveQuestionBackRequest data);
//
//    /**
//     * 关于我们
//     */
//    @POST("/user/url")
//    Call<AboutUsResponse> aboutUs(@Header("token") String token);
//
//
//    /**
//     * 创建钱包
//     */
//    @POST("/user/createWallet")
//    Call<CreateWalletResponse> createWallet(@Header("token") String token, @Body CreateWalletRequest data);
//
//    /**
//     * 创建钱包校验助记词
//     */
//    @POST("/user/regEthMnemonic")
//    Call<ResultDTO> regEthMnemonic(@Header("token") String token, @Body RegEthMnemonicRequest data);
//
//
//    /**
//     * 恢复钱包    返回值与导出是一样的，所以用一个返回值
//     */
//    @POST("/user/importWallet")
//    Call<ExportWalletResponse> recoverWallet(@Header("token") String token, @Body RecoverWalletRequest data);
//
//    /**
//     * 修改登录密码
//     */
//    @POST("/user/updateLoginPwd")
//    Call<ResultDTO> modifyLoginPwd(@Header("token") String token, @Body ModifyLoginPwdRequest data);
//
//    /**
//     * 修改交易密码
//     */
//    @POST("/user/updatePayPwd")
//    Call<ResultDTO> modifyDealPwd(@Header("token") String token, @Body ModifyDealPwdRequest data);
//
//    /**
//     * 隐私模式开关
//     */
//    @POST("/user/updateDisplay")
//    Call<UpdateDisplayResponse> updateDisplay(@Header("token") String token, @Body UpdateDisplayRequest data);
//
//    /**
//     * 资产
//     */
//    @POST("/user/purse")
//    Call<AssetResponse> queryAsset(@Header("token") String token, @Body RequestDTO data);
//
//
//    /**
//     * 资产  转账
//     */
//    @POST("/transfer/toTransfer")
//    Call<ResultDTO> assetTransfer(@Header("token") String token, @Body AssetTransferRequest data);
//
//    /**
//     * 资产  转账记录
//     */
//    @POST("/transfer/list")
//    Call<AssetListResponse> assetList(@Header("token") String token, @Body AssetListRequest data);
//
//    /**
//     * 资产  转账记录   转账详情
//     */
//    @POST("/transfer/detail")
//    Call<AssetListDetailResponse> assetListDetail(@Header("token") String token, @Body AssetListDetailRequest data);
//
//
//    //修改钱包地址
//    @POST("/user/exportWallet")
//    Call<ExportWalletResponse> exportWallet(@Header("token") String token, @Body ExportWalletRequest data);
//
//    /**
//     * 信鸽   轮播图
//     */
//    @POST("/notice/list")
//    Call<PigeonBannerResponse> pigeonBanner(@Header("token") String token, @Body MarketRequest data);
//
//    /**
//     * 开启前查询信鸽信息
//     */
//    @POST("/homingPigeon/queryHomingPigeon")
//    Call<QueryHomingPigeonResponse> queryHomingPigeon(@Header("token") String token, @Body RequestDTO data);
//
//
//    /**
//     * 开启信鸽
//     */
//    @POST("/homingPigeon/save")
//    Call<ResultDTO> savePigeon(@Header("token") String token, @Body SavePigeonRequest data);
//
//    /**
//     * 获取旷工费
//     */
//    @POST("/json/ethgasAPI.json")
//    Call<EthResponse> getEth();

}
