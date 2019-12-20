package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.CoinLogListResponse;
import lanjing.com.titan.util.DateUtils;
import lanjing.com.titan.util.MoneyUtil;

/**
 * Created by chenxi on 2019/5/24.
 * 充值提现  列表适配器
 */

public class WithDrawalAdapter extends BaseQuickAdapter<CoinLogListResponse.Data, BaseViewHolder> {

    public WithDrawalAdapter(int layoutResId, @Nullable List<CoinLogListResponse.Data> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoinLogListResponse.Data item) {
        int typeMoney = Integer.parseInt(item.getChangeCoin());
        int type = Integer.parseInt(item.getChangeType());
        Log.e("币种", typeMoney + "");
        switch (typeMoney) {
            case 1:
                helper.setText(R.id.tv_currency, "TITAN");
                helper.setText(R.id.tv_typemonet, "TITAN");
                break;
            case 2:
                helper.setText(R.id.tv_currency, "TITANC");
                helper.setText(R.id.tv_typemonet, "TITANC");
                break;
            case 3:
                helper.setText(R.id.tv_currency, "USD");
                helper.setText(R.id.tv_typemonet, "USD");
                break;
            case 5:
                helper.setText(R.id.tv_currency, "BAR");
                helper.setText(R.id.tv_typemonet, "BAR");
                break;
            case 10:
                helper.setText(R.id.tv_currency, "TTA");
                helper.setText(R.id.tv_typemonet, "TTA");
                break;
        }
        helper.setText(R.id.tv_deposit_type, item.getChangeDesc());
        helper.setText(R.id.tv_number, MoneyUtil.formatFour(item.getChangeAmount()));
        String time = DateUtils.timedate(item.getChangeTime());//时间戳转换成时间格式
        helper.setText(R.id.tv_create_time, time);
        helper.setText(R.id.tv_number, MoneyUtil.formatFour(item.getChangeAmount()));

    }
}
