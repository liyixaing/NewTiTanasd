package lanjing.com.titan.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;

import butterknife.BindView;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.BillDetailContact;
import lanjing.com.titan.response.BillDetailResponse;
import lanjing.com.titan.response.SellOrderDetailResponse;
import lanjing.com.titan.util.DateUtils;
import lanjing.com.titan.util.MoneyUtil;
import retrofit2.Response;

public class ExchangeDetailsActivity extends MvpActivity<BillDetailContact.BillDetailPresent> implements BillDetailContact.IBillDetailView {

    @BindView(R.id.tv_titan_num)
    TextView tv_titan_num;//数量
    @BindView(R.id.tv_titan_type)
    TextView tv_titan_type;//类型
    @BindView(R.id.tv_ource_address)
    TextView tv_ource_address;//汇率
    @BindView(R.id.tv_titan_address)
    TextView tv_titan_address;//获得数量
    @BindView(R.id.tv_titan_time)
    TextView tv_titan_time;//时间
    @BindView(R.id.tv_getandset)
    TextView tv_getandset;//得到或者花费

    String type;

    @Override
    protected BillDetailContact.BillDetailPresent createPresent() {
        return new BillDetailContact.BillDetailPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        String id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("name");
        mPresent.billDetail(context, id);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_exchange_details;
    }

    @Override
    public void getBillDeatilResult(Response<BillDetailResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            //21 titan兑换  22 bar兑换
            if (response.body().getData().getChangeType().equals("22")) {//titan兑换
                tv_getandset.setText(getResources().getString(R.string.qty_acquire));//设置类型  花费
                tv_titan_num.setText(MoneyUtil.formatFour(response.body().getData().
                        getSourceAmount()) + "\r" + response.body().getData().getSourceCoinName());
                tv_ource_address.setText("1 TITAN≈" + MoneyUtil.formatFour(response.body().getData().getConvertRate()) + response.body().getData().getTargetCoinName());//汇率
                tv_titan_address.setText(response.body().getData().getTargetAmount() + response.body().getData().getTargetCoinName());
                tv_titan_time.setText(DateUtils.timedate(response.body().getData().getCreateTime()));
            } else if (response.body().getData().getChangeType().equals("21")) {//bar兑换
                tv_getandset.setText(getResources().getString(R.string.qty_cost)); //设置类型 获得
                tv_titan_address.setText(MoneyUtil.formatFour(response.body().getData().
                        getSourceAmount()) + "\r" + response.body().getData().getSourceCoinName());
                tv_ource_address.setText("1 TITAN≈" + MoneyUtil.formatFour(response.body().getData().getConvertRate()) + response.body().getData().getTargetCoinName());//汇率
                tv_titan_num.setText(response.body().getData().getTargetAmount() + response.body().getData().getTargetCoinName());
                tv_titan_time.setText(DateUtils.timedate(response.body().getData().getCreateTime()));
            }
        }

    }

    @Override
    public void getSellOrderDetail(Response<SellOrderDetailResponse> response) {

    }

    @Override
    public void getDataFailed() {

    }
}
