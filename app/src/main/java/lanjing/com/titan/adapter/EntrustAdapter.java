package lanjing.com.titan.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;

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

public class EntrustAdapter extends BaseQuickAdapter<EntrustListResponse.DataBean, BaseViewHolder> {

    public EntrustAdapter(int layoutResId, @Nullable List<EntrustListResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EntrustListResponse.DataBean item) {

        int type = item.getType();
        switch (type) {  //1，买   2，卖
            case 1:
                helper.setText(R.id.tv_type, R.string.buy);//买卖类型
                helper.setTextColor(R.id.tv_type,Color.GREEN);

                helper.setBackgroundRes(R.id.tv_type_recall, R.drawable.shape_green_bg);//右侧撤销背景 绿色
                helper.setText(R.id.tv_num_type, R.string.buy_usd_num2);
                break;
            case 2:
                helper.setText(R.id.tv_type, R.string.sell);
                helper.setTextColor(R.id.tv_type, Color.RED);

                helper.setBackgroundRes(R.id.tv_type_recall, R.drawable.shape_red_bg);//右侧撤销背景 红色
                helper.setText(R.id.tv_num_type, R.string.sell_titan_num2);
                break;
        }

        helper.setText(R.id.tv_entrust_price, MoneyUtil.priceFormatDoubleFour(item.getPrice()) + "USD")
                .setText(R.id.tv_entrust_num, MoneyUtil.priceFormatDouble(item.getRest()))
                .setText(R.id.tv_time, item.getCreatetime());
        helper.addOnClickListener(R.id.tv_type_recall);//添加点击事件


    }
}
