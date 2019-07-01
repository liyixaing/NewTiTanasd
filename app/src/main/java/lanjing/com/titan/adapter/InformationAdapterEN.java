package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.InfoNoticeResponse;

/**
 * Created by chenxi on 2019/5/10.
 * 资讯  列表数据适配器
 */

public class InformationAdapterEN extends BaseQuickAdapter<InfoNoticeResponse.DataEHBean, BaseViewHolder> {

    public InformationAdapterEN(int layoutResId, @Nullable List<InfoNoticeResponse.DataEHBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoNoticeResponse.DataEHBean item) {
        ImageView imgOne= helper.getView(R.id.img);

                Glide.with(mContext).load(item.getTitleimg()).into(imgOne);
                helper.setText(R.id.tv_information_title,item.getTitle())
                        .setText(R.id.tv_information_time,item.getComtent());
                helper.addOnClickListener(R.id.information_item);


    }
}
