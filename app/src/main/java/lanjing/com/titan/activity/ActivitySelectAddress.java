package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxh.baselibray.dialog.AlertDialog;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.net.ServiceGenerator;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.adapter.SelectAddressAdapter;
import lanjing.com.titan.api.ApiService;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.SelectAddressContact;
import lanjing.com.titan.net.NetCallBack;
import lanjing.com.titan.request.deleterRequest;
import lanjing.com.titan.response.AddressListResponse;
import lanjing.com.titan.response.ResultDTO;
import lanjing.com.titan.util.RecyclerViewAnimation;
import lanjing.com.titan.util.SizeUtils;
import retrofit2.Response;

/**
 * 选择提币地址界面
 */
public class ActivitySelectAddress extends MvpActivity<SelectAddressContact.SelectAddressPresent> implements SelectAddressContact.SelectView {
    @BindView(R.id.lv_normal_show)
    LinearLayout LvNormalShow;
    @BindView(R.id.iv_address)
    ImageView IvAddress;
    @BindView(R.id.rv_select_list)
    RecyclerView RvSelectList;

    @BindView(R.id.refresh)//下拉刷新
            SmartRefreshLayout refresh;
    //添加适配器
    SelectAddressAdapter selecAdapter;
    String taitanSum;

    int page = 1;
    int pageSize = 20;
    List<AddressListResponse.Data> mList;

    @Override
    protected SelectAddressContact.SelectAddressPresent createPresent() {
        return new SelectAddressContact.SelectAddressPresent();
    }

    AlertDialog pwdDialog = null;

    @Override
    public void initData(Bundle savedInstanceState) {
        taitanSum = getIntent().getStringExtra("taitanSum");
        mPresent.walletList(context, String.valueOf(page), String.valueOf(pageSize));
        mList = new ArrayList<>();
        selecAdapter = new SelectAddressAdapter(R.layout.item_select_address, mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        RvSelectList.setLayoutManager(manager);
        RvSelectList.setAdapter(selecAdapter);
        selecAdapter.setOnItemChildClickListener(new SelectAddressAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.iv_retun) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .addDefaultAnimation()//默认弹窗动画
                            .setCancelable(true)
                            .setContentView(R.layout.dialog_renter)//载入布局文件
                            .setWidthAndHeight(SizeUtils.dp2px(context, 258), ViewGroup.LayoutParams.WRAP_CONTENT)//设置弹窗宽高
                            .setOnClickListener(R.id.tx_sure, v -> {//设置点击事件
                                String token = SPUtils.getString(Constant.TOKEN, "", context);
                                ApiService service = ServiceGenerator.createService(ApiService.class);
                                deleterRequest sendCodeRequest = new deleterRequest(mList.get(position).getId());
                                service.deleteTransferAddress(token, sendCodeRequest).enqueue(new NetCallBack<ResultDTO>() {
                                    @Override
                                    public void onSuccess(retrofit2.Call<ResultDTO> call, retrofit2.Response<ResultDTO> response) {
                                        if (response.body().getCode() == 200) {
                                            ToastUtils.showShortToast(context, response.body().getMsg());
                                            refresh.autoRefresh();//删除成功直接刷新界面
                                        } else {
                                            ToastUtils.showShortToast(context, response.body().getMsg());
                                        }
                                    }

                                    @Override
                                    public void onFailed() {
                                        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
                                    }
                                });
                                pwdDialog.dismiss();
                            }).setOnClickListener(R.id.tx_cancel, v -> pwdDialog.dismiss());
                    pwdDialog = builder.create();
                    pwdDialog.show();


                } else if (view.getId() == R.id.ll_item) {
                    Intent intent = new Intent(context, TiTanWithdrawMoney.class);
                    intent.putExtra("id", mList.get(position).getId());
                    intent.putExtra("taitanSum", taitanSum);
                    startActivity(intent);
                    finish();
                }
            }
        });

        refresh.setOnRefreshListener(refreshLayout -> {
            page = 1;
            mPresent.walletList(context, String.valueOf(page), String.valueOf(pageSize));

        });
        refresh.setOnLoadMoreListener(refreshLayout -> {
            page++;
            mPresent.walletList(context, String.valueOf(page), String.valueOf(pageSize));
        });

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        refresh.autoRefresh();//自动刷新 一进入当前界面就执行刷新
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_address;
    }

    @OnClick({R.id.iv_address, R.id.iv_backretun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_backretun:
                Intent back = new Intent(context, TiTanWithdrawMoney.class);
                back.putExtra("id", "0");
                back.putExtra("taitanSum", taitanSum);
                startActivity(back);
                finish();
                break;
            case R.id.iv_address:
                Intent added = new Intent(context, ActivityAddedAddress.class);
                startActivity(added);
                break;
        }
    }

    List<AddressListResponse.Data> data;

    @Override
    public void getSelect(Response<AddressListResponse> response) {
        refresh.finishRefresh();
        refresh.finishLoadMore();

        //判断返回的参数是否正确
        if (response.body().getCode() == Constant.SUCCESS_CODE) {

            if (page == 1) {
                mList.clear();
            }
            data = response.body().data;
            if (!ObjectUtils.isEmpty(data)) {
                LvNormalShow.setVisibility(View.GONE);
                mList.clear();
                mList.addAll(data);
                selecAdapter.notifyDataSetChanged();
                RecyclerViewAnimation.runLayoutAnimation(RvSelectList);//动画显示
                if (data != null && data.size() >= pageSize) {
                    refresh.setEnableLoadMore(true);
                } else {
                    refresh.setEnableLoadMore(false);
                }
                RvSelectList.setVisibility(View.VISIBLE);
            } else if (page != 1) {
                refresh.setEnableLoadMore(false);
            } else {
                LvNormalShow.setVisibility(View.VISIBLE);
                RvSelectList.setVisibility(View.GONE);
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
