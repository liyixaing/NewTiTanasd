package lanjing.com.titan.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.HistoryListResponse;
import lanjing.com.titan.util.DateUtils;
import lanjing.com.titan.util.MoneyUtil;

/**
 * extends BaseQuickAdapter<WalletDetailResponse.HistoryBean, BaseViewHolder>
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
//        String type = "已完成";
        String titannum = MoneyUtil.formatFour(String.valueOf(item.getChangeAmount()));//保留四位小数
        int changeType = Integer.parseInt(item.getChangeType());
        switch (changeType) {
            case 202:
                helper.setText(R.id.titan_type, R.string.direct_push_trade_gains);//矿层交易获得
                break;
            case 203:
                helper.setText(R.id.titan_type, R.string.rank_trading_weight);//矿池交易获得
                break;
            case 13:
                helper.setText(R.id.titan_type, R.string.buy);//买入
                break;
            case 201:
                helper.setText(R.id.titan_type, R.string.trade_get);//交易获得
                break;
            case 11:
                helper.setText(R.id.titan_type, R.string.sell);//卖出
                break;
            case 12:
                helper.setText(R.id.titan_type, R.string.selling_charges);//卖出手续费
                break;
            case 1:
                helper.setText(R.id.titan_type, R.string.top_up_c);//充币
                break;
            case 2:
                helper.setText(R.id.titan_type, R.string.withdraw_c);//提币
                break;
            case 3:
                helper.setText(R.id.titan_type, R.string.pls_four1);//提币手续费
                break;
            case 22:
                helper.setText(R.id.titan_type, R.string.exchange_consumption);//兑换消耗
                break;
            case 21:
                helper.setText(R.id.titan_type, R.string.exchange_get);//兑换获得
                break;


        }
        helper.setText(R.id.tv_titan_num, titannum)
//                .setText(R.id.titan_type, item.getChangeDesc())
                .setText(R.id.tv_titan_state, R.string.finish)
                .setText(R.id.tv_titan_time, time);
    }


}
