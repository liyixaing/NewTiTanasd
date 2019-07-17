package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.InformationResponse;

public class GovernanceAdapter extends BaseQuickAdapter<InformationResponse.DataCH, BaseViewHolder> {

    public GovernanceAdapter(int layoutResId, @Nullable List<InformationResponse.DataCH> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InformationResponse.DataCH item) {
        helper.setText(R.id.tv_notice_title, item.getTitle());

    }
}
