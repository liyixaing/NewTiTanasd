package lanjing.com.titan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxh.baselibray.dialog.AlertDialog;
import com.lxh.baselibray.mvp.MvpFragment;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.SizeUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import lanjing.com.titan.R;
import lanjing.com.titan.activity.BindingPhoneActivity;
import lanjing.com.titan.activity.HistoryEntrustActivity;
import lanjing.com.titan.adapter.BuySixAdapter;
import lanjing.com.titan.adapter.BuySixOneAdapter;
import lanjing.com.titan.adapter.BuySixZeroAdapter;
import lanjing.com.titan.adapter.EntrustAdapter;
import lanjing.com.titan.adapter.SellSixAdapter;
import lanjing.com.titan.adapter.SellSixOneAdapter;
import lanjing.com.titan.adapter.SellSixZeroAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.DealContact;
import lanjing.com.titan.eventbus.EventImpl;
import lanjing.com.titan.response.EntrustListResponse;
import lanjing.com.titan.response.InterDealResponse;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.response.SixTradeResponse;
import lanjing.com.titan.response.WalletDataResponse;
import lanjing.com.titan.util.MoneyUtil;
import lanjing.com.titan.util.SoftKeyBoardListener;
import retrofit2.Response;

import static lanjing.com.titan.util.RecyclerViewAnimation.runLayoutAnimation;

public class DealFragment extends MvpFragment<DealContact.DealPresent> implements DealContact.IDealView {

    int EDIT_OK = 200;
    @BindView(R.id.tv_service_charge)
    TextView tvServiceCharge;
    @BindView(R.id.buy_btn)

    RadioButton buyBtn;
    @BindView(R.id.sell_btn)
    RadioButton sellBtn;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.ed_number)
    EditText edNumber;
    //    @BindView(R.id.indicator_seek_bar)
//    SeekBarView indicatorSeekBar;
//    @BindView(R.id.tv_indicator)
//    TextView tvIndicator;
    @BindView(R.id.tv_coin_type)
    TextView tvCoinType;
    @BindView(R.id.tv_coin_type2)
    TextView tvCoinType2;
    @BindView(R.id.btn_buy)
    TextView btnBuy;
    @BindView(R.id.btn_sell)
    TextView btnSell;
    @BindView(R.id.rv_normal_show)
    LinearLayout rvNormalShow;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.rv_sell)
    RecyclerView rvSell;
    @BindView(R.id.tv_usd_price)
    TextView tvUsdPrice;
    //    @BindView(R.id.tv_usd_to_yuan)
//    TextView tvUsdToYuan;
    @BindView(R.id.rv_buy)
    RecyclerView rvBuy;
    @BindView(R.id.rb_two)
    RadioButton rbTwo;
    @BindView(R.id.rb_one)
    RadioButton rbOne;
    @BindView(R.id.rb_zero)
    RadioButton rbZero;
    @BindView(R.id.tv_history_entrust)
    TextView tvHistoryEntrust;
    @BindView(R.id.checkbox_inter)
    CheckBox checkboxInter;
    int page = 1;
    int size = 10;
    int id;

    String price;

    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_deal_num)
    TextView tvDealNum;
    @BindView(R.id.img_refresh)
    LinearLayout imgRefresh;
    @BindView(R.id.refresh_two)
    SmartRefreshLayout refreshTwo;
    Unbinder unbinder;

    private Boolean isInput = false;
    SellSixAdapter mAdapterSell;
    BuySixAdapter mAdapterBuy;
    SellSixOneAdapter mAdapterSellOne;
    BuySixOneAdapter mAdapterBuyOne;
    SellSixZeroAdapter mAdapterSellZero;
    BuySixZeroAdapter mAdapterBuyZero;
    List<SixTradeResponse.SelldataBean> mListSell;
    List<SixTradeResponse.BuydataBean> mListBuy;

    EntrustAdapter mAdapter;
    List<EntrustListResponse.DataBean> mList;
    String phone;
    String usd;
    String titan;

    private boolean isEdit = true;
    private boolean isSeek = true;
    private boolean notHandleAfterTextChangedEvent = false;
    Integer titanMax = 0;
    Integer usdMax = 0;
    int type = 0;//0表示买入，1表示卖出；
    double keyongDouble;
    private boolean flag = true;
    String balance;
    BigDecimal totalAmount;
    //    private Timer timer;
    String keyong;
    int typeDatae = 1;


    @Override
    public void initData(Bundle savedInstanceState) {
        SoftKeyBoardListener.setListener(context, onSoftKeyBoardChangeListener);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        edNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final TextView v, final int actionId, final KeyEvent event) {
                checkTwo();
                // 可捕捉右下角的Return按钮

                //添加抛出收起事件代码
                return false;
            }
        });


        type = 0;

//        tvIndicator.setText(0 + "%");

        //判断是否开启智能交易
        int isAuto = SPUtils.getInt(Constant.ISAUTO, 0, context);
        if (isAuto == 0) {
            checkboxInter.setChecked(false);
        } else {
            checkboxInter.setChecked(true);
        }

        phone = SPUtils.getString(Constant.PHONE, "", context);

        //获取钱包数据
        mPresent.walletDataTitan(context);


        mList = new ArrayList<>();
        mAdapter = new EntrustAdapter(R.layout.recy_item_entrustment_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        mPresent.entrustList(context, String.valueOf(page), String.valueOf(size), "1");

        mAdapter.setOnItemChildClickListener(new EntrustAdapter.OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                id = mList.get(position).getFid();
                showConfirmRecallDialog(String.valueOf(id));
            }
        });

        refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mPresent.entrustList(context, String.valueOf(page), String.valueOf(size), "1");

        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            mPresent.entrustList(context, String.valueOf(page), String.valueOf(size), "1");
        });

        balance = titan;

//        initSeekBar();//监听进度条

//        timer=new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                // (1) 使用handler发送消息
//                Message message=new Message();
//                message.what=0;
//                mHandler.sendMessage(message);
//            }
//        },0,3000);//每隔三秒使用handler发送一下消息,也就是每隔一秒执行一次,一直重复执行

        //主模块 上部下拉刷新
        refreshTwo.setOnRefreshListener(refreshLayout -> {
            checkTwo();
        });
        refreshTwo.setEnableLoadMore(false);
    }

    /**
     * 软键盘弹出收起监听
     */
    private SoftKeyBoardListener.OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener = new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
        @Override
        public void keyBoardShow(int height) {
            edNumber.setCursorVisible(true);
        }

        @Override
        public void keyBoardHide(int height) {
            checkTwo();
            edNumber.setCursorVisible(false);
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveActivity(EventImpl.UpdateNicknameEvent event) {
        if (!ObjectUtils.isEmpty(event)) {
            phone = SPUtils.getString(Constant.PHONE, "", context);
        }
    }
//    // (2) 使用handler处理接收到的消息
//    private Handler mHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            if(msg.what == 0){
//                checkTwo();
//            }
//        }
//    };

    @Override
    public void onResume() {
        buyBtn.setChecked(true);
        btnSell.setVisibility(View.GONE);
        btnBuy.setVisibility(View.VISIBLE);
        tvCoinType.setText("TITAN");
        //查询数据
        checkTwo();
        tvDealNum.setText("0");
        phone = SPUtils.getString(Constant.PHONE, "", context);

        if (ObjectUtils.isEmpty(phone)) {
            checkboxInter.setChecked(false);
        }
        initEdits();//监听输入文本
        super.onResume();
    }

    @Override
    public void onStop() {
//        timer.cancel();
        buyBtn.setChecked(true);
        edNumber.setText("");
        tvDealNum.setText("0");
        super.onStop();
    }

    @Override
    public void onDestroy() {
//        timer.cancel();
        buyBtn.setChecked(true);
        edNumber.setText("");
        tvDealNum.setText("0");
        super.onDestroy();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_deal;
    }

    @OnClick({R.id.img_refresh, R.id.checkbox_inter, R.id.buy_btn, R.id.sell_btn, R.id.ed_number, R.id.rb_two, R.id.rb_one, R.id.rb_zero, R.id.btn_buy, R.id.btn_sell, R.id.tv_history_entrust})
    public void onViewClicked(View view) {
        String number = edNumber.getText().toString().trim();
        switch (view.getId()) {
            case R.id.img_refresh://点击刷新按钮刷新数据
                checkTwo();
                break;
            case R.id.checkbox_inter://智能交易
                if (checkboxInter.isChecked() == true) {
                    if (ObjectUtils.isEmpty(phone)) {
                        showBindingPhoneDialog();
                        return;
                    }
                    showInterTipDialog();
                } else {
                    showInterCloseTipDialog();
                }
                break;
            case R.id.buy_btn://切换买入
                type = 0;
                edNumber.setText("");
                tvDealNum.setText("0");
//                tvIndicator.setText(0 + "%");
//                if (titanMax != 0) {
//                    indicatorSeekBar.setEnabled(true);
//                    indicatorSeekBar.setMax(titanMax);
//                }else {
//                    indicatorSeekBar.setEnabled(false);
//                }
//                indicatorSeekBar.setProgress(0);
                mPresent.walletDataTitan(context);
                tvNum.setText(titan);
                btnBuy.setVisibility(View.VISIBLE);
                btnSell.setVisibility(View.GONE);
                tvCoinType.setText("TITAN");
                tvCoinType2.setText("USD");

                break;
            case R.id.sell_btn://切换卖出
                type = 1;
                edNumber.setText("");
                tvDealNum.setText("0");
//                tvIndicator.setText(0 + "%");
//                if (usdMax != 0) {
//                    indicatorSeekBar.setEnabled(true);
//                    indicatorSeekBar.setMax(usdMax);
//                }else {
//                    indicatorSeekBar.setEnabled(false);
//                }
//                indicatorSeekBar.setProgress(0);
                tvNum.setText(usd);
                mPresent.walletDataUsd(context);
                btnBuy.setVisibility(View.GONE);
                btnSell.setVisibility(View.VISIBLE);
                tvCoinType.setText("USD");
                tvCoinType2.setText("TITAN");

                break;
            case R.id.ed_number:
                edNumber.setCursorVisible(true);//光标显示
                break;
            case R.id.rb_two://2位
                checkTwo();
                break;
            case R.id.rb_one://1位
                checkOne();
                break;
            case R.id.rb_zero://0位
                checkZero();
                break;
            case R.id.btn_buy://执行买入
//                checkTwo();//点击买入时执行
                typeDatae = 1;
                if (ObjectUtils.isEmpty(phone)) {
                    showBindingPhoneDialog();
                    return;
                }

                if (ObjectUtils.isEmpty(number)) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.buy_tip));
                    return;
                }
                if (number.equals("0")) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.num_tip));
                    return;
                }
                if (Integer.parseInt(edNumber.getText().toString()) > titanMax) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.unexecutable));
                    return;
                }

                showPwdBuyDialog();

                break;
            case R.id.btn_sell://执行卖出
//                checkTwo();//点击卖出时执行
                typeDatae = 2;
                if (ObjectUtils.isEmpty(phone)) {
                    showBindingPhoneDialog();
                    return;
                }
                if (ObjectUtils.isEmpty(number)) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.sell_tip));
                    return;
                }
                if (number.equals("0")) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.num_tip));
                    return;
                }
                if (Integer.parseInt(edNumber.getText().toString()) > usdMax) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.unexecutable));
                    edNumber.setText("");
                    return;
                }

                showPwdSellDialog();

                break;
            case R.id.tv_history_entrust://历史委托
                Intent intent = new Intent(context, HistoryEntrustActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
                break;

        }
    }

    //弹出 买入 密码框
    AlertDialog pwdBuyDialog = null;

    private void showPwdBuyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_pwd)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 258), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_sure, v -> {//设置点击事件
                    EditText dealPwd = pwdBuyDialog.getView(R.id.ed_deal_pwd);
                    String pwd = dealPwd.getText().toString();
//                    pwd = Md5Utils.MD5(pwd).toUpperCase();
                    mPresent.dealPwdBuy(context, pwd);
                    pwdBuyDialog.dismiss();
                }).setOnClickListener(R.id.tx_cancel, v -> pwdBuyDialog.dismiss());
        pwdBuyDialog = builder.create();
        pwdBuyDialog.show();

    }


    //弹出 卖出 密码框
    AlertDialog pwdSellDialog = null;

    private void showPwdSellDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_pwd)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 258), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_sure, v -> {//设置点击事件
                    EditText dealPwd = pwdSellDialog.getView(R.id.ed_deal_pwd);
                    String pwd = dealPwd.getText().toString();
//                    pwd = Md5Utils.MD5(pwd).toUpperCase();

                    mPresent.dealPwdSell(context, pwd);
                    pwdSellDialog.dismiss();
                }).setOnClickListener(R.id.tx_cancel, v -> pwdSellDialog.dismiss());
        pwdSellDialog = builder.create();
        pwdSellDialog.show();

    }


    AlertDialog confirmRecallDialog = null;

    private void showConfirmRecallDialog(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_confirm_recall)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 250), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_cancel, v -> {//取消切换手机号
                    confirmRecallDialog.dismiss();
                }).setOnClickListener(R.id.tx_sure, v -> {//确定切换手机号
                    mPresent.recallEntrust(context, id);
                    confirmRecallDialog.dismiss();
                });
        confirmRecallDialog = builder.create();
        confirmRecallDialog.show();

    }

    //提示绑定手机号弹窗
    AlertDialog bindingPhoneDialog = null;

    private void showBindingPhoneDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_binging_phone)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 250), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_cancel, v -> {//取消绑定手机号
                    checkboxInter.setChecked(false);
                    bindingPhoneDialog.dismiss();
                }).setOnClickListener(R.id.tx_sure, v -> {//确定绑定手机号
                    Intent intent = new Intent(context, BindingPhoneActivity.class);
                    startActivity(intent);
                    bindingPhoneDialog.dismiss();
                });
        bindingPhoneDialog = builder.create();
        bindingPhoneDialog.show();

    }


    //智能模式的开启提示框
    AlertDialog interTipDialog = null;

    private void showInterTipDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_inter_tip)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 300), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_cancel, v -> {
                    checkboxInter.setChecked(false);
                    int off = 0;
                    mPresent.interDeal(context, off);
                    interTipDialog.dismiss();
                }).setOnClickListener(R.id.tx_sure, v -> {
                    checkboxInter.setChecked(true);
                    int on = 1;
                    mPresent.interDeal(context, on);
                    interTipDialog.dismiss();
                });
        interTipDialog = builder.create();
        interTipDialog.show();

    }

    //智能模式的关闭提示框
    AlertDialog interCloseTipDialog = null;

    private void showInterCloseTipDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .setContentView(R.layout.dialog_inter_close_tip)//载入布局文件
                .setWidthAndHeight(SizeUtils.dp2px(context, 300), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.tx_cancel, v -> {
                    checkboxInter.setChecked(true);
                    mPresent.interDeal(context, 1);
                    interCloseTipDialog.dismiss();
                }).setOnClickListener(R.id.tx_sure, v -> {
                    checkboxInter.setChecked(false);
                    mPresent.interDeal(context, 0);
                    interCloseTipDialog.dismiss();
                });
        interCloseTipDialog = builder.create();
        interCloseTipDialog.show();

    }

    //当前委托列表数据
    public void recallList() {
        mList = new ArrayList<>();
        mAdapter = new EntrustAdapter(R.layout.recy_item_entrustment_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        mPresent.entrustList(context, String.valueOf(page), String.valueOf(size), "1");

        mAdapter.setOnItemChildClickListener(new EntrustAdapter.OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                id = mList.get(position).getFid();
                showConfirmRecallDialog(String.valueOf(id));
            }
        });

        refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mPresent.entrustList(context, String.valueOf(page), String.valueOf(size), "1");

        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            mPresent.entrustList(context, String.valueOf(page), String.valueOf(size), "1");
        });
    }

    //右侧数据单价四位显示
    public void checkTwo() {
        mListBuy = new ArrayList<>();
        mListSell = new ArrayList<>();
        mAdapterBuy = new BuySixAdapter(R.layout.recy_item_buy_right_list, mListBuy);
        mAdapterSell = new SellSixAdapter(R.layout.recy_item_sell_right_list, mListSell);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        LinearLayoutManager manager2 = new LinearLayoutManager(context);
        rvBuy.setLayoutManager(manager);
        rvSell.setLayoutManager(manager2);
        rvBuy.setAdapter(mAdapterBuy);
        rvSell.setAdapter(mAdapterSell);

        mPresent.rightSixList(context);
    }

    //右侧数据单价一位显示
    public void checkOne() {
        mListBuy = new ArrayList<>();
        mListSell = new ArrayList<>();
        mAdapterBuyOne = new BuySixOneAdapter(R.layout.recy_item_buy_right_list, mListBuy);
        mAdapterSellOne = new SellSixOneAdapter(R.layout.recy_item_sell_right_list, mListSell);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        LinearLayoutManager manager2 = new LinearLayoutManager(context);
        rvBuy.setLayoutManager(manager);
        rvSell.setLayoutManager(manager2);
        rvBuy.setAdapter(mAdapterBuyOne);
        rvSell.setAdapter(mAdapterSellOne);

        mPresent.rightSixListOne(context);
    }

    //右侧数据单价零位显示
    public void checkZero() {
        mListBuy = new ArrayList<>();
        mListSell = new ArrayList<>();
        mAdapterBuyZero = new BuySixZeroAdapter(R.layout.recy_item_buy_right_list, mListBuy);
        mAdapterSellZero = new SellSixZeroAdapter(R.layout.recy_item_sell_right_list, mListSell);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        LinearLayoutManager manager2 = new LinearLayoutManager(context);
        rvBuy.setLayoutManager(manager);
        rvSell.setLayoutManager(manager2);
        rvBuy.setAdapter(mAdapterBuyZero);
        rvSell.setAdapter(mAdapterSellZero);

        mPresent.rightSixListZero(context);
    }

    //输入框监听
    private void initEdit() {
        edNumber.addTextChangedListener(new TextWatcher() {
            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                edNumber.removeTextChangedListener(this);
            }

            @Override

            public void afterTextChanged(Editable s) {
                // TODO自动生成的方法存根
                if (Double.parseDouble(titan) == 0) {
//                    ToastUtils.showShortToast(context, "TITAN余额不足");
                    return;
                }

                if (notHandleAfterTextChangedEvent) {
                    notHandleAfterTextChangedEvent = false;
                    // 光标置最后
                    CharSequence text = edNumber.getText();
                    if (text instanceof Spannable) {
                        Spannable spanText = (Spannable) text;
                        Selection.setSelection(spanText, text.length());
                    }
                    return;
                }

                if (s == null) {
                    return;
                }
                Integer value = 0;
                Integer max = 0;
                if (type == 0) {
                    max = titanMax;
                    balance = titan;
                } else if (type == 1) {
                    max = usdMax;
                    balance = usd;
                }
                BigDecimal result = new BigDecimal(s.toString());
                keyongDouble = MoneyUtil.stringToDouble(balance);
                totalAmount = BigDecimal.valueOf(keyongDouble);//获取币的数量
                //不允许超出最大值
                if (result.compareTo(totalAmount) == 1) {
                    ToastUtils.showShortToast(context, getResources().getString(R.string.current_maximum_input) + totalAmount);
                    edNumber.setText("");
                }

                if (s.toString().trim().equals("")) {
                    notHandleAfterTextChangedEvent = true;
                    edNumber.removeTextChangedListener(this);
                    edNumber.setText("");
                } else {
                    value = Integer.valueOf(s.toString());
                }

                if (value != null) {
                    if (value < 0) {
                        value = max;
                    } else if (value > max) {
                        value = max;
                    }

                    notHandleAfterTextChangedEvent = true;
                    edNumber.setText(Integer.toString(value) + "");
                    tvDealNum.setText(Integer.toString(value) + "");
//                    edNumber.setText(String.valueOf(value));
//                    tvDealNum.setText(String.valueOf(value));
//                    try {
//                        if (!isSeek) {
//                            isEdit = true;
//                            indicatorSeekBar.setProgress(value);
//                            try{
//                                String baifen = (new BigDecimal(value).divide(new BigDecimal(max)) + "");
//                                tvIndicator.setText(MoneyUtil.priceFormatBaiToString(baifen));
//                            }catch (Exception e){
//                                e.printStackTrace();
//                            }
//                        } else {
//                            isSeek = false;
//                        }
//
//                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });
    }

    //输入框监听
    private void initEdits() {
        edNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //输入时调用
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mHandler.removeCallbacks(mRunnable);
                //800毫秒没有输入认为输入完毕
                mHandler.postDelayed(mRunnable, 2000);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString()) && flag) {
                    BigDecimal result;
                    String temp = s.toString();
                    int posDot = temp.indexOf(".");
                    try {
                        if (posDot == s.length() - 1) {
                            return;
                        }
                        result = new BigDecimal(s.toString());
                    } catch (Exception e) {
                        return;
                    }


                    if (type == 0) {
                        keyong = String.valueOf(titanMax);
                        String ed = edNumber.getText().toString().trim();
                        String price = tvUsdPrice.getText().toString();
                        try {
                            String UsdNum = (new BigDecimal(ed).multiply(new BigDecimal(price)) + "");
                            String UsdResult = (new BigDecimal(0.998).multiply(new BigDecimal(UsdNum)) + "");
                            tvDealNum.setText(MoneyUtil.formatFour(UsdResult));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (type == 1) {
                        keyong = String.valueOf(usdMax);
                        String ed = edNumber.getText().toString().trim();
                        String price = tvUsdPrice.getText().toString();
                        try {
                            double zhi = Double.parseDouble(ed) / Double.parseDouble(price);
                            String zhi2 = MoneyUtil.priceFormatDoubleFour(zhi);
//                            String TitanNum = (new BigDecimal(ed).divide(new BigDecimal(price))+"");
                            String TitanResult = (new BigDecimal(0.998).multiply(new BigDecimal(zhi2)) + "");
                            tvDealNum.setText(MoneyUtil.formatFour(TitanResult));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                    keyongDouble = MoneyUtil.stringToDouble(keyong);
                    totalAmount = BigDecimal.valueOf(keyongDouble);//获取币的数量
                    //不允许超出最大值
                    if (result.compareTo(totalAmount) == 1) {
                        ToastUtils.showShortToast(context, getResources().getString(R.string.current_maximum_input) + totalAmount);
                        edNumber.setText("");
                        tvDealNum.setText("0");
                    }

                    //保留两位小数
                    flag = false;
                    s.clear();
                    if (posDot > 0 && temp.length() - posDot - 1 > 2) {
                        temp = temp.substring(0, posDot + 3);
                    }
                    s.append(temp);
                    flag = true;
                }
//                tvDealNum.setText(s.toString());
            }
        });
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(EDIT_OK);
        }
    };

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (EDIT_OK == msg.what) {
//                checkTwo();
            }

        }
    };


    @Override
    protected DealContact.DealPresent createPresent() {
        return new DealContact.DealPresent();
    }

    List<SixTradeResponse.SelldataBean> sellData;
    List<SixTradeResponse.BuydataBean> buyData;

    //开启智能交易
    @Override
    public void getInterDealResult(Response<InterDealResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            SPUtils.putInt(Constant.ISAUTO, response.body().getIsauto(), context);
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    //获取TITAn的可用余额
    @Override
    public void getWalletDataTitanResult(Response<WalletDataResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            tvNum.setText(MoneyUtil.formatFour(response.body().getData().getTitannum()));

            usd = MoneyUtil.formatFour(response.body().getData().getUSD1num());
            titan = MoneyUtil.formatFour(response.body().getData().getTitannum());

            int i = titan.indexOf(".");
            String bala1 = titan.substring(0, i);
            titanMax = Integer.parseInt(bala1);
//            if (titanMax != 0) {
//                indicatorSeekBar.setEnabled(true);
//                indicatorSeekBar.setMax(titanMax);
//            }else {
//                indicatorSeekBar.setEnabled(false);
//            }
            int j = usd.indexOf(".");
            String bala2 = usd.substring(0, j);
            usdMax = Integer.parseInt(bala2);

        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    //获取Usd的可用余额
    @Override
    public void getWalletDataUsdResult(Response<WalletDataResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            tvNum.setText(MoneyUtil.formatFour(response.body().getData().getUSD1num()));

            usd = MoneyUtil.formatFour(response.body().getData().getUSD1num());
            titan = MoneyUtil.formatFour(response.body().getData().getTitannum());
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    //两位数据显示
    @Override
    public void getDealSixResult(Response<SixTradeResponse> response) {
        refreshTwo.finishRefresh();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            tvUsdPrice.setText(MoneyUtil.priceFormatDoubleFour(response.body().getPrice()));
            tvPrice.setText(MoneyUtil.priceFormatDoubleFour(response.body().getPrice()));
//            tvUsdToYuan.setText("≈￥" + MoneyUtil.priceFormatDoubleFour(response.body().getCNYprice()));

            sellData = response.body().getSelldata();
            if (!ObjectUtils.isEmpty(sellData)) {
                mListSell.clear();
                mListSell.addAll(sellData);
                mAdapterSell.notifyDataSetChanged();
            }
            buyData = response.body().getBuydata();
            if (!ObjectUtils.isEmpty(buyData)) {
                mListBuy.clear();
                mListBuy.addAll(buyData);
                mAdapterBuy.notifyDataSetChanged();
            }

        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    //一位数据显示
    @Override
    public void getDealSixOneResult(Response<SixTradeResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            tvUsdPrice.setText(MoneyUtil.priceFormatDoubleFour(response.body().getPrice()));
            tvPrice.setText(MoneyUtil.priceFormatDoubleFour(response.body().getPrice()));
//            tvUsdToYuan.setText("≈￥" + MoneyUtil.priceFormatDoubleFour(response.body().getCNYprice()));
            sellData = response.body().getSelldata();
            if (!ObjectUtils.isEmpty(sellData)) {
                mListSell.clear();
                mListSell.addAll(sellData);
                mAdapterSellOne.notifyDataSetChanged();
            }
            buyData = response.body().getBuydata();
            if (!ObjectUtils.isEmpty(buyData)) {
                mListBuy.clear();
                mListBuy.addAll(buyData);
                mAdapterBuyOne.notifyDataSetChanged();

            }

        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    //零位数据显示
    @Override
    public void getDealSixZeroResult(Response<SixTradeResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            tvUsdPrice.setText(MoneyUtil.priceFormatDoubleFour(response.body().getPrice()));
            tvPrice.setText(MoneyUtil.priceFormatDoubleFour(response.body().getPrice()));
//            tvUsdToYuan.setText("≈￥" + MoneyUtil.priceFormatDoubleFour(response.body().getCNYprice()));
            sellData = response.body().getSelldata();
            if (!ObjectUtils.isEmpty(sellData)) {
                mListSell.clear();
                mListSell.addAll(sellData);
                mAdapterSellZero.notifyDataSetChanged();
            }
            buyData = response.body().getBuydata();
            if (!ObjectUtils.isEmpty(buyData)) {
                mListBuy.clear();
                mListBuy.addAll(buyData);
                mAdapterBuyZero.notifyDataSetChanged();
            }

        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    //委托列表返回数据
    List<EntrustListResponse.DataBean> data;

    //委托列表返回数据
    @Override
    public void getEntrustListResult(Response<EntrustListResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            if (page == 1) {
                mList.clear();
            }
            data = response.body().getData();
            if (!ObjectUtils.isEmpty(data)) {
                rvNormalShow.setVisibility(View.GONE);
                mList.addAll(data);
                mAdapter.notifyDataSetChanged();
                runLayoutAnimation(rv);
                if (data != null && data.size() == size) {
                    refresh.setEnableLoadMore(true);
                } else {
                    refresh.setEnableLoadMore(false);
                }
                rv.setVisibility(View.VISIBLE);
            } else {
                rvNormalShow.setVisibility(View.VISIBLE);
                rv.setVisibility(View.GONE);
            }
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    //撤销委托返回数据
    @Override
    public void getRecallEntrustResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.recall_success));
            recallList();
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    //买入  数据返回处理
    @Override
    public void getDealPwdBuyResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            String count = edNumber.getText().toString();
            mPresent.buyOrSell(context, count, "1");
//            ToastUtils.showShortToast(context, getResources().getString(R.string.submit_successfully));
            recallList();
//            mPresent.walletDataTitan(context);//获取数据
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    //卖出  数据返回处理
    @Override
    public void getDealPwdSellResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            String count = edNumber.getText().toString();
            mPresent.buyOrSell(context, count, "2");
//            ToastUtils.showShortToast(context, getResources().getString(R.string.submit_successfully));
            recallList();
//            mPresent.walletDataUsd(context);//获取数据
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    //买单  卖单
    @Override
    public void getBuyOrSellResult(Response<ResultDTO> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.success));
            edNumber.setText(0 + "");
            Integer time = 1000;
            Handler handler = new Handler();
            //当计时结束时，跳转至主界面
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (typeDatae == 1) {
                        mPresent.walletDataTitan(context);
                    } else {
                        mPresent.walletDataUsd(context);
                    }
                }
            }, time);
//            indicatorSeekBar.setProgress(0);
            recallList();
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    //定时执行
    public void inittime() {
        Integer time = 1000;
        Handler handler = new Handler();
        //当计时结束时，跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresent.walletDataTitan(context);
                mPresent.walletDataUsd(context);
            }
        }, time);
    }


    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }

}
