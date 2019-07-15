package lanjing.com.titan.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.Nullable;

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
                switch (state){
                    case 2:
                        helper.setText(R.id.tv_type_recall,R.string.finish);
                        helper.setBackgroundRes(R.id.tv_type_recall, R.drawable.shape_blue_bg);//右侧已完成背景 绿色
                        break;
                    case 4:
                        helper.setText(R.id.tv_type_recall,R.string.had_withdrawn);
                        helper.setBackgroundRes(R.id.tv_type_recall, R.drawable.shape_red_bg);//右侧已撤销背景 绿色
                        break;
                }

                helper.setText(R.id.tv_num_type, R.string.sell_titan_num);//获得USD数量
                break;
            case 2:
                helper.setText(R.id.tv_type, R.string.sell);
                helper.setTextColor(R.id.tv_type,Color.RED);

                switch (state){
                    case 2:
                        helper.setText(R.id.tv_type_recall,R.string.finish);
                        helper.setBackgroundRes(R.id.tv_type_recall, R.drawable.shape_blue_bg);//右侧已完成背景 绿色
                        break;
                    case 4:
                        helper.setText(R.id.tv_type_recall,R.string.had_withdrawn);
                        helper.setBackgroundRes(R.id.tv_type_recall, R.drawable.shape_red_bg);//右侧已撤销背景 绿色
                        break;
                }
                helper.setText(R.id.tv_num_type, R.string.buy_usd_num);//获得TITAN数量
                break;
        }

        helper.setText(R.id.tv_entrust_price, MoneyUtil.priceFormatDoubleFour(item.getPrice()) + "USD")
                .setText(R.id.tv_entrust_num, MoneyUtil.priceFormatDouble(item.getTradenum()))
                .setText(R.id.tv_time, item.getCreatetime());


    }
}
