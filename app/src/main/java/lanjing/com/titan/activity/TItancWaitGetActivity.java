package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.CoinTitanAdapter;
import lanjing.com.titan.adapter.CoinTitancAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.WalletDetailContact;
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

    String walletId;//钱包ID


    CoinTitancAdapter mAdapter;
    List<WalletDetailResponse.History2Bean> mList;

    int page = 1;
    int pageSize = 20;
    String type;//0，手续费 1，交易释放 2，充币 3，提币 4，买入 5，卖出 6，系统 7，其他 （不填写为全部）

    @Override
    public void initData(Bundle savedInstanceState) {
        walletId = getIntent().getStringExtra("walletId");

        mList = new ArrayList<>();
        mAdapter = new CoinTitancAdapter(R.layout.recy_item_titanc_wait_get_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        mPresent.walletDetail(context, walletId, "", String.valueOf(page), String.valueOf(pageSize));

//        mAdapter.setOnItemChildClickListener(new CoinTitancAdapter.OnItemChildClickListener(){
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(context,AssetTitanDetailActivity.class);
//                intent.putExtra("id",mList.get(position).getId());
//                startActivity(intent);
//            }
//        });

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
        return R.layout.activity_titanc_wait_get;
    }

    @Override
    protected WalletDetailContact.WalletDetailPresent createPresent() {
        return new WalletDetailContact.WalletDetailPresent();
    }
    List<WalletDetailResponse.History2Bean> data;
    @Override
    public void getWalletDeatilResult(Response<WalletDetailResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            tvTitancWaitGet.setText(MoneyUtil.priceFormatDoubleFour(response.body().getData().getSum())+"TITANC");//可用余额
            if (page == 1) {
                mList.clear();
            }
            data = response.body().getHistory2();
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
