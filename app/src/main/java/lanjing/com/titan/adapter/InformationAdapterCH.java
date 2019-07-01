package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;
import java.util.Locale;

import lanjing.com.titan.R;
import lanjing.com.titan.response.InfoNoticeResponse;
import lanjing.com.titan.response.WalletDataResponse;

/**
 * Created by chenxi on 2019/5/10.
 * 资讯  列表数据适配器
 */

public class InformationAdapterCH extends BaseQuickAdapter<InfoNoticeResponse.DataCHBean, BaseViewHolder> {

    public InformationAdapterCH(int layoutResId, @Nullable List<InfoNoticeResponse.DataCHBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InfoNoticeResponse.DataCHBean item) {
        ImageView imgOne= helper.getView(R.id.img);

                Glide.with(mContext).load(item.getTitleimg()).into(imgOne);
                helper.setText(R.id.tv_information_title,item.getTitle())
                        .setText(R.id.tv_information_time,item.getComtent());
                helper.addOnClickListener(R.id.information_item);


    }
}
