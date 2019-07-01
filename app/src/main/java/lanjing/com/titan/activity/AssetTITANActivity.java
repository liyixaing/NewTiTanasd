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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.CoinTitanAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.WalletDetailContact;
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
    @BindView(R.id.tv_titan_not_trading)
    TextView tvTitanNotTrading;
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

    String walletId;//钱包ID


    CoinTitanAdapter mAdapter;
    List<WalletDetailResponse.HistoryBean> mList;

    int page = 1;
    int pageSize = 20;
    String type;//0，手续费 1，交易释放 2，充币 3，提币 4，买入 5，卖出 6，系统 7，其他 （不填写为全部）

    @Override
    public void initData(Bundle savedInstanceState) {
        walletId = getIntent().getStringExtra("walletId");

        mList = new ArrayList<>();
        mAdapter = new CoinTitanAdapter(R.layout.recy_item_titan_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        mPresent.walletDetail(context, walletId, "", String.valueOf(page), String.valueOf(pageSize));

        mAdapter.setOnItemChildClickListener(new CoinTitanAdapter.OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //判断是否要进入详情页
                if (mList.get(position).getType().equals("32") || mList.get(position).getType().equals("33")) {
                    Intent intent = new Intent(context, AssetTitanDetailActivity.class);
                    intent.putExtra("id", mList.get(position).getId());
                    intent.putExtra("name", mList.get(position).getType());
                    startActivity(intent);
                }

            }
        });

        refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mPresent.walletDetail(context, walletId, "", String.valueOf(page), String.valueOf(pageSize));

        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            mPresent.walletDetail(context, walletId, "", String.valueOf(page), String.valueOf(pageSize));
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

    @OnClick({R.id.tv_titan_screen, R.id.top_up_c_btn, R.id.withdraw_c_btn, R.id.exchange_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titan_screen://筛选   弹框选择
                showScreenDialog();
                break;
            case R.id.top_up_c_btn://充币
                Intent topUp = new Intent(context, TItanTopUpActivity.class);
                startActivity(topUp);
                break;
            case R.id.withdraw_c_btn://提币
                Intent withdrwa = new Intent(context, TItanWithdrawActivity.class);
                withdrwa.putExtra("balance", tvTixianBalance.getText().toString());
                startActivity(withdrwa);
                break;
            case R.id.exchange_btn://交易页面
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("flag", 2);
                startActivity(intent);
                finish();
                break;
        }
    }

    AlertDialog screenDialog = null;

    //0，手续费 1，交易释放 2，充币 3，提币 4，买入 5，卖出 6，系统 7，其他 （不填写为全部）
    private void showScreenDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .fromRight(true)
                .setContentView(R.layout.dialog_titan_screen)//载入布局文件
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.nine, v -> {//同级交易获得
                    type = "7";
                    mPresent.walletDetail(context, walletId, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.all, v -> {//查询所有
                    type = "";
                    mPresent.walletDetail(context, walletId, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_buy, v -> {//买入查询
                    type = "34";
                    mPresent.walletDetail(context, walletId, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_sell, v -> {//卖出查询
                    type = "35";
                    mPresent.walletDetail(context, walletId, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_service_fee, v -> {//手续费查询
                    type = "30";
                    mPresent.walletDetail(context, walletId, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_unfrozen, v -> {//交易释放查询
                    type = "1";
                    mPresent.walletDetail(context, walletId, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_top_up, v -> {//充币查询
                    type = "2";
                    mPresent.walletDetail(context, walletId, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_withdraw, v -> {//提币查询
                    type = "3";
                    mPresent.walletDetail(context, walletId, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_seven, v -> {//直推交易获得
                    type = "5";
                    mPresent.walletDetail(context, walletId, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_eight, v -> {//等级交易加权
                    type = "6";
                    mPresent.walletDetail(context, walletId, type, String.valueOf(page), String.valueOf(pageSize));
                    screenDialog.dismiss();
                });
        screenDialog = builder.create();
        screenDialog.show();
    }

    List<WalletDetailResponse.HistoryBean> data;

    @Override
    public void getWalletDeatilResult(Response<WalletDetailResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            tvAssetBalance.setText(MoneyUtil.priceFormatDoubleFour(response.body().getData().getSum()) + " TITAN");//可用余额
            tvTixianBalance.setText(MoneyUtil.priceFormatDoubleFour(response.body().getData().getCoinnum()));//提现余额

            tvTitanNotTrading.setText(MoneyUtil.priceFormatDoubleFour(response.body().getData().getShiftnum()));//转入
            tvTitanTradingFrozen.setText(MoneyUtil.priceFormatDoubleFour(response.body().getData().getFrozennum()));//交易冻结

            if (page == 1) {
                mList.clear();
            }
            data = response.body().getHistory();
            if (!ObjectUtils.isEmpty(data)) {
                rvNormalShow.setVisibility(View.GONE);
                mList.clear();
                mList.addAll(data);
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

        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }

    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }


}
