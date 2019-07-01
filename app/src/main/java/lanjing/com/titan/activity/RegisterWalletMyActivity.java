package lanjing.com.titan.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lxh.baselibray.base.XActivity;
import com.lxh.baselibray.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lanjing.com.titan.R;

/**
 * 注册（创建钱包   第三步  生成私钥  可以复制助记词和私钥）
 */
public class RegisterWalletMyActivity extends XActivity {

    @BindView(R.id.word_one)
    TextView wordOne;
    @BindView(R.id.word_two)
    TextView wordTwo;
    @BindView(R.id.word_three)
    TextView wordThree;
    @BindView(R.id.tv_copy_word)
    TextView tvCopyWord;
    @BindView(R.id.tv_private_key)
    TextView tvPrivateKey;
    @BindView(R.id.tv_copy_private_key)
    TextView tvCopyPrivateKey;
    @BindView(R.id.complete_btn)
    TextView completeBtn;

    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    public void initData(Bundle savedInstanceState) {
        wordOne.setText(getIntent().getStringExtra("wordone"));
        wordTwo.setText(getIntent().getStringExtra("wordtwo"));
        wordThree.setText(getIntent().getStringExtra("wordthree"));
        tvPrivateKey.setText(getIntent().getStringExtra("key"));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_wallet_my;
    }

    @OnClick({R.id.tv_copy_word, R.id.tv_copy_private_key, R.id.complete_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_copy_word:
                myClipboard = (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
                String wordHelp = wordOne.getText().toString()+" "+wordTwo.getText().toString()+" "+wordThree.getText().toString();
                myClip = ClipData.newPlainText("text", wordHelp);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShortToast(context, wordHelp + getResources().getString(R.string.word_copy_tip));
                break;
            case R.id.tv_copy_private_key:
                myClipboard = (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
                String key = tvPrivateKey.getText().toString();
                myClip = ClipData.newPlainText("text", key);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShortToast(context, key + getResources().getString(R.string.key_copy_tip));
                break;
            case R.id.complete_btn:
                ToastUtils.showShortToast(context,getResources().getString(R.string.create_success_tip));
                Intent intent = new Intent(context,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
