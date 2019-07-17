package lanjing.com.titan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxh.baselibray.mvp.MvpFragment;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import lanjing.com.titan.R;
import lanjing.com.titan.activity.InformationDetailActivity;
import lanjing.com.titan.activity.NoticeActivity;
import lanjing.com.titan.adapter.InformationAdapterCH;
import lanjing.com.titan.adapter.InformationAdapterEN;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.InfoNoticeContact;
import lanjing.com.titan.response.InfoNoticeResponse;
import lanjing.com.titan.util.GlideImageLoader;
import lanjing.com.titan.view.ScrollTextView;
import retrofit2.Response;

import static lanjing.com.titan.util.RecyclerViewAnimation.runLayoutAnimation;

/**
 * 资讯数据列表
 */
public class InformationFragment extends MvpFragment<InfoNoticeContact.InfoNoticePresent> implements InfoNoticeContact.IInfoNoticeView {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.list_notice_content)
    ScrollTextView listNoticeContent;
    @BindView(R.id.rv_normal_show)
    LinearLayout rvNormalShow;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;

    int page = 1;
    int pageSize = 10;
    InformationAdapterCH mAdapter;
    List<InfoNoticeResponse.DataCHBean> mList;
    InformationAdapterEN mAdapterEn;
    List<InfoNoticeResponse.DataEHBean> mListEn;
    private List<String> title = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
//        initList();
    }

    private void initList(){
        Locale locale = getResources().getConfiguration().locale;
        if(locale.equals(Locale.SIMPLIFIED_CHINESE)){
            mList = new ArrayList<>();
            mAdapter = new InformationAdapterCH(R.layout.recy_item_information_list,mList);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            rv.setLayoutManager(manager);
            rv.setAdapter(mAdapter);

            mPresent.information(context,String.valueOf(page),String.valueOf(pageSize));
            mPresent.notice(context,String.valueOf(page),String.valueOf(pageSize), "1");



            mAdapter.setOnItemChildClickListener(new InformationAdapterCH.OnItemChildClickListener(){

                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(context,InformationDetailActivity.class);
                    intent.putExtra("content",mList.get(position).getComtent());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    startActivity(intent);
                }
            });
        }else if (locale.equals(Locale.ENGLISH)){
            mListEn = new ArrayList<>();
            mAdapterEn = new InformationAdapterEN(R.layout.recy_item_information_list,mListEn);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            rv.setLayoutManager(manager);
            rv.setAdapter(mAdapterEn);
            mPresent.information(context,String.valueOf(page),String.valueOf(pageSize));
            mPresent.notice(context,String.valueOf(page),String.valueOf(pageSize), "1");

            mAdapterEn.setOnItemChildClickListener(new InformationAdapterCH.OnItemChildClickListener(){

                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(context,InformationDetailActivity.class);
                    intent.putExtra("content",mListEn.get(position).getComtent());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    startActivity(intent);
                }
            });
        }


        refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mPresent.information(context,String.valueOf(page),String.valueOf(pageSize));
            mPresent.notice(context,String.valueOf(page),String.valueOf(pageSize),"1");

        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            mPresent.information(context,String.valueOf(page),String.valueOf(pageSize));
            mPresent.notice(context,String.valueOf(page),String.valueOf(pageSize),"1");
        });


        listNoticeContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,NoticeActivity.class);
                intent.putExtra("type", "1");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        initList();
        super.onResume();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_information;
    }

    @Override
    protected InfoNoticeContact.InfoNoticePresent createPresent() {
        return new InfoNoticeContact.InfoNoticePresent();
    }
    List<InfoNoticeResponse.DataCHBean> data;
    List<InfoNoticeResponse.DataEHBean> dataEn;
    @Override
    public void getInformationResult(Response<InfoNoticeResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            Locale locale = getResources().getConfiguration().locale;//判断当前的语言
            if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                //轮播图
                List<String> bannerBean =  response.body().getBannerCH();
                if (ObjectUtils.isEmpty(bannerBean)) return;
                List<String> imgPath = new ArrayList<>();
                for (int i = 0; i < bannerBean.size(); i++) {
                    imgPath.add(bannerBean.get(i));
                }
                banner.setImages(imgPath);
                banner.setImageLoader(new GlideImageLoader());
                banner.start();

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
                }else {
                    rvNormalShow.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.GONE);
                }



            } else if (locale.equals(Locale.ENGLISH)) {
                //轮播图
                List<String> bannerBean =  response.body().getBannerEH();
                if (ObjectUtils.isEmpty(bannerBean)) return;
                List<String> imgPath = new ArrayList<>();
                for (int i = 0; i < bannerBean.size(); i++) {
                    imgPath.add(bannerBean.get(i));
                }
                banner.setImages(imgPath);
                banner.setImageLoader(new GlideImageLoader());
                banner.start();


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
                }else {
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
    public void getNoticeResult(Response<InfoNoticeResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();
        if(response.body().getCode() == Constant.SUCCESS_CODE){
            Locale locale = getResources().getConfiguration().locale;
            if(locale.equals(Locale.SIMPLIFIED_CHINESE)){
                List<InfoNoticeResponse.DataCHBean> data = response.body().getDataCH();
                if (ObjectUtils.isEmpty(data)) return;
                title.clear();
                for (int i = 0; i < data.size(); i++) {
                    title.add(data.get(i).getTitle());
                }
                listNoticeContent.setList(title);
                listNoticeContent.startScroll();
            }else if(locale.equals(Locale.ENGLISH)){
                List<InfoNoticeResponse.DataEHBean> data = response.body().getDataEH();
                if (ObjectUtils.isEmpty(data)) return;
                title.clear();
                for (int i = 0; i < data.size(); i++) {
                    title.add(data.get(i).getTitle());
                }
                listNoticeContent.setList(title);
                listNoticeContent.startScroll();
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
