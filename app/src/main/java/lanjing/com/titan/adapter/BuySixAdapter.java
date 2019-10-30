package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.math.BigDecimal;
import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.SixTradeResponse;
import lanjing.com.titan.util.MoneyUtil;

import static lanjing.com.titan.util.MoneyUtil.priceFormatDoubleZero;

/**
 * Created by chenxi on 2019/5/10.
 * 币币交易  上面只显示六条数据
 */

public class BuySixAdapter extends BaseQuickAdapter<SixTradeResponse.Data.BuydataBean, BaseViewHolder> {

    public BuySixAdapter(int layoutResId, @Nullable List<SixTradeResponse.Data.BuydataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SixTradeResponse.Data.BuydataBean item) {

        helper.setText(R.id.tv_buy_price, MoneyUtil.priceFormatDoubleFour(item.getPrice()));

        int num0 = Integer.parseInt(priceFormatDoubleZero(item.getRest()));

        if(num0>1000){
            String num =  priceFormatDoubleZero(item.getRest());
            String num2 = (new BigDecimal(num).multiply(new BigDecimal(0.001))+"");
            helper.setText(R.id.tv_buy_num,MoneyUtil.priceFormatString(num2)+"k");
        }else {
            helper.setText(R.id.tv_buy_num,priceFormatDoubleZero(item.getRest()));
        }

    }
}
