package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.CoinLogListResponse;
import lanjing.com.titan.response.WithdrawalResponse;
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
        switch (typeMoney) {
            case 1:
                helper.setText(R.id.tv_currency, "TITAN");
                helper.setText(R.id.tv_typemonet, "TITAN");
                break;
            case 5:
                helper.setText(R.id.tv_currency, "BAR");
                helper.setText(R.id.tv_typemonet, "BAR");
                break;
        }
        switch (type){
            case 1:
                helper.setText(R.id.tv_deposit_type, R.string.top_up_c);
                break;
            case 2:
                helper.setText(R.id.tv_deposit_type, R.string.withdraw_c);
                break;

        }


        helper.setText(R.id.tv_number, MoneyUtil.formatFour(item.getChangeAmount()));
        String time = DateUtils.timedate(item.getChangeTime());//时间戳转换成时间格式
        helper.setText(R.id.tv_create_time, time);
        helper.setText(R.id.tv_number, MoneyUtil.formatFour(item.getChangeAmount()));

    }
}
