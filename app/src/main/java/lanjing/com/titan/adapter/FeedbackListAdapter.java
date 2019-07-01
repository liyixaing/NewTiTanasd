package lanjing.com.titan.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.FeedbackListResponse;

/**
 * Created by chenxi on 2019/5/25.
 */

public class FeedbackListAdapter extends BaseQuickAdapter<FeedbackListResponse.DataBean,BaseViewHolder> {
    public FeedbackListAdapter(int layoutResId, @Nullable List<FeedbackListResponse.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedbackListResponse.DataBean item) {


        helper.setText(R.id.tv_title,item.getTitle())
                .setText(R.id.tv_create_time,item.getCreatetime());
        int state = item.getState();
        switch (state){
            case 0://未回复
                helper.setVisible(R.id.red_lay, false);
                break;
            case 1://已回复
                helper.setVisible(R.id.red_lay, true);
                break;
        }
        helper.addOnClickListener(R.id.feedback_item);


    }
}
