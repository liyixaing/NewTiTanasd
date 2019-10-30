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

public class SellSixOneAdapter extends BaseQuickAdapter<SixTradeResponse.Data.SelldataBean, BaseViewHolder> {

    public SellSixOneAdapter(int layoutResId, @Nullable List<SixTradeResponse.Data.SelldataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SixTradeResponse.Data.SelldataBean item) {

        helper.setText(R.id.sell_price, MoneyUtil.priceFormatDoubleOne(item.getPrice()));

        int num0 = Integer.parseInt(priceFormatDoubleZero(item.getRest()));

        if(num0>1000){
            String num =  priceFormatDoubleZero(item.getRest());
            String num2 = (new BigDecimal(num).multiply(new BigDecimal(0.001))+"");
            helper.setText(R.id.sell_num,MoneyUtil.priceFormatString(num2)+"k");
        }else {
            helper.setText(R.id.sell_num,priceFormatDoubleZero(item.getRest()));
        }

    }
}
