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
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.AwardAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.AwardContact;
import lanjing.com.titan.response.AwardResponse;
import retrofit2.Response;

import static lanjing.com.titan.util.RecyclerViewAnimation.runLayoutAnimation;

/**
 * 空投奖励
 */
public class RewardDropFragment extends MvpFragment<AwardContact.AwardPresent> implements AwardContact.IAwardView {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.rv_normal_show)
    LinearLayout rvNormalShow;

    int page = 1;
    int size = 10;
    AwardAdapter mAdapter;
    List<AwardResponse.DataBean> mList;


    @Override
    public void initData(Bundle savedInstanceState) {
        //查询列表数据
        initList();
    }

    private void initList() {
        mList = new ArrayList<>();
        mAdapter = new AwardAdapter(R.layout.recy_item_award_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        mPresent.award(context, String.valueOf(page), String.valueOf(size));

        refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mPresent.award(context, String.valueOf(page), String.valueOf(size));

        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            mPresent.award(context, String.valueOf(page), String.valueOf(size));
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_reward_drop;
    }

    @Override
    protected AwardContact.AwardPresent createPresent() {
        return new AwardContact.AwardPresent();
    }
    List<AwardResponse.DataBean> data;
    @Override
    public void getAwardResult(Response<AwardResponse> response) {
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
