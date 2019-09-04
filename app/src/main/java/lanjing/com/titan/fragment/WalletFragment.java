package lanjing.com.titan.fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxh.baselibray.dialog.AlertDialog;
import com.lxh.baselibray.mvp.MvpFragment;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.SizeUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.activity.AssetTITANActivity;
import lanjing.com.titan.activity.AssetUSDActivity;
import lanjing.com.titan.activity.FeedbackListActivity;
import lanjing.com.titan.activity.NoticeActivity;
import lanjing.com.titan.activity.TItancWaitGetActivity;
import lanjing.com.titan.activity.UsdAirdroppedActivity;
import lanjing.com.titan.activity.WalletListActivity;
import lanjing.com.titan.activity.WalletManagerActivity;
import lanjing.com.titan.appupdate.UpdateHelper;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.WalletDataContact;
import lanjing.com.titan.response.ActiveResponse;
import lanjing.com.titan.response.InfoNoticeResponse;
import lanjing.com.titan.response.PersonResponse;
import lanjing.com.titan.response.VersionResponse;
import lanjing.com.titan.response.WalletDataResponse;
import lanjing.com.titan.util.APKVersionCodeUtils;
import lanjing.com.titan.util.MoneyUtil;
import lanjing.com.titan.view.ScrollTextView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;

//钱包  页面
public class WalletFragment extends MvpFragment<WalletDataContact.WalletDataPresent> implements WalletDataContact.IWalletDataView {

    @BindView(R.id.list_notice_content)
    ScrollTextView listNoticeContent;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_wallet_id)
    TextView tvWalletId;
    @BindView(R.id.checkbox_private_mode)
    CheckBox checkboxPrivateMode;
    @BindView(R.id.titan_lay)
    LinearLayout titanLay;
    @BindView(R.id.usd_lay)
    LinearLayout usdLay;
    @BindView(R.id.tv_wallet_name)
    TextView tvWalletName;
    @BindView(R.id.titan_num)
    TextView titanNum;
    @BindView(R.id.titan_price)
    TextView titanPrice;
    @BindView(R.id.usd_num)
    TextView usdNum;
    @BindView(R.id.usd_price)
    TextView usdPrice;
    @BindView(R.id.titanc_num)
    TextView titancNum;
    @BindView(R.id.titanc_lay)
    LinearLayout titancLay;
    @BindView(R.id.usd2_num)
    TextView usd2Num;
    @BindView(R.id.tv_bar)
    TextView TvBar;
    @BindView(R.id.tv_barnum)
    TextView TvBarnum;
    @BindView(R.id.usd2_lay)
    LinearLayout usd2Lay;
    @BindView(R.id.manage_wallet)
    LinearLayout manageWallet;
    @BindView(R.id.rl_home_notice)
    RelativeLayout RlHomeNotice;//铃铛点击
    @BindView(R.id.ll_red_dot)
    LinearLayout LlRedDot;//小红点
    @BindView(R.id.ll_activation)
    LinearLayout ll_activation;//激活按钮

    int page = 1;
    int pageSize = 10;

    String titanId;//TITAN页面钱包id查询数据
    String usdId;//USD页面钱包id查询数据
    String titancId;//TITANC页面钱包id查询数据
    String usd2Id;//USD2页面钱包id查询数据
    String walletAddress;//钱包地址
    String labelAddress;//标签地址
    int versionCode;
    private List<String> title = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        versionCode = APKVersionCodeUtils.getVersionCode(context);
        mPresent.notice(context, String.valueOf(page), String.valueOf(pageSize));


        mPresent.updateApp(context, 1, versionCode);
//        initUpdate();
        mPresent.walletData(context);
        mPresent.person(context);
        tvWalletName.setText(SPUtils.getString(Constant.WALLET_NAME, null, context));

        listNoticeContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoticeActivity.class);
                intent.putExtra("type", "1");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
            }
        });

    }

    AlertDialog UpdateDialog = null;

    private void showUpdateDialog(String VersionName, String VersionInfo, String downloadUrl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .addDefaultAnimation()//默认弹窗动画
                .setContentView(R.layout.dialog_update_app)//载入布局文件
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)//设置弹窗宽高
                .setText(R.id.tv_title, getResources().getString(R.string.update_yes) + VersionName + getResources().getString(R.string.update_version))
                .setText(R.id.tv_update_info, VersionInfo)
                .setOnClickListener(R.id.btn_ok, v -> {//设置点击事件  打开网页
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            (Uri.parse(downloadUrl))
                    ).addCategory(Intent.CATEGORY_BROWSABLE)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    UpdateDialog.dismiss();
                });
        UpdateDialog = builder.create();
        UpdateDialog.show();

    }

    private void initUpdate() {
        //版本更新
        new RxPermissions(getActivity()).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(aBoolean -> {
            if (aBoolean) {
                new UpdateHelper(getActivity(), true).update();
            } else {
                ToastUtils.showShortToast(getActivity(), "权限未开启");
            }
        });
    }

    @Override
    public void onResume() {
        versionCode = APKVersionCodeUtils.getVersionCode(context);
        checkboxPrivateMode.setChecked(false);
        mPresent.updateApp(context, 1, versionCode);
        mPresent.walletData(context);
        super.onResume();
    }

    private void initShow() {
        mPresent.walletData(context);
    }

    private void initHide() {
        tvAmount.setText("****");
        titanNum.setText("****");
        titanPrice.setText("*****");
        usdNum.setText("****");
        usdPrice.setText("****");
        titancNum.setText("****");
        usd2Num.setText("****");
        TvBar.setText("****");
        TvBarnum.setText("****");

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wallet;
    }


    @OnClick({R.id.checkbox_private_mode, R.id.tv_wallet_name, R.id.manage_wallet, R.id.titan_lay,
            R.id.usd_lay, R.id.titanc_lay, R.id.usd2_lay, R.id.rl_home_notice, R.id.ll_bar, R.id.ll_activation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.checkbox_private_mode:
                if (checkboxPrivateMode.isChecked()) {
                    initHide();
                } else {
                    initShow();
                }
                break;
            case R.id.tv_wallet_name://进入  钱包列表  可以进行钱包的命名和切换  以及导入钱包  和  删除钱包
                Intent walletList = new Intent(context, WalletListActivity.class);
                walletList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(walletList);
                break;
            case R.id.manage_wallet://进入   钱包管理  可以导出私钥和助记词
                Intent intent = new Intent(context, WalletManagerActivity.class);
                intent.putExtra("walletAddress", walletAddress);
                intent.putExtra("labelAddress", labelAddress);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
                break;
            case R.id.titan_lay://进入  TITAN 的资产页面
                Intent titan = new Intent(context, AssetTITANActivity.class);
                titan.putExtra("walletId", titanId);
                titan.putExtra("coin", "1");
                titan.putExtra("type", "TITAN");
                titan.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(titan);
                break;
            case R.id.ll_bar://进入  BAR 的资产界面
                Intent bra = new Intent(context, AssetTITANActivity.class);
                bra.putExtra("walletId", titanId);
                bra.putExtra("coin", "5");
                bra.putExtra("type", "BAR");
                bra.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(bra);
                break;
            case R.id.usd_lay://进入   USD 的资产页面
                Intent usd = new Intent(context, AssetUSDActivity.class);
                usd.putExtra("walletId", usdId);
                usd.putExtra("coin", "3");
                usd.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(usd);
                break;
            case R.id.titanc_lay://进入    TITANC的待领取
                Intent titanc = new Intent(context, TItancWaitGetActivity.class);
                titanc.putExtra("walletId", titancId);
                titanc.putExtra("coin", "2");
                titanc.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(titanc);
                break;
            case R.id.usd2_lay://进入   USD的待空投
                Intent usd2 = new Intent(context, UsdAirdroppedActivity.class);
                usd2.putExtra("walletId", usd2Id);
                usd2.putExtra("coin", "4");
                usd2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(usd2);
                break;
            case R.id.rl_home_notice://反馈的铃铛
                Intent lingdang = new Intent(context, FeedbackListActivity.class);
                startActivity(lingdang);
                break;
            case R.id.ll_activation:
                ToastUtils.showLongToast(context, "激活");
                showactionDialog();
                break;
        }
    }


    //解决跟新地址重向问题
//    public void initOkhttp() {
//
//        Request request = new Request.Builder().url("http://www.titans.world/app/titan.apk").build();
//
//        OkHttpClient okHttpClient = new OkHttpClient();
//        new Thread(() -> {
//            try {
//
//                okhttp3.Response response = okHttpClient.newCall(request).execute();
//                response.request().url();
//                Log.e("xiaozaidiz", String.valueOf(response.request().url()));
//                System.out.print("Response:" + response.body().toString());
//                Log.e("xaioqaing", response.body().toString());
//            } catch (Exception e) {
//                Log.e("xiaqopiang", String.valueOf(e));
//            }
//        }).start();
//    }

    @Override
    protected WalletDataContact.WalletDataPresent createPresent() {
        return new WalletDataContact.WalletDataPresent();
    }


    /**
     * @param response
     */
    List<WalletDataResponse.DataBean.Wellets> mList;

    @Override
    public void getWalletDataResult(Response<WalletDataResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            mList = response.body().getData().wellets;
            tvAmount.setText("$" + MoneyUtil.formatFour(response.body().getData().getTotal_asset_usd()));
            tvWalletId.setText("ID：" + response.body().getData().getUser_tag());
            walletAddress = response.body().getData().getUser_address();
            labelAddress = response.body().getData().getUser_tag();
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).getCoin().equals("1")) {
                    titanNum.setText(MoneyUtil.formatFour(mList.get(i).getCoin_num()));
                    titanPrice.setText("$" + MoneyUtil.formatFour(mList.get(i).getCoin_usd_worth()));
                } else if (mList.get(i).getCoin().equals("2")) {
                    titancNum.setText(MoneyUtil.formatFour(mList.get(i).getCoin_num()));
                } else if (mList.get(i).getCoin().equals("3")) {
                    usdNum.setText(MoneyUtil.formatFour(mList.get(i).getCoin_num()));
                    usdPrice.setText("¥" + MoneyUtil.formatFour(mList.get(i).getCoin_cny_worth()));
                } else if (mList.get(i).getCoin().equals("4")) {
                    usd2Num.setText(MoneyUtil.formatFour(mList.get(i).getCoin_num()));
                } else if (mList.get(i).getCoin().equals("5")) {
                    TvBar.setText(MoneyUtil.formatFour(mList.get(i).getCoin_num()));
                    TvBarnum.setText("$" + MoneyUtil.formatFour(mList.get(i).getCoin_usd_worth()));
                }
            }


        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }

    }
    //公告列表

    @Override
    public void getNoticeResult(Response<InfoNoticeResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            Locale locale = getResources().getConfiguration().locale;
            if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                List<InfoNoticeResponse.DataCHBean> data = response.body().getDataCH();
                if (ObjectUtils.isEmpty(data)) return;
                title.clear();
                for (int i = 0; i < data.size(); i++) {
                    title.add(data.get(i).getTitle());
                }
                listNoticeContent.setList(title);
                listNoticeContent.startScroll();
            } else if (locale.equals(Locale.ENGLISH)) {
                List<InfoNoticeResponse.DataEHBean> data = response.body().getDataEH();
                if (ObjectUtils.isEmpty(data)) return;
                title.clear();
                for (int i = 0; i < data.size(); i++) {
                    title.add(data.get(i).getTitle());
                }
                listNoticeContent.setList(title);
                listNoticeContent.startScroll();
            }
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getPersonResult(Response<PersonResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            SPUtils.putString(Constant.PHONE, response.body().getData().getPhone(), context);
            SPUtils.putString(Constant.WALLET_NAME, response.body().getData().getUsername(), context);
            SPUtils.putString(Constant.PORTRAIT, response.body().getData().getPicture(), context);
            String walletName = response.body().getData().getWelletname();
            SPUtils.putString(Constant.NODE, String.valueOf(response.body().getData().getIsnode()), context);
            SPUtils.putString(Constant.ISVIP, String.valueOf(response.body().getData().getIsvip()), context);
            SPUtils.putString(Constant.NODE_NUM, String.valueOf(response.body().getData().getNodenum()), context);
            SPUtils.putInt(Constant.LEVEL, response.body().getData().getGrade(), context);
            SPUtils.putInt(Constant.ISAUTO, response.body().getData().getIsauto(), context);

            if (!ObjectUtils.isEmpty(walletName) && walletName.toString().trim() != "") {
                tvWalletName.setText(walletName);
            } else {
                tvWalletName.setText(response.body().getData().getUsername());
            }
            //判断是否显示激活按钮
            if (response.body().getData().getState() == 10) {
                ll_activation.setVisibility(View.VISIBLE);
            } else {
                ll_activation.setVisibility(View.GONE);
            }

        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }


    //弹出 账号激活码的输入框
    AlertDialog ActivationDialog = null;

    private void showactionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_activation)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 258), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_sure, v -> {//设置点击事件
                    EditText dealPwd = ActivationDialog.getView(R.id.ed_deal_pwd);
                    String pwd = dealPwd.getText().toString();
//                    pwd = Md5Utils.MD5(pwd).toUpperCase();
                    mPresent.ActiveCode(context, pwd);
                    ActivationDialog.dismiss();
                }).setOnClickListener(R.id.tx_cancel, v -> ActivationDialog.dismiss());
        ActivationDialog = builder.create();
        ActivationDialog.show();

    }

    //版本更新
    @Override
    public void getupdateAppResult(Response<VersionResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {

        } else if (response.body().getCode() == 201) {
            int systemCode = Integer.parseInt(response.body().getData().getVersioncode());
            if (systemCode > versionCode) {
                showUpdateDialog(response.body().getData().getVersionname(), response.body().getData().getRemarks(), response.body().getData().getUrl());
            }
        } else if (response.body().getCode() == 202) {
            int systemCode = Integer.parseInt(response.body().getData().getVersioncode());
            if (systemCode > versionCode) {
                showUpdateDialog(response.body().getData().getVersionname(), response.body().getData().getRemarks(), response.body().getData().getUrl());
            }
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    /**
     * 激活返回执行
     *
     * @param response
     */
    @Override
    public void getActiveCode(Response<ActiveResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            //激活成功，输出内容
            ToastUtils.showLongToast(context, response.body().getMsg());
            ll_activation.setVisibility(View.GONE);
        } else if (response.body().getCode() == -10) {
            //异地登录提示
            ToastUtils.showLongToast(context, getResources().getString(R.string.not_login));
        } else {
            //其他错误 直接输出提示语
            ToastUtils.showLongToast(context, response.body().getMsg());
        }

    }

    /**
     * 返回错误
     */
    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }
}