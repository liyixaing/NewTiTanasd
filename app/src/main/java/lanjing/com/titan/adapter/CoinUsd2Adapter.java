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
 * USD2(USD待空投)  列表数据适配器
 */

public class CoinUsd2Adapter extends BaseQuickAdapter<WalletDetailResponse.History2Bean, BaseViewHolder> {

    public CoinUsd2Adapter(int layoutResId, @Nullable List<WalletDetailResponse.History2Bean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletDetailResponse.History2Bean item) {
        int type = Integer.parseInt(item.getType());
        switch (type){
            case 21://空投释放
                helper.setText(R.id.tv_usd2_type,R.string.airdrop_release);//空投   已改为  空投释放
                helper.setText(R.id.tv_type_two,R.string.usd_release_qty);//释放USD数量
                break;
            case 40://空投配比
                helper.setText(R.id.tv_usd2_type,R.string.airdrop_matching);//空投配比
                helper.setText(R.id.tv_type_two,R.string.usd_award_qty);//空投USD数量
                break;
        }

        helper.setText(R.id.tv_usd2_chang_num,MoneyUtil.formatFour(item.getCoinNum2()));
        helper.setText(R.id.tv_usd2_time,item.getTime());

//        String up = "+";
//        String down = "-";
//        String upAndDown = String.valueOf(MoneyUtil.formatFour(item.getCoinNum1()));
//        boolean over = upAndDown.contains(up);
//        if(over == true){
//            helper.setText(R.id.tv_usd2_num,"+"+upAndDown);
//        }else {
//            helper.setText(R.id.tv_usd2_num,upAndDown);
//        }
//        boolean over2 = upAndDown.contains(down);
//        if(over2 == true){
//            helper.setText(R.id.tv_usd2_num,upAndDown);
//        }else {
//            helper.setText(R.id.tv_usd2_num,"+"+upAndDown);
//        }


//        helper.setText(R.id.tv_usd2_num,item.getCoinNum1());

//        helper.addOnClickListener(R.id.usd_item);//添加item的点击事件

    }
}
