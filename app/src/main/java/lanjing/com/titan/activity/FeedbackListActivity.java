package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxh.baselibray.base.XActivity;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.FeedbackListAdapter;
import lanjing.com.titan.adapter.WithDrawalAdapter;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.FeedbackListContact;
import lanjing.com.titan.response.FeedbackListResponse;
import retrofit2.Response;

import static lanjing.com.titan.util.RecyclerViewAnimation.runLayoutAnimation;

/**
 * 建议反馈  列表
 */
public class FeedbackListActivity extends MvpActivity<FeedbackListContact.FeedbackListPresent> implements FeedbackListContact.IFeedbackListView {


    @BindView(R.id.rv_normal_show)
    LinearLayout rvNormalShow;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    FeedbackListAdapter mAdapter;
    List<FeedbackListResponse.DataBean> mList;
    int page = 1;
    int size = 20;



    @Override
    public void initData(Bundle savedInstanceState) {
        //查询列表数据
        initList();
    }

    private void initList() {
        mList = new ArrayList<>();
        mAdapter = new FeedbackListAdapter(R.layout.recy_item_feedback_list, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv.setLayoutManager(manager);
        rv.setAdapter(mAdapter);
        mPresent.feedbackList(context, String.valueOf(page), String.valueOf(size));

        refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mPresent.feedbackList(context, String.valueOf(page), String.valueOf(size));

        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            mPresent.feedbackList(context, String.valueOf(page), String.valueOf(size));
        });

        mAdapter.setOnItemChildClickListener(new FeedbackListAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context,FeedbackDetailActivity.class);
                intent.putExtra("title",mList.get(position).getTitle());
                intent.putExtra("content",mList.get(position).getContent());
                intent.putExtra("reply",mList.get(position).getReply());
                startActivity(intent);
            }
        });
    }



    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback_list;
    }
    List<FeedbackListResponse.DataBean> data;
    @Override
    protected FeedbackListContact.FeedbackListPresent createPresent() {
        return new FeedbackListContact.FeedbackListPresent();
    }

    @Override
    public void getFeedbackListResult(Response<FeedbackListResponse> response) {
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
        ToastUtils.showShortToast(context,getResources().getString(R.string.network_error));
    }
}
