package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.WalletDetailResponse;
import lanjing.com.titan.util.MoneyUtil;

/**
 * Created by chenxi on 2019/5/10.
 * USD 列表数据适配器
 */

public class CoinUsdAdapter extends BaseQuickAdapter<WalletDetailResponse.HistoryBean, BaseViewHolder> {

    public CoinUsdAdapter(int layoutResId, @Nullable List<WalletDetailResponse.HistoryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletDetailResponse.HistoryBean item) {
        //	0，手续费 4，买入 5，卖出 6，系统 7，其他 （不填写为全部）
        int type = Integer.parseInt(item.getType());
        switch (type){
            case 30:
                helper.setText(R.id.usd_type,R.string.service_fee);
                break;
            case 34:
                helper.setText(R.id.usd_type,R.string.buy);
                break;
            case 35:
                helper.setText(R.id.usd_type,R.string.sell);
                break;
//            case 6:
//                helper.setText(R.id.usd_type,R.string.system);
//                break;
//            case 7:
//                helper.setText(R.id.usd_type,R.string.others);
//                break;
        }


        String up = "+";
        String down = "-";
        String upAndDown = String.valueOf(MoneyUtil.formatFour(item.getNum()));
        boolean over = upAndDown.contains(up);
        if(over == true){
            helper.setText(R.id.tv_usd_num,"+"+upAndDown);
        }else {
            helper.setText(R.id.tv_usd_num,upAndDown);
        }
        boolean over2 = upAndDown.contains(down);
        if(over2 == true){
            helper.setText(R.id.tv_usd_num,upAndDown);
        }else {
            helper.setText(R.id.tv_usd_num,"+"+upAndDown);
        }

//        helper.setText(R.id.tv_usd_num,item.getNum());
        int stateType = item.getStatetype();
        switch (stateType){
            case 0://已完成
                helper.setText(R.id.tv_usd_state,R.string.finish);
                break;
            case 1://进行中
                helper.setText(R.id.tv_usd_state,R.string.underway);
                break;
        }

        helper.setText(R.id.tv_usd_time,item.getTime());
        helper.addOnClickListener(R.id.usd_item);//添加item的点击事件

    }
}
