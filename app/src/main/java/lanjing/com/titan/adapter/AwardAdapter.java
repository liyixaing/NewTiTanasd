package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.AwardResponse;
import lanjing.com.titan.response.CoinDealResponse;
import lanjing.com.titan.util.MoneyUtil;

/**
 * Created by chenxi on 2019/5/24.
 * 币币交易  列表适配器
 */

public class AwardAdapter extends BaseQuickAdapter<AwardResponse.DataBean,BaseViewHolder> {

    public AwardAdapter(int layoutResId, @Nullable List<AwardResponse.DataBean> data) {
        super(layoutResId, data);
    }

//    case 1://空投
//            helper.setText(R.id.tv_award_type,R.string.airdrop);//奖励类型
//                helper.setBackgroundRes(R.id.img_type,R.mipmap.icon_usd);
//                break;
//            case 2://交易释放
//                    helper.setText(R.id.tv_award_type,R.string.unfrozen);//奖励类型
//                break;
//            case 3://空投分享加权
//                    helper.setText(R.id.tv_award_type,R.string.tx_two);
//                break;
//            case 4://空投等级加权
//                    helper.setText(R.id.tv_award_type,R.string.tx_one);
//                break;
//            case 5://释放等级加权
//                    helper.setText(R.id.tv_award_type,R.string.tx_three);
//                break;
//            case 6://释放分享加权
//                    helper.setText(R.id.tv_award_type,R.string.tx_four);
//                break;
//            case 7://交易直推增加
//                    helper.setText(R.id.tv_award_type,R.string.tx_six);
//                break;
//            case 21://空投释放
//                    helper.setText(R.id.tv_award_type,R.string.tx_seven);
//                break;
//            case 22://卖出释放减少
//                    helper.setText(R.id.tv_award_type,R.string.tx_eight);
//                break;
//            case 25://交易分享减少
//                    helper.setText(R.id.tv_award_type,R.string.tx_nine);
//                break;
//            case 26://交易等级减少
//                    helper.setText(R.id.tv_award_type,R.string.tx_ten);
//                break;
//            case 27://交易直接减少
//                    helper.setText(R.id.tv_award_type,R.string.tx_eleven);
//                break;

    @Override
    protected void convert(BaseViewHolder helper, AwardResponse.DataBean item) {
        //1，空投   2 ，交易释放 3，空投等级加权  4，空投分享加权   5，释放等级加权     6，释放分享加权' 7 治理委员会奖励
        int type = Integer.parseInt(item.getType());
        switch (type){
            //40 空投配比（USD），1 空投释放（TITANC），3 分享加权（TITANC），4 等级加权（TITANC），2 交易获得（TITAN），
            // 5 直推交易获得（TITAN），6 等级交易获得（TITAN），7 等级交易获得（TITAN）
            case 1://空投释放
                helper.setText(R.id.tv_award_type,R.string.airdrop_release);
                helper.setBackgroundRes(R.id.img_type,R.mipmap.icon_usd);
                helper.setText(R.id.tv_bi_type,"TITANC");
                break;
            case 2://交易获得
                helper.setText(R.id.tv_award_type,R.string.trade_get);//交易获得
                helper.setText(R.id.tv_bi_type,"TITAN");
                break;
            case 3://分享加权
                helper.setText(R.id.tv_award_type,R.string.titanc_one);
                helper.setText(R.id.tv_bi_type,"TITANC");
                break;
            case 4://等级加权
                helper.setText(R.id.tv_award_type,R.string.titanc_two);
                helper.setText(R.id.tv_bi_type,"TITANC");
                break;
            case 5://直推交易获得
                helper.setText(R.id.tv_award_type,R.string.direct_push_trade_gains);//直推交易获得
                helper.setText(R.id.tv_bi_type,"TITAN");
                break;
            case 6://等级交易获得
                helper.setText(R.id.tv_award_type,R.string.rank_trading_weight2);//等级交易获得
                helper.setText(R.id.tv_bi_type,"TITAN");
                break;
            case 7://同级交易获得
                helper.setText(R.id.tv_award_type,R.string.peer_acquisition);//同级交易获得
                helper.setText(R.id.tv_bi_type,"TITAN");
                break;
            case 40://空投配比
                helper.setText(R.id.tv_award_type,R.string.airdrop_matching);
                helper.setText(R.id.tv_bi_type,"USD");
                break;
            //            case 21://空投释放
//                helper.setText(R.id.tv_award_type,R.string.tx_seven);
//                break;
//            case 22://交易激活
//                helper.setText(R.id.tv_award_type,R.string.unfrozen);
//                break;
//            case 25://直推交易激活
//                helper.setText(R.id.tv_award_type,R.string.titanc_three);
//                break;
//            case 26://等级交易激活
//                helper.setText(R.id.tv_award_type,R.string.titanc_four);
//                break;
//            case 27://同级交易激活
//                helper.setText(R.id.tv_award_type,R.string.titanc_five);
//                break;
        }
//        //币的类型
//        int cointype = Integer.parseInt(item.getCointype2());
//        switch (cointype){
//            case 1://Titan
//                helper.setText(R.id.tv_bi_type,"TITAN");
//                break;
//            case 2://Tianc
//                helper.setText(R.id.tv_bi_type,"TITANC");
//                break;
//        }

        helper.setText(R.id.tv_number, MoneyUtil.formatFour(item.getCoinNum2()));//买入TITAN

        helper.setText(R.id.tv_create_time,item.getTime());//时间


    }
}
