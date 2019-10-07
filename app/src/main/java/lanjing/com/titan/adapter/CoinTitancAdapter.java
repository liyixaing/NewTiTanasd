package lanjing.com.titan.adapter;

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
 * extends BaseQuickAdapter<WalletDetailResponse.History2Bean, BaseViewHolder>
 * Created by chenxi on 2019/5/10.
 * TITANC  列表数据适配器
 */

public class CoinTitancAdapter extends BaseQuickAdapter<HistoryListResponse.mData, BaseViewHolder> {
    public CoinTitancAdapter(int recy_item_titanc_wait_get_list, List<HistoryListResponse.mData> mList) {
        super(recy_item_titanc_wait_get_list, mList);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryListResponse.mData item) {
        String time = DateUtils.timedate(item.getChangeTime());
        int changeType = Integer.parseInt(item.getChangeType());
        String type;
        String sum = "";
        if (item.getChangeAmount() > 0) {
            type = "Qty";
            sum = "+" + MoneyUtil.formatFour(String.valueOf(item.getChangeAmount()));
        } else {
            type = "Qty";
            sum = MoneyUtil.formatFour(String.valueOf(item.getChangeAmount()));
        }
        switch (changeType) {
            case 1003:
                helper.setText(R.id.tv_titanc_type, R.string.of_mineral);//矿层交易激活
                break;
            case 1004:
                helper.setText(R.id.tv_titanc_type, R.string.ore_pool);//矿池交易激活
                break;
            case 1002:
                helper.setText(R.id.tv_titanc_type, R.string.unfrozen);//交易激活
                break;
            case 102:
                helper.setText(R.id.tv_titanc_type, R.string.ore_root);//空投加权
                break;
            case 103:
                helper.setText(R.id.tv_titanc_type, R.string.ore_lv);//空投等级收益
                break;
            case 501:
                helper.setText(R.id.tv_titanc_type, R.string.airdrop_gift);//空投赠送
                break;
            case 1:
                helper.setText(R.id.tv_titanc_type, R.string.top_up_c);//充币
                break;
            case 21:
                helper.setText(R.id.tv_titanc_type, R.string.flash_exchange);
                break;

        }
        helper.setText(R.id.tv_titanc_change_num, sum)
                .setText(R.id.tv_type_two, R.string.qty)
                .setText(R.id.tv_titanc_time, time);

    }
}
