package lanjing.com.titan.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxh.baselibray.util.ToastUtils;

import java.util.List;

import lanjing.com.titan.R;
import lanjing.com.titan.response.CdkeyListResponse;


public class StartCodeAdapter extends BaseQuickAdapter<CdkeyListResponse.Data, BaseViewHolder> {

    public StartCodeAdapter(int recy_item_cash_value_list, List<CdkeyListResponse.Data> mList) {
        super(recy_item_cash_value_list, mList);
    }

    @Override
    protected void convert(BaseViewHolder helper, CdkeyListResponse.Data item) {
        helper.setText(R.id.tv_currency, item.getCdkey());
        if (item.getUseTime() == 0) {
            helper.setText(R.id.tv_create_time, "已使用");
        } else {
            helper.setText(R.id.tv_create_time, "未使用");
        }
        TextView tv_copy_invitation = helper.getView(R.id.tv_copy_invitation);
        tv_copy_invitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager copy = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(item.getCdkey());
                ToastUtils.showLongToast(mContext, "已复制");
            }
        });

    }
}
