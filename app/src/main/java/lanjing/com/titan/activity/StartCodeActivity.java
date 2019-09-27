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
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.StartCodeAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.StartCodeContact;
import lanjing.com.titan.response.CdkeyListResponse;
import retrofit2.Response;

public class StartCodeActivity extends MvpActivity<StartCodeContact.StartCodePresent> implements StartCodeContact.StartCodeView {

    int page = 1;
    int size = 10;
    @BindView(R.id.rv_recycle)
    RecyclerView rv_recycle;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;

    @BindView(R.id.rv_normal_show)
    LinearLayout rvNormalShow;
    List<CdkeyListResponse.Data> mList;
    List<CdkeyListResponse.Data> data;
    StartCodeAdapter startCodeAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresent.CdkeyList(context, page, size);
        initList();

    }

    public void initList() {
        mList = new ArrayList<>();
        startCodeAdapter = new StartCodeAdapter(R.layout.item_get_records, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv_recycle.setLayoutManager(manager);
        rv_recycle.setAdapter(startCodeAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_code_start;
    }

    @Override
    protected StartCodeContact.StartCodePresent createPresent() {
        return new StartCodeContact.StartCodePresent();
    }


    @Override
    public void getCdkeyList(Response<CdkeyListResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            if (page == 1) {
                mList.clear();
            }
            data = response.body().getData();
            if (!ObjectUtils.isEmpty(data)) {
                rvNormalShow.setVisibility(View.GONE);
                mList.addAll(data);
                startCodeAdapter.notifyDataSetChanged();
                if (data != null && data.size() == size) {
                    refresh.setEnableLoadMore(true);
                } else {
                    refresh.setEnableLoadMore(false);
                }
                rv_recycle.setVisibility(View.VISIBLE);
            } else if (page != 1) {
                refresh.setEnableLoadMore(false);
            } else {
                rvNormalShow.setVisibility(View.VISIBLE);
                rv_recycle.setVisibility(View.GONE);
            }


        } else if (response.body().getCode() == -10) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {

    }
}
