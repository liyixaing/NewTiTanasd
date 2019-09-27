package lanjing.com.titan.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.lxh.baselibray.mvp.MvpFragment;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.WithDrawalAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.WithDrawalContact;
import lanjing.com.titan.response.CoinLogListResponse;
import lanjing.com.titan.response.WithdrawalResponse;
import retrofit2.Response;

import static lanjing.com.titan.util.RecyclerViewAnimation.runLayoutAnimation;

/**
 * 充值提现
 */
public class CashValueFragment extends MvpFragment<WithDrawalContact.WithDrawalPresent> implements WithDrawalContact.IWithDrawalView {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    Unbinder unbinder;

    int page = 1;
    int size = 10;
    WithDrawalAdapter mAdapter;
    List<CoinLogListResponse.Data> mList;
    List<CoinLogListResponse.Data> data;
    @BindView(R.id.rv_normal_show)
    LinearLayout rvNormalShow;
    String coin = "";


    @Override
    public void initData(Bundle savedInstanceState) {
        //查询列表数据
        initList();
    }

    private void initList() {
        mList = new ArrayList<>();
        mAdapter = new WithDrawalAdapter(R.layout.recy_item_cash_value_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        mPresent.CoinLogList(context, coin, "1", String.valueOf(page), String.valueOf(size));

        refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mPresent.CoinLogList(context, coin, "1", String.valueOf(page), String.valueOf(size));

        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            mPresent.CoinLogList(context, coin, "1", String.valueOf(page), String.valueOf(size));
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cash_value;
    }

    @Override
    protected WithDrawalContact.WithDrawalPresent createPresent() {
        return new WithDrawalContact.WithDrawalPresent();
    }

    @Override
    public void getWithDrawalResult(Response<WithdrawalResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {


        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getCoinLogList(Response<CoinLogListResponse> response) {
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
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }

}
