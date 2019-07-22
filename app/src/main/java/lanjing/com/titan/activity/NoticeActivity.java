package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.lxh.baselibray.view.TitleView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.NoticeAdapterCH;
import lanjing.com.titan.adapter.NoticeAdapterEN;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.InfoNoticeContact;
import lanjing.com.titan.response.InfoNoticeResponse;
import retrofit2.Response;

import static lanjing.com.titan.util.RecyclerViewAnimation.runLayoutAnimation;

/**
 * 公告列表
 */
public class NoticeActivity extends MvpActivity<InfoNoticeContact.InfoNoticePresent> implements InfoNoticeContact.IInfoNoticeView {

    @BindView(R.id.rv_normal_show)
    LinearLayout rvNormalShow;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.tv_titleView)
    TitleView TvTitleView;

    int type;
    String stringExtra;
    int page = 1;
    int pageSize = 10;
    NoticeAdapterCH mAdapter;
    List<InfoNoticeResponse.DataCHBean> mList;
    NoticeAdapterEN mAdapterEn;
    List<InfoNoticeResponse.DataEHBean> mListEn;

    @Override
    public void initData(Bundle savedInstanceState) {
        stringExtra = getIntent().getStringExtra("type");
        if (stringExtra.equals("1")) {
            type = 1;
            TvTitleView.setTitleText(getResources().getString(R.string.notice));
        } else {
            type = 2;
            TvTitleView.setTitleText(getResources().getString(R.string.committee));
        }
        Locale locale = getResources().getConfiguration().locale;
        if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
            mList = new ArrayList<>();
            mAdapter = new NoticeAdapterCH(R.layout.recy_item_notice_list, mList);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            rv.setLayoutManager(manager);
            rv.setAdapter(mAdapter);
            mPresent.notice(context, String.valueOf(page), String.valueOf(pageSize), stringExtra);

            mAdapter.setOnItemChildClickListener(new NoticeAdapterCH.OnItemChildClickListener() {

                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(context, NoticeDetailActivity.class);
                    intent.putExtra("title", mList.get(position).getTitle());
                    intent.putExtra("content", mList.get(position).getComtent());
                    intent.putExtra("time", mList.get(position).getCreatetime());
                    intent.putExtra("team", mList.get(position).getUname());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    startActivity(intent);
                }
            });
        } else if (locale.equals(Locale.ENGLISH)) {
            mListEn = new ArrayList<>();
            mAdapterEn = new NoticeAdapterEN(R.layout.recy_item_notice_list, mListEn);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            rv.setLayoutManager(manager);
            rv.setAdapter(mAdapterEn);
            mPresent.notice(context, String.valueOf(page), String.valueOf(pageSize), stringExtra);
            mAdapterEn.setOnItemChildClickListener(new NoticeAdapterCH.OnItemChildClickListener() {

                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(context, NoticeDetailActivity.class);
                    intent.putExtra("title", mListEn.get(position).getTitle());
                    intent.putExtra("content", mListEn.get(position).getComtent());
                    intent.putExtra("time", mListEn.get(position).getCreatetime());
                    intent.putExtra("team", mListEn.get(position).getUname());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    startActivity(intent);
                }
            });
        }


        refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mPresent.notice(context, String.valueOf(page), String.valueOf(pageSize), stringExtra);

        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            mPresent.notice(context, String.valueOf(page), String.valueOf(pageSize), stringExtra);
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_notice;
    }


    @Override
    protected InfoNoticeContact.InfoNoticePresent createPresent() {
        return new InfoNoticeContact.InfoNoticePresent();
    }

    @Override
    public void getInformationResult(Response<InfoNoticeResponse> response) {

    }

    List<InfoNoticeResponse.DataCHBean> data;
    List<InfoNoticeResponse.DataEHBean> dataEn;

    @Override
    public void getNoticeResult(Response<InfoNoticeResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            Locale locale = getResources().getConfiguration().locale;
            if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                if (page == 1) {
                    mList.clear();
                    mAdapter.notifyDataSetChanged();
                }
                data = response.body().getDataCH();
                if (!ObjectUtils.isEmpty(data)) {
                    rvNormalShow.setVisibility(View.GONE);
                    mList.addAll(data);
                    mAdapter.notifyDataSetChanged();
                    runLayoutAnimation(rv);
                    if (data != null && data.size() == pageSize) {
                        refresh.setEnableLoadMore(true);
                    } else {
                        refresh.setEnableLoadMore(false);
                    }
                    rv.setVisibility(View.VISIBLE);
                } else {
                    rvNormalShow.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.GONE);
                }
            } else if (locale.equals(Locale.ENGLISH)) {
                if (page == 1) {
                    mListEn.clear();
                    mAdapterEn.notifyDataSetChanged();
                }
                dataEn = response.body().getDataEH();
                if (!ObjectUtils.isEmpty(dataEn)) {
                    rvNormalShow.setVisibility(View.GONE);
                    mListEn.addAll(dataEn);
                    mAdapterEn.notifyDataSetChanged();
                    runLayoutAnimation(rv);
                    if (dataEn != null && dataEn.size() == pageSize) {
                        refresh.setEnableLoadMore(true);
                    } else {
                        refresh.setEnableLoadMore(false);
                    }
                    rv.setVisibility(View.VISIBLE);
                } else {
                    rvNormalShow.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.GONE);
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
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }
}
