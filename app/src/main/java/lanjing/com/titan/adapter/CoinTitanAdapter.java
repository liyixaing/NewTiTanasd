package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.WalletDataResponse;
import lanjing.com.titan.response.WalletDetailResponse;
import lanjing.com.titan.util.MoneyUtil;

/**
 * Created by chenxi on 2019/5/10.
 * TITAN 列表数据适配器
 */

public class CoinTitanAdapter extends BaseQuickAdapter<WalletDetailResponse.HistoryBean, BaseViewHolder> {

    public CoinTitanAdapter(int layoutResId, @Nullable List<WalletDetailResponse.HistoryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletDetailResponse.HistoryBean item) {
        //0，手续费 1，交易释放 2，充币 3，提币 4，买入 5，卖出 6，系统 7，其他 （不填写为全部）
        int type = Integer.parseInt(item.getType());
        switch (type){
            case 2:
                helper.setText(R.id.titan_type,R.string.trade_get);//交易获得
                break;
            case 5:
                helper.setText(R.id.titan_type,R.string.direct_push_trade_gains);//直推交易获得
                break;
            case 6:
                helper.setText(R.id.titan_type,R.string.rank_trading_weight2);//等级交易获得
                break;
            case 7:
                helper.setText(R.id.titan_type,R.string.peer_acquisition);//同级交易获得
                break;
            case 30:
                helper.setText(R.id.titan_type,R.string.service_fee);//手续费
                break;
            case 32:
                helper.setText(R.id.titan_type,R.string.top_up_c);//充币
                break;
            case 33:
                helper.setText(R.id.titan_type,R.string.withdraw_c);//提币
                break;
            case 34:
                helper.setText(R.id.titan_type,R.string.buy);//买入
                break;
            case 35:
                helper.setText(R.id.titan_type,R.string.sell);//卖出
                break;
        }
        String up = "+";
        String down = "-";
        String upAndDown = String.valueOf(MoneyUtil.formatFour(item.getNum()));
        boolean over = upAndDown.contains(up);
        if(over == true){
            helper.setText(R.id.tv_titan_num,"+"+upAndDown);
        }else {
            helper.setText(R.id.tv_titan_num,upAndDown);
        }
        boolean over2 = upAndDown.contains(down);
        if(over2 == true){
            helper.setText(R.id.tv_titan_num,upAndDown);
        }else {
            helper.setText(R.id.tv_titan_num,"+"+upAndDown);
        }

//        helper.setText(R.id.tv_titan_num,item.getNum());
        int stateType = item.getStatetype();
        switch (stateType){
            case 0://已完成
                helper.setText(R.id.tv_titan_state,R.string.finish);
                break;
            case 1://进行中
                helper.setText(R.id.tv_titan_state,R.string.underway);
                break;
        }
        helper.setText(R.id.tv_titan_time,item.getTime());
        helper.addOnClickListener(R.id.titan_item);//添加item的点击事件

    }
}
