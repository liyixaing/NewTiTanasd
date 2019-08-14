package lanjing.com.titan.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.HistoryListResponse;
import lanjing.com.titan.response.WalletDetailResponse;
import lanjing.com.titan.util.DateUtils;
import lanjing.com.titan.util.MoneyUtil;

/**
 * extends BaseQuickAdapter<WalletDetailResponse.HistoryBean, BaseViewHolder>
 * Created by chenxi on 2019/5/10.
 * USD 列表数据适配器
 */

public class CoinUsdAdapter extends BaseQuickAdapter<HistoryListResponse.mData, BaseViewHolder> {
    public CoinUsdAdapter(int recy_item_usd_list, List<HistoryListResponse.mData> mList) {
        super(recy_item_usd_list, mList);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryListResponse.mData item) {
        int type = Integer.parseInt(item.getChangeType());
        String upAndDown = String.valueOf(MoneyUtil.formatFour(String.valueOf(item.getChangeAmount())));
        String time = DateUtils.timedate(item.getChangeTime());
        switch (type) {
            case 11:
                helper.setText(R.id.usd_type, R.string.sell);//卖出
                break;
            case 12:
                helper.setText(R.id.usd_type, R.string.selling_charges);//卖出手续费
                break;

            case 13:
                helper.setText(R.id.usd_type, R.string.buy);//买入
                break;
            case 14:
                helper.setText(R.id.usd_type, R.string.loop);//循环
                break;
        }
        helper.setText(R.id.tv_usd_num, upAndDown)
                .setText(R.id.tv_usd_state, R.string.finish)
                .setText(R.id.tv_usd_time, time);

    }

//
//    @Override
//    protected void convert(BaseViewHolder helper, WalletDetailResponse.HistoryBean item) {
//        //	0，手续费 4，买入 5，卖出 6，系统 7，其他 （不填写为全部）
//        int type = Integer.parseInt(item.getType());
//        switch (type){
//            case 30:
//                helper.setText(R.id.usd_type,R.string.service_fee);
//                break;
//            case 34:
//                helper.setText(R.id.usd_type,R.string.buy);
//                break;
//            case 35:
//                helper.setText(R.id.usd_type,R.string.sell);
//                break;
////            case 6:
////                helper.setText(R.id.usd_type,R.string.system);
////                break;
////            case 7:
////                helper.setText(R.id.usd_type,R.string.others);
////                break;
//        }
//
//
//        String up = "+";
//        String down = "-";
//        String upAndDown = String.valueOf(MoneyUtil.formatFour(item.getNum()));
//        boolean over = upAndDown.contains(up);
//        if(over == true){
//            helper.setText(R.id.tv_usd_num,"+"+upAndDown);
//        }else {
//            helper.setText(R.id.tv_usd_num,upAndDown);
//        }
//        boolean over2 = upAndDown.contains(down);
//        if(over2 == true){
//            helper.setText(R.id.tv_usd_num,upAndDown);
//        }else {
//            helper.setText(R.id.tv_usd_num,"+"+upAndDown);
//        }
//
////        helper.setText(R.id.tv_usd_num,item.getNum());
//        int stateType = item.getStatetype();
//        switch (stateType){
//            case 0://已完成
//                helper.setText(R.id.tv_usd_state,R.string.finish);
//                break;
//            case 1://进行中
//                helper.setText(R.id.tv_usd_state,R.string.underway);
//                break;
//        }
//
//        helper.setText(R.id.tv_usd_time,item.getTime());
//        helper.addOnClickListener(R.id.usd_item);//添加item的点击事件
//
//    }
}
