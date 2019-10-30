package lanjing.com.titan.activity;


import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ToastUtils;
import com.lxh.baselibray.view.TitleView;

import butterknife.BindView;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.BillDetailContact;
import lanjing.com.titan.response.BillDetailResponse;
import lanjing.com.titan.response.SellOrderDetailResponse;
import lanjing.com.titan.util.MoneyUtil;
import retrofit2.Response;

/**
 * TITANC  资产详情
 */
public class AssetTitanDetailActivity extends MvpActivity<BillDetailContact.BillDetailPresent> implements BillDetailContact.IBillDetailView {

    @BindView(R.id.tv_titan_num)
    TextView tvTitanNum;
    @BindView(R.id.tv_titan_type)
    TextView tvTitanType;
    @BindView(R.id.tv_titan_state)
    TextView tvTitanState;
    @BindView(R.id.tv_titan_time)
    TextView tvTitanTime;
    @BindView(R.id.tv_titan_blockchain_id)
    TextView tvTitanBlockchainId;
    @BindView(R.id.tv_title_name_id)
    TitleView TvTitleNameId;
    String type;
    @BindView(R.id.v_division_one)
    View v_division_one;
    @BindView(R.id.v_division_tow)
    View v_division_tow;
    @BindView(R.id.ll_address)
    LinearLayout ll_address;
    @BindView(R.id.Ticket_label)
    LinearLayout Ticket_label;
    @BindView(R.id.ll_transactionID)
    LinearLayout ll_transactionID;
    @BindView(R.id.v_xian_three)
    View v_xian_three;
    @BindView(R.id.v_xian_four)
    View VXianFour;
    @BindView(R.id.ll_ourceaddress)
    LinearLayout llOurceaddress;
    @BindView(R.id.tv_titan_label)
    TextView TvTitanLabel;
    @BindView(R.id.tv_titan_address)
    TextView TvTitanAddress;
    @BindView(R.id.tv_ource_address)
    TextView TvOurceAddress;

    @Override
    public void initData(Bundle savedInstanceState) {
        String id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("name");
        tvTitanType.setText(type);
        mPresent.billDetail(context, id);

    }


    //实现长按弹出系统复制方法
    public void TextViewCopy() {
        tvTitanBlockchainId.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                cmb.setText(tvTitanBlockchainId.getText());
                ToastUtils.showShortToast(context, getResources().getString(R.string.over_copy));
                return false;
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_asset_titan_detail;
    }

    @Override
    protected BillDetailContact.BillDetailPresent createPresent() {
        return new BillDetailContact.BillDetailPresent();
    }

    @Override
    public void getBillDeatilResult(Response<BillDetailResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            tvTitanType.setText(response.body().getData().getChangeDesc());
            String upAndDown = MoneyUtil.formatFour(response.body().getData().getNum());
            if (response.body().getData().getType().equals("1")) {
                TvTitleNameId.setTitleText(response.body().getData().getCoinDesc() + getResources().getString(R.string.top_up_c));
                ll_transactionID.setVisibility(View.VISIBLE);
                v_xian_three.setVisibility(View.VISIBLE);
                tvTitanNum.setText("+" + upAndDown + response.body().getData().getCoinDesc());
                VXianFour.setVisibility(View.VISIBLE);
                llOurceaddress.setVisibility(View.VISIBLE);
            } else if (response.body().getData().getType().equals("2")) {
                TvTitleNameId.setTitleText(response.body().getData().getCoinDesc() + getResources().getString(R.string.withdraw_c));
                ll_transactionID.setVisibility(View.VISIBLE);
                v_xian_three.setVisibility(View.VISIBLE);
                v_division_one.setVisibility(View.VISIBLE);
                v_division_tow.setVisibility(View.VISIBLE);
                tvTitanNum.setText("-" + upAndDown + response.body().getData().getCoinDesc());
                ll_address.setVisibility(View.VISIBLE);
                Ticket_label.setVisibility(View.VISIBLE);
            }

            if (response.body().getData().getCoin().equals("1")){

            }
            //订单详情状态
            int state = Integer.parseInt(response.body().getData().getState());
            switch (state) {
                case 0:
                    tvTitanState.setText(R.string.underway);
                    break;
                case 1:
                    tvTitanState.setText(R.string.finish);
                    break;
            }

            tvTitanTime.setText(response.body().getData().getTime());
            tvTitanBlockchainId.setText(response.body().getData().getKeys());
            TvTitanLabel.setText(response.body().getData().getToTag());
            TvTitanAddress.setText(response.body().getData().getToAddress());
            TvOurceAddress.setText(response.body().getData().getFromAddress());
        } else if (response.body().getCode() == -10) {
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }
    }

    @Override
    public void getSellOrderDetail(Response<SellOrderDetailResponse> response) {

    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }

}
