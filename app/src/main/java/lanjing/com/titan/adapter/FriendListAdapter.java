package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.FriendListResponse;

/**
 * Created by chenxi on 2019/5/10.
 * 我的邀请  好友列表数据适配器
 */

public class FriendListAdapter extends BaseQuickAdapter<FriendListResponse.DataBean,BaseViewHolder> {

    public FriendListAdapter(int layoutResId, @Nullable List<FriendListResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendListResponse.DataBean item) {
        helper.setText(R.id.tv_phone,item.getfId())
                .setText(R.id.tv_nick_name,item.getNickName())
                .setText(R.id.tv_time,item.getTime());
    }
}
