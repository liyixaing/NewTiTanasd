package lanjing.com.titan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lxh.baselibray.mvp.MvpActivity;
import com.lxh.baselibray.util.ObjectUtils;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;
import lanjing.com.titan.constant.Constant;
import lanjing.com.titan.contact.ImportWalletContact;
import lanjing.com.titan.response.ImportWalletResponse;
import lanjing.com.titan.response.IsfindResponse;
import retrofit2.Response;

/**
 * 导入钱包   输入助记词 和 私钥  验证之后  下一步  可以设置昵称、密码等
 */
public class ImportWalletActivity extends MvpActivity<ImportWalletContact.ImportWalletPresent> implements ImportWalletContact.IImportWalletView {


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

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_import_wallet;
    }

    @Override
    protected ImportWalletContact.ImportWalletPresent createPresent() {
        return new ImportWalletContact.ImportWalletPresent();
    }

    @Override
    public void getImportWalletResult(Response<IsfindResponse> response) {
        dismissLoadingDialog();
        if (response.body().getCode() == Constant.SUCCESS_CODE) {
            ToastUtils.showShortToast(context,getResources().getString(R.string.import_success));
            Intent intent = new Intent(context,ImportWalletModifyActivity.class);
            intent.putExtra("userId",response.body().getData().getUid());
            intent.putExtra("userName",response.body().getData().getUserName());
            intent.putExtra("verificationCode", response.body().getData().getVerificationCode());
            startActivity(intent);
            finish();
        } else if (response.body().getCode() == -10){
            ToastUtils.showShortToast(context, getResources().getString(R.string.not_login));
        }else {
            ToastUtils.showShortToast(context, response.body().getMsg());
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
                if (validate(word1,word2,word3,key))
                    return;
                showLoadingDialog();
                String help = word1+","+word2+","+word3;
                mPresent.importWallet(context,help,key);
                break;
        }
    }
    private boolean validate(String word1, String word2, String word3,String key) {
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
