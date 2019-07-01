package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.WithdrawalResponse;
import lanjing.com.titan.util.MoneyUtil;

/**
 * Created by chenxi on 2019/5/24.
 * 充值提现  列表适配器
 */

public class WithDrawalAdapter extends BaseQuickAdapter<WithdrawalResponse.DataBean,BaseViewHolder> {

    public WithDrawalAdapter(int layoutResId, @Nullable List<WithdrawalResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WithdrawalResponse.DataBean item) {
        int type = Integer.parseInt(item.getType());
        switch (type){
            case 32://充币
                helper.setText(R.id.tv_deposit_type,R.string.top_up);
                helper.setText(R.id.tv_number, MoneyUtil.formatFour(item.getNum()));
                helper.setVisible(R.id.tv_add,true);
                break;
            case 33://提币
                helper.setText(R.id.tv_deposit_type,R.string.withdraw);
                helper.setText(R.id.tv_number, MoneyUtil.formatFour(item.getNum()));
                helper.setVisible(R.id.tv_add,false);
                break;
        }

        helper.setText(R.id.tv_create_time,item.getTime());
                helper.setText(R.id.tv_number, MoneyUtil.formatFour(item.getNum()));

    }
}
