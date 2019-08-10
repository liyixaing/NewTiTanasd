package lanjing.com.titan.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.EntrustListResponse;
import lanjing.com.titan.util.MoneyUtil;

/**
 * Created by chenxi on 2019/5/10.
 * 币币交易  委托列表  列表数据适配器
 */

public class HistoryEntrustAdapter extends BaseQuickAdapter<EntrustListResponse.DataBean, BaseViewHolder> {

    public HistoryEntrustAdapter(int layoutResId, @Nullable List<EntrustListResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EntrustListResponse.DataBean item) {

        int type = item.getType();
        int state = item.getState();
        switch (type) {  //1，买   2，卖
            case 1:
                helper.setText(R.id.tv_type, R.string.buy);//买卖类型
                helper.setTextColor(R.id.tv_type, Color.GREEN);
                if (item.getSourceCoin() == 1) {//单位为TITAN
                    helper.setText(R.id.tv_entrust_price, MoneyUtil.priceFormatDouble(item.getSourceAmount()) + "\rTITAN");
                } else if (item.getSourceCoin() == 5) {//单位为USD
                    helper.setText(R.id.tv_entrust_price, MoneyUtil.priceFormatDouble(item.getSourceAmount()) + "\rBAR");
                }
                switch (state) {
                    case 2:
                        helper.setText(R.id.tv_type_recall, R.string.finish);
                        helper.setBackgroundRes(R.id.tv_type_recall, R.drawable.shape_blue_bg);//右侧已完成背景 绿色
                        break;
                    case 4:
                        helper.setText(R.id.tv_type_recall, R.string.had_withdrawn);
                        helper.setBackgroundRes(R.id.tv_type_recall, R.drawable.shape_red_bg);//右侧已撤销背景 绿色
                        break;
                }

                break;
            case 2:
                helper.setText(R.id.tv_type, R.string.sell);
                helper.setTextColor(R.id.tv_type, Color.RED);
                TextView tv_num_type = helper.getView(R.id.tv_num_type);
                tv_num_type.setVisibility(View.GONE);
                TextView tv_entrust_num = helper.getView(R.id.tv_entrust_num);
                tv_entrust_num.setVisibility(View.GONE);
                helper.setText(R.id.tv_entrust_price, MoneyUtil.priceFormatDouble(item.getSourceAmount()) + "\rUSD");
                switch (state) {
                    case 2:
                        helper.setText(R.id.tv_type_recall, R.string.finish);
                        helper.setBackgroundRes(R.id.tv_type_recall, R.drawable.shape_blue_bg);//右侧已完成背景 绿色
                        break;
                    case 4:
                        helper.setText(R.id.tv_type_recall, R.string.had_withdrawn);
                        helper.setBackgroundRes(R.id.tv_type_recall, R.drawable.shape_red_bg);//右侧已撤销背景 绿色
                        break;
                }

                break;
        }
        if (item.getSourceCoin() == 1) {
            helper.setText(R.id.tv_num_type, R.string.buy_usd_num_a);//获得TITAN数量
//            helper.setText(R.id.tv_num_type, "TITAN单价");
        } else if (item.getSourceCoin() == 5) {
            helper.setText(R.id.tv_num_type, R.string.sell_titan_num_a);//获得USD数量
//            helper.setText(R.id.tv_num_type, "BAR单价");
        }

        helper.setText(R.id.tv_entrust_num, MoneyUtil.priceFormatDoubleFour(item.getPrice()))
                .setText(R.id.tv_time, item.getCreatetime());


    }
}
