package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.BillDetailContact;
import lanjing.com.titan.response.BillDetailResponse;
import lanjing.com.titan.response.SellOrderDetailResponse;
import lanjing.com.titan.util.DateUtils;
import lanjing.com.titan.util.MoneyUtil;
import retrofit2.Response;

public class DetailsOfBillsActivity extends MvpActivity<BillDetailContact.BillDetailPresent> implements BillDetailContact.IBillDetailView {


    @BindView(R.id.tv_titan_type)
    TextView tv_titan_type;//花费数量
    @BindView(R.id.tv_titan_state)
    TextView tv_titan_state;//手续费
    @BindView(R.id.tv_ource_address)
    TextView tv_ource_address;//成交时bar单间
    @BindView(R.id.tv_titan_address)
    TextView tv_titan_address;//获得BAR数量
    @BindView(R.id.tv_deal_one)
    TextView tv_deal_one;//成交时TITAN单价
    @BindView(R.id.tv_gettitansun)
    TextView tv_gettitansun;//获得TITAN数量
    @BindView(R.id.tv_titan_time)
    TextView tv_titan_time;//时间

    String id;

    @Override
    protected BillDetailContact.BillDetailPresent createPresent() {
        return new BillDetailContact.BillDetailPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        id = getIntent().getStringExtra("id");
        mPresent.SellOrderDetail(context, id);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_details_of_bills;
    }

    @Override
    public void getBillDeatilResult(Response<BillDetailResponse> response) {

    }

    //币币交易详情数据  coin1Price
    @Override
    public void getSellOrderDetail(Response<SellOrderDetailResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            tv_titan_type.setText(MoneyUtil.formatFour(String.valueOf(response.body().getData().getTradeAmount() + response.body().getData().getTradeFee())) + "\rUSD");//花费数量
            tv_titan_state.setText(MoneyUtil.formatFour(String.valueOf(response.body().getData().getTradeFee())) + "\rUSD");//手续费
//            tv_ource_address.setText(MoneyUtil.formatFour(String.valueOf(response.body().getData().getBarPrice())) + "\rUSD");//成交时BAR单价
            tv_titan_address.setText(MoneyUtil.formatFour(String.valueOf(response.body().getData().getGainCoin2Amount())) + "\rUSD");//获得BAR数量
            tv_deal_one.setText(MoneyUtil.formatFour(String.valueOf(response.body().getData().getCoin1Price())) + "\rUSD");//成交时TITAN单价
            tv_gettitansun.setText(MoneyUtil.formatFour(String.valueOf(response.body().getData().getGainCoin1Amount())) + "\rTITAN");//获得TITAN数量
            tv_titan_time.setText(DateUtils.timedate(response.body().getData().getCreateTime()));//时间
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
        }

    }

    @Override
    public void getDataFailed() {

    }
}
