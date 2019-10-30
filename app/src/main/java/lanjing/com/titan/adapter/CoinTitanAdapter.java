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
 * TITAN 列表数据适配器
 */

public class CoinTitanAdapter extends BaseQuickAdapter<HistoryListResponse.mData, BaseViewHolder> {
    public CoinTitanAdapter(int recy_item_titan_list, List<HistoryListResponse.mData> historylist) {
        super(recy_item_titan_list, historylist);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryListResponse.mData item) {
        String time = DateUtils.timedate(item.getChangeTime());
        helper.setText(R.id.titan_type, item.getChangeDesc());
        String titannum = MoneyUtil.formatFour(String.valueOf(item.getChangeAmount()));//保留四位小数
        helper.setText(R.id.tv_titan_num, titannum)
                .setText(R.id.tv_titan_state, R.string.finish)
                .setText(R.id.tv_titan_time, time);
    }


}
