package lanjing.com.titan.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.HistoryEntrustAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.HistoryContact;
import lanjing.com.titan.response.EntrustListResponse;
import retrofit2.Response;

import static lanjing.com.titan.util.RecyclerViewAnimation.runLayoutAnimation;

/**
 * 历史委托列表
 */
public class HistoryEntrustActivity extends MvpActivity<HistoryContact.HistoryPresent> implements HistoryContact.IHistoryView {


    @BindView(R.id.rv_normal_show)
    LinearLayout rvNormalShow;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    HistoryEntrustAdapter mAdapter;
    List<EntrustListResponse.DataBean> mList;
    int page = 1;
    int size = 10;

    @Override
    public void initData(Bundle savedInstanceState) {
        mList = new ArrayList<>();
        mAdapter = new HistoryEntrustAdapter(R.layout.recy_item_history_entrustment_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        mPresent.historyList(context, String.valueOf(page), String.valueOf(size), "2");

        refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mPresent.historyList(context, String.valueOf(page), String.valueOf(size), "2");

        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            mPresent.historyList(context, String.valueOf(page), String.valueOf(size), "2");
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_entrust;
    }

    @Override
    protected HistoryContact.HistoryPresent createPresent() {
        return new HistoryContact.HistoryPresent();
    }

    List<EntrustListResponse.DataBean> data;

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
