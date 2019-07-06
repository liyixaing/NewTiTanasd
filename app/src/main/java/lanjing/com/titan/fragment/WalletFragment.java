package lanjing.com.titan.fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxh.baselibray.dialog.AlertDialog;
import com.lxh.baselibray.mvp.MvpFragment;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.SPUtils;
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
import lanjing.com.titan.activity.NoticeActivity;
import lanjing.com.titan.activity.TItancWaitGetActivity;
import lanjing.com.titan.activity.UsdAirdroppedActivity;
import lanjing.com.titan.activity.WalletListActivity;
import lanjing.com.titan.activity.WalletManagerActivity;
import lanjing.com.titan.appupdate.UpdateHelper;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.WalletDataContact;
import lanjing.com.titan.response.InfoNoticeResponse;
import lanjing.com.titan.response.PersonResponse;
import lanjing.com.titan.response.VersionResponse;
import lanjing.com.titan.response.WalletDataResponse;
import lanjing.com.titan.util.APKVersionCodeUtils;
import lanjing.com.titan.util.MoneyUtil;
import lanjing.com.titan.view.ScrollTextView;
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
    @BindView(R.id.usd2_lay)
    LinearLayout usd2Lay;
    @BindView(R.id.manage_wallet)
    LinearLayout manageWallet;

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

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wallet;
    }


    @OnClick({R.id.checkbox_private_mode, R.id.tv_wallet_name, R.id.manage_wallet, R.id.titan_lay, R.id.usd_lay, R.id.titanc_lay, R.id.usd2_lay})
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
                titan.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(titan);
                break;
            case R.id.usd_lay://进入   USD 的资产页面
                Intent usd = new Intent(context, AssetUSDActivity.class);
                usd.putExtra("walletId", usdId);
                usd.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(usd);
                break;
            case R.id.titanc_lay://进入    TITANC的待领取
                Intent titanc = new Intent(context, TItancWaitGetActivity.class);
                titanc.putExtra("walletId", titancId);
                titanc.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(titanc);
                break;
            case R.id.usd2_lay://进入   USD的待空投
                Intent usd2 = new Intent(context, UsdAirdroppedActivity.class);
                usd2.putExtra("walletId", usd2Id);
                usd2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(usd2);
                break;
        }
    }

    @Override
    protected WalletDataContact.WalletDataPresent createPresent() {
        return new WalletDataContact.WalletDataPresent();
    }


    @Override
    public void getWalletDataResult(Response<WalletDataResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            walletAddress = response.body().getAddress();
            labelAddress = response.body().getWelletId();

            tvAmount.setText("$" + MoneyUtil.formatFour(response.body().getSum()));
            tvWalletId.setText("ID：" + response.body().getWelletId());
            titanNum.setText(MoneyUtil.formatFour(response.body().getData().getTitannum()));//TITAN数量
            titanPrice.setText("$" + MoneyUtil.formatFour(response.body().getData().getTitanprice()));//TITAN单价
            usdNum.setText(MoneyUtil.formatFour(response.body().getData().getUSD1num()));//USD数量
            usdPrice.setText(MoneyUtil.formatFour(response.body().getData().getUSD1price()));//USD  价格
            titancNum.setText(MoneyUtil.formatFour(response.body().getData().getTitancnum()));//TITANC  数量
            usd2Num.setText(MoneyUtil.formatFour(response.body().getData().getUSD2num()));// USD2 数量
            titanId = response.body().getData().getTitancoinId();
            usdId = response.body().getData().getUSD1coinId();
            titancId = response.body().getData().getTitanccoinId();
            usd2Id = response.body().getData().getUSD2coinId();


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
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getPersonResult(Response<PersonResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            SPUtils.putString(Constant.PHONE, response.body().getData().getPhonenum(), context);
            SPUtils.putString(Constant.WALLET_NAME, response.body().getData().getUsername(), context);
            SPUtils.putString(Constant.PORTRAIT, response.body().getData().getPicture(), context);
            String walletName = response.body().getData().getWelletname();
            SPUtils.putString(Constant.NODE, String.valueOf(response.body().getData().getIsnode()), context);
            SPUtils.putString(Constant.ISVIP, String.valueOf(response.body().getData().getIsVip()), context);
            SPUtils.putString(Constant.NODE_NUM, String.valueOf(response.body().getData().getNodenum()), context);
            SPUtils.putInt(Constant.LEVEL, response.body().getGrede(), context);

            SPUtils.putInt(Constant.ISAUTO, response.body().getData().getIsauto(), context);

            if (!ObjectUtils.isEmpty(walletName) && walletName.toString().trim() != "") {
                tvWalletName.setText(walletName);
            } else {
                tvWalletName.setText(response.body().getData().getUsername());
            }

        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
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
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }


    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }
}
