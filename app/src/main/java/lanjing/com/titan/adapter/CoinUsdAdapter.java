package lanjing.com.titan.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.HistoryListResponse;
import lanjing.com.titan.response.WalletDetailResponse;
import lanjing.com.titan.util.DateUtils;
import lanjing.com.titan.util.MoneyUtil;

/**
 * Created by chenxi on 2019/5/10.
 * USD 列表数据适配器
 */

public class CoinUsdAdapter extends BaseQuickAdapter<HistoryListResponse.mData, BaseViewHolder> {
    public CoinUsdAdapter(int recy_item_usd_list, List<HistoryListResponse.mData> mList) {
        super(recy_item_usd_list, mList);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryListResponse.mData item) {
        String upAndDown = String.valueOf(MoneyUtil.formatFour(String.valueOf(item.getChangeAmount())));
        String time = DateUtils.timedate(item.getChangeTime());
        helper.setText(R.id.usd_type, item.getChangeDesc());//卖出
        helper.setText(R.id.tv_usd_num, upAndDown)
                .setText(R.id.tv_usd_state, R.string.finish)
                .setText(R.id.tv_usd_time, time);

    }
}
