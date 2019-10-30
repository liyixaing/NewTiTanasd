package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxh.baselibray.dialog.AlertDialog;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.lxh.baselibray.view.TitleView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.CoinTitanAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.WalletDetailContact;
import lanjing.com.titan.response.HistoryListResponse;
import lanjing.com.titan.response.WalletDetailResponse;
import lanjing.com.titan.util.MoneyUtil;
import lanjing.com.titan.util.RecyclerViewAnimation;
import retrofit2.Response;

/**
 * TITAN  资产
 */
public class AssetTITANActivity extends MvpActivity<WalletDetailContact.WalletDetailPresent> implements WalletDetailContact.IWalletDetailView {


    @BindView(R.id.tv_asset_balance)
    TextView tvAssetBalance;
    @BindView(R.id.tv_tixian_balance)
    TextView tvTixianBalance;
    @BindView(R.id.tv_titan_trading_frozen)
    TextView tvTitanTradingFrozen;
    @BindView(R.id.tv_titan_screen)
    TextView tvTitanScreen;
    @BindView(R.id.rv_normal_show)
    LinearLayout rvNormalShow;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.top_up_c_btn)
    TextView topUpCBtn;
    @BindView(R.id.withdraw_c_btn)
    TextView withdrawCBtn;
    @BindView(R.id.exchange_btn)
    TextView exchangeBtn;
    @BindView(R.id.bottom_lay)
    LinearLayout bottomLay;
    @BindView(R.id.title_lay)
    TitleView TitleLay;
    @BindView(R.id.tv_exchange)
    TextView TvExchange;
    @BindView(R.id.ll_exchange)
    LinearLayout LlExchange;
    @BindView(R.id.tv_cash)
    TextView TvCash;
    @BindView(R.id.tv_zhican)
    TextView TvZhican;

    String walletId;//钱包ID


    CoinTitanAdapter mAdapter;
    List<HistoryListResponse.mData> historylist;

    int page = 1;
    int pageSize = 20;
    String type;//0，手续费 1，交易释放 2，充币 3，提币 4，买入 5，卖出 6，系统 7，其他 （不填写为全部）

    String types;//接收上个界面传来的类型（用来判断是哪一个界面跳转过来的）
    String suntaitan;
    String coin;//接收类型 1为泰坦  5为BAR

    @Override
    public void initData(Bundle savedInstanceState) {
        walletId = getIntent().getStringExtra("walletId");
        types = getIntent().getStringExtra("type");
        coin = getIntent().getStringExtra("coin");
        if (coin.equals("1")) {
            LlExchange.setVisibility(View.GONE);
        } else if (coin.equals("5")) {
            LlExchange.setVisibility(View.GONE);
            TvZhican.setVisibility(View.GONE);
            tvTitanTradingFrozen.setVisibility(View.GONE);
            TvCash.setText(getResources().getString(R.string.frozen));
        }
        TitleLay.setTitleText(types);

        //判断来源
        if (types.equals("TITAN")) {//从泰坦资产跳转过来

        } else {//从bra界面跳转过来
            withdrawCBtn.setVisibility(View.GONE);
        }
        historylist = new ArrayList<>();
        mAdapter = new CoinTitanAdapter(R.layout.recy_item_titan_list, historylist);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        mPresent.walletDetail(context, coin);
        mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                //判断是否要进入详情页  1 充币  2 提币  21 titan兑换  22 bar兑换
                if (historylist.get(position).getChangeType().equals("1") || historylist.get(position).getChangeType().equals("2")) {
                    Intent intent = new Intent(context, AssetTitanDetailActivity.class);
                    intent.putExtra("id", String.valueOf(historylist.get(position).getId()));
                    intent.putExtra("name", historylist.get(position).getChangeDesc());
                    startActivity(intent);
                } else if (historylist.get(position).getChangeType().equals("21") || historylist.get(position).getChangeType().equals("22")) {
                    Intent details = new Intent(context, ExchangeDetailsActivity.class);
                    details.putExtra("id", String.valueOf(historylist.get(position).getId()));
                    details.putExtra("name", historylist.get(position).getChangeDesc());
                    startActivity(details);
                }
            }
        });
        refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));

        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_asset_titan;
    }

    @Override
    protected WalletDetailContact.WalletDetailPresent createPresent() {
        return new WalletDetailContact.WalletDetailPresent();
    }

    @OnClick({R.id.tv_titan_screen, R.id.top_up_c_btn, R.id.withdraw_c_btn, R.id.exchange_btn, R.id.tv_exchange})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titan_screen://筛选 弹框选择
                if (coin.equals("1")) {
                    showScreenDialog();//泰坦筛选弹出选择
                } else if (coin.equals("5")) {
                    showBARDialog();//BAR赛选弹出选择
                }

                break;
            case R.id.top_up_c_btn://充币
                Intent topUp = new Intent(context, TItanTopUpActivity.class);
                topUp.putExtra("coin", coin);
                startActivity(topUp);
                break;
            case R.id.withdraw_c_btn://提币
                Intent TTmoney = new Intent(context, TiTanWithdrawMoney.class);
                TTmoney.putExtra("id", "0");
                TTmoney.putExtra("taitanSum", suntaitan);
                startActivity(TTmoney);
                break;
            case R.id.exchange_btn://交易页面
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("flag", 2);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_exchange://兑换
                Intent exchange = new Intent(context, ExchangeActivity.class);
                exchange.putExtra("num", tvAssetBalance.getText().toString());
                startActivity(exchange);
                break;
        }
    }


    AlertDialog BARDialog = null;

    //bra资产进入选择列表
    private void showBARDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .fromRight(true)
                .setContentView(R.layout.dialog_bar)//载入布局文件
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.all, v -> {//全部
                    type = "";
                    tvTitanScreen.setText(getResources().getString(R.string.all));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    BARDialog.dismiss();
                }).setOnClickListener(R.id.tv_purchase, v -> {
                    type = "13";
                    tvTitanScreen.setText(getResources().getString(R.string.buy));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    BARDialog.dismiss();
                })
                .setOnClickListener(R.id.tv_sell, v -> {//卖出
                    type = "11";
                    tvTitanScreen.setText(getResources().getString(R.string.sell));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    BARDialog.dismiss();
                }).setOnClickListener(R.id.tv_top_up, v -> {//充币
                    type = "1";
                    tvTitanScreen.setText(getResources().getString(R.string.top_up_c));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    BARDialog.dismiss();
                }).setOnClickListener(R.id.tv_exchange, v -> {//兑换
                    type = "21";
                    tvTitanScreen.setText(getResources().getString(R.string.top_exchange));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    BARDialog.dismiss();
                }).setOnClickListener(R.id.tv_service_fee, v -> {//手续费
                    type = "12";
                    tvTitanScreen.setText(getResources().getString(R.string.service_fee));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    BARDialog.dismiss();
                });
        BARDialog = builder.create();
        BARDialog.show();
    }

    AlertDialog screenDialog = null;

    //0，手续费 1，交易释放 2，充币 3，提币 4，买入 5，卖出 6，系统 7，其他 （不填写为全部）
    //泰坦资产进入选择列表
    private void showScreenDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .fromRight(true)
                .setContentView(R.layout.dialog_titan_screen)//载入布局文件
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.nine, v -> {//同级交易获得
                    type = "204";
                    tvTitanScreen.setText(getResources().getString(R.string.peer_acquisition));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.all, v -> {//查询所有
                    type = "";
                    tvTitanScreen.setText(getResources().getString(R.string.all));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_buy, v -> {//买入查询
                    type = "13";
                    tvTitanScreen.setText(getResources().getString(R.string.buy));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_sell, v -> {//卖出查询
                    type = "11";
                    tvTitanScreen.setText(getResources().getString(R.string.sell));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_service_fee, v -> {//手续费查询
                    type = "12";
                    tvTitanScreen.setText(getResources().getString(R.string.service_fee));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_unfrozen, v -> {//交易释放查询
                    type = "201";
                    tvTitanScreen.setText(getResources().getString(R.string.trade_get));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_top_up, v -> {//充币查询
                    type = "1";
                    tvTitanScreen.setText(getResources().getString(R.string.top_up_c));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_withdraw, v -> {//提币查询
                    type = "2";
                    tvTitanScreen.setText(getResources().getString(R.string.withdraw_c));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_exchange, v -> {//兑换
                    type = "22";
                    tvTitanScreen.setText(getResources().getString(R.string.top_exchange));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();

                }).setOnClickListener(R.id.tv_seven, v -> {//直推交易获得
                    type = "202";
                    tvTitanScreen.setText(getResources().getString(R.string.direct_push_trade_gains));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_eight, v -> {//等级交易加权
                    type = "203";
                    tvTitanScreen.setText(getResources().getString(R.string.rank_trading_weight));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                })
                .setOnClickListener(R.id.tv_sd, v -> {
                    type = "22";
                    tvTitanScreen.setText(getResources().getString(R.string.flash_exchange));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                });
        screenDialog = builder.create();
        screenDialog.show();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        mPresent.walletDetail(context, coin);
        refresh.autoRefresh();//自动刷新
    }

    @Override
    public void getWalletDeatilResult(Response<WalletDetailResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();

        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            suntaitan = MoneyUtil.priceFormatDoubleFour(response.body().getData().getWellet().getCoinnum());
            tvAssetBalance.setText(MoneyUtil.priceFormatDoubleFour(response.body().getData().getWellet().getCoinnum()));
            if (coin.equals("1")) {
                tvTixianBalance.setText(MoneyUtil.priceFormatDoubleFour(response.body().getData().getWellet().getCoinnum()));//提现余额
            } else if (coin.equals("5")) {
                tvTixianBalance.setText(MoneyUtil.priceFormatDoubleFour(response.body().getData().getWellet().getFrozennum()));//提现余额
            }
            tvTitanTradingFrozen.setText(MoneyUtil.priceFormatDoubleFour(response.body().getData().getWellet().getFrozennum()));//交易冻结
        }

    }


    List<HistoryListResponse.mData> data;

    @Override
    public void gethistorylist(Response<HistoryListResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            if (page == 1) {
                historylist.clear();
            }
            data = response.body().getData();
            if (!ObjectUtils.isEmpty(data)) {
                rvNormalShow.setVisibility(View.GONE);
                historylist.clear();
                historylist.addAll(data);
                mAdapter.notifyDataSetChanged();
                RecyclerViewAnimation.runLayoutAnimation(rv);//动画显示
                if (data != null && data.size() >= pageSize) {
                    refresh.setEnableLoadMore(true);
                } else {
                    refresh.setEnableLoadMore(false);
                }
                rv.setVisibility(View.VISIBLE);
            } else if (page != 1) {
                refresh.setEnableLoadMore(false);
            } else {
                rvNormalShow.setVisibility(View.VISIBLE);
                rv.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }


}
