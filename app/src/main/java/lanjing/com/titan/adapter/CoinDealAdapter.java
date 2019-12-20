package lanjing.com.titan.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.CoinDealResponse;
import lanjing.com.titan.response.WithdrawalResponse;
import lanjing.com.titan.util.MoneyUtil;

/**
 * Created by chenxi on 2019/5/24.
 * 币币交易  列表适配器
 */

public class CoinDealAdapter extends BaseQuickAdapter<CoinDealResponse.DataBean, BaseViewHolder> {

    public CoinDealAdapter(int layoutResId, @Nullable List<CoinDealResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoinDealResponse.DataBean item) {
        int type = Integer.parseInt(item.getType());
        LinearLayout ll_num = helper.getView(R.id.ll_num);
        String coin = item.getCoin1();
        String types = "TITAN";
        if (coin.equals("5")) {
            types = "BAR";
        }else if (coin.equals("10")){
            types = "TTA";
        } else {
            types = "TITAN";
        }

        switch (type) {
            case 1://买入
                helper.setText(R.id.deal_type, R.string.buy);
                helper.setText(R.id.tv_cost_num, MoneyUtil.formatFour(item.getCoinnum1()) + types);//卖出TITAN
                helper.setText(R.id.tv_get_num, MoneyUtil.formatFour(item.getCoinmun2()) + "USD");//得到USD
                helper.setText(R.id.tv_fee, MoneyUtil.formatFour(item.getFee()) + types);//扣除TITAN的手续费
                break;
            case 2://卖出
                helper.setText(R.id.deal_type, R.string.sell);
                helper.setText(R.id.tv_cost_num, MoneyUtil.formatFour(item.getCoinnum1()) + "USD");//卖出USD
                helper.setText(R.id.tv_get_num, item.getCoinmun2() + "TITAN");//得到TITAN
                helper.setText(R.id.tv_fee, MoneyUtil.formatFour(item.getFee()) + "USD");//扣除USD的手续费
                ll_num.setVisibility(View.GONE);
                break;
        }


        helper.setText(R.id.tv_time, item.getUpdatetime());//时间


    }
}
