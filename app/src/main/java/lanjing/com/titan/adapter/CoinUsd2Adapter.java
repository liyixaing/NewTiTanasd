package lanjing.com.titan.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.HistoryListResponse;
import lanjing.com.titan.util.DateUtils;
import lanjing.com.titan.util.MoneyUtil;

/**
 * Created by chenxi on 2019/5/10.
 * USD2(USD待空投)  列表数据适配器
 */

public class CoinUsd2Adapter extends BaseQuickAdapter<HistoryListResponse.mData, BaseViewHolder> {
    public CoinUsd2Adapter(int recy_item_usd_wait_raward_list, List<HistoryListResponse.mData> mList) {
        super(recy_item_usd_wait_raward_list, mList);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryListResponse.mData item) {
        String time = DateUtils.timedate(item.getChangeTime());
        helper.setText(R.id.tv_usd2_type, item.getChangeDesc());
        helper.setText(R.id.tv_type_two, R.string.qty)
                .setText(R.id.tv_usd2_chang_num, MoneyUtil.formatFour(item.getChangeAmount() + ""))
                .setText(R.id.tv_usd2_time, time);
    }

}
