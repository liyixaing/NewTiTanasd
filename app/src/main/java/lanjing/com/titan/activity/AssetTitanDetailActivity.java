package lanjing.com.titan.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxh.baselibray.view.TitleView;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.BillDetailContact;
import lanjing.com.titan.response.BillDetailResponse;
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
        mPresent.billDetail(context, id);
        initTypeData();
//        TvTitleNameId.setTitleText(type);
    }

    public void initTypeData() {
        if (type.equals("1")) {

            TvTitleNameId.setTitleText("交易释放");
            tvTitanType.setText("交易释放");
        } else if (type.equals("32")) {
            TvTitleNameId.setTitleText("TITAN 充币");
            tvTitanType.setText("充币");
            ll_transactionID.setVisibility(View.VISIBLE);
            v_xian_three.setVisibility(View.VISIBLE);
            VXianFour.setVisibility(View.VISIBLE);
            llOurceaddress.setVisibility(View.VISIBLE);
        } else if (type.equals("33")) {
            TvTitleNameId.setTitleText("TITAN 提币");
            tvTitanType.setText("提币");
            ll_transactionID.setVisibility(View.VISIBLE);
            v_xian_three.setVisibility(View.VISIBLE);
            v_division_one.setVisibility(View.VISIBLE);
            v_division_tow.setVisibility(View.VISIBLE);
            ll_address.setVisibility(View.VISIBLE);
            Ticket_label.setVisibility(View.VISIBLE);
        } else if (type.equals("5")) {
            TvTitleNameId.setTitleText("TITAN 直推交易获得");
            tvTitanType.setText("直推交易获得");
        } else if (type.equals("6")) {
            TvTitleNameId.setTitleText("TITAN 等级交易加权");
            tvTitanType.setText("等级交易加权");
        } else if (type.equals("7")) {
            TvTitleNameId.setTitleText("TITAN 同等交易获得");
            tvTitanType.setText("同等交易获得");
        } else if (type.equals("5")) {
            TvTitleNameId.setTitleText("TITAN 直推交易获得");
            tvTitanType.setText("直推交易获得");
        } else if (type.equals("34")) {
            TvTitleNameId.setTitleText("TITAN 买入");
            tvTitanType.setText("买入");
        } else if (type.equals("35")) {
            TvTitleNameId.setTitleText("TITAN 卖出");
            tvTitanType.setText("卖出");
        } else if (type.equals("30")) {
            TvTitleNameId.setTitleText("TITAN 手续费");
            tvTitanType.setText("手续费");
        }

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
            String up = "+";
            String down = "-";
            String upAndDown = MoneyUtil.formatFour(response.body().getHistory().getNum());
            boolean over = upAndDown.contains(up);
            if (over == true) {
                tvTitanNum.setText("+" + upAndDown + "TITAN");
            } else {
                tvTitanNum.setText(upAndDown + "TITAN");
            }
            boolean over2 = upAndDown.contains(down);
            if (over2 == true) {
                tvTitanNum.setText(upAndDown + "TITAN");
            } else {
                tvTitanNum.setText("+" + upAndDown + "TITAN");
            }

//            tvTitanNum.setText(response.body().getHistory().getNum()+"TITAN");
//            String types =  response.body().getHistory().getType();

            int type = Integer.parseInt(response.body().getHistory().getType());
//            switch (type) {
//                case 0:
//                    tvTitanType.setText(R.string.service_fee);
//                    break;
//                case 1:
//                    tvTitanType.setText(R.string.unfrozen);
//                    break;
//                case 2:
//                    tvTitanType.setText(R.string.top_up_c);
//                    break;
//                case 3:
//                    tvTitanType.setText(R.string.withdraw_c);//提币
//                    break;
//                case 4:
//                    tvTitanType.setText(R.string.buy);
//                    break;
//                case 5:
//                    tvTitanType.setText(R.string.sell);
//                    break;
//                case 6:
//                    tvTitanType.setText(R.string.system);
//                    break;
//                case 7:
//                    tvTitanType.setText(R.string.others);
//                    break;
//            }

//            tvTitanType.setText(response.body().getHistory().getType());
            //订单详情状态
            int state = Integer.parseInt(response.body().getHistory().getState());
            switch (state) {
                case 0:
                    tvTitanState.setText(R.string.underway);
                    break;
                case 1:
                    tvTitanState.setText(R.string.finish);
                    break;
            }


//            tvTitanState.setText(response.body().getHistory().getState());
            tvTitanTime.setText(response.body().getHistory().getTime());
            tvTitanBlockchainId.setText(response.body().getHistory().getKeys());
            TvTitanLabel.setText(response.body().getHistory().getToTag());
            TvTitanAddress.setText(response.body().getHistory().getToAddress());
            TvOurceAddress.setText(response.body().getHistory().getFromAddress());
        } else {
            ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
    }

}
