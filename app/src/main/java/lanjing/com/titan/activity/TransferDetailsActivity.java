package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.TransferDetailsContact;
import lanjing.com.titan.response.BillDetailResponse;
import lanjing.com.titan.util.DateUtils;
import lanjing.com.titan.util.MoneyUtil;
import retrofit2.Response;


/**
 * 转出详情界面
 */
public class TransferDetailsActivity extends MvpActivity<TransferDetailsContact.TransferPresent> implements TransferDetailsContact.TransferDetailsView {

    @BindView(R.id.tv_titan_num)
    TextView tv_titan_num;//数量显示

    @BindView(R.id.tv_titan_type)
    TextView tv_titan_type;//类型

    @BindView(R.id.tv_target)
    TextView tv_target;//显示转出还是转入

    @BindView(R.id.tv_ource_address)
    TextView tv_ource_address;//来源ID

    @BindView(R.id.tv_titan_time)
    TextView tv_titan_time;//时间

    @Override
    protected TransferDetailsContact.TransferPresent createPresent() {
        return new TransferDetailsContact.TransferPresent();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        String id = getIntent().getStringExtra("id");
        mPresent.billDetail(context, id);


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_transfer_details;
    }

    @Override
    public void getDataFailed() {

    }


    //获取数据详情
    @Override
    public void getBillDeatilResult(Response<BillDetailResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            if (response.body().getData().getChangeType().equals("25")) {//转出单
                tv_titan_num.setText("-" + MoneyUtil.formatFouras(response.body().getData().getTransferCoinAmount())
                        +"\r"+ response.body().getData().getTransferCoinName());

                tv_titan_type.setText(getResources().getString(R.string.turn_out));//转出类型

                tv_target.setText(getResources().getString(R.string.Recipient_ID));//显示来源id提示语

                tv_ource_address.setText(response.body().getData().getToUser());//来源id

                tv_titan_time.setText(DateUtils.timedate(response.body().getData().getCreateTime()));//时间

            } else if (response.body().getData().getChangeType().equals("15")) {
                tv_titan_num.setText("+" + MoneyUtil.formatFouras(response.body().getData().getTransferCoinAmount())
                        +"\r"+response.body().getData().getTransferCoinName());

                tv_titan_type.setText(getResources().getString(R.string.turn_put));//转入类型

                tv_target.setText(getResources().getString(R.string.Source_ID));//显示接收id提示语

                tv_ource_address.setText(response.body().getData().getFromUser());//接收id

                tv_titan_time.setText(DateUtils.timedate(response.body().getData().getCreateTime()));//时间
            }

        } else {
            ToastUtils.showLongToast(context, response.body().getMsg());
        }

    }
}
