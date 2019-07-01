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
 * TITANC  列表数据适配器
 */

public class CoinTitancAdapter extends BaseQuickAdapter<WalletDetailResponse.History2Bean, BaseViewHolder> {

    public CoinTitancAdapter(int layoutResId, @Nullable List<WalletDetailResponse.History2Bean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletDetailResponse.History2Bean item) {

        int type = Integer.parseInt(item.getType());
        switch (type){
            case 1://空投释放
                helper.setText(R.id.tv_titanc_type,R.string.airdrop_release);
                helper.setText(R.id.tv_type_two,R.string.titanc_get_qty);//获得TITANC数量
                helper.setText(R.id.tv_titanc_change_num,"+"+MoneyUtil.formatFour(item.getCoinNum2()));
                break;
            case 3://分享加权
                helper.setText(R.id.tv_titanc_type,R.string.titanc_one);
                helper.setText(R.id.tv_type_two,R.string.titanc_get_qty);//获得TITANC数量
                helper.setText(R.id.tv_titanc_change_num,"+"+MoneyUtil.formatFour(item.getCoinNum2()));
                break;
            case 4://等级加权
                helper.setText(R.id.tv_titanc_type,R.string.titanc_two);
                helper.setText(R.id.tv_type_two,R.string.titanc_get_qty);//获得TITANC数量
                helper.setText(R.id.tv_titanc_change_num,"+"+MoneyUtil.formatFour(item.getCoinNum2()));
                break;
            case 22://交易激活
                helper.setText(R.id.tv_titanc_type,R.string.unfrozen);
                helper.setText(R.id.tv_type_two,R.string.titanc_conversion_qty);//激活TITANC数量
                helper.setText(R.id.tv_titanc_change_num,MoneyUtil.formatFour(item.getCoinNum2()));
                break;
            case 25://直推交易激活
                helper.setText(R.id.tv_titanc_type,R.string.titanc_three);
                helper.setText(R.id.tv_type_two,R.string.titanc_conversion_qty);//激活TITANC数量
                helper.setText(R.id.tv_titanc_change_num,MoneyUtil.formatFour(item.getCoinNum2()));
                break;
            case 26://等级交易激活
                helper.setText(R.id.tv_titanc_type,R.string.titanc_four);
                helper.setText(R.id.tv_type_two,R.string.titanc_conversion_qty);//激活TITANC数量
                helper.setText(R.id.tv_titanc_change_num,MoneyUtil.formatFour(item.getCoinNum2()));
                break;
            case 27://同级交易激活
                helper.setText(R.id.tv_titanc_type,R.string.titanc_five);
                helper.setText(R.id.tv_type_two,R.string.titanc_conversion_qty);//激活TITANC数量
                helper.setText(R.id.tv_titanc_change_num,MoneyUtil.formatFour(item.getCoinNum2()));
                break;
        }

//        String up = "+";
//        String down = "-";
//        String upAndDown = String.valueOf(MoneyUtil.formatFour(item.getCoinNum1()));
//        boolean over = upAndDown.contains(up);
//        if(over == true){
//            helper.setText(R.id.tv_titanc_num,"+"+upAndDown);
//        }else {
//            helper.setText(R.id.tv_titanc_num,upAndDown);
//        }
//        boolean over2 = upAndDown.contains(down);
//        if(over2 == true){
//            helper.setText(R.id.tv_titanc_num,upAndDown);
//        }else {
//            helper.setText(R.id.tv_titanc_num,"+"+upAndDown);
//        }

//        helper.setText(R.id.tv_titanc_num,item.getCoinNum1());

        helper.setText(R.id.tv_titanc_time,item.getTime());
//        helper.addOnClickListener(R.id.titanc_item);//添加item的点击事件

    }
}
