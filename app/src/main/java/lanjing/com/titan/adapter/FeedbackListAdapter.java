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


        helper.setText(R.id.tv_title,item.getTitle());
//                .setText(R.id.tv_create_time,item.getCreatetime());
        int state = item.getState();
        if (state ==0){
            helper.setVisible(R.id.red_lay, false);
        }else if (state ==1){
            helper.setVisible(R.id.red_lay, false);
        }else if (state ==2){
            helper.setVisible(R.id.red_lay, true);
        }else {
            helper.setVisible(R.id.red_lay, false);
        }

        helper.addOnClickListener(R.id.feedback_item);


    }
}
