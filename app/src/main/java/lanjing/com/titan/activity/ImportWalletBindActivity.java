package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lxh.baselibray.base.XActivity;
import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.BindContact;
import lanjing.com.titan.response.ResultDTO;
import retrofit2.Response;

/**
 * 导入钱包 提交修改数据之后  进入此页面绑定联系人  可以跳过
 */
public class ImportWalletBindActivity extends MvpActivity<BindContact.BindPresent> implements BindContact.IBindView {


    @BindView(R.id.tv_skip)
    TextView tvSkip;
    @BindView(R.id.ed_references_id)
    EditText edReferencesId;
    @BindView(R.id.submit_btn)
    TextView submitBtn;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_import_wallet_bind;
    }

    @OnClick({R.id.tv_skip, R.id.ed_references_id, R.id.submit_btn})
    public void onViewClicked(View view) {
        String people = edReferencesId.getText().toString().trim();
        switch (view.getId()) {
            case R.id.tv_skip:
//                mPresent.bind(context,"99999999");
//                finish();
                break;
            case R.id.ed_references_id:
                edReferencesId.setCursorVisible(true);//光标显示
                break;
            case R.id.submit_btn:
                if(ObjectUtils.isEmpty(people)){
                    ToastUtils.showShortToast(context,getResources().getString(R.string.please_fill_in_the_referee_id));
                    return;
                }
                showLoadingDialog();
                mPresent.bind(context,people);
                break;
        }
    }

    @Override
    protected BindContact.BindPresent createPresent() {
        return new BindContact.BindPresent();
    }

    @Override
    public void getBindResult(Response<ResultDTO> response) {
        dismissLoadingDialog();
        if(response.body().getCode() == Constant.SUCCESS_CODE){
            ToastUtils.showShortToast(context,getResources().getString(R.string.submit_successfully));
            Intent intent = new Intent(context,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            ToastUtils.showShortToast(context,response.body().getMsg());
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showLongToast(context,getResources().getString(R.string.network_error ));
        dismissLoadingDialog();
    }
}
