package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.WalletListResponse;

/**
 * Created by chenxi on 2019/5/10.
 */

public class WalletListAdapter extends BaseQuickAdapter<WalletListResponse.DataBean, BaseViewHolder> {
    public WalletListAdapter(int layoutResId, @Nullable List<WalletListResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletListResponse.DataBean item) {
        helper.setText(R.id.tv_wallet_name, item.getWelletName())//钱包名
                .setText(R.id.tv_user_name, "(" + item.getUsername() + ")")//用户名
                .setText(R.id.tv_wallet_id, item.getWId());//钱包ID
        helper.addOnLongClickListener(R.id.tv_set_wallet_name);
        helper.addOnClickListener(R.id.tv_set_wallet_name);//点击事件
        helper.addOnClickListener(R.id.wallet_change);//钱包切换
    }
}
