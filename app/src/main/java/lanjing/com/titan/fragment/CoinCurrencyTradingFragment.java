package lanjing.com.titan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxh.baselibray.mvp.MvpFragment;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import lanjing.com.titan.R;
import lanjing.com.titan.activity.DetailsOfBillsActivity;
import lanjing.com.titan.adapter.CoinDealAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.CoinDealContact;
import lanjing.com.titan.response.CoinDealResponse;
import retrofit2.Response;

import static lanjing.com.titan.util.RecyclerViewAnimation.runLayoutAnimation;

/**
 * 币币交易
 */
public class CoinCurrencyTradingFragment extends MvpFragment<CoinDealContact.CoinDealPresent> implements CoinDealContact.ICoinDealView {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.rv_normal_show)
    LinearLayout rvNormalShow;
    Unbinder unbinder;

    int page = 1;
    int size = 10;
    CoinDealAdapter mAdapter;
    List<CoinDealResponse.DataBean> mList;

    @Override
    public void initData(Bundle savedInstanceState) {
        //查询列表数据
        initList();
    }

    private void initList() {
        mList = new ArrayList<>();
        mAdapter = new CoinDealAdapter(R.layout.recy_item_coin_curr_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //type 1买入 2卖出
                if (mList.get(position).getType().equals("2")) {
                    Intent intent = new Intent(context, DetailsOfBillsActivity.class);
                    intent.putExtra("id", mList.get(position).getId());
                    startActivity(intent);
                } else {
                    //type类型为1不做处理
                }

            }
        });
        mPresent.coinDeal(context, String.valueOf(page), String.valueOf(size));

        refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mPresent.coinDeal(context, String.valueOf(page), String.valueOf(size));

        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            mPresent.coinDeal(context, String.valueOf(page), String.valueOf(size));
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_coin_currency_trading;
    }

    @Override
    protected CoinDealContact.CoinDealPresent createPresent() {
        return new CoinDealContact.CoinDealPresent();
    }

    List<CoinDealResponse.DataBean> data;

    @Override
    public void getCoinDealResult(Response<CoinDealResponse> response) {
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
            } else if (page != 1) {
                refresh.setEnableLoadMore(false);
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

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }

}
