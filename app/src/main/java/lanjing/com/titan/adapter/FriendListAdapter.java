package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.FriendListResponse;
import lanjing.com.titan.util.MoneyUtil;

/**
 * Created by chenxi on 2019/5/10.
 * 我的邀请  好友列表数据适配器
 */

public class FriendListAdapter extends BaseQuickAdapter<FriendListResponse.DataBean, BaseViewHolder> {

    public FriendListAdapter(int layoutResId, @Nullable List<FriendListResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendListResponse.DataBean item) {

        TextView tv_xing = helper.getView(R.id.tv_xing);
        if (item.getValid() == 1){
            tv_xing.setVisibility(View.VISIBLE);
        }else {
            tv_xing.setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_fomID, item.getfId())
                .setText(R.id.tv_nick_name, item.getNickName())
                .setText(R.id.tv_time, item.getTime())
                .setText(R.id.tv_predice_number, MoneyUtil.formatFouras(item.getPredice_mining_number_of_usd()));
    }
}
