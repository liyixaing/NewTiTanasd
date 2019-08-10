package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import lanjing.com.titan.adapter.CoinUsdAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.WalletDetailContact;
import lanjing.com.titan.response.HistoryListResponse;
import lanjing.com.titan.response.WalletDetailResponse;
import lanjing.com.titan.util.MoneyUtil;
import lanjing.com.titan.util.RecyclerViewAnimation;
import retrofit2.Response;

/**
 * USD  资产
 */
public class AssetUSDActivity extends MvpActivity<WalletDetailContact.WalletDetailPresent> implements WalletDetailContact.IWalletDetailView {

    @BindView(R.id.tv_usd_balance)
    TextView tvUsdBalance;
    @BindView(R.id.tv_usd_frozen)
    TextView tvUsdFrozen;
    @BindView(R.id.tv_usd_screen)
    TextView tvUsdScreen;
    @BindView(R.id.rv_normal_show)
    LinearLayout rvNormalShow;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.exchange_btn)
    TextView exchangeBtn;

    String walletId;//钱包ID

    CoinUsdAdapter mAdapter;

    int page = 1;
    int pageSize = 20;
    String type = "";//0，手续费 1，交易释放 2，充币 3，提币 4，买入 5，卖出 6，系统 7，其他 （不填写为全部）
    @BindView(R.id.title_lay)
    TitleView titleLay;
    String coin;

    List<HistoryListResponse.mData> mList;

    @Override
    public void initData(Bundle savedInstanceState) {

        walletId = getIntent().getStringExtra("walletId");
        coin = getIntent().getStringExtra("coin");
        mPresent.walletDetail(context, coin);
        mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
        Log.e("coin", coin);
        Log.e("type", type);
        Log.e("pageSize", String.valueOf(pageSize));
        Log.e("page", String.valueOf(page));
        mList = new ArrayList<>();
        mAdapter = new CoinUsdAdapter(R.layout.recy_item_usd_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //点击子控件跳转详情
//                Intent intent = new Intent(context, AssetUsdDetailActivity.class);
//                intent.putExtra("id", mList.get(position).getId());
//                intent.putExtra("type", mList.get(position).getChangeType());
//                startActivity(intent);
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
        return R.layout.activity_asset_usd;
    }

    @Override
    protected WalletDetailContact.WalletDetailPresent createPresent() {
        return new WalletDetailContact.WalletDetailPresent();
    }

//    List<WalletDetailResponse.HistoryBean> data;

    @Override
    public void getWalletDeatilResult(Response<WalletDetailResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            tvUsdBalance.setText(MoneyUtil.priceFormatDoubleFour(response.body().getData().getWellet().getCoinnum()) + "USD");//USD资产余额
            tvUsdFrozen.setText(MoneyUtil.priceFormatDoubleFour(response.body().getData().getWellet().getFrozennum()));//获取usd的冻结资产

        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }


    List<HistoryListResponse.mData> data;

    @Override
    public void gethistorylist(Response<HistoryListResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {

            if (page == 1) {
                mList.clear();
            }
            data = response.body().getData();
            if (!ObjectUtils.isEmpty(data)) {
                rvNormalShow.setVisibility(View.GONE);
                mList.clear();
                mList.addAll(data);
//                mAdapter.notifyDataSetChanged();
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

    AlertDialog screenDialog = null;

    //0，手续费 1，交易释放 2，充币 3，提币 4，买入 5，卖出 6，系统 7，其他 （不填写为全部）
    private void showScreenDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .fromRight(true)
                .setContentView(R.layout.dialog_usd_screen)//载入布局文件
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                .setOnClickListener(R.id.all, v -> {//查询所有
                    type = "";
                    tvUsdScreen.setText(getResources().getString(R.string.all));
                    mPresent.walletDetail(context, coin);
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_buy, v -> {//买入查询
                    type = "34";
                    tvUsdScreen.setText(getResources().getString(R.string.buy));
                    mPresent.walletDetail(context, coin);
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_sell, v -> {//卖出查询
                    type = "35";
                    tvUsdScreen.setText(getResources().getString(R.string.sell));
                    mPresent.walletDetail(context, coin);
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_service_fee, v -> {//手续费查询
                    type = "30";
                    tvUsdScreen.setText(getResources().getString(R.string.service_fee));
                    mPresent.walletDetail(context, coin);
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_system, v -> {//系统查询
                    type = "6";
                    mPresent.walletDetail(context, coin);
                    screenDialog.dismiss();
                }).setOnClickListener(R.id.tv_others, v -> {//其它查询
                    type = "7";
                    mPresent.walletDetail(context, coin);
                    screenDialog.dismiss();
                });
        screenDialog = builder.create();
        screenDialog.show();

    }


    @OnClick({R.id.tv_usd_screen, R.id.exchange_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_usd_screen:
                showScreenDialog();
                break;
            case R.id.exchange_btn:
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("flag", 4);
                startActivity(intent);
                finish();
                break;
        }
    }


}
