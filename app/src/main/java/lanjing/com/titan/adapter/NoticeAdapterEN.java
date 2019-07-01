package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.InfoNoticeResponse;

/**
 * Created by chenxi on 2019/5/10.
 */

public class NoticeAdapterEN extends BaseQuickAdapter<InfoNoticeResponse.DataEHBean, BaseViewHolder> {

    public NoticeAdapterEN(int layoutResId, @Nullable List<InfoNoticeResponse.DataEHBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoNoticeResponse.DataEHBean item) {

        helper.setText(R.id.tv_notice_time,item.getCreatetime())
                .setText(R.id.tv_notice_title,item.getTitle())
                .setText(R.id.tv_team,item.getUname());
        helper.addOnClickListener(R.id.notice_item);

    }
}
