package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.AddressListResponse;

public class SelectAddressAdapter extends BaseQuickAdapter<AddressListResponse.Data, BaseViewHolder> {
    public SelectAddressAdapter(int layoutResId, @Nullable List<AddressListResponse.Data> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, AddressListResponse.Data item) {
        String Tag = item.getToTag();//标签  R.string.Label_allin
        String Account = item.getToAccount();//地址  R.string.dizhi_allin
        String Memo = item.getToMemo();//备注 R.string.beizhu_allin
        helper.setText(R.id.tv_address, Account)
                .setText(R.id.tv_tag, Tag)
                .setText(R.id.tv_memo, Memo);
        helper.addOnClickListener(R.id.iv_retun);//设置点击事件
        helper.addOnClickListener(R.id.ll_item);//设置全局点击


    }
}
