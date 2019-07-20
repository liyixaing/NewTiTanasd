package lanjing.com.titan.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ToastUtils;
import com.lxh.baselibray.view.TitleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.BillDetailContact;
import lanjing.com.titan.response.BillDetailResponse;
import lanjing.com.titan.util.MoneyUtil;
import retrofit2.Response;

/**
 * USD  资产详情
 */
public class AssetUsdDetailActivity extends MvpActivity<BillDetailContact.BillDetailPresent> implements BillDetailContact.IBillDetailView {

    @BindView(R.id.tv_usd_num)
    TextView tvUsdNum;
    @BindView(R.id.tv_usd_type)
    TextView tvUsdType;
    @BindView(R.id.tv_usd_state)
    TextView tvUsdState;
    @BindView(R.id.tv_usd_time)
    TextView tvUsdTime;
    @BindView(R.id.tv_title_type)
    TitleView TvTitleType;

    @Override
    public void initData(Bundle savedInstanceState) {
        String id = getIntent().getStringExtra("id");
        String type = getIntent().getStringExtra("type");
        if (type.equals("30")) {
            tvUsdType.setText(R.string.service_fee);
            TvTitleType.setTitleText(getResources().getString(R.string.service_fee));
        } else if (type.equals("34")) {
            tvUsdType.setText(R.string.buy);
            TvTitleType.setTitleText("USD "+getResources().getString(R.string.buy));
        } else if (type.equals("35")) {
            tvUsdType.setText(R.string.sell);
            TvTitleType.setTitleText("USD "+getResources().getString(R.string.sell));
        } else {
            tvUsdType.setText("其他");
        }
        mPresent.billDetail(context, id);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_asset_usd_detail;
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
                tvUsdNum.setText("+" + upAndDown + "USD");
            } else {
                tvUsdNum.setText(upAndDown + "USD");
            }
            boolean over2 = upAndDown.contains(down);
            if (over2 == true) {
                tvUsdNum.setText(upAndDown + "USD");
            } else {
                tvUsdNum.setText("+" + upAndDown + "USD");
            }


            int type = Integer.parseInt(response.body().getHistory().getType());
            switch (type) {
                case 0:
                    tvUsdType.setText(R.string.service_fee);
                    break;
                case 1:
                    tvUsdType.setText(R.string.unfrozen);
                    break;
                case 2:
                    tvUsdType.setText(R.string.top_up_c);
                    break;
                case 3:
                    tvUsdType.setText(R.string.withdraw_c);
                    break;
                case 4:
                    tvUsdType.setText(R.string.buy);
                    break;
                case 5:
                    tvUsdType.setText(R.string.sell);
                    break;
                case 6:
                    tvUsdType.setText(R.string.system);
                    break;
                case 7:
                    tvUsdType.setText(R.string.others);
                    break;
            }
            //状态
            int state = Integer.parseInt(response.body().getHistory().getState());
            switch (state) {
                case 0:
                    tvUsdState.setText(R.string.underway);
                    break;
                case 1:
                    tvUsdState.setText(R.string.finish);
                    break;
            }

            tvUsdTime.setText(response.body().getHistory().getTime());
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
