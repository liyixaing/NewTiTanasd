package lanjing.com.titan.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jaydenxiao.guider.HighLightGuideView;
import com.lxh.baselibray.mvp.MvpFragment;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.ToastUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import lanjing.com.titan.R;
import lanjing.com.titan.activity.AdviceFeedbackActivity;
import lanjing.com.titan.activity.BillingDetailsActivity;
import lanjing.com.titan.activity.CommitteeActivity;
import lanjing.com.titan.activity.DisclaimerActivity;
import lanjing.com.titan.activity.MyInviteActivity;
import lanjing.com.titan.activity.NodeActivity;
import lanjing.com.titan.activity.NoticeActivity;
import lanjing.com.titan.activity.OnlineServiceActivity;
import lanjing.com.titan.activity.PersonalActivity;
import lanjing.com.titan.activity.SecurityCenterActivity;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.PersonContact;
import lanjing.com.titan.eventbus.EventImpl;
import lanjing.com.titan.response.PersonResponse;
import lanjing.com.titan.util.MoneyUtil;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MyFragment extends MvpFragment<PersonContact.PersonPresent> implements PersonContact.IPersonView {


    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.people_data_lay)
    LinearLayout peopleDataLay;
    @BindView(R.id.checkbox_privacy_mode)
    CheckBox checkboxPrivacyMode;
    @BindView(R.id.lay_security_center)
    LinearLayout laySecurityCenter;
    @BindView(R.id.lay_billing_details)
    LinearLayout layBillingDetails;
    @BindView(R.id.lay_my_invite)
    LinearLayout layMyInvite;
    @BindView(R.id.tv_online_customer_service)
    TextView tvOnlineCustomerService;
    @BindView(R.id.tv_advice_feedback)
    TextView tvAdviceFeedback;
    @BindView(R.id.tv_disclaimer)
    TextView tvDisclaimer;
    @BindView(R.id.tv_invite_code)
    TextView tvInviteCode;
    @BindView(R.id.iv_user_head_pic)
    ImageView ivUserHeadPic;
    @BindView(R.id.titan_amount)
    TextView titanAmount;
    @BindView(R.id.titan_amount_to_usd)
    TextView titanAmountToUsd;
    @BindView(R.id.tv_node)
    TextView tvNode;
    @BindView(R.id.tv_committee)
    TextView tvCommittee;

    String portrait;
    @BindView(R.id.node_lay)
    LinearLayout nodeLay;
    @BindView(R.id.committee_lay)
    LinearLayout committeeLay;
    Unbinder unbinder;
    @BindView(R.id.icon_security_center)
    ImageView iconSecurityCenter;
    Unbinder unbinder1;
    @BindView(R.id.tv_level)
    TextView tvLevel;

    @Override
    public void initData(Bundle savedInstanceState) {
        //用户等级
        int level = SPUtils.getInt(Constant.LEVEL, 0, context);
        switch (level) {
            case 0:
                tvLevel.setText("T");
                break;
            case 1:
                tvLevel.setText("T1");
                break;
            case 2:
                tvLevel.setText("T2");
                break;
            case 3:
                tvLevel.setText("T3");
                break;
            case 4:
                tvLevel.setText("T4");
                break;
            case 5:
                tvLevel.setText("T5");
                break;
            case 6:
                tvLevel.setText("T6");
                break;
            case 7:
                tvLevel.setText("T7");
                break;
        }

        //判断是否有节点

        int node = Integer.parseInt(SPUtils.getString(Constant.NODE, "", context));
        if (node == 1) {
            nodeLay.setVisibility(View.VISIBLE);
        }


        //判断是否有制度委员会
        int isVip = Integer.parseInt(SPUtils.getString(Constant.ISVIP, "", context));
        Log.e("xiaoqiang", isVip + "");
        if (isVip == 1) {
            committeeLay.setVisibility(View.VISIBLE);
        }


//        //获取头像
        String portrait = SPUtils.getString(Constant.PORTRAIT, null, context);
        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                .skipMemoryCache(true);//不做内存缓存
        Glide.with(context).load(portrait).apply(mRequestOptions).into(ivUserHeadPic);

        mPresent.person(context);
//        new Thread() {
//            public void run() {
//                Message msg = hand.obtainMessage();
//                hand.sendMessage(msg);
//            }
//
//        }.start();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveActivity(EventImpl.UpdatePortraitEvent event) {
        if (!ObjectUtils.isEmpty(event)) {
            //获取头像
            mPresent.person(context);
//            String portrait = SPUtils.getString(Constant.PORTRAIT, null, context);
//            RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
//                    .skipMemoryCache(true);//不做内存缓存
//            Glide.with(context).load(portrait).apply(mRequestOptions).into(ivUserHeadPic);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveActivity(EventImpl.UpdateNicknameEvent event) {
        if (!ObjectUtils.isEmpty(event)) {
            mPresent.person(context);
//            tvNickName.setText(SPUtils.getString(Constant.NICK_NAME, null, context));//用户昵称
        }
    }

    private void initShow() {
        mPresent.person(context);
    }

    private void initHide() {
        titanAmount.setText("****");
        titanAmountToUsd.setText("****");
    }

    // 判断是否是第一次启动程序 利用 SharedPreferences 将数据保存在本地
    private boolean isFristRun() {
        //实例化SharedPreferences对象（第一步）
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "share", MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象（第二步）
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!isFirstRun) {
            return false;
        } else {
            //保存数据 （第三步）
            editor.putBoolean("isFirstRun", false);
            //提交当前数据 （第四步）
            editor.commit();
            return true;
        }
    }

    @Override
    public void onResume() {
        checkboxPrivacyMode.setChecked(false);
        super.onResume();
    }

    Handler hand = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isFristRun()) {
                // 如果是第一次启动程序则进入引导界面

                //全屏提示，没高亮控件情况引导
//                HighLightGuideView.builder(context) .addNoHighLightGuidView(R.drawable.wallet_list_yindao) .setMaskColor(getResources().getColor(R.color.mask_color)) .show();

                //有高亮控件情况引导（单个高亮控件）
                HighLightGuideView.builder(context).addHighLightGuidView(iconSecurityCenter, R.drawable.dmtext).setHighLightStyle(HighLightGuideView.VIEWSTYLE_OVAL).show();

                //有高亮控件情况引导（多个高亮控件）
//                HighLightGuideView.builder(context) .addHighLightGuidView(laySecurityCenter, R.drawable.dstext) .addHighLightGuidView(layBillingDetails, R.drawable.dmtext).addHighLightGuidView(layMyInvite, R.drawable.dmtext)  .setHighLightStyle(HighLightGuideView.VIEWSTYLE_RECT).show();
            }

        }

        ;
    };


    @OnClick({R.id.checkbox_privacy_mode, R.id.people_data_lay, R.id.lay_security_center, R.id.lay_billing_details, R.id.lay_my_invite, R.id.tv_online_customer_service, R.id.tv_advice_feedback, R.id.tv_disclaimer, R.id.tv_node, R.id.tv_committee})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.checkbox_privacy_mode:
                if (checkboxPrivacyMode.isChecked()) {
                    initHide();
                } else {
                    initShow();
                }
                break;
            case R.id.people_data_lay://修改个人资料  头像   和   昵称
                Intent intentPeople = new Intent(context, PersonalActivity.class);
                intentPeople.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intentPeople);
                break;
            case R.id.lay_security_center://安全中心   绑定手机号   修改密码（登录密码、交易密码）  安全退出软件
                Intent intentCenter = new Intent(context, SecurityCenterActivity.class);
                intentCenter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentCenter);
                break;
            case R.id.lay_billing_details://账单明细
                Intent bing = new Intent(context, BillingDetailsActivity.class);
                bing.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(bing);
                break;
            case R.id.lay_my_invite://我的邀请
                Intent invite = new Intent(context, MyInviteActivity.class);
                invite.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(invite);
                break;
            case R.id.tv_online_customer_service://在线客服
                Intent service = new Intent(context, OnlineServiceActivity.class);
                service.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(service);
                break;
            case R.id.tv_advice_feedback://建议反馈
                Intent advice = new Intent(context, AdviceFeedbackActivity.class);
                advice.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(advice);
                break;
            case R.id.tv_disclaimer://免责声明
                Intent disclaimer = new Intent(context, DisclaimerActivity.class);
                disclaimer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(disclaimer);
                break;
            case R.id.tv_node://节点  前期不显示  根据用户登录返回的值来进行判断
                Intent node = new Intent(context, NodeActivity.class);
                node.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(node);
                break;
            case R.id.tv_committee://委员会  前期不显示  根据用户登录返回的值来进行判断
                Intent committee = new Intent(context, NoticeActivity.class);
                committee.putExtra("type", "3");
                committee.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(committee);
                break;

        }
    }

    @Override
    protected PersonContact.PersonPresent createPresent() {
        return new PersonContact.PersonPresent();
    }

    @Override
    public void getPersonResult(Response<PersonResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            tvNickName.setText(response.body().getData().getNickname());
            tvInviteCode.setText(String.valueOf(response.body().getData().getKeyes()));
//            //获取头像
//            SPUtils.putString(Constant.PORTRAIT, response.body().getData().getPicture(), context);
//            portrait = response.body().getData().getPicture();
//            RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
//                    .skipMemoryCache(true);//不做内存缓存
//            Glide.with(context).load(portrait).apply(mRequestOptions).into(ivUserHeadPic);

            SPUtils.putString(Constant.NICK_NAME, response.body().getData().getNickname(), context);

            titanAmount.setText(MoneyUtil.priceFormatDoubleFour(response.body().getData().getTitanNum()));
            titanAmountToUsd.setText(MoneyUtil.priceFormatDoubleFour(response.body().getData().getWorthUsd()));

        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }
}
