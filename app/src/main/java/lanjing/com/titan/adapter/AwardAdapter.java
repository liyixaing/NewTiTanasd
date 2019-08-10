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
        switch (changeType) {
            case 202:
                helper.setText(R.id.tv_award_type, R.string.direct_push_trade_gains);//矿层交易获得
                break;
            case 203:
                helper.setText(R.id.tv_award_type, R.string.rank_trading_weight);//矿池交易获得
                break;
            case 201:
                helper.setText(R.id.tv_award_type, R.string.trade_get);//交易获得
                break;
            case 103:
                helper.setText(R.id.tv_award_type, R.string.ore_lv);//空投等级收益
                break;
            case 101:
                helper.setText(R.id.tv_award_type, R.string.airdrop_release);//空投释放
                break;
            case 102:
                helper.setText(R.id.tv_award_type, R.string.ore_root);//空投加权
                break;
            case 401:
                helper.setText(R.id.tv_award_type, R.string.airdrop_matching);//空投加权
                break;
        }
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
