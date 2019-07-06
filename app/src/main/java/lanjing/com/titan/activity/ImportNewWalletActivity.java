package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.SPUtils;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.WalletListImportContact;
import lanjing.com.titan.response.ListWalletImportResponse;
import retrofit2.Response;

/**
 * 钱包列表   导入新钱包
 */
public class ImportNewWalletActivity extends MvpActivity<WalletListImportContact.WalletListImportPresent> implements WalletListImportContact.IWalletListImportView {

    @BindView(R.id.ed_word_one)
    EditText edWordOne;
    @BindView(R.id.ed_word_two)
    EditText edWordTwo;
    @BindView(R.id.ed_word_three)
    EditText edWordThree;
    @BindView(R.id.ed_private_key)
    EditText edPrivateKey;
    @BindView(R.id.next_step_btn)
    TextView nextStepBtn;
    String adminNo;

    @Override
    public void initData(Bundle savedInstanceState) {
        //获取设备唯一标示
        adminNo = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_import_new_wallet;
    }

    @Override
    protected WalletListImportContact.WalletListImportPresent createPresent() {
        return new WalletListImportContact.WalletListImportPresent();
    }

    @Override
    public void getImportWalletListResult(Response<ListWalletImportResponse> response) {
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            Intent intent = new Intent(context, SetWalletActivity.class);
            intent.putExtra("userName", response.body().getUserName());
            intent.putExtra("address", response.body().getAddress());
            intent.putExtra("addressLabel", response.body().getKeyes());
            SPUtils.putString(Constant.TOKEN2, response.body().getToken(), context);
            startActivity(intent);
            finish();
        } else {
            ToastUtils.showShortToast(context, response.body().getMsg());
            dismissLoadingDialog();
        }
    }

    @Override
    public void getDataFailed() {
        ToastUtils.showShortToast(context, getResources().getString(R.string.network_error));
        dismissLoadingDialog();
    }

    @OnClick({R.id.ed_word_one, R.id.next_step_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_word_one:
                edWordOne.setCursorVisible(true);//光标显示
                break;
            case R.id.next_step_btn:
                String word1 = edWordOne.getText().toString();
                String word2 = edWordTwo.getText().toString();
                String word3 = edWordThree.getText().toString();
                String key = edPrivateKey.getText().toString();
                if (validate(word1, word2, word3, key))
                    return;
                showLoadingDialog();
                String help = word1 + "," + word2 + "," + word3;
                mPresent.importWalletlist(context, help, key, adminNo);
                break;
        }
    }

    private boolean validate(String word1, String word2, String word3, String key) {
        if (ObjectUtils.isEmpty(word1)) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.please_ed_one_word));
            return true;
        }
        if (ObjectUtils.isEmpty(word2)) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.please_ed_two_word));
            return true;
        }
        if (ObjectUtils.isEmpty(word3)) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.please_ed_three_word));
            return true;
        }
        if (ObjectUtils.isEmpty(key)) {
            ToastUtils.showLongToast(context, getResources().getString(R.string.please_ed_key));
            return true;
        }
        return false;
    }
}
