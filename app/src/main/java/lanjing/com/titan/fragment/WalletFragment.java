package lanjing.com.titan.fragment;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import lanjing.com.titan.activity.NoticeActivity;
import lanjing.com.titan.activity.PaymentCodeActivity;
import lanjing.com.titan.activity.TItancWaitGetActivity;
import lanjing.com.titan.activity.UsdAirdroppedActivity;
import lanjing.com.titan.activity.WalletListActivity;
import lanjing.com.titan.activity.WalletManagerActivity;
import lanjing.com.titan.appupdate.UpdateHelper;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.WalletDataContact;
import lanjing.com.titan.response.ActiveResponse;
import lanjing.com.titan.response.CdkeyResponse;
import lanjing.com.titan.response.InfoNoticeResponse;
import lanjing.com.titan.response.PersonResponse;
import lanjing.com.titan.response.SeckillCdkeyResponse;
import lanjing.com.titan.response.TodayFreeResponse;
import lanjing.com.titan.response.VersionResponse;
import lanjing.com.titan.response.WalletDataResponse;
import lanjing.com.titan.util.APKVersionCodeUtils;
import lanjing.com.titan.util.MoneyUtil;
import lanjing.com.titan.view.ScrollTextView;
import lanjing.com.titan.zxing.android.CaptureActivity;
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
    @BindView(R.id.tv_activation)
    TextView tv_activation;//激活文字显示
    @BindView(R.id.iv_tow_code)
    ImageView iv_tow_code;//二维码生成按钮

    int page = 1;
    int pageSize = 10;

    String titanId;//TITAN页面钱包id查询数据
    String usdId;//USD页面钱包id查询数据
    String titancId;//TITANC页面钱包id查询数据
    String usd2Id;//USD2页面钱包id查询数据
    String walletAddress;//钱包地址
    String labelAddress;//标签地址
    int versionCode;
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    public static final int RESULT_OK = -1;
    private static final int REQUEST_CODE_SCAN = 0x0000;
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
            R.id.usd_lay, R.id.titanc_lay, R.id.usd2_lay, R.id.rl_home_notice, R.id.ll_bar, R.id.ll_activation, R.id.iv_tow_code})
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
                //判断是否有相机权限
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    goScan();
                }
                break;
            case R.id.iv_tow_code://跳转到二维吗界面
                Intent TowCode = new Intent(context, PaymentCodeActivity.class);
                startActivity(TowCode);
                break;
            case R.id.ll_activation://点击激活按钮
//                mPresent.TodayFreeActiveTimes(context);//获取激活信息
                mPresent.SeckillCdkeyConfig(context);
                break;
        }
    }

    //跳转到扫描界面
    public void goScan() {
        Intent intent = new Intent(context, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    //扫码权限获取
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScan();
                } else {
                    ToastUtils.showLongToast(context, "你拒绝了权限申请，可能无法打开相机扫码哟!");
                }
                break;
            default:
        }
    }

    //返回扫描结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                //返回的文本内容
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                //返回的BitMap图像
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);

                Log.e("扫描内容", content);
            }
        }
    }

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

    int state;//激活状态 0为未激活，1为已激活

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
                ll_activation.setVisibility(View.VISIBLE);//未激活状态
                tv_activation.setText("未激活");
                state = 0;
            } else {
                ll_activation.setVisibility(View.VISIBLE);//已激活
                tv_activation.setText("已激活");
                state = 1;
            }

        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }


    /**
     * dialog_activation.xml 输入界面
     * dialog_automatic.xml  未激活用户抢到激活码
     * dialog_free.xml  已激活用抢到激活码
     * dialog_grab_tomorrow.xml 开始抢名额
     * dialog_replication_activation.xml 名额已抢完
     */

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
                    mPresent.ActiveCode(context, pwd);
                    ActivationDialog.dismiss();
                }).setOnClickListener(R.id.tx_cancel, v -> ActivationDialog.dismiss());
        ActivationDialog = builder.create();
        ActivationDialog.show();

    }


    //抢到激活码弹出界面
    AlertDialog FreeDialog = null;

    private void FreeDialogDialog(String sun) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_free)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 258), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setText(R.id.tv_number, "恭喜您获得激活码")
                .setText(R.id.tv_activitycode, sun)
                .setOnClickListener(R.id.login_activation_btn, v -> {
                    ClipboardManager copy = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    copy.setText(sun);
                    ToastUtils.showLongToast(context, getResources().getString(R.string.over_copy));
                    FreeDialog.dismiss();
                });

        FreeDialog = builder.create();
        FreeDialog.show();

    }

    //未激活用户自动激活弹窗
    AlertDialog SurplusDialog = null;

    private void SurplusDialogDig(String code) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .addDefaultAnimation()
                .setCancelable(true)
                .setContentView(R.layout.dialog_automatic)
                .setText(R.id.tv_jhcode, code)
                .setWidthAndHeight(SizeUtils.dp2px(context, 258), ViewGroup.LayoutParams.WRAP_CONTENT);
//        执行到这里的话说明抢到激活名额了并且系统自动帮我激活了
        tv_activation.setText("已激活");//改变现实文字
        state = 1;//改变激活状态
        SurplusDialog = builder.create();
        SurplusDialog.show();
    }

    //开始抢激活码界面
    AlertDialog StaterDialog = null;

    private void StaterDialogDig(String sun) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .addDefaultAnimation()
                .setCancelable(true)
                .setContentView(R.layout.dialog_grab_tomorrow)
                .setText(R.id.tv_number, "激活码剩余：" + sun)
                .setWidthAndHeight(SizeUtils.dp2px(context, 258), ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOnClickListener(R.id.ll_qiang, v -> {
                    mPresent.seckillCdkey(context);
                    StaterDialog.dismiss();
                });

        StaterDialog = builder.create();
        StaterDialog.show();
    }

    //名额已经抢完了 弹出界面
    AlertDialog endDialog = null;

    private void endDialogDig() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .addDefaultAnimation()
                .setCancelable(true)
                .setContentView(R.layout.dialog_replication_activation)
                .setText(R.id.tv_number, "今日激活码已全部发放")
                .setWidthAndHeight(SizeUtils.dp2px(context, 258), ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOnClickListener(R.id.ll_qiang, v -> {
                    ToastUtils.showLongToast(context, "开始抢激活码罗");
                });

        endDialog = builder.create();
        endDialog.show();
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

    //获取免费激活数量
    @Override
    public void getTodayFreeActiveTimes(Response<TodayFreeResponse> response) {
//        if (response.body().getCode() == Constant.SUCCESS_CODE) {
//            //获取成功
//            Log.e("xiaoqiang", "获取免费激活名额成功");
//            if (response.body().getData() > 0) {
//
//                FreeDialogDialog(response.body().getData());//免费赠送弹出框
//            } else {
//
////                showactionDialog();//输入激活码弹出框
//            }
//        } else if (response.body().getCode() == -10) {
//            //异地登录提示
//            ToastUtils.showLongToast(context, getResources().getString(R.string.not_login));
//        } else {
//            //其他错误直接给它后台提示
//            ToastUtils.showLongToast(context, response.body().getMsg());
//        }

    }


    /**
     * int state;    //激活状态 0为未激活用户，1为已激活用户
     * showactionDialog();  //未激活用户在活动未开始时点击进入的界面（输入激活码界面）
     * FreeDialogDialog(sun);  //抢到激活名额界面
     * SurplusDialogDig();   //未激活用户抢到激活名额
     * StaterDialogDig(sun);  //活动开始界面
     * endDialogDig();  //名额已经抢完了
     *
     * <p>
     * sStart //是否开始 0为未开始 1为开始
     */
    //获取激活配置
    @Override
    public void getSeckillCdkeyConfig(Response<SeckillCdkeyResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {//获取数据是否成功
            String sun = String.valueOf(response.body().getData().getFreeTimes());//剩余活动名额
            String Newest = response.body().getData().getNewestSeckillCdkey();//抢到的激活码
            if (state == 0) {//判断用户是否激活
                if (response.body().getData().getIsStart() == 0) {//判断活动是否开始
//                    未激活用户在活动未开始状态下点击激活按钮
                    showactionDialog();
                } else {
//                    未激活用户在活动开始状态下点击激活按钮
                    if (response.body().getData().getFreeTimes() == 0) {//判断是否还有名额
                        if (response.body().getData().getIsSuccessSeckill() == 0) {//判断今天抢过没有
                            showactionDialog();
                        } else {
                            FreeDialogDialog(Newest);
                        }
                    } else {
                        StaterDialogDig(sun);
                    }
                }
            } else {
                if (response.body().getData().getIsStart() == 0) {//判断活动是否开始
//                    已激活用户在活动未开始状态下点击激活按钮
                    ToastUtils.showLongToast(context, "活动未开始");
                } else {
//                    已激活用户在活动开始状态下点击激活按钮
                    if (response.body().getData().getFreeTimes() == 0) {//判断是否还有名额
                        endDialogDig();
                    } else {
                        if (response.body().getData().getIsSuccessSeckill() == 0) {//判断今天抢过没有
                            StaterDialogDig(sun);
                        } else {
                            FreeDialogDialog(Newest);
                        }
                    }
                }
            }

        } else if (response.body().getCode() == -10) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.not_login));//多端登陆
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());//未知原因直接输出masger
        }
    }

    //点击抢名额后返回的内容
    @Override
    public void getseckillCdkey(Response<CdkeyResponse> response) {
        if (response.body().getCode() == -201) {
            ToastUtils.showLongToast(context, response.body().getMsg());
        } else if (response.body().getCode() == -203) {
            ToastUtils.showLongToast(context, response.body().getMsg());
        } else if (response.body().getCode() == Constant.SUCCESS_CODE) {//抢到激活码名额了
            String sun = response.body().getData();
            if (state == 0) {
                //未激活用户
                SurplusDialogDig(sun);
            } else {
                FreeDialogDialog(sun);
            }
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());//未知错误类型直接输出massger
        }

    }

    /**
     * 返回错误
     */
    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
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

}