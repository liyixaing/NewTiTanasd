package lanjing.com.titan.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.GovernanceAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.GovernanceContact;
import lanjing.com.titan.response.InformationResponse;
import retrofit2.Response;

import static lanjing.com.titan.util.RecyclerViewAnimation.runLayoutAnimation;

/**
 * 委员会  列表形式
 */
public class CommitteeActivity extends MvpActivity<GovernanceContact.GovernanceCommittee> implements GovernanceContact.IInfoCommitteeView {

    @BindView(R.id.rv_normal_show)
    LinearLayout RvNormalShow;//空显示
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    List<InformationResponse.DataCH> mList;
    int type = 3;//委员会类型固定为3
    int page = 1;
    int pageSize = 10;
    int language = 1;
    //添加适配器
    GovernanceAdapter goveAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        mList = new ArrayList<>();
        goveAdapter = new GovernanceAdapter(R.layout.recy_item_notice_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(goveAdapter);
        mPresent.initGovernance(context, type, page, pageSize, language);
        goveAdapter.setOnItemChildClickListener(new GovernanceAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_committee;
    }

    @Override
    protected GovernanceContact.GovernanceCommittee createPresent() {
        return new GovernanceContact.GovernanceCommittee();
    }

    List<InformationResponse.DataCH> data;

    @Override
    public void getGovernance(Response<InformationResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            Locale locale = getResources().getConfiguration().locale;
            if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                if (page == 1) {
                    mList.clear();
                    goveAdapter.notifyDataSetChanged();
                }
                data = response.body().getDataCH();
                if (!ObjectUtils.isEmpty(data)) {
                    RvNormalShow.setVisibility(View.GONE);
                    mList.addAll(data);
                    goveAdapter.notifyDataSetChanged();
                    runLayoutAnimation(recyclerView);
                    if (data != null && data.size() == pageSize) {
                        refresh.setEnableLoadMore(true);
                    } else {
                        refresh.setEnableLoadMore(false);
                    }
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    RvNormalShow.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));

        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {

    }
}
