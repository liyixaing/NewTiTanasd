package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.AwardResponse;
import lanjing.com.titan.response.CoinDealResponse;
import lanjing.com.titan.response.CoinLogListResponse;
import lanjing.com.titan.util.DateUtils;
import lanjing.com.titan.util.MoneyUtil;

/**
 * Created by chenxi on 2019/5/24.
 * 币币交易  列表适配器
 */

public class AwardAdapter extends BaseQuickAdapter<CoinLogListResponse.Data, BaseViewHolder> {

    public AwardAdapter(int layoutResId, @Nullable List<CoinLogListResponse.Data> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoinLogListResponse.Data item) {
        int changeType = Integer.parseInt(item.getChangeType());
        helper.setText(R.id.tv_award_type, item.getChangeDesc());

        int changeCoin = Integer.parseInt(item.getChangeCoin());
        switch (changeCoin) {
            case 1:
                helper.setText(R.id.tv_bi_type, "TITAN");
                break;
            case 2:
                helper.setText(R.id.tv_bi_type, "TITANC");
                break;
            case 3:
                helper.setText(R.id.tv_bi_type, "USD");
                break;
            case 4:
                helper.setText(R.id.tv_bi_type, "USD");
                break;
            case 5:
                helper.setText(R.id.tv_bi_type, "BAR");
                break;
        }
        String time = DateUtils.timedate(item.getChangeTime());
        helper
                .setText(R.id.tv_create_time, time)
                .setText(R.id.tv_number, MoneyUtil.formatFour(item.getChangeAmount()));
    }
}
