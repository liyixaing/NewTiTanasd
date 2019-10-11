package lanjing.com.titan.activity;

import android.content.Context;
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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.CoinTitanAdapter;
import lanjing.com.titan.adapter.CoinTitancAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.WalletDetailContact;
import lanjing.com.titan.response.HistoryListResponse;
import lanjing.com.titan.response.WalletDetailResponse;
import lanjing.com.titan.util.MoneyUtil;
import lanjing.com.titan.util.RecyclerViewAnimation;
import retrofit2.Response;

/**
 * TITANC  待激活
 */
public class TItancWaitGetActivity extends MvpActivity<WalletDetailContact.WalletDetailPresent> implements WalletDetailContact.IWalletDetailView {

    @BindView(R.id.tv_titanc_wait_get)
    TextView tvTitancWaitGet;
    @BindView(R.id.rv_normal_show)
    LinearLayout rvNormalShow;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.tv_all_btn)
    TextView tv_all_btn;//全部

    @BindView(R.id.top_up_c_btn)
    TextView top_up_c_btn;//充币
    @BindView(R.id.withdraw_c_btn)
    TextView withdraw_c_btn;//闪兑
    @BindView(R.id.exchange_btn)
    TextView exchange_btn;//转出
    String walletId;//钱包ID


    CoinTitancAdapter mAdapter;
    List<HistoryListResponse.mData> mList;

    String sun = "0";

    int page = 1;
    int pageSize = 20;
    String type = "";//0，手续费 1，交易释放 2，充币 3，提币 4，买入 5，卖出 6，系统 7，其他 （不填写为全部）
    String coin;

    @Override
    public void initData(Bundle savedInstanceState) {
        walletId = getIntent().getStringExtra("walletId");
        coin = getIntent().getStringExtra("coin");
        Log.e("类型", coin);
        mList = new ArrayList<>();
        mAdapter = new CoinTitancAdapter(R.layout.recy_item_titanc_wait_get_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        mPresent.walletDetail(context, coin);
        mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mList.get(position).getChangeType().equals("1") || mList.get(position).getChangeType().equals("2")) {
                    Intent intent = new Intent(context, AssetTitanDetailActivity.class);
                    intent.putExtra("id", String.valueOf(mList.get(position).getId()));
                    intent.putExtra("name", mList.get(position).getChangeDesc());
                    startActivity(intent);
                } else if (mList.get(position).getChangeType().equals("21") || mList.get(position).getChangeType().equals("22")) {
                    Intent details = new Intent(context, ExchangeDetailsActivity.class);
                    details.putExtra("id", String.valueOf(mList.get(position).getId()));
                    details.putExtra("name", mList.get(position).getChangeDesc());
                    startActivity(details);
                } else if (mList.get(position).getChangeType().equals("25") || mList.get(position).getChangeType().equals("15")) {
                    //转出类型跳转
                    Intent Transfer_out = new Intent(context, TransferDetailsActivity.class);
                    Transfer_out.putExtra("id", String.valueOf(mList.get(position).getId()));
                    startActivity(Transfer_out);

                }
            }
        });

        //点击子方法跳转界面
//        Intent intent = new Intent(context,AssetTitanDetailActivity.class);
//        intent.putExtra("id",mList.get(position).getId());
//        startActivity(intent);

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
        return R.layout.activity_titanc_wait_get;
    }

    @OnClick({R.id.tv_all_btn, R.id.top_up_c_btn, R.id.withdraw_c_btn, R.id.exchange_btn})

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_all_btn:
                showBARDialog();
                break;
            case R.id.top_up_c_btn://充币
                Intent topUp = new Intent(context, TItanTopUpActivity.class);
                topUp.putExtra("coin", coin);
                startActivity(topUp);
                break;
            case R.id.withdraw_c_btn://闪兑
                Intent exchange = new Intent(context, ExchangeActivity.class);
                exchange.putExtra("num", "0");
                startActivity(exchange);
                break;
            case R.id.exchange_btn://转出
                Intent exchanget = new Intent(context, TurnOutActivity.class);
                exchanget.putExtra("coin", coin);
                exchanget.putExtra("sun", sun);
                startActivity(exchanget);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresent.walletDetail(context, coin);
        mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
    }

    //titanc 选择类型弹窗
    AlertDialog TiTancDialog = null;

    private void showBARDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .addDefaultAnimation()//默认弹窗动画
                .setCancelable(true)
                .fromRight(true)
                .setContentView(R.layout.dialog_titanc)//载入布局文件
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOnClickListener(R.id.all, v -> {//全部
                    type = "";
                    tv_all_btn.setText(getResources().getString(R.string.all));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    TiTancDialog.dismiss();
                })
                .setOnClickListener(R.id.tv_charging, v -> {//充币
                    type = "1";
                    tv_all_btn.setText(getResources().getString(R.string.top_up_c));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    TiTancDialog.dismiss();
                })
                .setOnClickListener(R.id.tv_top_up, v -> {//转出
                    type = "25";
                    tv_all_btn.setText(getResources().getString(R.string.turn_out));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    TiTancDialog.dismiss();
                })//tv_top_set
                .setOnClickListener(R.id.tv_top_set, v -> {//转入
                    type = "15";
                    tv_all_btn.setText(getResources().getString(R.string.turn_put));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    TiTancDialog.dismiss();
                })//tv_top_set
                .setOnClickListener(R.id.tv_exchange_get, v -> {//兑换获得
                    type = "21";
                    tv_all_btn.setText(getResources().getString(R.string.flash_exchange));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    TiTancDialog.dismiss();
                })
                .setOnClickListener(R.id.tv_sell, v -> {//交易激活
                    type = "1002";
                    tv_all_btn.setText(getResources().getString(R.string.unfrozen));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    TiTancDialog.dismiss();

                })
                .setOnClickListener(R.id.tv_of_mineral, v -> {//矿层交易激活
                    type = "1003";
                    tv_all_btn.setText(getResources().getString(R.string.of_mineral));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    TiTancDialog.dismiss();
                })
                .setOnClickListener(R.id.tv_ore_pool, v -> {//矿池交易激活
                    type = "1004";
                    tv_all_btn.setText(getResources().getString(R.string.ore_pool));
                    mPresent.historylist(context, coin, type, String.valueOf(page), String.valueOf(pageSize));
                    TiTancDialog.dismiss();
                });
        TiTancDialog = builder.create();
        TiTancDialog.show();
    }


    @Override
    protected WalletDetailContact.WalletDetailPresent createPresent() {
        return new WalletDetailContact.WalletDetailPresent();
    }

//    List<WalletDetailResponse.History2Bean> data;

    @Override
    public void getWalletDeatilResult(Response<WalletDetailResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            sun = MoneyUtil.priceFormatDoubleFour(response.body().getData().getWellet().getCoinnum());
            tvTitancWaitGet.setText(MoneyUtil.priceFormatDoubleFour(response.body().getData().getWellet().getCoinnum()) + "TITANC");//可用余额
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

}
