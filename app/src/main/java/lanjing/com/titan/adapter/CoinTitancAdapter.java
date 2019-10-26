package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
        LinearLayout titanc_item = helper.getView(R.id.ll_all);
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
        helper.setText(R.id.tv_titanc_type, item.getChangeDesc());
        helper.setText(R.id.tv_titanc_change_num, sum)
                .setText(R.id.tv_type_two, R.string.qty)
                .setText(R.id.tv_titanc_time, time);

    }
}
