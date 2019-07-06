package lanjing.com.titan.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

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
        String nnum = item.getCoinnum1();
        String nnum1 = nnum.substring(0, nnum.indexOf("."));
        String userIdJiequ = nnum.substring(nnum.indexOf("."));
        int asd = Integer.parseInt(nnum1);
        int sdf= 0;
        char fir = userIdJiequ.charAt(1);
        int asfdaf = fir;
        Log.e("nnum1", nnum1);
        Log.e("fir", String.valueOf(fir));
        Log.e("userIdJiequ", userIdJiequ);
        Log.e("asfdaf", String.valueOf(asfdaf));
        if (fir >= 4) {
            sdf = asd + 1;
        }
        switch (type) {
            case 1://买入
                helper.setText(R.id.deal_type, R.string.buy);
                helper.setText(R.id.tv_cost_num, MoneyUtil.formatFour(String.valueOf(sdf-1)) + "TITAN");//卖出TITAN
                helper.setText(R.id.tv_get_num, MoneyUtil.formatFour(item.getCoinmun2()) + "USD");//得到USD
                helper.setText(R.id.tv_fee, MoneyUtil.formatFour(item.getFee()) + "TITAN");//扣除TITAN的手续费

                break;
            case 2://卖出
                helper.setText(R.id.deal_type, R.string.sell);
                helper.setText(R.id.tv_cost_num, MoneyUtil.formatFour(item.getCoinnum1()) + "USD");//卖出USD
                helper.setText(R.id.tv_get_num, MoneyUtil.formatFour(item.getCoinmun2()) + "TITAN");//得到TITAN
                helper.setText(R.id.tv_fee, MoneyUtil.formatFour(item.getFee()) + "USD");//扣除USD的手续费
                break;
        }


        helper.setText(R.id.tv_time, item.getUpdatetime());//时间


    }
}
