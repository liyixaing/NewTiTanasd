package lanjing.com.titan.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.FriendListResponse;
import lanjing.com.titan.response.MarketListResponse;
import lanjing.com.titan.util.MoneyUtil;

/**
 * Created by chenxi on 2019/5/10.
 */

public class MarketListAdapter extends BaseQuickAdapter<MarketListResponse.DataBean, BaseViewHolder> {

    public MarketListAdapter(int layoutResId, @Nullable List<MarketListResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketListResponse.DataBean item) {
        String up = "+";
        String down = "-";
        String upAndDown = MoneyUtil.priceFormatString(item.getChange()) +"%";
        boolean over = upAndDown.contains(up);
        if(over == true){
            helper.setText(R.id.tv_change,"+"+upAndDown);
            helper.setBackgroundRes(R.id.tv_change,R.drawable.shape_green_bg);
        }else {
            helper.setText(R.id.tv_change,upAndDown);
            helper.setBackgroundRes(R.id.tv_change,R.drawable.shape_red_bg);
        }
        boolean over2 = upAndDown.contains(down);
        if(over2 == true){
            helper.setText(R.id.tv_change,upAndDown);
            helper.setBackgroundRes(R.id.tv_change,R.drawable.shape_red_bg);
        }else {
            helper.setText(R.id.tv_change,"+"+upAndDown);
            helper.setBackgroundRes(R.id.tv_change,R.drawable.shape_green_bg);
        }



        String bi = item.getSymbol();
        String one;
        String two;
        one = bi.substring(0,bi.indexOf("/"));//截取字符串
        two = bi.substring(bi.indexOf("/")+1);

        helper.setText(R.id.tv_symbol, one)//币种
                .setText(R.id.tv_symbol_two,two)
                .setText(R.id.tv_amount, MoneyUtil.formatFour(item.getAmount()))//数量
                .setText(R.id.tv_price, MoneyUtil.formatFour(item.getPrice()))//价格
                .setText(R.id.tv_CNY, "$" +MoneyUtil.formatFour(item.getCNY()));//人民币价值



    }
}
