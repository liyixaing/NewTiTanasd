package lanjing.com.titan.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.EntrustListResponse;
import lanjing.com.titan.response.SixTradeResponse;
import lanjing.com.titan.util.MoneyUtil;

/**
 * Created by chenxi on 2019/5/10.
 * 币币交易  委托列表  列表数据适配器
 */

public class EntrustAdapter extends BaseQuickAdapter<EntrustListResponse.Data.OrderList, BaseViewHolder> {

    public EntrustAdapter(int layoutResId, @Nullable List<EntrustListResponse.Data.OrderList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EntrustListResponse.Data.OrderList item) {

        int type = item.getType();
        switch (type) {  //1，买   2，卖
            case 1:
                helper.setText(R.id.tv_type, R.string.buy);//买卖类型
                helper.setTextColor(R.id.tv_type, Color.GREEN);

                helper.setBackgroundRes(R.id.tv_type_recall, R.drawable.shape_green_bg);//右侧撤销背景 绿色
                if (item.getSourceCoin() == 1) {
                    helper.setText(R.id.tv_entrust_price, MoneyUtil.priceFormatDoubleZero(item.getRest()) + ".00 TITAN");
                    helper.setText(R.id.tv_num_type, R.string.buy_usd_num_a);//R.string.buy_usd_num2
                } else if (item.getSourceCoin() == 5) {
                    helper.setText(R.id.tv_entrust_price, MoneyUtil.priceFormatDoubleZero(item.getRest()) + ".00 BAR");
                    helper.setText(R.id.tv_num_type, R.string.sell_titan_num_a);
                }
                break;
            case 2:
                //隐藏状态
                TextView tv_num_type = helper.getView(R.id.tv_num_type);
                tv_num_type.setVisibility(View.GONE);
                TextView tv_entrust_num = helper.getView(R.id.tv_entrust_num);
                tv_entrust_num.setVisibility(View.GONE);
                helper.setText(R.id.tv_entrust_price, MoneyUtil.priceFormatDoubleZero(item.getRest()) + ".00 USD");
                helper.setText(R.id.tv_type, R.string.sell);
                helper.setTextColor(R.id.tv_type, Color.RED);
                helper.setBackgroundRes(R.id.tv_type_recall, R.drawable.shape_red_bg);//右侧撤销背景 红色
                helper.setText(R.id.tv_num_type, R.string.prices);
                break;
        }

        helper.setText(R.id.tv_entrust_num, MoneyUtil.priceFormatDoubleFour(item.getPrice()) + "\rUSD")
                .setText(R.id.tv_time, item.getCreatetime());
        helper.addOnClickListener(R.id.tv_type_recall);//添加点击事件


    }
}
